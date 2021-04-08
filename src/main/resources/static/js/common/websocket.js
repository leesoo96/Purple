'use strict'

let socket

if (typeof socket == 'undefined') {
  socket = new WebSocket('ws://' + location.hostname + ':8091/socket')
  socket.onopen = function () {
    console.log('웹소켓 서버 가동')
    let createSocketParam = {
      user_id: document.querySelector('#temp_user').innerText,
      type: 'CREATE',
    }
    socket.send(JSON.stringify(createSocketParam))
  }

  // 서버로부터 응답이 올 때
  socket.onmessage = function (data) {
    // 서버로 부터 받은 데이터를 json 형태로 변환
    let msg = JSON.parse(data.data)

    if (msg.type === 'CHAT') {
      getNoRealAllMessage()
      if (document.querySelector('.room_id')) {
        if (msg.room_id === document.querySelector('.room_id').value) {
          let friendMsgContainerDiv = document.createElement('div')
          friendMsgContainerDiv.className = 'friendMsgContainer'
          chatDiv.appendChild(friendMsgContainerDiv)

          let friendMsgP = document.createElement('p')
          friendMsgP.className = 'friendMsg'
          friendMsgP.innerHTML = msg.chat_ctnt
          friendMsgContainerDiv.appendChild(friendMsgP)

          let friendMsg_timeSmall = document.createElement('small')
          friendMsg_timeSmall.className = 'friendMsg_time'
          friendMsg_timeSmall.innerText = msg.chat_time
          friendMsgContainerDiv.appendChild(friendMsg_timeSmall)
          goToBottom()
        }
      }
    } else if (msg.type === 'ALARM') {
      getAlarmCount()
    }
  }
  socket.onerror = function (error) {
    console.log(`${error.message}`)
  }
  socket.onclose = function () {
    console.log('소켓 종료')
    socket = new WebSocket('ws://' + location.hostname + ':8091/socket')
  }
}

function sendAlarm(alarm_category, alarm_valuepk, alarm_sendto) {
    let params = {
      alarm_from: user_pk.value,
      alarm_sendto,
      alarm_category,
      alarm_valuepk,
      type: 'ALARM',
    }
    socket.send(JSON.stringify(params))
}

// 서버로 값을 보낼 때
const sendChatBtn = document.querySelector('button[name="send_btn"]')
const sendInput = document.querySelector('input[name="msg_input"]')
const send_id = document.querySelector('#chat_friendName')

sendChatBtn.addEventListener('click', () => {
  if(sendInput.value === '') {
    return
  }
  sendChat()
  goToBottom()
})
  
function sendChat() {
  const room_id = document.querySelector('.room_id')
  let params = {
    room_id: room_id.value,
    send_to: send_id.innerText.split('@')[1],
    from: document.querySelector('#temp_user').innerText,
    chat_ctnt: sendInput.value,
    chat_time: getTimeStamp(),
    type: 'CHAT',
  }
  socket.send(JSON.stringify(params))

  // socket에 보내고 난 뒤 채팅창 화면에 내가 보낸 쪽지 추가
  let myMsgContainerDiv = document.createElement('div')
  myMsgContainerDiv.className = 'myMsgContainer'
  chatDiv.appendChild(myMsgContainerDiv)

  let myMsgP = document.createElement('p')
  myMsgP.className = 'myMsg'
  myMsgP.innerHTML = params.chat_ctnt
  myMsgContainerDiv.appendChild(myMsgP)

  let myMsg_timeSmall = document.createElement('small')
  myMsg_timeSmall.className = 'myMsg_time'
  myMsg_timeSmall.innerText = getTimeStamp()
  myMsgContainerDiv.appendChild(myMsg_timeSmall)
  sendInput.value = ''
}

function enterKey(event) {
  if(event.keyCode === 13) {
    if(sendInput.value === '') {
      return false
    }else {
      sendChat()
      goToBottom()
      return true
    }
  }
}