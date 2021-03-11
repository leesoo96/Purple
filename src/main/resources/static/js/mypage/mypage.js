"use strict"
// 모달창 열고 닫기
function openCloseModal(modalname, state) {
  let modalElem = document.querySelector(`${modalname}`)
  let blackBgElem = document.querySelector("#modal_background")

  modalElem.style.display = `${state}`
  blackBgElem.style.display = `${state}`
}

const saveBtn = document.querySelector("#save_userModBtn")
const userModFrm = document.querySelector("#userModFrm")
saveBtn.addEventListener("click", () => {
  let mod_id = userModFrm.mod_id.value
  let mod_name = userModFrm.mod_name.value
  let mod_bio = userModFrm.mod_bio.value
  let mod_location = userModFrm.mod_location.value
  let mod_website = userModFrm.mod_website.value
  let mod_birth = userModFrm.mod_birth.value

  console.log(mod_website)
  console.log(mod_birth)
})
