'use strict';

// input객체에서 글자수를 제한 할 수 있는 함수(이름,자기소개,위치,웹 사이트)
function chkword(obj, maxlength) {
  var str = obj.value;          // 이벤트가 일어난 컨트롤의 value 값
  var str_length = str.length;  // 전체길이

  // 변수초기화 부분
  var max_length = maxlength; // 제한할 글자수 크기     
  var i = 0;  // for문에 사용   
  var ko_byte = 0;  // 한글일 경우 : 2 그 밖은 1을 더함  
  var li_len = 0; // substring하기 위해서 사용 
  var one_char = "";  // 한글자씩 검사한다  
  var str2 = "";  // 글자수를 초과하면 제한할수 글자전까지만 보여준다. 
         
  for (i = 0; i < str_length; i++) {
    // 한 글자 추출         
    one_char = str.charAt(i);             
    ko_byte++;         
  }           
  // 전체 크기가 max_length를 넘지않으면..         
  if (ko_byte <= max_length) {
    li_len = i + 1;         
  }            
  // 전체길이를 초과하면     
  if (ko_byte > max_length) {
    alert("최대 " + max_length + " 자까지 입력 가능합니다.");         
    str2 = str.substr(0, max_length);        
    obj.value = str2;     
  }
  obj.focus();   
}

// Save
function mypage_store(){
  location.href="mypage.html";
}

// 모달창 열고 닫기
function openCloseCmtModal(state){
	var modalWrapElem = document.querySelector('.modal_wrap')
	var blackBgElem = document.querySelector('.black_bg')

	modalWrapElem.style.display = state
	blackBgElem.style.display = state
}

// 프로필 수정
function profileMod(){
	openCloseCmtModal('block')
}

var mypage_Profile = document.querySelector('.mypage_mod')

if(mypage_Profile) {
	// 모달창 닫기 버튼
	var profileCloseElem = document.querySelector('.close')
	
	profileCloseElem.addEventListener('click', function(){
		openCloseCmtModal('none')
	})
}