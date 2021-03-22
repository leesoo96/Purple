//돌아가기
function return_page() {
  history.back()
}

const notice_write_form = document.querySelector('#notice_write_form')
const notice_ctnt_div = document.querySelector('textarea[name="notice_ctnt"]')
const notice_img = document.querySelector('input[name="notice_img"]')
//이미지 미리보기
notice_img.addEventListener('change', function (event) {
  let reader = new FileReader()
  let notice_ctnt_img = document.querySelector('.ctnt_img')
  reader.onload = function (event) {
    let img = document.createElement('img')
    img.setAttribute('class', 'ctnt_img')
    img.src = event.target.result
    notice_ctnt_img.replaceWith(img)
  }
  reader.readAsDataURL(event.target.files[0])
})

//공지사항 등록
function notice_write_submit_btn() {
  if (!notice_write_form.notice_pk.value) {
    if (confirm('등록 하시겠습니까?')) {
      if (notice_write_form.notice_title.value === '') {
        alert('제목을 입력하시오')
        notice_write_form.notice_title.focus()
        return false
      } else if (notice_write_form.notice_ctnt.value === '') {
        alert('내용을 입력하시오')
        notice_write_form.notice_ctnt.focus()
        return false
      }
      noticeReg()
      return true
    }
    return false
  }
  noticeUpd()
}

function noticeReg() {
  Regajax().then((notice_pk) => {
    if (notice_img) {
      RegajaxImg(notice_pk)
    }
    location.href = `/notice`
  })
}

function Regajax() {
  return new Promise(function (resolve) {
    let param = {
      notice_title: notice_write_form.notice_title.value,
      notice_ctnt: notice_write_form.notice_ctnt.value,
      notice_userpk: document.querySelector('#user_pk').value,
    }
    fetch('/notice_write', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then(function (myJson) {
        resolve(myJson.result)
      })
  })
}

async function RegajaxImg(notice_pk) {
  var formData = new FormData()
  formData.append('img', notice_img.files[0])
  formData.append('notice_pk', notice_pk)
  fetch('/notice_img', {
    method: 'post',
    body: formData,
  })
    .then((res) => res.json())
    .then((myJson) => {})
}

//공지사항 수정
function noticeUpd() {
  Updajax().then(() => {
    location.href = `/notice`
  })
}
async function Updajax() {
  let img = await UpdajaxImg()
  let param = {
    notice_title: notice_write_form.notice_title.value,
    notice_ctnt: notice_write_form.notice_ctnt.value,
    notice_pk: notice_write_form.notice_pk.value,
    notice_img: img,
  }
  console.log(param)
  fetch('/notice_upd', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(param),
  })
    .then((res) => res.json())
    .then(function (myJson) {})
}
function UpdajaxImg() {
  return new Promise(function (resolve) {
    var formData = new FormData()
    formData.append('img', notice_img.files[0])
    formData.append('notice_pk', notice_write_form.notice_pk.value)
    fetch('/notice_img', {
      method: 'post',
      body: formData,
    })
      .then((res) => res.json())
      .then((myJson) => {
        resolve(myJson.result)
      })
  })
}
