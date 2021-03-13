const input_ht = document.querySelector('.text_hashtag')
const ht = document.querySelector('#write_hashtag')

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
  }

  let input_ht_val = input_ht.value
  let span_ht = document.createElement('span')
  span_ht.className = 'span_ht'
  let div_DeleteBtn2 = document.createElement('div')
  div_DeleteBtn2.className = 'DeleteBtn2'
  div_DeleteBtn2.innerText = 'x'
  div_DeleteBtn2.addEventListener('click', function () {
    span_ht.remove()
    htCount = ht.childElementCount - 1
    console.log(htCount)
    if (htCount < 10) {
      ht.appendChild(input_ht)
    }
  })
  span_ht.innerText = sarp(input_ht_val)
  span_ht.append(div_DeleteBtn2)
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

    console.log(image)
    reader.readAsDataURL(image)
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
}

const writer_containerBtn = document.querySelector('#submit_btn')
const inputImgElem = document.querySelector('#file_video')
writer_containerBtn.addEventListener('click', () => {
  let formData = new FormData()
  for (var i = 0; i < inputImgElem.files.length; i++) {
    formData.append('imgs', inputImgElem.files[i])
  }
  console.log(formData)

  let feed_ctntVal = document.querySelector('#write_text').value
  console.log('feed_ctnt: ' + feed_ctntVal)

  let hashtag_ctntVals = document.querySelectorAll('.span_ht')
  for (let i = 0; i < hashtag_ctntVals.length; i++) {
    console.log(hashtag_ctntVals[i].innerText.slice(0, -1))
  }
})
