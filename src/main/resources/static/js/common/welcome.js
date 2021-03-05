'use strict';

// 로그인/회원가입 css 위치 
const loginFrmEle = document.querySelector('#loginFrm');
const registerFrmEle = document.querySelector('#registerFrm');
const btn_wrapEle = document.querySelector('#btn-wrap');
const togglebtn_span = document.querySelectorAll('.togglebtn span')
const errMSG = document.querySelector('#errMSG')

function login() {
	togglebtn_span[0].style.color = '#fff'
	togglebtn_span[1].style.color = '#000'
	loginFrmEle.style.left = "50px"
	registerFrmEle.style.left = "480px"
	btn_wrapEle.style.left = "0"
}

function register() {
	togglebtn_span[0].style.color = '#000'
	togglebtn_span[1].style.color = '#fff'
	loginFrmEle.style.left = "-400px"
	registerFrmEle.style.left = "50px"
	btn_wrapEle.style.left = "147px"
	errMSG.style.left = "-400px"
}

// 로그인
const loginBtn = document.querySelector('#login_Btn')
let user_id = loginFrmEle.user_id
let user_pw = loginFrmEle.user_pw
let remember_Id = loginFrm.remember_userId

loginBtn.addEventListener('click', () => {
	loginFunc()
})	

function loginFunc() {
	
	ajax()
	
	function ajax() {
		let param = {
			user_id : user_id.value,
			user_pw : user_pw.value,
			remember_Id : remember_Id.checked
		}	
		
		fetch('/login', {
			method: 'post',
			headers : {
				'Content-Type': 'application/json',
			},
			body : JSON.stringify(param)
		})
		.then(res => res.json())
		.then(function(myJson) {
			console.log(myJson)
		})
	}
}

// 회원가입
// 변수 선언
const joinBtn = document.querySelector('#join_btn')

let overlap_state = 0
let user_idEle = registerFrmEle.user_id
let user_nameEle = registerFrmEle.user_name
let user_pwEle = registerFrmEle.user_pw
let user_pwChkEle = registerFrmEle.user_pw_check
let user_emailEle = registerFrmEle.user_email
let user_birthEle = registerFrmEle.user_birth

joinBtn.addEventListener('click', () => {
	joinProc()
})

function joinProc() {
	if (overlap_state === 0) {
		alert('아이디 중복체크를 해주세요')
		return
	}
	if(!pw_chk()){
		return
	}
	ajax()

	function ajax() {
		if(user_nameEle.value === '') {
			alert('성함을 입력해주세요')
			user_nameEle.focus()
			return
		}
		else if(user_pwEle.value === '') {
			alert('비밀번호를 입력해주세요')
			user_pwEle.focus()
			return
		}
		else if(user_emailEle.value === '') {
			alert('이메일을 입력해주세요')
			user_emailEle.focus()
			return
		}
		else if(user_birthEle.value === '') {
			alert('생년월일을 입력해주세요')
			user_birthEle.focus()
			return
		}
		
		let param = {
			user_id: user_idEle.value,
			user_name: user_nameEle.value,
			user_pw: user_pwEle.value,
			user_email: user_emailEle.value,
			user_birth: user_birthEle.value,
		}
		fetch('/join', {
			method: 'post',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(param)
		})
			.then((res) => res.json())
			.then(function(myJson) {
				proc(myJson)
			})
	}

	function proc(myJson) {
		if (myJson.result === 0) {
			alert('회원가입에 실패하였습니다.')
			return
		}
		alert('회원가입을 축하합니다!')
		location.href = '/'
	}
}
function pw_chk() {
	if (user_pwEle.value !== user_pwChkEle.value) {
		alert('비밀번호가 일치하지않습니다!')
		user_pwChkEle.value = ''
		user_pwChkEle.focus()
		return false
	}
	return true
}

// 중복확인 ////////////////////////////////////////////
let chkFail = document.querySelector('.ChkFail')
let chkSuccess = document.querySelector('.ChkSuccess')

// 아이디 중복확인
const overlap_idBtn = document.querySelector('#overlap_id')

overlap_idBtn.addEventListener('click', () => {
	ajax()
	function ajax() {
		let idEle_value = user_idEle.value

		fetch(`/join/${idEle_value}`)
			.then(res => res.json())
			.then(function(myJson) {
				if (myJson.result === 1) {
					chkSuccess.style.display = 'none'
					chkFail.style.display = 'inline'
					overlap_state = 0
				} else {
					chkFail.style.display = 'none'
					chkSuccess.style.display = 'inline'
					overlap_state = 1
				}
			})
	}
})
