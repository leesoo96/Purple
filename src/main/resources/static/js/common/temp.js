"use strict"

// 각 아이콘 hover event
let home = document.querySelector("#home a")
let search = document.querySelector("#search a")
let news = document.querySelector("#news a")
let bookmark = document.querySelector("#bookmark a")

let menus = [home, search, news, bookmark]
for (let i = 0; i < menus.length; i++) {
  menus[i].onmouseover = function () {
    menus[i].classList.add("hoverMenu")
  }
  menus[i].onmouseout = function () {
    menus[i].classList.remove("hoverMenu")
  }
}

// 고객센터 hover event
let cs_menus = document.querySelector("#cs_menus")
cs.addEventListener("mouseover", function () {
  cs_menus.style.display = "block"
})
cs.addEventListener("mouseout", function () {
  cs_menus.style.display = "none"
})

// 외부 클릭 시 user_modal 닫힘(main 제외)
// user_modal 이벤트
let only_modalClose = document.querySelector(".only_modalClose")
let temp_user = document.querySelector("#temp_user")
let user_modal = document.querySelector("#temp_user_modal")
temp_user.onclick = function () {
  user_modal.style.display = "block"

  only_modalClose.onclick = function (e) {
    e.target === only_modalClose ? (user_modal.style.display = "none") : false
  }
}

// dm창 열고 닫기 이벤트
function dm_view() {
  let dm_open = document.querySelector(".dm_open")
  let dm_close = document.querySelector(".dm_close")
  let chat_view = document.querySelector(".chat_view")

  dm_open.addEventListener("click", function () {
    chat_view.style.visibility = "visible"
    chat_view.style.opacity = "1"
  })

  dm_close.addEventListener("click", function () {
    chat_view.style.visibility = "hidden"
    chat_view.style.opacity = "0"
  })
}
dm_view()

// 친구목록 버튼과 대화목록 버튼 클릭 시
let f_btn = document.querySelector("#friend_btn")
let c_btn = document.querySelector("#chat_btn")

let friend_list = document.querySelector(".friend_list")
let chat_list = document.querySelector(".chat_list")

f_btn.onclick = function () {
  f_btn.style.color = "#7901a1"
  c_btn.style.color = "#525252"

  friend_list.style.left = "0"
  friend_list.style.transition = ".5s"

  chat_list.style.left = "27em"

  chat_msg.style.right = "-23em"
  chat_msg.style.transition = ".5s"
}

c_btn.onclick = function () {
  f_btn.style.color = "#525252"
  c_btn.style.color = "#7901a1"

  chat_list.style.left = "0"
  chat_list.style.transition = ".5s"

  friend_list.style.left = "-27em"

  chat_msg.style.right = "-23em"
  chat_msg.style.transition = ".5s"
}

// 친구 아이디 클릭 시 차단하기 표시 & 숨김
let friend_info = document.querySelectorAll("#friend_info")
let friend_block = document.querySelectorAll("#friend_block")
for (let i = 0; i < friend_info.length; i++) {
  friend_info[i].onclick = function () {
    if (friend_block[i].style.visibility === "visible") {
      friend_block[i].style.visibility = "hidden"
    } else {
      friend_block[i].style.visibility = "visible"
    }
  }
}

// 대화목록에서 각 대화 클릭 시
let chat_msg = document.querySelector(".chat_msg")
let c_div = document.querySelectorAll(".chat_list div")

for (let i = 0; i < c_div.length; i++) {
  c_div[i].onclick = function () {
    chat_msg.style.right = "17px"
    chat_msg.style.transition = ".5s"

    chat_list.style.left = "-24em"
  }
}

// feed_content 클릭 이벤트
let feed_content = document.querySelector(".feed_content")
let feed_overlay = document.querySelector(".feed_overlay")
feed_content.onclick = function () {
  feed_overlay.style.display = "block"
}

const feed_overlay_close = document.querySelector(".fa-times")
feed_overlay_close.onclick = function () {
  feed_overlay.style.display = "none"
}

// 채팅 - 친구목록 확인
const friend_btn = document.querySelector("#friend_btn")
let fList_table = document.querySelector(".friend_list table")

friend_btn.addEventListener("click", () => {
  getFriendListFunc()
})

function getFriendListFunc() {
  fetch(`/friendList`)
    .then((res) => res.json())
    .then((myJson) => {
      friend_list(myJson)
    })
}

let fList_span = document.querySelector(".friend_list span")
function friend_list(myJson) {
  if (myJson.length === 0) {
    let fList_div = document.createElement("div")
    fList_span.append(fList_div)
    fList_div.innerHTML = "친구가 없습니다ㅠ"
    return
  }

  for (let i = 0; i < myJson.length; i++) {
    let fList_tr = document.createElement("tr")

    let name_td = document.createElement("td")
    name_td.innerText = MyJson[i].user_id
    fList_tr.appendChild(name_td)
    let profileimg_td = document.createElement("td")
    let bio_td = document.createElement("td")

    fList_table.appendChild(fList_tr)
  }
}
