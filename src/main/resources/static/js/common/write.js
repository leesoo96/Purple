'use strict'

const input_ht = document.querySelector('.text_hashtag')
const ht = document.querySelector('#write_hashtag')
const write_contentEle = document.querySelector('#write_content')
let arr = new Array()

// 클릭 이벤트
function on() {
  document.querySelector('#overlay').style.display = 'block'
}

function off() {
  document.querySelector('#overlay').style.display = 'none'
}

function keyEnter(event) {
  if (event.keyCode === 13) {
    return false
  }
  return true
}

function checkNull() {
  let content_ctnt = document.querySelector('#write_text')
  if(content_ctnt.value == '' && document.querySelector('.span_ht') == null && document.querySelector('.preview') == null){
    alert('내용을 입력해 주세요')
    return false
  }
}

//해시태그
function hashtag() {
  let htCount = ht.childElementCount
  if (htCount >= 10) {
    alert('10개까지 입력 가능합니다')
    input_ht.remove()
  } else {

  }

  // 해시태그 내용 span 생성
  let input_ht_val = input_ht.value
  let span_ht = document.createElement('span')
  span_ht.className = 'span_ht'
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] == input_ht_val) {
      input_ht.value = ''
      alert('같은내용의 해시태그가 있습니다')
      return
    }
  }
  arr.push(input_ht_val)

  // span태그 삭제 버튼
  let div_DeleteBtn2 = document.createElement('div')
  div_DeleteBtn2.className = 'DeleteBtn2'
  div_DeleteBtn2.innerText = 'x'
  div_DeleteBtn2.addEventListener('click', function () {
    for (let i = 0; i < arr.length; i++) {
      let hidden_inputval = hidden_input.value
      if ('#' + arr[i].slice(0, -1) == hidden_inputval) {
        arr.splice(i, 1)
        i--
      }
    }
    span_ht.remove()
    hidden_input.remove()
    htCount = ht.childElementCount - 1
    console.log(htCount)
    if (htCount < 10) {
      ht.appendChild(input_ht)
    }
  })

  // hidden input 생성
  let hidden_input = document.createElement('input')
  hidden_input.type = 'hidden'
  hidden_input.value = sarp(input_ht_val).trim()
  hidden_input.name = 'hashtag'

  span_ht.innerText = sarp(input_ht_val)
  span_ht.append(div_DeleteBtn2)
  span_ht.append(hidden_input)
  document.querySelector('#write_hashtag').appendChild(span_ht)
  input_ht.before(span_ht)
  input_ht.value = ''
}

// 맨앞에 #붙이기
function sarp(input_ht_val) {
  if (input_ht_val.charAt(0) !== '#') {
    input_ht_val = '#' + input_ht_val
    return input_ht_val
  }
  return input_ht_val
}

// 해시태그에서 엔터, 스페이스바치면 넘어가게 처리
function enterkey() {
  if (window.event.keyCode == 32 || window.event.keyCode == 13) {
    if (input_ht.value == ' ' || input_ht.value == '') {
      input_ht.value = ''
      alert('내용을 입력해 주세요')
    } else {
      hashtag()
    }
  }
}

// preview_img
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
      img.className = 'img'
      img.setAttribute('src', event.target.result)
      div_preview.append(div_DeleteBtn)
      div_preview.append(img)
      document.querySelector('div.write_preview').appendChild(div_preview)
    }

    reader.readAsDataURL(image)
  }
}
