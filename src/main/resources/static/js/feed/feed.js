'use strict'

let page_count = 0

const feedEle = document.querySelector('#feed')

// feed scroll
const windowHeight = window.innerHeight // 현재 보이는 창 높이

document.addEventListener('DOMContentLoaded', async function () {
  // HTML과 script가 로드된 시점에 발생하는 이벤트.
  await makeFeedAjax(
    document.querySelector('select[name="feed"]').value,
    page_count, '/feed'
  ).then((myJson) => {
    makeFeed(myJson, feedEle)
  })
  page_count++
  await ajax()
  function ajax() {
    if (document.body.scrollHeight <= windowHeight) {
      makeFeedAjax(
        document.querySelector('select[name="feed"]').value,
        page_count, '/feed'
      ).then((myJson) => {
        makeFeed(myJson, feedEle)
      })
      page_count++
    }
  }
})

document.querySelector('select[name="feed"]').addEventListener('change', () => {
  page_count = 0

  document
    .querySelector('#feed')
    .querySelectorAll('*')
    .forEach((test) => test.remove())

  makeFeedAjax(document.querySelector('select[name="feed"]').value, page_count, '/feed')
    .then((myJson) => {
      makeFeed(myJson, feedEle)
      page_count++
    })
    .then(() => {
      let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
      let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이
      if (scrollLocation + windowHeight >= fullHeight) {
        makeFeedAjax(
          document.querySelector('select[name="feed"]').value,
          page_count, '/feed'
        ).then((myJson) => {
          makeFeed(myJson, feedEle)
        })
        page_count++
      }
    })
})

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이

  if (scrollLocation + windowHeight >= fullHeight) {
    makeFeedAjax(
      document.querySelector('select[name="feed"]').value,
      page_count, '/feed'
    ).then((myJson) => {
      makeFeed(myJson, feedEle)
    })
    page_count++
  }
})

