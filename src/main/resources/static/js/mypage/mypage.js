'use strict'
// 모달창 열고 닫기
function openCloseModal(modalname, state) {
  let modalElem = document.querySelector(`${modalname}`)
  let blackBgElem = document.querySelector('#modal_background')

  modalElem.style.display = `${state}`
  blackBgElem.style.display = `${state}`
}

// 프로필 이미지 업로드 & 미리보기
const mod_userimg = document.querySelector('#mod_userimg')

mod_userimg.addEventListener('change', (event) => {
  let reader = new FileReader()

  reader.onload = (event) => {
    let img = document.createElement('img')
    img.src = event.target.result
    mod_userimg.querySelector('img').remove()
    mod_userimg.querySelector('label').appendChild(img)
    console.log(img)
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
    mod_userbackground.querySelector('img').remove()
    mod_userbackground.querySelector('label').append(img)
    console.log(img)
  }
  reader.readAsDataURL(event.target.files[0])
})
const userModFrm = document.querySelector('#userModFrm')
// ID 중복검사
let check_state = 0
const id_chekBtn = document.querySelector('input[name="id_check"]')
id_chekBtn.addEventListener('click', () => {
  ajax()
  function ajax() {
    let mod_id = userModFrm.mod_id.value

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
// save
const userMod_contentEle = document.querySelector('#userMod_content')
const saveBtn = document.querySelector('#save_userModBtn')

saveBtn.addEventListener('click', () => {
  let mod_id = userModFrm.mod_id.value
  let mod_name = userModFrm.mod_name.value
  let mod_bio = userModFrm.mod_bio.value
  let mod_location = userModFrm.mod_location.value
  let mod_website = userModFrm.mod_website.value
  let mod_birth = userModFrm.mod_birth.value

  if (mod_id) {
    if (check_state === 0) {
      alert('아이디 중복체크를 해주세요')
      return
    }
    return
  }
  console.log(mod_website)
  console.log(mod_birth)
})
