'use strict'

// feedDetail
let feed_DetailOverlay = document.querySelector('.feed_overlay')
const feedDetailClass = document.querySelector('.feedDetail')

function feedDetail(e, feed_pk) {
  return new Promise(function (resolve, reject) {
    fetchAjax(feed_pk, 'get','/feed/detail/', resolve)
  }).then((myJson) => {
    if(myJson.status === 500) {
    reject(new Error('에러발생'))
    return
    }
    makeFeedDetail(e, myJson.result)
  }).catch((e)=> {
    alert('삭제된 피드입니다!')
  })
}

function makeFeedDetail(e, myJson) {

  feed_DetailOverlay.style.display = 'block' // feedDetail show

  //detail content
  const detail_ctnt_containerDiv = document.createElement('div')
  detail_ctnt_containerDiv.className = 'detail_ctnt_container'
  feedDetailClass.appendChild(detail_ctnt_containerDiv)

  // 닫기 버튼
  const closeDiv = document.createElement('div')
  closeDiv.className = 'detail_close'
  detail_ctnt_containerDiv.appendChild(closeDiv)
  
  const closeI = document.createElement('i')
  closeI.className = 'fas fa-times'
  closeDiv.appendChild(closeI)

  //이미지가 있을 때
  if (myJson.media_url.length > 0) {
    feedDetailClass.style.left = '19%'
    feedDetailClass.style.width = '80em'

    // 이미지 div
    let detail_img_containerDiv = document.createElement('div')
    detail_img_containerDiv.className = 'detail_img_container'
    feedDetailClass.appendChild(detail_img_containerDiv)

    let previousDiv = document.createElement('div')
    previousDiv.className = 'detail_previous'
    previousDiv.setAttribute('onclick', 'previousImg(this)')
    detail_img_containerDiv.appendChild(previousDiv)

    let previousI = document.createElement('i')
    previousI.className = 'fas fa-chevron-left'
    previousDiv.appendChild(previousI)

    let detail_ImgDiv = document.createElement('div')
    detail_ImgDiv.className = 'detail_Img'
    for(let i =0; i< myJson.media_url.length;i++) {
      let img = document.createElement('img')
      img.src = myJson.media_url[i].media_url
      detail_ImgDiv.appendChild(img)
    }
    detail_img_containerDiv.appendChild(detail_ImgDiv)

    let nextDiv = document.createElement('div')
    nextDiv.className = 'detail_next'
    nextDiv.setAttribute('onclick', 'nextImg(this)')
    detail_img_containerDiv.appendChild(nextDiv)

    let nextI = document.createElement('i')
    nextI.className = 'fas fa-chevron-right'
    nextDiv.appendChild(nextI)
    }

  //detail titlebat
  const detail_titlebarDiv = document.createElement('div')
  detail_titlebarDiv.className = 'detail_titlebar'
  detail_ctnt_containerDiv.appendChild(detail_titlebarDiv)

  //user info
  // 작성자 프로필 이미지
  const userImg = document.createElement('img')
  userImg.src = `${myJson.user_profileimg}`
  if(myJson.user_profileimg === null) {
    userImg.src = '/resources/img/common/basic_profile.png'
  }
  detail_titlebarDiv.appendChild(userImg)

  // 작성자 아이디
  const idSpan = document.createElement('span')
  let user_idA = document.createElement('a')
  user_idA.setAttribute('href', `/userpage/${myJson.user_id}`)
  idSpan.innerText = `${myJson.user_id}`
  user_idA.appendChild(idSpan)
  detail_titlebarDiv.appendChild(user_idA)

  // 작성 날짜
  const dateSpan = document.createElement('span')
  dateSpan.innerText = `${myJson.feed_writedate}`
  detail_titlebarDiv.appendChild(dateSpan)

  // content container
  const detail_ctntDiv = document.createElement('div')
  detail_ctntDiv.classList.add('detail_ctnt')
  detail_ctnt_containerDiv.appendChild(detail_ctntDiv)

  // 해시태그
  const hastagDiv = document.createElement('div')
  hastagDiv.className= 'hashtag_container'
  for (let j = 0; j < myJson.hashtag_ctnt.length; j++) {
    const hashtagA = document.createElement('a')
    let hashtag_ctnt = `${myJson.hashtag_ctnt[j].hashtag_ctnt}`
        hashtag_ctnt = hashtag_ctnt.split('#')[1]
        hashtagA.href = '/search/' + hashtag_ctnt
        hashtagA.innerText = myJson.hashtag_ctnt[j].hashtag_ctnt
    hastagDiv.appendChild(hashtagA)
  }
  detail_ctntDiv.appendChild(hastagDiv)

  // 글 내용
  const ctntDiv = document.createElement('div')
  ctntDiv.className = 'ctnt_container'
  detail_ctntDiv.appendChild(ctntDiv)

  const ctntP = document.createElement('span')
  ctntP.innerText = `${myJson.feed_ctnt}`
  ctntDiv.appendChild(ctntP)

  //functionbar
  const detail_functionbarDiv = document.createElement('div')
  detail_functionbarDiv.className = 'detail_functionbar'
  detail_ctnt_containerDiv.appendChild(detail_functionbarDiv)

  // 댓글 수
  const commentI = document.createElement('i')
  commentI.className = 'fal fa-comment'
  detail_functionbarDiv.appendChild(commentI)

  // 좋아요
  const heartI = document.createElement('i')
  if(myJson.favorite_state === 1) {
    heartI.className = 'fas fa-heart'
  }else {
    heartI.className = 'far fa-heart'
  }
  heartI.setAttribute('onclick', `feedFavorite(this, ${myJson.feed_pk})`)
  heartI.innerText=`${myJson.favorite_count}`
  detail_functionbarDiv.appendChild(heartI)

  // 북마크
  const bookmarkI = document.createElement('i')
  if(myJson.bookmark_state === 1){
    bookmarkI.className = 'fas fa-bookmark'
  }else{
    bookmarkI.className = 'far fa-bookmark'
  }
  bookmarkI.setAttribute('onclick', `feedBookmark(this, ${myJson.feed_pk})`)
  detail_functionbarDiv.appendChild(bookmarkI)

  // 댓글창 ////////////////////////////////////////
  const detail_comment_containerDiv = document.createElement('div')
  detail_comment_containerDiv.classList.add('detail_comment_container')
  detail_ctnt_containerDiv.appendChild(detail_comment_containerDiv)

  const comment_listDiv = document.createElement('div')
  comment_listDiv.className = 'comment_list'
  detail_comment_containerDiv.appendChild(comment_listDiv)

  if(myJson.comment_list.length == 0) {
    let span = document.createElement('span')
    span.innerHTML = '댓글이 없습니다.'
    span.className = 'noComment'
    comment_listDiv.appendChild(span)
  }

  for(let k =0; k < myJson.comment_list.length; k++) {
    let commentbarDiv = document.createElement('div')
    commentbarDiv.className = 'commentbar'
    commentbarDiv.setAttribute('data-comment_pk', `${myJson.comment_list[k].comment_pk}`)
    comment_listDiv.appendChild(commentbarDiv)

    let commentUserImg = document.createElement('img')
    commentUserImg.src = `${myJson.comment_list[k].user_profileimg}`
    
    if(myJson.comment_list[k].user_profileimg == null) {
      commentUserImg.src = '/resources/img/common/basic_profile.png'
    }
    commentbarDiv.appendChild(commentUserImg)

    let commentUserIdSpan = document.createElement('span')
    commentUserIdSpan.innerText = `${myJson.comment_list[k].user_id}`
    commentbarDiv.appendChild(commentUserIdSpan)

    let commentCtntSpan = document.createElement('span')
    commentCtntSpan.innerText = `${myJson.comment_list[k].comment_ctnt}`
    commentbarDiv.appendChild(commentCtntSpan)

    let commentFunctionbarDiv = document.createElement('div')
    commentFunctionbarDiv.className = 'commentFunctionbar'
    commentbarDiv.append(commentFunctionbarDiv)

    let recommentButton = document.createElement('button')
    recommentButton.className = 'commentWriteSpan'
    recommentButton.innerText = '답글 달기'
    recommentButton.setAttribute('onclick', "recomment(this)")
    commentFunctionbarDiv.appendChild(recommentButton)

    let commentViewMoreSpan = document.createElement('button')
    commentViewMoreSpan.className = 'commentViewMore'
    commentViewMoreSpan.innerText = '더 보기'
    commentViewMoreSpan.setAttribute('onclick', `viewMore(this)`)
    commentFunctionbarDiv.appendChild(commentViewMoreSpan)
  }

  // 댓글 입력
  const comment_inputDiv = document.createElement('div')
  comment_inputDiv.classList.add('comment_input')
  detail_comment_containerDiv.appendChild(comment_inputDiv)

  const CommentInput = document.createElement('input')
  CommentInput.setAttribute('name', 'comment_ctnt')
  CommentInput.type='text'
  CommentInput.placeholder = '댓글을 입력해주세요'
  comment_inputDiv.appendChild(CommentInput)

  const input_submit = document.createElement('input')
  input_submit.type = 'button'
  input_submit.value = '입력'
  input_submit.setAttribute('onclick', `comment_submit(this,${myJson.feed_pk})`)
  CommentInput.after(input_submit)

  // // feedDetail close only
  const feed_overlay_close = document.querySelector('.fa-times')
  feed_overlay_close.onclick = function () {
  feedDetailClass.querySelectorAll('*').forEach((test) => test.remove())

  feed_DetailOverlay.style.display = 'none'
  location.reload()
  }
}

function recomment(e) {
  if(document.querySelector('span[name=recomment_userid]')) {
    document.querySelector('span[name=recomment_userid]').remove()
  }
  const commentbar = e.parentNode.parentNode
  const user_id = commentbar.firstChild.nextSibling.innerText

  const user_idSpan = document.createElement('span')
  user_idSpan.innerText = '@' + user_id
  user_idSpan.setAttribute('name', 'recomment_userid')
  user_idSpan.setAttribute('data-comment_pk', commentbar.dataset.comment_pk)
  document.querySelector('input[name="comment_ctnt"]').before(user_idSpan)

  const delete_recommentI = document.createElement('i')
  delete_recommentI.className = 'fas fa-times'
  delete_recommentI.onclick = (e) => {
    e.target.parentNode.remove()
  }
  user_idSpan.appendChild(delete_recommentI)
}

function comment_submit(e,feed_pk) {
  const comment_inputEle = e.parentNode
  let comment_ctnt = comment_inputEle.querySelector('input[name="comment_ctnt"]').value
  if(comment_ctnt == '') {
    alert('댓글을 입력해주세요')
    return
  }
  if(comment_inputEle.querySelector('span[name="recomment_userid"]') != null) {
    let parent_comment_user_pk = comment_inputEle.querySelector('span[name="recomment_userid"]').innerText
    
    let params = {
      comment_feedpk: feed_pk,
      comment_parentpk : comment_inputEle.querySelector('span[name="recomment_userid"]').dataset.comment_pk,
      comment_ctnt
    }
    fetchAjax(params, 'post','/feed/recomment',(myJson) => {
    if(myJson.result === 1) {
      comment_inputEle.querySelector('input[name="comment_ctnt"]').value = ''
      comment_inputEle.querySelector('span[name="recomment_userid"]').remove()
      sendAlarm(4,feed_pk,parent_comment_user_pk.split('@')[1])
      sendAlarm(3,feed_pk,e.parentNode.parentNode.parentNode.querySelector('.detail_titlebar span').innerText)
    }
  })
    return
  }

  let params = {
    comment_feedpk: feed_pk,
    comment_ctnt
  }

  fetchAjax(params, 'post', '/feed/comment', (myJson) => {
    if(myJson.result === 1) {
      comment_inputEle.querySelector('input[name="comment_ctnt"]').value = ''
      getCommentList(e, feed_pk)
      sendAlarm(3,feed_pk,e.parentNode.parentNode.parentNode.querySelector('.detail_titlebar span').innerText)
      return
    }
    alert('댓글 작성을 실패하였습니다.')
    })
}

function getCommentList(e, feed_pk) {
  e.parentNode.parentNode.firstChild.querySelectorAll('*').forEach((nodes) => nodes.remove())
  fetchAjax(feed_pk, 'get', '/feed/getcomment/', (myJson) => {
    for(let i=0; i< myJson.result.length; i++) {
      let commentbarDiv = document.createElement('div')
      commentbarDiv.className = 'commentbar'
      commentbarDiv.setAttribute('data-comment_pk', `${myJson.result[i].comment_pk}`)
      e.parentNode.parentNode.firstChild.appendChild(commentbarDiv)

      let commentUserImg = document.createElement('img')
      commentUserImg.src = `${myJson.result[i].user_profileimg}`
      if(myJson.result[i].user_profileimg == null) {
        commentUserImg.src = '/resources/img/common/basic_profile.png'
      }
      commentbarDiv.appendChild(commentUserImg)

      let commentUserIdSpan = document.createElement('span')
      commentUserIdSpan.innerText = `${myJson.result[i].user_id}`
      commentbarDiv.appendChild(commentUserIdSpan)

      let commentCtntSpan = document.createElement('span')
      commentCtntSpan.innerText = `${myJson.result[i].comment_ctnt}`
      commentbarDiv.appendChild(commentCtntSpan)

      let commentFunctionbarDiv = document.createElement('div')
      commentFunctionbarDiv.className = 'commentFunctionbar'
      commentbarDiv.append(commentFunctionbarDiv)

      let recommentButton = document.createElement('button')
      recommentButton.className = 'commentWriteSpan'
      recommentButton.innerText = '답글 달기'
      recommentButton.setAttribute('onclick', "recomment(this)")
      commentFunctionbarDiv.appendChild(recommentButton)

      let commentViewMoreSpan = document.createElement('button')
      commentViewMoreSpan.className = 'commentViewMore'
      commentViewMoreSpan.innerText = '더 보기'
      commentViewMoreSpan.setAttribute('onclick', `viewMore(this)`)
      commentFunctionbarDiv.appendChild(commentViewMoreSpan)
    }
  })
}

function viewMore(e) {
  const commentBar = e.parentNode.parentNode
  let comment_parentpk = e.parentNode.parentNode.dataset.comment_pk
  e.parentNode.parentNode.parentNode.querySelectorAll('.recommentbar').forEach((test) => test.remove())

  fetchAjax(comment_parentpk, 'get', '/feed/getrecomment/', (myJson) => {
    if(myJson.result.length == 0) {
      alert('댓글이 없습니다.')
      return
    }
    for(let i =0; i < myJson.result.length; i++) {
      let recommentbarDiv = document.createElement('div')
      recommentbarDiv.className = 'recommentbar'
      commentBar.after(recommentbarDiv)

      let recommentUserImg = document.createElement('img')
      recommentUserImg.src = `${myJson.result[i].user_profileimg}`
      if(myJson.result[i].user_profileimg == null) {
        recommentUserImg.src = '/resources/img/common/basic_profile.png'
      }
      recommentbarDiv.appendChild(recommentUserImg)

      let recommentUserIdSpan = document.createElement('span')
      recommentUserIdSpan.innerText = `${myJson.result[i].user_id}`
      recommentbarDiv.appendChild(recommentUserIdSpan)

      let recommentCtntSpan = document.createElement('span')
      recommentCtntSpan.innerText = `${myJson.result[i].comment_ctnt}`
      recommentbarDiv.appendChild(recommentCtntSpan)
    }
  })
}
