// 모달창 열고 닫기
function openCloseModal(modalname, state) {
  let modalElem = document.querySelector(`${modalname}`)
  let blackBgElem = document.querySelector('#modal_background')

  modalElem.style.display = `${state}`
  blackBgElem.style.display = `${state}`

  check_state = 0
}

// 프로필 이미지 업로드 & 미리보기
const mod_userimg = document.querySelector('#mod_userimg')

mod_userimg.addEventListener('change', (event) => {
  let reader = new FileReader()

  reader.onload = (event) => {
    let img = document.createElement('img')
    img.src = event.target.result
    mod_userimg.querySelector('img').replaceWith(img)
  }

  reader.readAsDataURL(event.target.files[0])
})

// 백그라운드 이미지 업로드 & 미리보기
const mod_userbackground = document.querySelector('#mod_userbackground')

mod_userbackground.addEventListener('change', (event) => {
  let reader = new FileReader()

  reader.onload = (event) => {
    let img = document.createElement('img')
    img.src = event.target.result
    mod_userbackground.querySelector('img').replaceWith(img)
  }
  reader.readAsDataURL(event.target.files[0])
})
const userModFrm = document.querySelector('#userModFrm')
// ID 중복검사
let check_state = 0
const id_chekBtn = document.querySelector('button[name="id_check"]')
id_chekBtn.addEventListener('click', () => {
  let mod_id = userModFrm.mod_id.value
  if (mod_id === '' || mod_id === null) {
    alert('변경할 아이디를 입력해주세요')
    userModFrm.mod_id.focus()
    return
  }
  ajax()
  function ajax() {
    fetch(`/join/${mod_id}`)
      .then((res) => res.json())
      .then(function (myJson) {
        if (myJson.result === 1) {
          alert('이미 존재하는 아이디입니다.')
          check_state = 0
        } else {
          alert('변경 가능한 아이디입니다.')
          check_state = 1
        }
      })
  }
})
// 사용자 정보 변경
const userMod_contentEle = document.querySelector('#userMod_content')
const saveBtn = document.querySelector('#save_userModBtn')
const user_pkInput = document.querySelector('#user_pk')
const user_profileimg = document.querySelector('#mod_img')
const user_backgroundimg = document.querySelector('#mod_background')

saveBtn.addEventListener('click', () => {
  // 아이디 변경을 할 때 중복검사 여부 확인
  if (userModFrm.mod_id.value) {
    if (check_state === 0) {
      alert('아이디 중복체크를 해주세요')
      return
    }
  }

  if (confirm('정보를 변경하시겠습니까?')) {
    ajax()
  }
  function ajaxProfileImg() {
    return new Promise(function (resolve) {
      var formData = new FormData()
      formData.append('img', user_profileimg.files[0])
      fetch('/mypage/profile_img', {
        method: 'post',
        body: formData,
      })
        .then((res) => res.json())
        .then((myJson) => {
          resolve(myJson.result)
        })
    })
  }
  function ajaxBackgroundImg() {
    return new Promise(function (resolve) {
      var formData = new FormData()
      formData.append('img', user_backgroundimg.files[0])
      fetch('/mypage/background_img', {
        method: 'post',
        body: formData,
      })
        .then((res) => res.json())
        .then((myJson) => {
          resolve(myJson.result)
        })
    })
  }

  async function ajax() {
    let profile_img = await ajaxProfileImg()
    let background_img = await ajaxBackgroundImg()
    let params = {
      user_pk: user_pkInput.value,
      user_profileimg: profile_img,
      user_backgroundimg: background_img,
      user_id: userModFrm.mod_id.value,
      user_name: userModFrm.mod_name.value,
      user_bio: userModFrm.mod_bio.value,
      user_location: userModFrm.mod_location.value,
      user_website: userModFrm.mod_website.value,
      user_birth: userModFrm.mod_birth.value,
    }
    fetch(`/mypage/mod_userinfo`, {
      method: 'put',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result === 1) {
          alert('회원정보 수정이 완료되었습니다.')
          location.href = `/logout`
        } else {
          alert('회원정보 수정이 실패했습니다.')
          openCloseModal('#userMod_container', 'none')
        }
      })
  }
})

const userPwModFrm = document.querySelector('form[name="userPwModFrm"]')
//현재 비밀번호 검사
function pw_check(user_pk, user_pw) {
  let isTrue = true
  fetch(`/mypage/pw_check`, {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      user_pk: user_pk,
      user_pw: user_pw,
    }),
  })
    .then((res) => res.json())
    .then((myJson) => {
      if (myJson.result !== 1) {
        alert('현재 비밀번호가 틀렸습니다. 다시 확인해 주세요')
        userPwModFrm.user_pw.value = ''
        isTrue = false
      } else {
        isTrue = true
      }
    })
  return Boolean(isTrue)
}
//비밀번호 확인 검사
const mod_chkpw = userPwModFrm.mod_chkpw
mod_chkpw.addEventListener('change', () => {
  check_modpw()
  function check_modpw() {
    if (userPwModFrm.mod_pw.value !== mod_chkpw.value) {
      alert('비밀번호가 일치하지 않습니다!')
      mod_chkpw.value = ''
      mod_chkpw.focus()
      return false
    }
  }
})

//사용자 비밀번호 변경
const pwModBtn = document.querySelector('button[name="pwModBtn"]')

pwModBtn.addEventListener('click', () => {
  ajax()
  function ajax() {
    let user_pk = user_pkInput.value
    let user_pw = userPwModFrm.user_pw.value
    let mod_pw = userPwModFrm.mod_pw.value

    if (!pw_check(user_pk, user_pw)) {
      return
    }

    if (mod_pw === '' || mod_pw === null) {
      alert('변경할 비밀번호를 입력해 주세요')
      userPwModFrm.mod_pw.focus()
      return
    }

    if (mod_chkpw.value === '' || mod_chkpw.value === null) {
      alert('비밀번호 확인란을 입력해 주세요')
      mod_chkpw.focus()
      return
    }

    fetch(`/mypage/mod_userpw`, {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        user_pk: user_pk,
        user_pw: mod_pw,
      }),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result === 1) {
          alert('비밀번호가 변경 되었습니다.')
          location.href = `/logout`
          return
        }
      })
  }
})

// 프로필 수정 - 주소 (다음 주소 API 사용)
function postCode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 주소 변수
      var addr = ''

      // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다
      if (data.userSelectedType === 'R') {
        // 도로명 주소 선택
        addr = data.roadAddress
      } else {
        // 지번 주소 선택
        addr = data.jibunAddress
      }

      userMod_contentEle.querySelector(
        'input[name="mod_location"]'
      ).value = addr
    },
  }).open()
}
