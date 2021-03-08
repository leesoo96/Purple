//본문 참조 기능

//notice

let cs_notice_titlebar = document.querySelectorAll(".cs_notice_titlebar");
let cs_notice_detail_close = document.querySelectorAll(".cs_notice_detail");

for (let i = 0; i < cs_notice_titlebar.length; i++) {
  let cs_notice_titlebarEle = cs_notice_titlebar[i];
  cs_notice_titlebarEle.addEventListener("click", function () {
    let cs_notice_detail = this.nextSibling.nextSibling;
    if (cs_notice_detail.style.height < "100px") {
      for (let j = 0; j < cs_notice_detail_close.length; j++) {
        cs_notice_titlebar[j].style.backgroundColor = "rgb(255, 255, 255)";
        cs_notice_titlebar[j].style.color = "rgb(0, 0, 0)";
        cs_notice_detail_close[j].style.height = "0em";
        cs_notice_detail_close[j].style.padding = "0px";
      }
      let notice_pk = cs_notice_titlebarEle.dataset.pk;
      fetch(`/updNoticevieView?notice_pk=${notice_pk}`, {
        method: "put",
      }).then(function (res) {
        return res.json();
      });
      for (let i = 0; i <= 25; i++) {
        setTimeout(function () {
          cs_notice_detail.style.height = `${i}em`;
        });
      }
      cs_notice_titlebarEle.style.backgroundColor = "rgb(82, 0, 121)";
      cs_notice_titlebarEle.style.color = "rgb(255, 255, 255)";
      cs_notice_detail.style.paddingTop = "1em";
      cs_notice_detail.style.paddingBottom = "1em";
    } else if (cs_notice_detail.style.height > "100px") {
      for (let i = 25; i >= 0; i--) {
        setTimeout(function () {
          cs_notice_detail.style.height = `${i}em`;
        });
      }
      cs_notice_titlebarEle.style.backgroundColor = "rgb(255, 255, 255)";
      cs_notice_titlebarEle.style.color = "rgb(0, 0, 0)";
      cs_notice_detail.style.padding = "0px";
      //location.href = `notice`;
    }
  });
}

//공지사항 삭제\
function cs_del_btn(notice_pk) {
  if (alert("삭제 하시겠습니까?")) {
    return;
  }
  console.log(notice_pk);
  fetch(`/notice_del?notice_pk=${notice_pk}`, {
    method: "delete",
  })
    .then(function (res) {
      return res.json();
    })
    .then(function (myJson) {
      console.log(myJson);
      if (myJson.result === 1) {
        //삭제 완료
        location.href = `notice`;
      } else {
        //삭제 실패
        alert("삭제 실패하였습니다.");
      }
    });
}

/*
//페이징
let totalData = 100; // 총 데이터 수
let dataPerPage = 10; // 한 페이지에 나타낼 데이터 수
let pageCount = 10; // 한 화면에 나타낼 페이지 수
let totalPage = Math.ceil(totalData / dataPerPage); // 총 페이지 수
for (let index = 1; index < totalPage; index++) {
	//버튼 추가
	let cs_paging = document.querySelector("#cs_paging");
	cs_paging.innerHTML += `<a class="totalPage">${index}</a>`;
}
*/
