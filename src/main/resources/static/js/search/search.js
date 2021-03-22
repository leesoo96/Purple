let search_contentEle = document.querySelector('.search-form')
const search_content = document.querySelector('.search_content')
const search_input = document.querySelector('.search-input')

//유저 검색 리스트
function enterkey() {
  const form = document.querySelector('.search-form')
  let param = {
    search_user_id: form.search_input.value,
  }
  fetch('/search/searchUser', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(param),
  })
    .then((res) => res.json())
    .then((myJson) => {
      search_content.querySelectorAll('*').forEach((test) => test.remove())
      user_list(myJson)
    })

  function user_list(myJson) {
    for (let i = 0; i < myJson.length; i++) {
      let search_table = document.createElement('table')

      let search_tr = document.createElement('tr')
      search_table.appendChild(search_tr)

      let search_td = document.createElement('td')
      search_tr.appendChild(search_td)

      let user_img = document.createElement('img')
      user_img.src = `${myJson[i].user_profileimg}`
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
      user_bio.innerHTML = `@${myJson[i].user_bio}`
      search_td.appendChild(user_bio)

      let user_tag = document.createElement('div')
      user_tag.setAttribute('onclick', `addFriendFunc(${myJson[i].user_pk})`)
      search_td.appendChild(user_tag)

      let user_teg_i = document.createElement('i')
      user_teg_i.className = 'fas fa-user-plus'
      user_tag.appendChild(user_teg_i)

      search_content.appendChild(search_table)
    }
  }
}
function addFriendFunc(friend_pk) {
  let addFriendParam = {
    user_pk: document.querySelector('#user_pk').value,
    friend_pk: friend_pk,
  }
  fetch('/layout/addNewFriend', {
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
