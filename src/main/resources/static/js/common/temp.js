'use strict'

// 각 아이콘 hover event
let home = document.querySelector('#home a')
let search = document.querySelector('#search a')
let news = document.querySelector('#news a')
let bookmark = document.querySelector('#bookmark a')

let menus = [home, search, news, bookmark]
for (let i = 0; i < menus.length; i++) {
  menus[i].onmouseover = function () {
    menus[i].classList.add('hoverMenu')
  }
  menus[i].onmouseout = function () {
    menus[i].classList.remove('hoverMenu')
  }
}

// 고객센터 hover event
let cs_menus = document.querySelector('#cs_menus')
cs.addEventListener('mouseover', function () {
  cs_menus.style.display = 'block'
})
cs.addEventListener('mouseout', function () {
  cs_menus.style.display = 'none'
})

// 외부 클릭 시 user_modal 닫힘(main 제외)
// user_modal 이벤트
let only_modalClose = document.querySelector('.only_modalClose')
let temp_user = document.querySelector('#temp_user')
let user_modal = document.querySelector('#temp_user_modal')
temp_user.onclick = function () {
  user_modal.style.display = 'block'

  only_modalClose.onclick = function (e) {
    e.target === only_modalClose ? (user_modal.style.display = 'none') : false
  }
}

// dm창 열고 닫기 이벤트
function dm_view() {
  let dm_open = document.querySelector('.dm_open')
  let dm_close = document.querySelector('.dm_close')
  let chat_view = document.querySelector('.chat_view')

  dm_open.addEventListener('click', function () {
    chat_view.style.visibility = 'visible'
    chat_view.style.opacity = '1'
  })

  dm_close.addEventListener('click', function () {
    chat_view.style.visibility = 'hidden'
    chat_view.style.opacity = '0'
  })
}
dm_view()

// 친구목록 버튼과 대화목록 버튼 클릭 시
let f_btn = document.querySelector('#friend_btn')
let c_btn = document.querySelector('#chat_btn')

let friend_list = document.querySelector('#friend_list')
let chat_list = document.querySelector('.chat_list')

f_btn.onclick = function () {
  f_btn.style.color = '#7901a1'
  c_btn.style.color = '#525252'

  friend_list.style.left = '0'
  friend_list.style.transition = '.5s'

  chat_list.style.left = '27em'

  chat_msg.style.right = '-23em'
  chat_msg.style.transition = '.5s'
}

c_btn.onclick = function () {
  f_btn.style.color = '#525252'
  c_btn.style.color = '#7901a1'

  chat_list.style.left = '0'
  chat_list.style.transition = '.5s'

  friend_list.style.left = '-27em'

  chat_msg.style.right = '-23em'
  chat_msg.style.transition = '.5s'
}

// 대화목록에서 각 대화 클릭 시
let chat_msg = document.querySelector('.chat_msg')
let c_div = document.querySelectorAll('.chat_list div')

for (let i = 0; i < c_div.length; i++) {
  c_div[i].onclick = function () {
    chat_msg.style.right = '17px'
    chat_msg.style.transition = '.5s'

    chat_list.style.left = '-24em'
  }
}

// 채팅 - 친구목록 확인
const friend_btn = document.querySelector('#friend_btn')
const user_pk = document.querySelector('#user_pk')
const friend_listDiv = document.querySelector('#friend_list')
friend_btn.addEventListener('click', () => {
  while (friend_listDiv.querySelector('table').hasChildNodes()) {
    friend_listDiv
      .querySelector('table')
      .removeChild(friend_listDiv.querySelector('table').firstChild)
  }
  getFriendListFunc()
})

function getFriendListFunc() {
  let param = {
    user_pk: user_pk.value,
  }
  fetch('/chat/getFriendList', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(param),
  })
    .then((res) => res.json())
    .then((myJson) => {
      getFriend_list(myJson)
    })
}

const table = friend_listDiv.querySelector('table')
function getFriend_list(myJson) {
  if (myJson.length === 0) {
    let fList_div = document.createElement('div')
    table.append(fList_div)
    fList_div.className = 'notHaveAFriend'
    fList_div.innerText = '친구가 없습니다ㅠ'
    return
  }

  for (let i = 0; i < myJson.length; i++) {
    let table_tr = document.createElement('tr')
    table.append(table_tr)

    // 친구 프로필 사진
    let friend_profileImg_td = document.createElement('td')
    let friend_profileImg = document.createElement('img')
    friend_profileImg_td.appendChild(friend_profileImg)
    if (myJson[i].user_profileimg === null) {
      // 기본 프로필 이미지 사용
      friend_profileImg_td.innerHTML =
        '<img src="resources/img/common/basic_profile.png" alt="프로필사진">'
    } else {
      // -> 친구의 프로필이 표시되도록 변경되어야하는 코드
      friend_profileImg_td.innerHTML = '<img src="" alt="프로필사진">'
    }

    // 친구 아이디 + 친구 상태 메시지
    let friend_info_td = document.createElement('td')
    friend_info_td.classList.add('friend_info')

    let freind_Id_span = document.createElement('span')
    freind_Id_span.innerText = `${myJson[i].user_id}`

    let friend_bio_small = document.createElement('small')
    friend_bio_small.innerText = `${myJson[i].user_bio}`

    friend_info_td.appendChild(freind_Id_span)
    friend_info_td.appendChild(friend_bio_small)

    // 친구 정보 보기 + 차단하기
    let friend_block_td = document.createElement('td')
    friend_block_td.classList.add('friend_block')

    friend_block_td.innerHTML =
      '<span>친구정보보기</span> <span>차단하기</span>'

    // 대화
    let friend_Chat_td = document.createElement('td')
    friend_Chat_td.classList.add('friend_chat_func')
    friend_Chat_td.innerHTML = '<span>대화</span>'

    table_tr.appendChild(friend_profileImg_td)
    table_tr.appendChild(friend_info_td)
    table_tr.appendChild(friend_block_td)
    table_tr.appendChild(friend_Chat_td)
  }
  // 친구 아이디 클릭 시 친구정보 보기 + 차단하기 표시 & 숨김
  let friend_info = document.querySelectorAll('.friend_info')
  let friend_block = document.querySelectorAll('.friend_block')
  for (let i = 0; i < friend_block.length; i++) {
    friend_info[i].onclick = function () {
      if (friend_block[i].style.visibility === 'visible') {
        friend_block[i].style.visibility = 'hidden'
      } else {
        friend_block[i].style.visibility = 'visible'
      }
    }
  }
}
