'use strict';

// 각 아이콘 hover event
let home = document.querySelector('.home a')
let search = document.querySelector('.search a')
let news = document.querySelector('.news a')
let bookmark = document.querySelector('.bookmark a')
let cs = document.querySelector('.cs')

let menus = [home, search, news, bookmark, cs]
for (let i = 0; i < menus.length; i++) {
    menus[i].onmouseover = function () {
        menus[i].classList.add('hoverMenu')
    }
    menus[i].onmouseout = function () {
        menus[i].classList.remove('hoverMenu')
    }
}

// 고객센터 hover event
let cs_menus = document.querySelector('.cs_menus')
cs.addEventListener('mouseover', function () {
    cs_menus.style.display = 'block'
})
cs.addEventListener('mouseout', function () {
    cs_menus.style.display = 'none'
})

// 외부 클릭 시 user_modal 닫힘(main 제외)
// user_modal 이벤트
let only_modalClose = document.querySelector('.only_modalClose')
let temp_user = document.querySelector('.temp_user')
let user_modal = document.querySelector('.temp_user_modal')
temp_user.onclick = function () {
    user_modal.style.display = 'block'

    only_modalClose.onclick = function (e) {
        e.target === only_modalClose ? user_modal.style.display = 'none' : false
    }
}

// dm창 열고 닫기 이벤트 
function dm_view() {
    $('.dm_open').on('click', function() {
        $('.chat_view').stop(true).css({'display': 'block', 'opacity': 0}).animate({'opacity': 1}, 200)
    });
    $('.dm_close').on('click', function() {
        $('.chat_view').stop(true).animate({'opacity': 0}, 200, function() {
            $(this).css({'display': 'none'})
        });
    });
}
dm_view();

// + 버튼 클릭 시 대화 추가 알람 메시지 이벤트(테스트용 코드)
let add_chat_btn = document.querySelectorAll('.add_chat');
for (let i = 0; i < add_chat_btn.length; i++) {
    add_chat_btn[i].onclick = function () {
        alert('대화를 추가하시겠습니까?');
    }
}

// 친구목록 버튼과 대화목록 버튼 클릭 시 
let f_btn = document.querySelector('.friend_btn');
let c_btn = document.querySelector('.chat_btn');

let friend_list = document.querySelector('.friend_list');
let chat_list = document.querySelector('.chat_list');


f_btn.onclick = function () {
    friend_list.style.left = '0';
    friend_list.style.transition = '.5s';

    chat_list.style.left = '19em';
}

c_btn.onclick = function () {
    chat_list.style.left = '0';
    chat_list.style.transition = '.5s';

    friend_list.style.left = '-18em';
}

// 대화목록에서 각 대화 클릭 시 
let chat_msg = document.querySelector('.chat_msg');
let c_div = document.querySelectorAll('.chat_list div');

for (let i = 0; i < c_div.length; i++) {
    c_div[i].ondblclick = function () {
        chat_msg.style.right = '17px';
        chat_msg.style.transition = '.5s';
        
        chat_list.style.left = '-18em';
    }
}

// 채팅화면에서 화살표 이미지 클릭 시 이벤트 
let back = document.querySelector('.chat_msg img'); 
back.onclick = function () {
    chat_msg.style.right = '-17em';
    chat_list.style.left = '0';
}

