'use strict'

// 로그인/회원가입 css 위치
const loginFrmEle = document.querySelector('#loginFrm')
const registerFrmEle = document.querySelector('#registerFrm')
const btn_wrapEle = document.querySelector('#btn-wrap')
const togglebtn_span = document.querySelectorAll('.togglebtn span')
const errMSG = document.querySelector('#errMSG')
const oauth_button = document.querySelector('#oauth2button')
let findpw_open = document.querySelector('.findpw_btn')

function login() {
  togglebtn_span[0].style.color = '#fff'
  togglebtn_span[1].style.color = '#000'
  loginFrmEle.style.left = '50px'
  registerFrmEle.style.left = '480px'
  btn_wrapEle.style.left = '0'
  findpw_open.style.left = '15.9em'
  oauth_button.style.left = '0'
}

function register() {
  togglebtn_span[0].style.color = '#000'
  togglebtn_span[1].style.color = '#fff'
  loginFrmEle.style.left = '-400px'
  registerFrmEle.style.left = '50px'
  btn_wrapEle.style.left = '147px'
  findpw_open.style.left = '-6.1em'
  oauth_button.style.left = '-400px'
  errMSG.style.left = '-400px'
}

//이메일 형식
function email_check(email) {
  var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
  return email != '' && email != 'undefined' && regex.test(email)
}

//이름 형식
function CheckName(uid) {
  if (!/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,7}$/.test(uid)) {
    alert('이름은 한글로 2~7자리를 입력해야 합니다.')
    return false
  }
  return true
}

//비밀번호 형식
function CheckPassword(uid, upw) {
  if (
    !/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/.test(
      upw
    )
  ) {
    alert(
      '비밀번호는 숫자와 영문자와 특수문자 조합으로 8~20자리를 사용해야 합니다.'
    )
    return false
  }
  var chk_num = upw.search(/[0-9]/g)
  var chk_eng = upw.search(/[a-z]/gi)
  if (chk_num < 0 || chk_eng < 0) {
    alert('비밀번호는 숫자와 영무자를 혼용하여야 합니다.')
    return false
  }
  if (/(\w)\1\1\1/.test(upw)) {
    alert('비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.')
    return false
  }
  if (upw.search(uid) > -1) {
    alert('ID가 포함된 비밀번호는 사용하실 수 없습니다.')
    return false
  }
  return true
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
      user_id: user_id.value,
      user_pw: user_pw.value,
      remember_Id: remember_Id.checked,
    }
    fetch('/login', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => {
        res.json()
      })
      .then(function (myJson) {})
  }
}
//이메일 형식
function email_check(email) {
  var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
  return email != '' && email != 'undefined' && regex.test(email)
}

//아이디 형식
function CheckName(uid) {
  if (!/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,7}$/.test(uid)) {
    alert('이름은 한글로 2~7자리를 입력해야 합니다.')
    return false
  }
  return true
}

//비밀번호 형식
function CheckPassword(uid, upw) {
  if (
    !/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/.test(
      upw
    )
  ) {
    alert(
      '비밀번호는 숫자와 영문자와 특수문자 조합으로 8~20자리를 사용해야 합니다.'
    )
    return false
  }
  var chk_num = upw.search(/[0-9]/g)
  var chk_eng = upw.search(/[a-z]/gi)
  if (chk_num < 0 || chk_eng < 0) {
    alert('비밀번호는 숫자와 영무자를 혼용하여야 합니다.')
    return false
  }
  if (/(\w)\1\1\1/.test(upw)) {
    alert('비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.')
    return false
  }
  if (upw.search(uid) > -1) {
    alert('ID가 포함된 비밀번호는 사용하실 수 없습니다.')
    return false
  }
  return true
}

// 회원가입
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
  if (!pw_chk()) {
    return
  }
  ajax()

  function ajax() {
    if (!CheckName(user_nameEle.value)) {
      user_nameEle.focus()
      return
    } else if (!CheckPassword(user_idEle.value, user_pwEle.value)) {
      alert('비밀번호를 입력해주세요')
      user_pwEle.focus()
      return
    } else if (!email_check(user_emailEle.value)) {
      alert('이메일을 형식에 맞게 입력해주세요')
      user_emailEle.focus()
      return
    } else if (user_birthEle.value === '') {
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
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then(function (myJson) {
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
  if (user_idEle.value == '') {
    alert('아이디를 입력해주세요')
    user_idEle.focus()
    return
  }
  ajax()
  function ajax() {
    let idEle_value = user_idEle.value

    fetch(`/join/${idEle_value}`)
      .then((res) => res.json())
      .then(function (myJson) {
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

// 비밀번호 찾기 modal 창 열고 닫기
let findpw_modal = document.querySelector('.findpw_modal')

findpw_open.onclick = function () {
  findpw_modal.style.display = 'block'
}

const findpw_close = document.querySelector('.findpw_quit')
findpw_close.onclick = function () {
  findpw_modal.style.display = 'none'
}

// 비밀번호 찾기
const find_btn = document.querySelector('.find_btn')
let find_pw_id = document.querySelector('.findpw_id')
let find_pw_mail = document.querySelector('.findpw_email')

find_btn.onclick = function () {
  if (find_pw_id.value == '' || find_pw_mail.value == '') {
    return false
  }
  let param = {
    user_id: find_pw_id.value,
    user_email: find_pw_mail.value,
  }
  fetch('/findpw', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(param),
  })
    .then((res) => res.json())
    .then((myJson) => {
      findpw_proc(myJson.result)
    })
}

function findpw_proc(result) {
  switch (result) {
    case 1:
      alert('아이디가 없거나 잘못 입력하셨습니다.다시 확인해주세요.')
      find_pw_id.focus()
      break

    case 2:
      alert('메일 주소가 맞는지 다시 확인해주세요.')
      find_pw_mail.focus()
      break

    case 3:
      alert('메일이 발송되었습니다.메일함을 확인해주세요.')
      break

    case 4:
      alert('메일 형식이 올바르지 않습니다.다시 확인해주세요.')
      find_pw_mail.focus()
      break
  }
}
