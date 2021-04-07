const input_ht = document.querySelector('.text_hashtag')
const ht = document.querySelector('#write_hashtag')
const write_contentEle = document.querySelector('#write_content')
// 클릭 이벤트
function on() {
  document.querySelector('#overlay').style.display = 'block'
}

function off() {
  document.querySelector('#overlay').style.display = 'none'
}

//해시태그
function hashtag() {
  let htCount = ht.childElementCount
  console.log(htCount)
  if (htCount >= 10) {
    alert('10개까지 입력 가능합니다')
    input_ht.remove()
  } else {
  }

  // 해시태그 내용 span 생성
  let input_ht_val = input_ht.value
  let span_ht = document.createElement('span')
  span_ht.className = 'span_ht'

  // span태그 삭제 버튼
  let div_DeleteBtn2 = document.createElement('div')
  div_DeleteBtn2.className = 'DeleteBtn2'
  div_DeleteBtn2.innerText = 'x'
  div_DeleteBtn2.addEventListener('click', function () {
    span_ht.remove()
    hidden_input.remove()
    htCount = ht.childElementCount - 1
    console.log(htCount)
    if (htCount < 10) {
      ht.appendChild(input_ht)
    }
  })

  //hidden input 생성
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

//맨앞에 #붙이기
function sarp(input_ht_val) {
  if (input_ht_val.charAt(0) !== '#') {
    input_ht_val = '#' + input_ht_val
    return input_ht_val
  }
  return input_ht_val
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
      img.className = 'img'
      img.setAttribute('src', event.target.result)
      div_preview.append(div_DeleteBtn)
      div_preview.append(img)
      document.querySelector('div.write_preview').appendChild(div_preview)
    }

    reader.readAsDataURL(image)
  }
}
/*
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
}

const writer_containerBtn = document.querySelector('#submit_btn')
//const inputImgEle = document.querySelector('#file_video')
let user_pkEle = document.querySelector('#user_pk')
let feed_ctnt = write_contentEle.feed_ctnt
let hashtag_ctntVals = document.querySelectorAll('.span_ht')
writer_containerBtn.addEventListener('click', () => {
  uploadFunc()
})

function uploadFunc() {
  ajax()

  function ajax() {
    //let formData = new FormData()
    //for (var i = 0; i < inputImgEle.files.length; i++) {
    //  formData.append('imgs', inputImgEle.files[i])
    //}
    //console.log(formData)

    let formData = new FormData()
    for (let i = 0; i < hashtag_ctntVals.length; i++) {
      formData.append(hashtag_ctntVals[i].innerText.slice(0, -1))
    }
    let param = {
      feed_ctnt: feed_ctnt.value,
      hashtag_ctnt: formData,
      //user_pk: user_pkEle.value,
    }
    fetch('feed/uploadfeed', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then(function (myJson) {
        console.log(myJson)
        location.href = `/feed`
      })
  }
}
*/
