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

function alertCheck() {
  console.log(check_state)
  if(check_state === 1) {
    alert('아이디 중복 검사를 다시 해주세요.')
    check_state = 0
    userModFrm.mod_id.focus()
  }
}
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
      user_email: userModFrm.mod_email.value,
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
    
    if (!CheckPassword(mod_pw)) {
      mod_pw.focus()
    return
    }
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

let page_count = 0

function previousImg(e) {
  const feed_imgListDiv = e.parentNode.firstChild.nextSibling
  let first_img = feed_imgListDiv.firstChild
  while (first_img.nodeType !== 1) {
    first_img = first_img.nextSibling
  }
  let last_img = feed_imgListDiv.lastChild
  while (last_img.nodeType !== 1) {
    last_img = last_img.previousSibling
  }
  first_img.before(last_img)
}

function nextImg(e) {
  const feed_imgListDiv = e.parentNode.firstChild.nextSibling
  let first_img = feed_imgListDiv.firstChild
  while (first_img.nodeType !== 1) {
    first_img = first_img.nextSibling
  }
  let last_img = feed_imgListDiv.lastChild
  while (last_img.nodeType !== 1) {
    last_img = last_img.previousSibling
  }
  last_img.after(first_img)
}

// feed scroll
const windowHeight = window.innerHeight // 현재 보이는 창 높이

document.addEventListener('DOMContentLoaded', async function () { // HTML과 script가 로드된 시점에 발생하는 이벤트.
    await makeFeedAjax(1, page_count).then((myJson) => {
      makeFeed(myJson)
    })
    page_count++
    await ajax()
function ajax() {
    if(document.body.scrollHeight <= windowHeight) {
    makeFeedAjax(1, page_count).then((myJson) => {
      makeFeed(myJson)
    })
    page_count++
  }
}
})

document.addEventListener('scroll', () => {
  let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
  let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이

  if (scrollLocation + windowHeight >= fullHeight) {
    makeFeedAjax(1, page_count).then((myJson) => {
      makeFeed(myJson)
    })
    page_count++
  }
})

// const category = document.querySelector('select[name="feed"]')
function makeFeedAjax(category, page_count) {
    return new Promise(function (resolve) {
      let params = {
        category: category,
        page_count: page_count
      }
      fetch('/mypage', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(params), 
      }).then((res) => 
        res.json()
      ).then(function (myJson) {
        resolve(myJson)
      })
    })
}
const feedEle = document.querySelector('#feed')
function makeFeed(myJson){
  if(myJson.result.length === 0) {
    page_count--
    return
  }
    for(let i = 0; i < myJson.result.length; i++){
    // feed_container 생성
    let feed_containerEle = document.createElement('div')
    feed_containerEle.className = 'feed_container'
    
    // feedtitle 생성
    let feed_titleEle = document.createElement('div')
    feed_titleEle.className = 'feed_title'
    feed_containerEle.appendChild(feed_titleEle)
    let imgEle = document.createElement('img')
    imgEle.src = `/resources/img/common/basic_profile.png`
    if(myJson.result[i].user_profileimg !== null) {
      imgEle.src = `${myJson.result[i].user_profileimg}`
    }
    feed_titleEle.appendChild(imgEle)

    let user_idSpan = document.createElement('span')
    user_idSpan.innerText = `${myJson.result[i].user_id}`
    let user_idA = document.createElement('a')
    user_idA.setAttribute('href', `/userpage/${myJson.result[i].user_id}`)
    user_idA.appendChild(user_idSpan)
    feed_titleEle.appendChild(user_idA)

    let feed_writedateSpan = document.createElement('span')
    feed_writedateSpan.innerText = `${myJson.result[i].feed_writedate}`
    feed_titleEle.appendChild(feed_writedateSpan)

    if(myJson.result[i].user_pk === myJson.result[i].feed_userpk) {
      let feedMenuI = document.createElement('i')
      feedMenuI.className = 'fas fa-ellipsis-h'
      feedMenuI.setAttribute('onclick', 'openCloseMenu(this)')
      feed_titleEle.appendChild(feedMenuI)

      let feedMenuUl = document.createElement('ul')
      feedMenuUl.className = 'feedMenu'
      feedMenuUl.style.display = 'none'
      feedMenuI.appendChild(feedMenuUl)

      let feedMenuLi1 = document.createElement('li')
      feedMenuLi1.className = 'feedLi'
      feedMenuLi1.innerHTML = '삭제'
      feedMenuLi1.setAttribute('onclick', `delFeed(${myJson.result[i].feed_pk})`)
      feedMenuUl.appendChild(feedMenuLi1)
    }
    
    // 이미지
    if(myJson.result[i].media_url.length > 0) {
      let feed_imgDiv = document.createElement('div')
      feed_imgDiv.className = 'feed_img'
      feed_containerEle.appendChild(feed_imgDiv)

      let previousDiv = document.createElement('div')
      previousDiv.className = 'previous'
      previousDiv.setAttribute('onclick', 'previousImg(this)')
      feed_imgDiv.appendChild(previousDiv)

      let previousI = document.createElement('i')
      previousI.className = 'fas fa-chevron-left'
      previousDiv.appendChild(previousI)

      let feed_imgListDiv = document.createElement('div')
      feed_imgListDiv.className = 'feed_imgList'
      for(let j =0; j < myJson.result[i].media_url.length; j++) {
        let img = document.createElement('img')
        img.src = `${myJson.result[i].media_url[j].media_url}`
        feed_imgListDiv.appendChild(img)
      }
      feed_imgDiv.appendChild(feed_imgListDiv)

      let nextDiv = document.createElement('div')
      nextDiv.className = 'next'
      nextDiv.setAttribute('onclick', 'nextImg(this)')
      feed_imgDiv.appendChild(nextDiv)

      let nextI = document.createElement('i')
      nextI.className = 'fas fa-chevron-right'
      nextDiv.appendChild(nextI)
    }
    
    let feed_contentDiv = document.createElement('div')
    feed_contentDiv.className = 'feed_content'
    feed_containerEle.appendChild(feed_contentDiv)
    if(myJson.result[i].hashtag_ctnt.length > 0) {
      for(let k=0; k < myJson.result[i].hashtag_ctnt.length; k++) {
        let hashtagA = document.createElement('a')
        let hashtag_ctnt = `${myJson.result[i].hashtag_ctnt[k].hashtag_ctnt}`
        hashtag_ctnt = hashtag_ctnt.split('#')[1]
        hashtagA.href = '/search/' + hashtag_ctnt
        hashtagA.innerText = myJson.result[i].hashtag_ctnt[k].hashtag_ctnt
        feed_contentDiv.appendChild(hashtagA)
      }
    }
    let detailDiv = document.createElement('div')
    detailDiv.setAttribute('onclick', `feedDetail(this, ${myJson.result[i].feed_pk})`)
    feed_contentDiv.appendChild(detailDiv)

    let feed_ctntP = document.createElement('p')
    feed_ctntP.innerText = `${myJson.result[i].feed_ctnt}`
    detailDiv.appendChild(feed_ctntP)
    
    
    feed_containerEle.appendChild(feed_contentDiv)
    
    let feed_functionbarEle = document.createElement('div')
    feed_functionbarEle.className = 'feed_functionbar'

    let commentI = document.createElement('i')
    commentI.setAttribute('onclick', `feedDetail(this, ${myJson.result[i].feed_pk})`)
    commentI.className = 'fal fa-comment'
    commentI.innerHTML = `${myJson.result[i].comment_count}`
    feed_functionbarEle.appendChild(commentI)

    let favoriteI = document.createElement('i')
    if(myJson.result[i].favorite_state === 1) {
      favoriteI.className = 'fas fa-heart'
    }else {
      favoriteI.className = 'far fa-heart'
    }
    favoriteI.innerHTML = `${myJson.result[i].favorite_count}`
    favoriteI.setAttribute('onclick', `feedFavorite(this, ${myJson.result[i].feed_pk})`)
    feed_functionbarEle.appendChild(favoriteI)

    let bookmarkI = document.createElement('i')

    if(myJson.result[i].bookmark_state === 1){
      bookmarkI.className = 'fas fa-bookmark'
    }else{
      bookmarkI.className = 'far fa-bookmark'
    }
    bookmarkI.setAttribute('onclick', `feedBookmark(this, ${myJson.result[i].feed_pk})`)
    feed_functionbarEle.appendChild(bookmarkI)

    feed_containerEle.appendChild(feed_functionbarEle)

    feedEle.appendChild(feed_containerEle)
  }
}

function feedFavorite(e,feed_pk) {
  let favorite_state = 0
  const function_bar = e.parentNode
  let favoriteI = function_bar.querySelector('.fa-heart')
  if(favoriteI.className === 'fas fa-heart'){
    favorite_state = 1
  }else {
    favorite_state = 0
  }
  let params = {
    favorite_feedpk : feed_pk,
    favorite_state
  }
  fetch('/feed/favorite', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(params), 
  }).then((res) => res.json()
  ).then((myJson) => {

    if(myJson.result.favorite_state == 0) {
      favoriteI.className = 'far fa-heart'
    }else {
      favoriteI.className = 'fas fa-heart'
    }
    favoriteI.innerHTML = myJson.result.favorite_count
  })
}

// Feed Bookmark
function feedBookmark(e, feed_pk) {
  let bookmark_state = 0
  const function_bar = e.parentNode
  let bookmarkI = function_bar.querySelector('.fa-bookmark')
  if(bookmarkI.className === 'fas fa-bookmark'){
    bookmark_state = 1
  }else {
    bookmark_state = 0
  }
  let params = {
    bookmark_feedpk : feed_pk,
    bookmark_state
  }
  fetch('/feed/bookmark', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(params), 
  }).then((res) => res.json()
  ).then((myJson) => {
    if(myJson.result.bookmark_state == 0) {
      bookmarkI.className = 'far fa-bookmark'
    }else {
      bookmarkI.className = 'fas fa-bookmark'
    }
  })
}

function openCloseMenu(e) {
  if(e.querySelector('.feedMenu').style.display === 'none'){
    e.querySelector('.feedMenu').style.display = 'block'
    return
  }
  e.querySelector('.feedMenu').style.display ='none'
}

function delFeed(feed_pk) {

  if(confirm('정말 삭제하시겠습니까?')) {
    fetch(`/feed/deleteFeed`, {
      method: 'put',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(feed_pk),
    }).then((res)=>res.json())
    .then((myJson) => {
      if(myJson.result ===1){
        alert('삭제되었습니다.')
        location.reload()
      }
    })
    return
  }
}

function CheckPassword(upw) {
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

  return true
}