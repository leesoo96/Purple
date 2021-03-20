//돌아가기
function return_page() {
  history.back()
}

const question_write_form = document.querySelector('#question_write_form')
const question_ctnt_div = document.querySelector(
  'textarea[name="question_ctnt"]'
)
const question_img = document.querySelector('input[name="question_img"]')

question_img.addEventListener('change', function (event) {
  let reader = new FileReader()
  let question_ctnt_img = document.querySelector('.ctnt_img')
  reader.onload = function (event) {
    let img = document.createElement('img')
    img.setAttribute('class', 'ctnt_img')
    img.src = event.target.result
    question_ctnt_img.replaceWith(img)
  }
  reader.readAsDataURL(event.target.files[0])
})

function question_write_submit_btn() {
  if (!question_write_form.question_pk.value) {
    if (confirm('등록 하시겠습니까?')) {
      if (question_write_form.question_title.value === '') {
        alert('제목을 입력하시오')
        question_write_form.question_title.focus()
        return false
      } else if (question_write_form.question_ctnt.value === '') {
        alert('내용을 입력하시오')
        question_write_form.question_ctnt.focus()
        return false
      }
      questionReg()
      return true
    }
    return false
  }
  questionUpd()
}

function questionReg() {
  ajax()
  function ajaxImg() {
    return new Promise(function (resolve) {
      var formData = new FormData()
      formData.append('img', question_img.files[0])
      fetch('/question_img', {
        method: 'post',
        body: formData,
      })
        .then((res) => res.json())
        .then((myJson) => {
          console.log(myJson.result)
          resolve(myJson.result)
        })
    })
  }

  async function ajax() {
    let img = await ajaxImg()
    if (img == null) {
      img = '/images/question/basic_cs.jpg'
    }
    let param = {
      question_title: question_write_form.question_title.value,
      question_ctnt: question_write_form.question_ctnt.value,
      question_userpk: question_write_form.question_userpk.value,
      question_typ: question_write_form.question_type.value,
      question_img: img,
    }
    fetch('/question_write', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then(function (myJson) {
        location.href = `/question`
      })
  }
}

//문의사항 수정
function questionUpd() {
  ajax()
  function ajax() {
    let param = {
      question_title: question_write_form.question_title.value,
      question_ctnt: question_write_form.question_ctnt.value,
      question_pk: question_write_form.question_pk.value,
    }
    console.log(param)
    fetch('/question_upd', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then(function (myJson) {
        location.href = `/question`
      })
  }
}
