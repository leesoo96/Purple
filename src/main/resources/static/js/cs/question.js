//question
let cs_question_titlebar = document.querySelectorAll('.cs_question_titlebar')
let cs_question_detail_close = document.querySelectorAll('.cs_question_detail')
for (let i = 0; i < cs_question_titlebar.length; i++) {
  let cs_question_titlebarEle = cs_question_titlebar[i]
  let cs_cmt_regEle = cs_question_titlebarEle.nextSibling.nextSibling.childNodes.item(
    11
  )

  cs_question_titlebarEle.addEventListener('click', function () {
    let cs_question_detail = this.nextSibling.nextSibling
    let question_view = this.childNodes.item(3).childNodes.item(3)

    //슬라이드
    if (cs_question_detail.style.height < '100px') {
      for (let j = 0; j < cs_question_detail_close.length; j++) {
        cs_question_titlebar[j].style.backgroundColor = 'rgb(255, 255, 255)'
        cs_question_titlebar[j].style.color = 'rgb(0, 0, 0)'
        cs_question_detail_close[j].style.height = '0em'
        cs_question_detail_close[j].style.padding = '0px'
      }

      let question_pk = cs_question_titlebarEle.dataset.pk
      fetch(`/updQuestionView?question_pk=${question_pk}`, {
        method: 'put',
      })
        .then(function (res) {
          return res.json()
        })
        .then((myJson) => {
          if (myJson.result == 1) {
            question_view.innerHTML = myJson.question_view
          }
        })

      cs_question_detail.style.height = `25em`
      cs_question_titlebarEle.style.backgroundColor = 'rgb(82, 0, 121)'
      cs_question_titlebarEle.style.color = 'rgb(255, 255, 255)'
      cs_question_detail.style.paddingTop = '1em'
      cs_question_detail.style.paddingBottom = '1em'
    } else if (cs_question_detail.style.height > '100px') {
      cs_question_detail.style.height = `0em`
      cs_question_titlebarEle.style.backgroundColor = 'rgb(255, 255, 255)'
      cs_question_titlebarEle.style.color = 'rgb(0, 0, 0)'
      cs_question_detail.style.padding = '0px'
    }
    //슬라이드 끝
    //댓글 등록
    cs_cmt_regEle.addEventListener('click', function () {
      if (!confirm('등록 하시겠습니까?')) {
        return
      }
      if (this.nextSibling.nextSibling.value === '') {
        alert('글을 입력하시오')
        this.nextSibling.nextSibling.focus()
        return
      }
      let param = {
        answer_pk: this.dataset.pk,
        answer_userpk: document.querySelector('#user_pk').value,
        answer_ctnt: this.nextSibling.nextSibling.value,
      }
      fetch('/question_cmt_reg', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(param),
      })
        .then((res) => res.json())
        .then(function (myJson) {
          location.href = `/question`
        })
    })
  })
}

//댓글 모달창
function openCLoseCmtModal(state) {
  var blackBgElem = document.querySelector('.cs_black_bg')
  blackBgElem.style.display = state
}

let cs_cmt_btn = document.querySelectorAll('.cs_cmt_btn')
for (let i = 0; i < cs_cmt_btn.length; i++) {
  let cs_cmt_btnEle = cs_cmt_btn[i]
  cs_cmt_btnEle.addEventListener('click', function () {
    let cs_cmt_vlew = this.parentNode.nextSibling.nextSibling
    cs_cmt_vlew.style.display = 'block'
    openCLoseCmtModal('block')
  })
}

let cs_modal_close = document.querySelectorAll('.cs_modal_close')
for (let i = 0; i < cs_modal_close.length; i++) {
  let cs_modal_closeEle = cs_modal_close[i]
  cs_modal_closeEle.addEventListener('click', function () {
    let cs_cmt_vlew = this.parentNode
    let cs_question_detail = cs_cmt_vlew.previousSibling.previousSibling
    cs_cmt_vlew.style.display = 'none'
    openCLoseCmtModal('none')
    cs_question_detail.style.height = `25em`
  })
}

//댓글 삭제
function answer_del_btn(answer_pk) {
  if (!confirm('삭제 하시겠습니까?')) {
    return
  }
  fetch(`/answer_del?answer_pk=${answer_pk}`, {
    method: 'delete',
  })
    .then(function (res) {
      return res.json()
    })
    .then(function (myJson) {
      console.log(myJson)
      if (myJson.result === 1) {
        //삭제 완료
        location.href = `question`
      } else {
        //삭제 실패
        alert('삭제 실패하였습니다.')
      }
    })
}

//문의사항 삭제\
function cs_del_btn(question_pk) {
  if (!confirm('삭제 하시겠습니까?')) {
    return
  }
  fetch(`/question_del?question_pk=${question_pk}`, {
    method: 'delete',
  })
    .then(function (res) {
      return res.json()
    })
    .then(function (myJson) {
      console.log(myJson)
      if (myJson.result === 1) {
        //삭제 완료
        location.href = `question`
      } else {
        //삭제 실패
        alert('삭제 실패하였습니다.')
      }
    })
}

//페이징
let pageFrm = document.querySelector('#pageFrm')
function pageClick(page) {
  pageFrm.page.value = page
  pageFrm.submit()
}
