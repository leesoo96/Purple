'use strict'

const add_friend_btn = document.querySelector('#add_friend_btn')

add_friend_btn.addEventListener('click', () =>{
  let user_pk = document.querySelector('#user_pk').value
  let friend_pk = document.querySelector('#friend_pk').value

  let addFriendParam = {
      user_pk: user_pk,
      friend_pk: friend_pk,
    }
  fetchAjax(addFriendParam, 'post', '/friend/addNewFriend', (myJson) => {
    if(myJson.result === 1) {
      alert('친구 등록이 완료되었습니다.')
      location.reload()
      return
    }
  alert('이미 등록된 친구입니다.')
  location.reload()
  })            
})

let page_count = 0
const feedEle = document.querySelector('#feed')
const user_id = document.querySelector('.userpageuser_id').querySelector('span').innerHTML
// feed scroll
const windowHeight = window.innerHeight // 현재 보이는 창 높이

document.addEventListener('DOMContentLoaded', async function () { // HTML과 script가 로드된 시점에 발생하는 이벤트.
    await makeFeedAjax(document.querySelector('#friend_pk').value, page_count, '/userpage/'+user_id).then((myJson) => {
      makeFeed(myJson, feedEle)
    })
    page_count++
    await ajax()
function ajax() {
    if(document.body.scrollHeight <= windowHeight) {
    makeFeedAjax(document.querySelector('#friend_pk').value, page_count, '/userpage/'+user_id).then((myJson) => {
      makeFeed(myJson, feedEle)
    })
    page_count++
  }
}
})

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이

  if (scrollLocation + windowHeight >= fullHeight) {
    makeFeedAjax(document.querySelector('#friend_pk').value, page_count, '/userpage/'+user_id).then((myJson) => {
      makeFeed(myJson, feedEle)
    })
    page_count++
  }
})