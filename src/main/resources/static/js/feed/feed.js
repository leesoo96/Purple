// const feed_imgs = document.querySelectorAll('.feed_img')
// const feedDetail_imgs = document.querySelectorAll('.feedDetail_img')
// for (let i = 0; i < feed_imgs.length; i++) {
//   // 처음 사진을 띄워주는 부분
//   let present_img = feed_imgs[i].querySelector('img')
//   present_img.style.display = 'block'

//   feed_imgs[i].querySelector('.next').addEventListener('click', () => {
//     if (present_img.nextElementSibling.src) {
//       console.log(present_img.nextElementSibling)
//       present_img.style.display = 'none'
//       present_img = present_img.nextElementSibling
//       present_img.style.display = 'block'
//     }
//     return
//   })
//   feed_imgs[i].querySelector('.previous').addEventListener('click', () => {
//     if (present_img.previousElementSibling.src) {
//       console.log(present_img.previousElementSibling)
//       present_img.style.display = 'none'
//       present_img = present_img.previousElementSibling
//       present_img.style.display = 'block'
//     }
//     return
//   })
// }

// var eleI = document.createElement('i')

// eleI.classList.add('fas')
// eleI.classList.add('fa-chevron-left')

// eleI.addEventListener('click', function () {})

// for (let i = 0; i < feedDetail_imgs.length; i++) {
//   // 처음 사진을 띄워주는 부분
//   let present_detailimg = feedDetail_imgs[i].querySelector('img')
//   present_detailimg.style.display = 'block'

//   feedDetail_imgs[i]
//     .querySelector('.detail_next')
//     .addEventListener('click', () => {
//       if (present_detailimg.nextElementSibling.src) {
//         console.log(present_detailimg.nextElementSibling)
//         present_detailimg.style.display = 'none'
//         present_detailimg = present_detailimg.nextElementSibling
//         present_detailimg.style.display = 'block'
//       }
//       return
//     })
//   feedDetail_imgs[i]
//     .querySelector('.detail_previous')
//     .addEventListener('click', () => {
//       if (present_detailimg.previousElementSibling.src) {
//         console.log(present_detailimg.previousElementSibling)
//         present_detailimg.style.display = 'none'
//         present_detailimg = present_detailimg.previousElementSibling
//         present_detailimg.style.display = 'block'
//       }
//       return
//     })
// }

function previousImg(e) {
  const feed_imgListDiv = e.parentNode.querySelector('.feed_imgList')
  let first_img = feed_imgListDiv.firstChild.nextSibling
  while (first_img.nodeType !== 1) {
    first_img = first_img.nextSibling
  }
  let last_img = feed_imgListDiv.lastChild.previousSibling
  while (last_img.nodeType !== 1) {
    last_img = last_img.previousSibling
  }
  first_img.before(last_img)
}

function nextImg(e) {
  const feed_imgListDiv = e.parentNode.querySelector('.feed_imgList')
  let first_img = feed_imgListDiv.firstChild.nextSibling
  while (first_img.nodeType !== 1) {
    first_img = first_img.nextSibling
  }
  let last_img = feed_imgListDiv.lastChild.previousSibling
  while (last_img.nodeType !== 1) {
    last_img = last_img.previousSibling
  }
  last_img.after(first_img)
}

// const feed_containerEle = document.querySelector('.feed_container')

// document.querySelector('#feed').addEventListener('scroll', () => {
//   let scrollLocation = document.documentElement.scrollTop  // 현재 스크롤바 위치
// 	let windowHeight = window.innerHeight                    // 스크린 창
// 	let fullHeight = document.body.scrollHeight              //  margin 값은 포함 x

// 	if(scrollLocation + windowHeight >= fullHeight){
// 		let feed_titleEle = document.createElement('div')
//     feed_titleEle.className = 'feed_title'
//     feed_containerEle.appendChild(feed_titleEle)
//     let imgEle = document.createElement('img')
//     imgEle.src = "/resources/img/common/basic_profile.png"
//     imgEle.alt = "기본프로필사진"
//     feed_titleEle.appendChild(img)
//     let spanEle = document.createElement('span')
//     spanEle.innerText = 'USER234'
//     spanEle.innerText = '2021-01-23'
//     feed_titleEle.appendChild(spanEle)
//     let iEle = document.createElement('i')
//     iEle.className = 'fas fa-ellipsis-h'
//     feed_titleEle.appendChild(iEle)

//     feed_titleEle.className = 'feed_img'
//     feed_titleEle.onload = "loadImg()"
//     feed_containerEle.appendChild(feed_titleEle)
//     feed_titleEle.className = 'previous'
//     feed_titleEle.onclick = "previousImg(this)"

// 	}
// })