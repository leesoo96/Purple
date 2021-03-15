const feed_imgs = document.querySelectorAll('.feed_img')
const feedDetail_imgs = document.querySelectorAll('.feedDetail_img')
for (let i = 0; i < feed_imgs.length; i++) {
  // 처음 사진을 띄워주는 부분
  let present_img = feed_imgs[i].querySelector('img')
  present_img.style.display = 'block'

  feed_imgs[i].querySelector('.next').addEventListener('click', () => {
    if (present_img.nextElementSibling.src) {
      console.log(present_img.nextElementSibling)
      present_img.style.display = 'none'
      present_img = present_img.nextElementSibling
      present_img.style.display = 'block'
    }
    return
  })
  feed_imgs[i].querySelector('.previous').addEventListener('click', () => {
    if (present_img.previousElementSibling.src) {
      console.log(present_img.previousElementSibling)
      present_img.style.display = 'none'
      present_img = present_img.previousElementSibling
      present_img.style.display = 'block'
    }
    return
  })
}

var eleI = document.createElement('i')

eleI.classList.add('fas')
eleI.classList.add('fa-chevron-left')

eleI.addEventListener('click', function () {})

for (let i = 0; i < feedDetail_imgs.length; i++) {
  // 처음 사진을 띄워주는 부분
  let present_detailimg = feedDetail_imgs[i].querySelector('img')
  present_detailimg.style.display = 'block'

  feedDetail_imgs[i]
    .querySelector('.detail_next')
    .addEventListener('click', () => {
      if (present_detailimg.nextElementSibling.src) {
        console.log(present_detailimg.nextElementSibling)
        present_detailimg.style.display = 'none'
        present_detailimg = present_detailimg.nextElementSibling
        present_detailimg.style.display = 'block'
      }
      return
    })
  feedDetail_imgs[i]
    .querySelector('.detail_previous')
    .addEventListener('click', () => {
      if (present_detailimg.previousElementSibling.src) {
        console.log(present_detailimg.previousElementSibling)
        present_detailimg.style.display = 'none'
        present_detailimg = present_detailimg.previousElementSibling
        present_detailimg.style.display = 'block'
      }
      return
    })
}

// 피드 뿌리기
let feed_titleEle = document.querySelectorAll('.feed_title')
let feed_imgEle = document.querySelectorAll('.feed_img')
let feed_functionbarEle = document.querySelectorAll('.feed_functionbar')
let feed_contentEle = document.querySelectorAll('.feed_content')

function getFeedList() {
  ajax()

  function ajax() {
    let params = {
      user_id: feed_titleEle.value,
      user_joindate: feed_titleEle.value,
      favorite_cnt: feed_functionbarEle.value,
      comment_cnt: feed_functionbarEle.value,
      hashtag_ctnt: feed_contentEle.value,
      feed_ctnt: feed_contentEle.value,
    }

    console.log(params)

    fetch('/feed', {
      method: 'get',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    })
    

  }
}
