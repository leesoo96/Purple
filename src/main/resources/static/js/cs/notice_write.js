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

//공지사항 글 등록
function noticeReg() {
  Regajax().then((notice_pk) => {
    if (notice_img) {
      RegajaxImg(notice_pk)
    }
    location.href = `/notice`
  })
}

// 재목과 내용과 insert 후 글 pk 값을 반환
function Regajax() {
  return new Promise(function (resolve) {
    let param = {
      notice_title: notice_write_form.notice_title.value,
      notice_ctnt: notice_write_form.notice_ctnt.value,
    }
    fetchAjax(param, 'post', '/notice_write', (myJson) => {
      resolve(myJson.result)
    })
  })
}

//이미지 업로드와 이미지 경로 insert
function RegajaxImg(notice_pk) {
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

//제목과 내용과 이미지 경로 수정
async function noticeUpd() {
  await UpdajaxImg()
  let param = {
    notice_title: notice_write_form.notice_title.value,
    notice_ctnt: notice_write_form.notice_ctnt.value,
    notice_pk: notice_write_form.notice_pk.value,
  }

  fetch('/notice_upd', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(param),
  })
    .then((res) => res.json())
    .then(function (myJson) {
      location.href = `/notice`
    })
}

//이미지 업로드 후 경로 반환
function UpdajaxImg() {
  var formData = new FormData()
  formData.append('img', notice_img.files[0])
  formData.append('notice_pk', notice_write_form.notice_pk.value)
  fetch('/notice_img', {
    method: 'post',
    body: formData,
  })
    .then((res) => res.json())
    .then((myJson) => {})
}
