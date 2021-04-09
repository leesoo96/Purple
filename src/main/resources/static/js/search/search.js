'use strict'

let search_contentEle = document.querySelector('.search-form')
const search_content = document.querySelector('.search_content')
const search_input = document.querySelector('.search-input')
const form = document.querySelector('.search-form')

const windowHeight = window.innerHeight // 현재 보이는 창 높이

let page_count

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이
  if (scrollLocation + windowHeight >= fullHeight) {
    if(page_count === 0) {
      page_count =1
    }
    let search_check = document.querySelector('input[name="type"]:checked').value
    if (search_check == 1) {
      let param = {
        page_count,
        feed_ctnt: form.search_input.value,
      }
      fetchAjax(param, 'post', '/search/searchFeed', (myJson => {
        if (myJson.result.length != 0) {
          makeFeed(myJson, search_content)
          page_count++
        }
      }))
      
    } else if (search_check == 2) {
      let param = {
        page_count,
        search_hashtag_ctnt: form.search_input.value,
      }

      fetchAjax(param, 'post', '/search/searchHashtag', (myJson) => {
        if (myJson.result.length != 0) {
          makeFeed(myJson, search_content)
          page_count++
        }
      })  
    }
  }
})

function userSearchClick() {
  search_content.querySelectorAll('*').forEach((test) => test.remove())
  form.search_input.value = ''
  search_content.innerHTML = ''
  page_count = 0
}

function feedSearchClick() {
  search_content.querySelectorAll('*').forEach((test) => test.remove())
  form.search_input.value = ''
  search_content.innerHTML = ''
  page_count = 0
}

function hashtagSearchClick() {
  search_content.querySelectorAll('*').forEach((test) => test.remove())
  form.search_input.value = '#'
  search_content.innerHTML = ''
  page_count = 0
}

function enterkey(e) {
  page_count= 0
  let search_check = document.querySelector('input[name="type"]:checked').value

  // 유저검색
  if (search_check == 0) {
    let param = {
      search_user_id: form.search_input.value,
    }
    fetchAjax(param, 'post', '/search/searchUser', (myJson) => {
      if (myJson.length == 0) {
        search_content.querySelectorAll('*').forEach((test) => test.remove())
        let noSearchDiv = document.createElement('div')
        noSearchDiv.className = 'noSearch'
        noSearchDiv.innerHTML = '검색 결과가 없습니다.'
        search_content.appendChild(noSearchDiv)
        return
      }
      search_content.querySelectorAll('*').forEach((test) => test.remove())
      user_list(myJson)
    })

    // 내용검색
  } else if (search_check == 1) {
    let param = {
      page_count,
      feed_ctnt: form.search_input.value,
    }
    fetchAjax(param, 'post', '/search/searchFeed', (myJson) => {
      if (myJson.result.length == 0) {
        search_content.querySelectorAll('*').forEach((test) => test.remove())
        let noSearchDiv = document.createElement('div')
        noSearchDiv.className = 'noSearch'
        noSearchDiv.innerHTML = '검색 결과가 없습니다.'
        search_content.appendChild(noSearchDiv)
        return
      }
      search_content.querySelectorAll('*').forEach((test) => test.remove())
      makeFeed(myJson, search_content)
    })

    // 해시태그 검색
  } else if (search_check == 2) {
    let param = {
      page_count,
      search_hashtag_ctnt: form.search_input.value,
    }

    fetchAjax(param, 'post', '/search/searchHashtag', (myJson) => {
      if (myJson.result.length == 0) {
        search_content.querySelectorAll('*').forEach((test) => test.remove())
        let noSearchDiv = document.createElement('div')
        noSearchDiv.className = 'noSearch'
        noSearchDiv.innerHTML = '검색 결과가 없습니다.'
        search_content.appendChild(noSearchDiv)
        return
      }
      search_content.querySelectorAll('*').forEach((test) => test.remove())
      makeFeed(myJson, search_content)
    })
  }
}

if (form.search_input.value != '') {
  page_count = 0
  let param = {
    page_count,
    search_hashtag_ctnt: form.search_input.value,
  }
  fetchAjax(param, 'post', '/search/searchHashtag', (myJson) => {
    if (myJson.result.length == 0) {
      search_content.querySelectorAll('*').forEach((test) => test.remove())
      let noSearchDiv = document.createElement('div')
      noSearchDiv.className = 'noSearch'
      noSearchDiv.innerHTML = '검색 결과가 없습니다.'
      search_content.appendChild(noSearchDiv)
      return
    }
    search_content.querySelectorAll('*').forEach((test) => test.remove())
    makeFeed(myJson, search_content)
  })
}

function user_list(myJson) {
  let search_table = document.createElement('table')
  for (let i = 0; i < myJson.length; i++) {
    let search_tr = document.createElement('tr')
    search_table.appendChild(search_tr)

    let search_td = document.createElement('td')
    search_tr.appendChild(search_td)

    let user_img = document.createElement('img')
    if (myJson[i].user_profileimg == null) {
      user_img.src = '/resources/img/common/basic_profile.png'
    } else {
      user_img.src = `${myJson[i].user_profileimg}`
    }
    user_img.id = 'profileImg'
    user_img.alt = '기본프로필사진'
    search_td.appendChild(user_img)

    let user_id = document.createElement('a')
    user_id.href = `/userpage/${myJson[i].user_id}`
    search_td.appendChild(user_id)

    let user_id_span = document.createElement('span')
    user_id_span.id = 'writer'
    user_id_span.innerHTML = `@${myJson[i].user_id}`
    user_id.appendChild(user_id_span)

    let user_bio = document.createElement('span')
    user_bio.id = 'content'
    user_bio.innerHTML = `${myJson[i].user_bio}`
    if (myJson[i].user_bio === null) {
      user_bio.innerHTML = '상태 메세지가 없습니다.'
    }
    search_td.appendChild(user_bio)
    let friend_pk = `${myJson[i].user_pk}`
    let addFriendParam = {
      user_pk: document.querySelector('#user_pk').value,
      friend_pk: friend_pk,
    }
    fetchAjax(addFriendParam, 'post', '/friend/friendCheck', (myJson) => {
      if (myJson.result == 1) {
        let user_tag = document.createElement('div')

        user_tag.setAttribute('onclick', `addFriendFunc(${friend_pk})`)
        if (document.querySelector('#user_pk').value != `${friend_pk}`) {
          search_td.appendChild(user_tag)

          let user_teg_i = document.createElement('i')
          user_teg_i.className = 'fas fa-user-plus'
          user_tag.appendChild(user_teg_i)
        }
      }
    })
    search_content.appendChild(search_table)
  }
}

function addFriendFunc(friend_pk) {
  if (confirm('친구 추가 하시겠습니까?')) {
    let addFriendParam = {
      user_pk: document.querySelector('#user_pk').value,
      friend_pk: friend_pk,
    }
    fetch('/friend/addNewFriend', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(addFriendParam),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result === 1) {
          alert('친구 등록이 완료되었습니다.')
          return
        }
        alert('이미 등록된 친구입니다.')
      })
  }
}
