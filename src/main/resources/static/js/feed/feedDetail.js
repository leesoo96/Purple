// feedDetail
let feed_DetailOverlay = document.querySelector('.feed_overlay')
const feedDetailClass = document.querySelector('.feedDetail')

function feedDetail(e, feed_pk) {
  return new Promise(function (resolve) {
    fetch(`/feed/detail/` + feed_pk)
      .then((res) => res.json())
      .then((myJson) => {
        resolve(myJson.result)
      })
  }).then((myJson) => {
    makeFeedDetail(e, myJson)
  })
}


function makeFeedDetail(e, myJson) {
  console.log(e)
  console.log(myJson)
  feedDetailClass.style.display = 'block'
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

    let detail_img_containerDiv = document.createElement('div')
    detail_img_containerDiv.className = 'detail_img_container'
    feedDetailClass.appendChild(detail_img_containerDiv)
    // feedDetailClass.style.left = '20%'
    // feedDetailClass.style.width = '77em'
    // // 이미지 div
    // let detail_imgDiv = document.createElement('div')
    // feedDetail_imgDiv.className = 'detail_img_container'
    // feedDetailClass.appendChild(feedDetail_imgDiv)

    // let previousDiv = document.createElement('div')
    // previousDiv.className = 'detail_previous'
    // previousDiv.setAttribute('onclick', 'previousImg(this)')
    // feedDetail_imgDiv.appendChild(previousDiv)

    // let previousI = document.createElement('i')
    // previousI.className = 'fas fa-chevron-left'
    // previousDiv.appendChild(previousI)

    // let detail_ImgDiv = document.createElement('div')
    // detail_ImgDiv.className = 'detail_Img'
    // for(let i =0; i< myJson.media_url.length;i++) {
    //   let img = document.createElement('img')
    //   // img.src = myJson.media_url[i].media_url
    //   detail_ImgDiv.appendChild(img)
    // }
    // feedDetail_imgDiv.appendChild(detail_ImgDiv)

    // let nextDiv = document.createElement('div')
    // nextDiv.className = 'detail_next'
    // nextDiv.setAttribute('onclick', 'nextImg(this)')
    // feedDetail_imgDiv.appendChild(nextDiv)

    // let nextI = document.createElement('i')
    // nextI.className = 'fas fa-chevron-right'
    // nextDiv.appendChild(nextI)
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
    hashtagA.href = "#"
    hashtagA.innerText = myJson.hashtag_ctnt[j].hashtag_ctnt
    hastagDiv.appendChild(hashtagA)
  }
  detail_ctntDiv.appendChild(hastagDiv)

  // 글 내용
  const ctntDiv = document.createElement('div')
  ctntDiv.className = 'ctnt_container'
  detail_ctntDiv.appendChild(ctntDiv)

  const ctntP = document.createElement('p')
  ctntP.innerText = `${myJson.feed_ctnt}`
  ctntDiv.appendChild(ctntP)

  //functionbar
  const detail_functionbarDiv = document.createElement('div')
  detail_functionbarDiv.className = 'detail_functionbar'
  detail_ctnt_containerDiv.appendChild(detail_functionbarDiv)

  // 댓글 수
  const commentI = document.createElement('i')
  commentI.className = 'fal fa-comment'
  commentI.innerText = `${myJson.comment_count}`
  detail_functionbarDiv.appendChild(commentI)

  // 좋아요
  const heartI = document.createElement('i')
  heartI.className = 'far fa-heart'
  heartI.innerText=`${myJson.favorite_count}`
  detail_functionbarDiv.appendChild(heartI)

  // 북마크
  const bookmarkI = document.createElement('i')
  bookmarkI.className = 'far fa-bookmark'
  detail_functionbarDiv.appendChild(bookmarkI)


  // 댓글창 ////////////////////////////////////////
  const detail_comment_containerDiv = document.createElement('div')
  detail_comment_containerDiv.classList.add('detail_comment_container')
  detail_ctnt_containerDiv.appendChild(detail_comment_containerDiv)

  const comment_listDiv = document.createElement('div')
  comment_listDiv.className = 'comment_list'
  detail_comment_containerDiv.appendChild(comment_listDiv)

  if(myJson.comment_list.length == 0) {

  }
  for(let k =0; k < myJson.comment_list.length; k++) {
    let commentbarDiv = document.createElement('div')
    commentbarDiv.className = 'commentbar'
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

    let commentctntSpan = document.createElement('span')
    commentctntSpan.innerText = `${myJson.comment_list[k].comment_ctnt}`
    commentbarDiv.appendChild(commentctntSpan)
  }

  // const comment_read_containerDiv = document.createElement('div')
  // comment_read_containerDiv.classList.add('comment_read_container')
  // feed_commentDiv.appendChild(comment_read_containerDiv)

  // const comment_readDiv = document.createElement('div')
  // comment_readDiv.classList.add('comment_read')
  // comment_read_containerDiv.appendChild(comment_readDiv)
  // comment_readDiv.style.height = '13.3em'

  // // 댓글이 있을 경우와 없는 경우를 ajax로 작업해야합니다 
  // // 태그 생성 + 댓글 값 표시 
  // // 일반댓글
  //   const cmtReadDetail = document.createElement('div')
  //   cmtReadDetail.classList.add('cmtReadDetail')
  //   comment_readDiv.appendChild(cmtReadDetail) 

  //   for(let k=0; k < myJson.comment_list.length; k++) {
  //     let comment_barP = document.createElement('span')
  //     comment_barP.className = 'cmtReadDetail'
  //     comment_barP.innerText = `${myJson.comment_list[k].comment_ctnt}`
  //     cmtReadDetail.appendChild(comment_barP)
  //   }
  //   // 댓글 입력
  //   const comment_input = document.createElement('div')
  //   comment_input.classList.add('comment_input')
  //   comment_readDiv.after(comment_input)

  //   const input = document.createElement('input')
  //   input.placeholder = '댓글을 입력해주세요'
  //   comment_input.appendChild(input)

  //   const input_submit = document.createElement('input')
  //   input_submit.type = 'submit'
  //   input_submit.value = '입력'
  //   input.after(input_submit)
  // } else {
  //   // 이미지가 없을 때
  //   //detail content
  //   feedDetailClass.style.width = '37em'

  //   const detailCtnt_container = document.createElement('div')
  //   detailCtnt_container.id = 'detailCtnt_container'
  //   feedDetailClass.appendChild(detailCtnt_container)

  //   const feedDetail_contents = document.createElement('div')
  //   feedDetail_contents.classList.add('feedDetail_contents')
  //   detailCtnt_container.appendChild(feedDetail_contents)

  //   const feed_title = document.createElement('div')
  //   feed_title.classList.add('feed_title')
  //   feedDetail_contents.appendChild(feed_title)

  //   feed_title.appendChild(writer_imgClone)

  //   feed_title.style.left = '-22em'
  //   feed_title.style.width = '36em'

  //   // 작성자 아이디
  //   const idSpan = document.createElement('span')
  //   writer_imgClone.after(idSpan)
  //   idSpan.append(writer_id.innerText)

  //   // 작성 날짜
  //   const dateSpan = document.createElement('span')
  //   idSpan.after(dateSpan)
  //   dateSpan.append(write_date.innerText)

   

  //   // content container
  //   const detail_ctnt_container = document.createElement('div')
  //   detail_ctnt_container.classList.add('detail_ctnt_container')
  //   feed_title.after(detail_ctnt_container)
  //   detail_ctnt_container.style.position = 'relative'
  //   detail_ctnt_container.style.left = '-20em'
  //   detail_ctnt_container.style.top = '1em'
  //   detail_ctnt_container.style.fontSize = '1.07rem'

  //   // 해시태그
  //   for (let j = 0; j < feed_hashtag.length; j++) {
  //     const hashtag_a = document.createElement('a')
  //     hashtag_a.append(feed_hashtag[j].innerText)
  //     detail_ctnt_container.appendChild(hashtag_a)
  //   }

  //   // 글 내용
  //   const ctnt_div = document.createElement('div')
  //   ctnt_div.classList.add('ctnt_div')
  //   detail_ctnt_container.appendChild(ctnt_div)
  //   const ctnt_p = document.createElement('p')
  //   ctnt_p.append(feed_ctnt.innerText)
  //   ctnt_div.appendChild(ctnt_p)

  //   // 좋아요 수
  //   const feed_functionbar = document.createElement('div')
  //   feed_functionbar.classList.add('noImg_Functionbar')
  //   feed_functionbar.style.position = 'relative'
  //   feed_functionbar.style.top = '4.7em'
  //   feed_functionbar.style.left = '-22em'
  //   detail_ctnt_container.after(feed_functionbar)

  //   const heart_div = document.createElement('div')
  //   heart_div.classList.add('heart_div')
  //   heart_div.innerHTML = '<i class="far fa-heart">'

  //   const heart_span = document.createElement('span')
  //   heart_div.append(heart_span)
  //   heart_span.append(feed_heart.innerText)
  //   feed_functionbar.appendChild(heart_div)

  //   // 댓글 수
  //   const comment_div = document.createElement('div')
  //   comment_div.classList.add('comment_div')
  //   comment_div.innerHTML = '<i class="fal fa-comment">'

  //   const cmt_span = document.createElement('span')
  //   comment_div.append(cmt_span)
  //   cmt_span.append(feed_cmt.innerText)
  //   heart_div.after(comment_div)

  //   // 북마크
  //   const bookmark_div = document.createElement('div')
  //   bookmark_div.classList.add('bookmark_div')
  //   bookmark_div.innerHTML = '<i class="far fa-bookmark"></i>'
  //   comment_div.after(bookmark_div)

  //   // 댓글창 ////////////////////////////////////////
  //   const feed_comment = document.createElement('div')
  //   feed_comment.classList.add('feed_comment')
  //   feed_functionbar.after(feed_comment)
  //   feed_comment.style.marginTop = '4.7em'

  //   const comment_read_container = document.createElement('div')
  //   comment_read_container.classList.add('comment_read_container')
  //   feed_comment.appendChild(comment_read_container)
  //   comment_read_container.style.marginLeft = '-21em'

  //   const comment_read = document.createElement('div')
  //   comment_read.classList.add('comment_read')
  //   comment_read_container.appendChild(comment_read)
  //   comment_read.style.height = '13.3em'

  //   댓글이 있을 경우와 없는 경우를 ajax로 작업해야합니다 
  //     // 태그 생성 + 댓글 값 표시 
  //     // 일반댓글
  //     const cmtReadDetail = document.createElement('div')
  //     cmtReadDetail.classList.add('cmtReadDetail')
  //     comment_read.appendChild(cmtReadDetail) 

  //   // 댓글 입력
  //   const comment_input = document.createElement('div')
  //   comment_input.classList.add('comment_input')
  //   feed_comment.after(comment_input)
  //   comment_input.style.left = '-21em'

  //   const input = document.createElement('input')
  //   input.placeholder = '댓글을 입력해주세요'
  //   comment_input.appendChild(input)

  //   const input_submit = document.createElement('input')
  //   input_submit.type = 'submit'
  //   input_submit.value = '입력'
  //   input.after(input_submit)
  //   input_submit.style.left = '1em'
  //   input_submit.style.top = '0em'
  // }

  // // feedDetail close only
  // const feed_overlay_close = document.querySelector('.fa-times')
  // feed_overlay_close.onclick = function () {
  //   feedDetailClass.querySelectorAll('*').forEach((test) => test.remove())

  //   feed_DetailOverlay.style.display = 'none'
  // }
}
