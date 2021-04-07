'use strict'

let page_count = 0

// feed scroll
const windowHeight = window.innerHeight // 현재 보이는 창 높이
const feedEle = document.querySelector('#bookmark_container')

document.addEventListener('DOMContentLoaded', async function () { // HTML과 script가 로드된 시점에 발생하는 이벤트.
  await makeFeedAjax(1, page_count, '/bookmark').then((myJson) => {
    makeFeed(myJson,feedEle)
  })
  page_count++
  await ajax()
  function ajax() {
    if(document.body.scrollHeight <= windowHeight) {
      makeFeedAjax(1, page_count, '/bookmark').then((myJson) => {
        makeFeed(myJson,feedEle)
      })
      page_count++
    }
  }
})

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이

  if (scrollLocation + windowHeight >= fullHeight) {
    makeFeedAjax(1, page_count, '/bookmark').then((myJson) => {
      makeFeed(myJson,feedEle)
    })
    page_count++
  }
})