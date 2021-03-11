// 클릭 이벤트
function on() {
  document.querySelector('#overlay').style.display = 'block'
}

function off() {
  document.querySelector('#overlay').style.display = 'none'
}

//해시태그
function hashtag(event) {
  var input_test = document.querySelector('.text_hashtag')
  var input_ht = input_test.value
  var span_ht = document.createElement('span')
  span_ht.className = 'span_ht'
  var div_DeleteBtn2 = document.createElement('div')
  div_DeleteBtn2.className = 'DeleteBtn2'
  div_DeleteBtn2.innerText = 'x'
  div_DeleteBtn2.addEventListener('click', function () {
    span_ht.remove()
  })
  var input = document.createElement('input')
  input.className = 'text_hashtag'
  input.type = 'text'
  span_ht.innerText = sarp(input_ht)
  span_ht.append(div_DeleteBtn2)
  document.querySelector('#write_hashtag').appendChild(span_ht)
  input_test.before(span_ht)
  input_test.value = ''
}

//맨앞에 #붙이기
function sarp(input_ht) {
  if (input_ht.charAt(0) !== '#') {
    input_ht = '#' + input_ht
    return input_ht
  }
  return input_ht
}

//해시태그에서 엔터, 스페이스바치면 넘어가게 처리
function enterkey() {
  if (window.event.keyCode == 32 || window.event.keyCode == 13) {
    hashtag()
  }
}

//preview_img
function setThumbnail(event) {
  for (var image of event.target.files) {
    var reader = new FileReader()

    reader.onload = function (event) {
      var div_preview = document.createElement('div')
      div_preview.className = 'preview'
      var div_DeleteBtn = document.createElement('div')
      div_DeleteBtn.className = 'DeleteBtn'
      div_DeleteBtn.innerText = 'x'
      div_DeleteBtn.addEventListener('click', function () {
        div_preview.remove()
      })
      var img = document.createElement('img')
      img.setAttribute('src', event.target.result)
      div_preview.append(div_DeleteBtn)
      div_preview.append(img)
      document.querySelector('div.write_preview').appendChild(div_preview)
    }

    console.log(image)
    reader.readAsDataURL(image)
  }
}

//preview_video
function setThumbnailvideo(event) {
  for (var video of event.target.files) {
    var reader = new FileReader()

    reader.onload = function (event) {
      var div_preview = document.createElement('div')
      div_preview.className = 'preview'
      var div_DeleteBtn = document.createElement('div')
      div_DeleteBtn.className = 'DeleteBtn'
      div_DeleteBtn.innerText = 'x'
      div_DeleteBtn.addEventListener('click', function () {
        div_preview.remove()
      })
      var video = document.createElement('video')
      video.setAttribute('src', event.target.result)
      if (video.paused) {
        video.play()
      } else {
        video.pause()
      }
      div_preview.append(div_DeleteBtn)
      div_preview.append(video)
      document.querySelector('div.write_preview').appendChild(div_preview)
    }

    console.log(video)
    reader.readAsDataURL(video)
  }
}
// ------------------------------------------------------cs-----------------------------------------
//돌아가기

function return_page() {
  history.back()
}

//공지사항 등록
const notice_write_form = document.querySelector('#notice_write_form')

function notice_write_submit_btn() {
  if (!notice_write_form.notice_pk.value) {
    noticeReg()
    return
  }
  noticeUpd()
}

function noticeReg() {
  ajax()
  function ajax() {
    let param = {
      notice_title: notice_write_form.notice_title.value,
      notice_ctnt: notice_write_form.notice_ctnt.value,
      notice_userpk: notice_write_form.notice_userpk.value,
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
        console.log(myJson)
        if (myJson.result === 1) {
          location.href = `/notice`
        }
      })
  }
}

//공지사항 수정
function noticeUpd() {
  ajax()
  function ajax() {
    let param = {
      notice_title: notice_write_form.notice_title.value,
      notice_ctnt: notice_write_form.notice_ctnt.value,
      notice_pk: notice_write_form.notice_pk.value,
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
      .then(function (myJson) {
        console.log(myJson)
        location.href = `/notice`
      })
  }
}
//문의사항

const question_write_form = document.querySelector('#question_write_form')

function question_write_submit_btn() {
  if (!question_write_form.question_pk.value) {
    questionReg()
    return
  }
  questionUpd()
}

function questionReg() {
  ajax()
  function ajax() {
    let param = {
      question_title: question_write_form.question_title.value,
      question_ctnt: question_write_form.question_ctnt.value,
      question_userpk: question_write_form.question_userpk.value,
      question_typ: question_write_form.question_type.value,
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
        console.log(myJson)
        if (myJson.result === 1) {
          location.href = `/question`
        }
      })
  }
}

//공지사항 수정
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
        console.log(myJson)
        location.href = `/question`
      })
  }
}
