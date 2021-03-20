'use strict'

let page_count = 0

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

// ********************************************************** //
// Feed Scroll
document.querySelector('select[name="feed"]').addEventListener('change', () => {
  console.log('변경')
  page_count = 0
})

window.addEventListener('DOMContentLoaded', function () { // HTML과 script가 로드된 시점에 발생하는 이벤트. 
  console.log(page_count)
  page_count++
})

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let windowHeight = window.innerHeight // 스크린 창
  let fullHeight = document.body.scrollHeight // margin 값은 포함 x

  if (scrollLocation + windowHeight >= fullHeight) {
    makeFeed()
    console.log(page_count)
    page_count++
  }
})

function makeFeed(myJson) {
  for(let i = 0; i < 5; i++){
    let feedEle = document.querySelector('#feed')
    let feed_containerEle = document.createElement('div')
    feed_containerEle.className = 'feed_container'
    feedEle.appendChild(feed_containerEle)
    
    let feed_titleEle = document.createElement('div')
    feed_titleEle.className = 'feed_title'
    feed_containerEle.appendChild(feed_titleEle)
    let imgEle = document.createElement('img')
    imgEle.innerHTML =
    '<img src="/resources/img/common/basic_profile.png" alt="기본프로필사진">'
    feed_titleEle.appendChild(imgEle)
    let spanEle = document.createElement('span')
    spanEle.innerHTML = `<span>${myJson.user_id}</span><span>${myJson.feed_writedate}</span> `
    feed_titleEle.appendChild(spanEle)
    let iEle = document.createElement('i')
    iEle.innerHTML = '<i class="fas fa-ellipsis-h"></i>'
    feed_titleEle.appendChild(iEle)
    
    let feed_imgEle = document.createElement('div')
    feed_imgEle.className = 'feed_img'
    feed_containerEle.appendChild(feed_imgEle)
    let previousEle = document.createElement('div')
    previousEle.className = 'previous'
    previousEle.setAttribute('onclick', previousImg(this))
    feed_imgEle.appendChild(previousEle)
    let iEle2 = document.createElement('i')
    iEle2.innerHTML = '<i class="fas fa-chevron-left"></i>'
    previousEle.appendChild(iEle2)
    let feed_imgListEle = document.createElement('div')
    feed_imgListEle.className = 'feed_imgList'
    feed_imgEle.appendChild(feed_imgListEle)
    let img2Ele = document.createElement('img')
    img2Ele.innerHTML = `<img src="/resources/img/feed/${myJson.media_url}">`
    feed_imgListEle.appendChild(img2Ele)
    let nextEle = document.createElement('div')
    nextEle.className = 'next'
    nextEle.setAttribute('onclick', nextImg(this))
    let iEle3 = document.createElement('i')
    iEle3.innerHTML = '<i class="fas fa-chevron-right"></i>'
    nextEle.appendChild(iEle3)
    
    let feed_contentEle = document.createElement('div')
    feed_contentEle.className = 'feed_content'
    feed_contentEle.setAttribute('onclick', feedDetail(this))
    let pEle = document.createElement('p')
    feed_contentEle.appendChild(pEle)
    let aEle = document.createElement('a')
    aEle.innerHTML = `<a href="#">${myJson.hashtag_ctnt}</a>`
    pEle.appendChild(aEle)
    let pEle2 = document.createElement('p')
    pEle2.innerHTML = `${myJson.feed_ctnt}`
    feed_contentEle.appendChild(pEle2)
    
    let feed_functionbarEle = document.createElement('div')
    feed_functionbarEle.className = 'feed_functionbar'
    let iEle4 = document.createElement('i')
    iEle4.innerHTML = '<i class="far fa-heart"></i>'
    feed_functionbarEle.appendChild(iEle4)
    let spanEle2 = document.createElement('span')
    spanEle2.innerText = `${myJson.favorite_count}`
    feed_functionbarEle.appendChild(spanEle2)
    let iEle5 = document.createElement('i')
    iEle5.innerHTML = '<i class="fal fa-comment"></i>'
    feed_functionbarEle.appendChild(iEle5)
    let spanEle3 = document.createElement('span')
    spanEle3.innerText = `${myJson.comment_ctnt}`
    feed_functionbarEle.appendChild(spanEle3)
    let iEle6 = document.createElement('i')
    iEle6.innerHTML = '<i class="far fa-bookmark"></i>'
    feed_functionbarEle.appendChild(iEle6)

    ajax()
    
    function ajax() {
      let params = {
        category: category.value,
        user_pk: user_pk.value,
        page_count: page_count
      }
      console.log(params)
      
      fetch('/feed', {
        method: 'get',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(param), 
      }).then((res) => {
        res.json()
      }).then(function (myJson) {
        console.log(myJson)
      })
    }
  }
}