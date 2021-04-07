//본문 참조 기능

//notice

let cs_notice_titlebar = document.querySelectorAll('.cs_notice_titlebar')
let cs_notice_detail_close = document.querySelectorAll('.cs_notice_detail')

for (let i = 0; i < cs_notice_titlebar.length; i++) {
  let cs_notice_titlebarEle = cs_notice_titlebar[i]
  cs_notice_titlebarEle.addEventListener('click', function () {
    let cs_notice_detail = this.nextSibling.nextSibling
    let notice_view = this.childNodes.item(3).childNodes.item(3)
    if (cs_notice_detail.style.height < '100px') {
      for (let j = 0; j < cs_notice_detail_close.length; j++) {
        cs_notice_titlebar[j].style.backgroundColor = 'rgb(255, 255, 255)'
        cs_notice_titlebar[j].style.color = 'rgb(0, 0, 0)'
        cs_notice_detail_close[j].style.height = '0em'
        cs_notice_detail_close[j].style.padding = '0px'
      }
      let notice_pk = cs_notice_titlebarEle.dataset.pk
      fetch(`/updNoticeView?notice_pk=${notice_pk}`, {
        method: 'put',
      })
        .then(function (res) {
          return res.json()
        })
        .then((myJson) => {
          if (myJson.result == 1) {
            notice_view.innerHTML = myJson.notice_view
          }
        })
      cs_notice_detail.style.height = `25em`
      cs_notice_titlebarEle.style.backgroundColor = 'rgb(82, 0, 121)'
      cs_notice_titlebarEle.style.color = 'rgb(255, 255, 255)'
      cs_notice_detail.style.paddingTop = '1em'
      cs_notice_detail.style.paddingBottom = '1em'
    } else if (cs_notice_detail.style.height > '100px') {
      cs_notice_detail.style.height = `0em`
      cs_notice_titlebarEle.style.backgroundColor = 'rgb(255, 255, 255)'
      cs_notice_titlebarEle.style.color = 'rgb(0, 0, 0)'
      cs_notice_detail.style.padding = '0px'
      //location.href = `notice`;
    }
  })
}

//공지사항 삭제\
function cs_del_btn(notice_pk) {
  if (!confirm('삭제 하시겠습니까?')) {
    return
  }
  fetch(`/notice_del?notice_pk=${notice_pk}`, {
    method: 'delete',
  })
    .then(function (res) {
      return res.json()
    })
    .then(function (myJson) {
      if (myJson.result === 1) {
        //삭제 완료
        location.href = `notice`
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
