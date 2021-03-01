'use strict';

// 모달창 열고 닫기
function openClosePwModal(state){
	var modalPwElem = document.querySelector('.modal_pw')
	var pwBgElem = document.querySelector('.pw_bg')

	modalPwElem.style.display = state
	pwBgElem.style.display = state
}

// 비밀번호 변경
function popupCenter(){
	openClosePwModal('block')
}

var mypage_PW = document.querySelector('.mypage_change')

if(mypage_PW) {
	var pwCloseElem = document.querySelector('.modal_close')	// 모달창 닫기 버튼
	
	pwCloseElem.addEventListener('click', function(){
		openClosePwModal('none')
	})
}

// 비밀번호 변경 전, 확인
function chkPw(){
	var frm = document.querySelector('#frm');
	
	if(frm.current_pw.value == ''){
		alert('기존 비밀번호를 작성해 주세요.');
		frm.current_pw.focus();
		return false;
	}else if(frm.user_pw.value == ''){
		alert('새로운 비밀번호를 작성해 주세요.');
		frm.user_pw.focus();
		return false;
	} else if(frm.user_pw.value != frm.chk_user_pw.value){
		alert('변경/확인 비밀번호를 확인해 주세요.');
		frm.chk_user_pw.focus();
		return false;
	}
	return true;
}