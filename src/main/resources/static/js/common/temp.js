'use strict'

document.addEventListener('DOMContentLoaded', async () => {
  dm_view()
  await getRecommandFriendListFunc()
  getAlarmCount()
  getNoRealAllMessage()
})

// LeftSide
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
    getNoRealAllMessage()
    chat_view.style.visibility = 'hidden'
    chat_view.style.opacity = '0'
  })
}

// 친구목록 버튼과 대화목록 버튼 클릭 시
let friend_btn = document.querySelector('#friend_btn')
let chat_btn = document.querySelector('#chat_btn')

let friend_list = document.querySelector('#friend_list')
let chat_list = document.querySelector('.chat_list')
let chat_msg = document.querySelector('.chat_msg')

friend_btn.onclick = function () {
  friend_btn.style.color = '#7901a1'
  chat_btn.style.color = '#525252'

  friend_list.style.left = '0'
  friend_list.style.transition = '.5s'

  chat_list.style.left = '27em'

  chat_msg.style.right = '-23em'
  chat_msg.style.transition = '.5s'
}

chat_btn.onclick = function () {
  friend_btn.style.color = '#525252'
  chat_btn.style.color = '#7901a1'

  chat_list.style.left = '0'
  chat_list.style.transition = '.5s'

  friend_list.style.left = '-27em'

  chat_msg.style.right = '-23em'
  chat_msg.style.transition = '.5s'

  getFriendChatListFunc()
}

// 채팅 - 친구목록 확인
const user_pk = document.querySelector('#user_pk')
friend_btn.addEventListener('click', () => {
  while (friend_list.querySelector('table').hasChildNodes()) {
    friend_list
      .querySelector('table')
      .removeChild(friend_list.querySelector('table').firstChild)
  }
  getFriendListFunc()
})

function getFriendListFunc() {
  
  let param = {
    user_pk: user_pk.value,
  }
  fetchAjax(param, 'post', '/friend/getFriendList', (myJson) => {
    getFriend_list(myJson)
  })
}

const table = friend_list.querySelector('table')
function getFriend_list(myJson) {
  if (myJson.length === 0) {
    let fList_div = document.createElement('div')
    table.append(fList_div)
    fList_div.className = 'notHaveAFriend'
    let sad_cryI = document.createElement('i')
    sad_cryI.className = 'far fa-sad-cry'
    fList_div.innerText = '친구가 없습니다 '
    fList_div.appendChild(sad_cryI)
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
      friend_profileImg_td.innerHTML =
        '<img src="/resources/img/common/basic_profile.png" alt="프로필사진">'
    } else {
      friend_profileImg_td.innerHTML = `<img src="${myJson[i].user_profileimg}" alt="프로필사진">`
    }

    // 친구 아이디 + 친구 상태 메시지
    let friend_info_td = document.createElement('td')
    friend_info_td.classList.add('friend_info')

    let freind_Id_span = document.createElement('span')
    freind_Id_span.innerText = `${myJson[i].user_id}`

    let friend_bio_small = document.createElement('small')
    if (myJson[i].user_bio === null) {
      friend_bio_small.innerText = '아직 상태메시지가 없습니다.'
    } else {
      friend_bio_small.innerText = `${myJson[i].user_bio}`
    }

    friend_info_td.appendChild(freind_Id_span)
    friend_info_td.appendChild(friend_bio_small)

    // 친구 정보 보기 + 삭제하기 태그
    let friend_block_td = document.createElement('td')
    friend_block_td.classList.add('friend_block')

    const moveFriendpage = document.createElement('span')
    moveFriendpage.innerHTML = `<a href="/userpage/${myJson[i].user_id}">친구정보보기</a>`
    friend_block_td.appendChild(moveFriendpage)

    const delFriendBtn = document.createElement('span')
    delFriendBtn.innerText = '친구삭제'
    delFriendBtn.classList.add('delFriend')
    delFriendBtn.setAttribute('onclick', `delFriendFunc(${myJson[i].user_pk})`)
    moveFriendpage.after(delFriendBtn)

    // 대화
    let friend_Chat_td = document.createElement('td')
    friend_Chat_td.classList.add('friend_chat_func')
    friend_Chat_td.setAttribute(
      'onclick',
      `commonChatFunc(this, ${myJson[i].user_pk})`
    )
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

// 채팅방 만들기/가져오기
function commonChatFunc(e, friend_pk) {
  let user_id = e.parentNode.querySelector('.friend_info span').innerText
  let chatParam = {
    chatroom_friendpk: friend_pk,
  }
  fetchAjax(chatParam, 'post', '/chat/getRoom', (myJson) => {
    if (myJson.result != null) {
      enterChatroom(myJson.result, user_id)
    }
  })
}

// 친구 삭제
function delFriendFunc(friend_pk) {
  let delFriendConfirm = confirm('정말 삭제하시겠습니까?')

  if (delFriendConfirm == true) {
    delFriend()
  } else {
    return
  }

  function delFriend() {
    let delFriendParam = {
      friend_pk: friend_pk,
    }
    fetchAjax(delFriendParam, 'post', '/friend/delFriend', (myJson) => {
      if (myJson.result == 0) {
        alert('삭제에 실패하였습니다.')
        return
      } else {
        alert('삭제되었습니다.')
        history.go(0)
      }
    })
  }
}

function goToBottom() {
  document.querySelector('.chat').scrollTop = document.querySelector(
    '.chat'
  ).scrollHeight
}

// 채팅 - 대화 목록 확인
document.querySelector('.dm_open').addEventListener('click', () => {
  getFriendChatListFunc()
})

function getFriendChatListFunc() {
  let param = {
    chatroom_userpk: user_pk.value,
  }
  fetchAjax(param, 'post', '/chat/getChatList', (myJson) => {
    document
      .querySelector('.chat_list')
      .querySelectorAll('div')
      .forEach((test) => test.remove())

    getChat_List(myJson)
  })
}

const chat_list_divSpan = document.querySelector('.chat_list span')

function getChat_List(myJson) {
  if (myJson.length === 0) {
    const CList_div = document.createElement('div')
    chat_list_divSpan.after(CList_div)
    CList_div.innerText = '대화가 없습니다!'
    CList_div.style.marginLeft = '0.5em'
    CList_div.style.marginTop = '1em'
    return
  } else {
    for (let i = 0; i < myJson.length; i++) {
      let CList_div = document.createElement('div')
      CList_div.classList.add('CList_div')
      CList_div.setAttribute(
        'onclick',
        `enterChatroom('${myJson[i].chatroom_id}','${myJson[i].user_id}')`
      )
      chat_list_divSpan.after(CList_div)

      let CList_img = document.createElement('img')
      if (myJson[i].user_profileimg === null) {
        CList_img.src = '/resources/img/common/basic_profile.png'
      } else {
        CList_img.src = `${myJson[i].user_profileimg}`
      }
      CList_div.appendChild(CList_img)

      let CList_span = document.createElement('span')
      CList_img.after(CList_span)
      CList_span.innerText = `@${myJson[i].user_id}`

      let last_message = document.createElement('span')
      last_message.innerText = `${myJson[i].last_message}`
      if (myJson[i].last_message == null) {
        last_message.innerText = '대화 내용이 없습니다.'
      } else if (getTextLength(myJson[i].last_message) > 30) {
        let message_ctnt = `${myJson[i].last_message}`
        last_message.innerText = message_ctnt.substring(0, 15) + '...'
      }

      CList_span.after(last_message)
    }
  }
}

// 채팅방 들어갈 때
function enterChatroom(room_id, user_id) {
  document.querySelector('#chat_friendName').innerText = '@' + user_id
  document
    .querySelector('.chat')
    .querySelectorAll('div')
    .forEach((test) => test.remove())
  chat_msg.style.right = '17px'
  chat_msg.style.transition = '.5s'

  friend_list.style.left = '-24em'
  chat_list.style.left = '-24em'

  fetchAjax(room_id, 'get', '/chat/enterChatroom/', (myJson) => {
    if (document.querySelector('.room_id')) {
      document.querySelector('.room_id').remove()
    }
    let room_idInput = document.createElement('input')
    room_idInput.className = 'room_id'
    room_idInput.type = 'hidden'
    room_idInput.value = room_id
    document.querySelector('.chat_msg').appendChild(room_idInput)
    readMessage(room_id)
    makeChat(user_id, myJson)
    goToBottom()
  })
}

function clickRead(e) {
  readMessage(e.parentNode.parentNode.querySelector('.room_id').value)
}

function readMessage(room_id) {
  fetchAjax(room_id, 'get', '/chat/readmessage/', (myJson) => {})
}

const chatDiv = document.querySelector('.chat')
function makeChat(user_id, myJson) {
  for (let i = myJson.length - 1; i >= 0; i--) {
    if (myJson[i].user_id === user_id) {
      let friendMsgContainerDiv = document.createElement('div')
      friendMsgContainerDiv.className = 'friendMsgContainer'
      chatDiv.appendChild(friendMsgContainerDiv)

      let friendMsgP = document.createElement('p')
      friendMsgP.className = 'friendMsg'
      friendMsgP.innerHTML = myJson[i].message_ctnt
      friendMsgContainerDiv.appendChild(friendMsgP)

      let friendMsg_timeSmall = document.createElement('small')
      friendMsg_timeSmall.className = 'friendMsg_time'
      friendMsg_timeSmall.innerText = myJson[i].message_date
      friendMsgContainerDiv.appendChild(friendMsg_timeSmall)
    } else {
      let myMsgContainerDiv = document.createElement('div')
      myMsgContainerDiv.className = 'myMsgContainer'
      chatDiv.appendChild(myMsgContainerDiv)

      let myMsgP = document.createElement('p')
      myMsgP.className = 'myMsg'
      myMsgP.innerHTML = myJson[i].message_ctnt
      myMsgContainerDiv.appendChild(myMsgP)

      let myMsg_timeSmall = document.createElement('small')
      myMsg_timeSmall.className = 'myMsg_time'
      myMsg_timeSmall.innerText = myJson[i].message_date
      myMsgContainerDiv.appendChild(myMsg_timeSmall)
    }
  }
}

// 알 수도 있는 사람 목록(추천친구)
function getRecommandFriendListFunc() {
  let param = {
    user_pk: user_pk.value,
  }
  fetchAjax(param, 'post', '/friend/recommand', getRecFriend_List)
}

const recFriendTable = document.querySelector("table[name='recommand_friend']")

function getRecFriend_List(myJson) {
  for (let i = 0; i < myJson.length; i++) {
    let recFriendTr = document.createElement('tr')
    recFriendTable.appendChild(recFriendTr)

    let recFriend_profile_td = document.createElement('td')
    let recFriend_profile = document.createElement('img')
    recFriendTr.appendChild(recFriend_profile_td)

    recFriend_profile_td.appendChild(recFriend_profile)
    if (myJson[i].user_profileimg === null) {
      recFriend_profile_td.innerHTML =
        '<img src="/resources/img/common/basic_profile.png" alt="기본프로필사진">'
    } else {
      recFriend_profile_td.innerHTML =
        '<img src="' + myJson[i].user_profileimg + '" alt="프로필사진">'
    }

    let recFriendTd = document.createElement('td')
    recFriend_profile_td.after(recFriendTd)
    recFriendTd.innerHTML = `<span><a href="/userpage/${myJson[i].user_id}">${myJson[i].user_id}</a></span>`

    let addRecFriendTd = document.createElement('td')
    recFriendTd.after(addRecFriendTd)
    addRecFriendTd.innerHTML = '<i class="fas fa-plus"></i>'

    // 각 친구 pk 값
    let hiddenFriendPk = document.createElement('input')
    hiddenFriendPk.type = 'hidden'
    hiddenFriendPk.className = 'friend_pk'
    hiddenFriendPk.value = `${myJson[i].friend_pk}`
    addRecFriendTd.appendChild(hiddenFriendPk)

    let addNewFriendBtn_i = document.querySelectorAll('#recommand_div i')
    for (let j = 0; j < addNewFriendBtn_i.length; j++) {
      addNewFriendBtn_i[j].onclick = function () {
        let addFriendConfirmMsg = confirm(
          `${myJson[j].user_id}` + ' 님을 친구 추가하시겠습니까?'
        )

        if (addFriendConfirmMsg == true) {
          addFriendFunc()
        } else {
          return
        }

        function addFriendFunc() {
          let addFriendParam = {
            user_pk: user_pk.value,
            friend_pk: `${myJson[j].friend_pk}`,
          }

          fetchAjax(
            addFriendParam,
            'post',
            '/friend/addNewFriend',
            (addFriend) => {
              if (addFriend.result == 0) {
                alert('친구 추가에 실패하였습니다.')
                return
              } else {
                alert(`${myJson[j].user_id}` + ' 님을 친구 추가하였습니다.')
                sendAlarm(1, addFriendParam.user_pk, addFriendParam.friend_pk)
                history.go(0)
              }
            }
          )
        }
      }
    }
  }
}

function getAlarmCount() {
  const user_id = document.querySelector('#temp_user').innerText
  fetchAjax(user_id, 'get', '/alarm/getalarmcount/', (myJson) => {
    if (document.querySelector('#alarm')) {
      document.querySelector('#alarm').remove()
    }

    if (myJson > 0) {
      const alarmDiv = document.createElement('div')
      alarmDiv.setAttribute('id', 'alarm')
      document.querySelector('#news').appendChild(alarmDiv)

      let alarmCount = document.createElement('div')
      alarmCount.innerHTML = myJson
      alarmDiv.appendChild(alarmCount)
    }
  })
}

function getNoRealAllMessage() {
  const user_id = document.querySelector('#temp_user').innerText
  fetchAjax(user_id, 'get', '/chat/getnoreadallmessage/', (myJson) => {
    if (document.querySelector('#new_dm')) {
      document.querySelector('#new_dm').remove()
    }
    if (myJson > 0) {
      const new_dmDiv = document.createElement('div')
      new_dmDiv.setAttribute('id', 'new_dm')
      document.querySelector('#dm_view p').after(new_dmDiv)

      let noReadCount = document.createElement('div')
      noReadCount.innerHTML = myJson
      new_dm.appendChild(noReadCount)
    }
  })
}
