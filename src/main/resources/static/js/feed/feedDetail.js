// feedDetail
let feed_DetailOverlay = document.querySelector('.feed_overlay')
const feedDetailClass = document.querySelector('.feedDetail')

function feedDetail(e) {
  feed_DetailOverlay.style.display = 'block' // feedDetail show

  const feed_container = e.parentNode.parentNode

  let feedImg = feed_container.querySelectorAll('.feed_imgList img')

  let writer_img = feed_container.querySelector('.title_img') // 작성자 프로필사진
  let writer_imgClone = writer_img.cloneNode(false)

  let writer_id = feed_container.querySelector('span[name="user_id"]') // 글 작성자

  let write_date = feed_container.querySelector('span[name="feed_writedate"]') // 작성날짜

  let feed_hashtag = feed_container.querySelectorAll(
    'a[name="detailCtnt_hashtag"]'
  )
  console.log(feed_hashtag[0].innerHTML)
  let feed_ctnt = feed_container.querySelector('p[name="feed_content"]') // 글 내용
  let feed_heart = feed_container.querySelector('span[name="heartValue"]') // 좋아요 수
  let feed_cmt = feed_container.querySelector('span[name="cmtValue"]') // 댓글 수

  //이미지가 있을 때
  if (feedImg.length > 0) {
    feedDetailClass.style.width = '77em'

    // 이미지 div
    const feedDetail_img = document.createElement('div')
    feedDetail_img.classList.add('feedDetail_img')
    feedDetailClass.appendChild(feedDetail_img)

    // previous 버튼
    const detail_previous = document.createElement('div')
    detail_previous.classList.add('detail_previous')
    feedDetail_img.appendChild(detail_previous)
    detail_previous.innerHTML = '<i class="fas fa-chevron-left"></i>'

    detail_previous.onclick = function () {
      const detail_img_slide = detailImgClass
      let first_img = detail_img_slide.firstChild

      while (first_img.nodeType !== 1) {
        first_img = first_img.nextSibling
      }

      let last_img = detail_img_slide.lastChild
      console.log(last_img)

      while (last_img.nodeType !== 1) {
        last_img = last_img.previousSibling
      }
      last_img.after(first_img)
    }

    // 이미지 내용 복사 및 만들기
    const detailImgClass = document.createElement('div')
    detailImgClass.classList.add('detail_Img')

    for (let i = 0; i < feedImg.length; i++) {
      let detailImg = feedImg[i].cloneNode(false)
      detailImgClass.appendChild(detailImg)
    }

    detail_previous.after(detailImgClass)

    // next 버튼
    const detail_next = document.createElement('div')
    detail_next.classList.add('detail_next')
    detailImgClass.after(detail_next)
    detail_next.innerHTML = '<i class="fas fa-chevron-right"></i>'

    detail_next.onclick = function () {
      const detail_img_slide = detailImgClass
      let first_img = detail_img_slide.firstChild

      while (first_img.nodeType !== 1) {
        console.log('first = ' + first_img)

        first_img = first_img.nextSibling
      }
      let last_img = detail_img_slide.lastChild
      while (last_img.nodeType !== 1) {
        console.log('last = ' + last_img)

        last_img = last_img.previousSibling
      }
      last_img.after(first_img)
    }

    //detail content
    const detailCtnt_container = document.createElement('div')
    detailCtnt_container.id = 'detailCtnt_container'
    detail_next.after(detailCtnt_container)

    const feedDetail_contents = document.createElement('div')
    feedDetail_contents.classList.add('feedDetail_contents')
    detailCtnt_container.appendChild(feedDetail_contents)

    const feed_title = document.createElement('div')
    feed_title.classList.add('feed_title')
    feedDetail_contents.appendChild(feed_title)

    feed_title.appendChild(writer_imgClone)

    // 작성자 아이디
    const idSpan = document.createElement('span')
    writer_imgClone.after(idSpan)
    idSpan.append(writer_id.innerText)

    // 작성 날짜
    const dateSpan = document.createElement('span')
    idSpan.after(dateSpan)
    dateSpan.append(write_date.innerText)

    // 닫기 버튼
    const closeSpan = document.createElement('span')
    dateSpan.after(closeSpan)
    closeSpan.innerHTML = '<i class="fas fa-times">'

    // content container
    const detail_ctnt_container = document.createElement('div')
    detail_ctnt_container.classList.add('detail_ctnt_container')
    feed_title.after(detail_ctnt_container)

    // 해시태그
    for (let j = 0; j < feed_hashtag.length; j++) {
      const hashtag_a = document.createElement('a')
      hashtag_a.append(feed_hashtag[j].innerText)
      detail_ctnt_container.appendChild(hashtag_a)
    }

    // 글 내용
    const ctnt_div = document.createElement('div')
    detail_ctnt_container.appendChild(ctnt_div)
    const ctnt_p = document.createElement('p')
    ctnt_p.append(feed_ctnt.innerText)
    ctnt_div.appendChild(ctnt_p)

    // 좋아요 수
    const feed_functionbar = document.createElement('div')
    detail_ctnt_container.after(feed_functionbar)

    const heart_div = document.createElement('div')
    heart_div.classList.add('heart_div')
    heart_div.innerHTML = '<i class="far fa-heart">'

    const heart_span = document.createElement('span')
    heart_div.append(heart_span)
    heart_span.append(feed_heart.innerText)
    feed_functionbar.appendChild(heart_div)

    // 댓글 수
    const comment_div = document.createElement('div')
    comment_div.classList.add('comment_div')
    comment_div.innerHTML = '<i class="fal fa-comment">'

    const cmt_span = document.createElement('span')
    comment_div.append(cmt_span)
    cmt_span.append(feed_cmt.innerText)
    heart_div.after(comment_div)

    // 북마크
    const bookmark_div = document.createElement('div')
    bookmark_div.classList.add('bookmark_div')
    bookmark_div.innerHTML = '<i class="far fa-bookmark"></i>'
    comment_div.after(bookmark_div)

    // 댓글창 ////////////////////////////////////////
    const feed_comment = document.createElement('div')
    feed_comment.classList.add('feed_comment')
    feed_functionbar.after(feed_comment)

    const comment_read_container = document.createElement('div')
    comment_read_container.classList.add('comment_read_container')
    feed_comment.appendChild(comment_read_container)

    const comment_read = document.createElement('div')
    comment_read.classList.add('comment_read')
    comment_read_container.appendChild(comment_read)
    comment_read.style.height = '13.3em'

    /* 댓글이 있을 경우와 없는 경우를 ajax로 작업해야합니다 
      // 태그 생성 + 댓글 값 표시 
      // 일반댓글
      const cmtReadDetail = document.createElement('div')
      cmtReadDetail.classList.add('cmtReadDetail')
      comment_read.appendChild(cmtReadDetail) */

    // 댓글 입력
    const comment_input = document.createElement('div')
    comment_input.classList.add('comment_input')
    feed_comment.after(comment_input)

    const input = document.createElement('input')
    input.placeholder = '댓글을 입력해주세요'
    comment_input.appendChild(input)

    const input_submit = document.createElement('input')
    input_submit.type = 'submit'
    input_submit.value = '입력'
    input.after(input_submit)
  } else {
    // 이미지가 없을 때
    //detail content
    feedDetailClass.style.width = '37em'

    const detailCtnt_container = document.createElement('div')
    detailCtnt_container.id = 'detailCtnt_container'
    feedDetailClass.appendChild(detailCtnt_container)

    const feedDetail_contents = document.createElement('div')
    feedDetail_contents.classList.add('feedDetail_contents')
    detailCtnt_container.appendChild(feedDetail_contents)

    const feed_title = document.createElement('div')
    feed_title.classList.add('feed_title')
    feedDetail_contents.appendChild(feed_title)

    feed_title.appendChild(writer_imgClone)

    feed_title.style.left = '-22em'
    feed_title.style.width = '36em'

    // 작성자 아이디
    const idSpan = document.createElement('span')
    writer_imgClone.after(idSpan)
    idSpan.append(writer_id.innerText)

    // 작성 날짜
    const dateSpan = document.createElement('span')
    idSpan.after(dateSpan)
    dateSpan.append(write_date.innerText)

    // 닫기 버튼
    const closeSpan = document.createElement('span')
    dateSpan.after(closeSpan)
    closeSpan.innerHTML = '<i class="fas fa-times">'

    // content container
    const detail_ctnt_container = document.createElement('div')
    detail_ctnt_container.classList.add('detail_ctnt_container')
    feed_title.after(detail_ctnt_container)
    detail_ctnt_container.style.position = 'relative'
    detail_ctnt_container.style.left = '-20em'
    detail_ctnt_container.style.top = '1em'
    detail_ctnt_container.style.fontSize = '1.07rem'

    // 해시태그
    for (let j = 0; j < feed_hashtag.length; j++) {
      const hashtag_a = document.createElement('a')
      hashtag_a.append(feed_hashtag[j].innerText)
      detail_ctnt_container.appendChild(hashtag_a)
    }

    // 글 내용
    const ctnt_div = document.createElement('div')
    ctnt_div.classList.add('ctnt_div')
    detail_ctnt_container.appendChild(ctnt_div)
    const ctnt_p = document.createElement('p')
    ctnt_p.append(feed_ctnt.innerText)
    ctnt_div.appendChild(ctnt_p)

    // 좋아요 수
    const feed_functionbar = document.createElement('div')
    feed_functionbar.classList.add('noImg_Functionbar')
    feed_functionbar.style.position = 'relative'
    feed_functionbar.style.top = '4.7em'
    feed_functionbar.style.left = '-22em'
    detail_ctnt_container.after(feed_functionbar)

    const heart_div = document.createElement('div')
    heart_div.classList.add('heart_div')
    heart_div.innerHTML = '<i class="far fa-heart">'

    const heart_span = document.createElement('span')
    heart_div.append(heart_span)
    heart_span.append(feed_heart.innerText)
    feed_functionbar.appendChild(heart_div)

    // 댓글 수
    const comment_div = document.createElement('div')
    comment_div.classList.add('comment_div')
    comment_div.innerHTML = '<i class="fal fa-comment">'

    const cmt_span = document.createElement('span')
    comment_div.append(cmt_span)
    cmt_span.append(feed_cmt.innerText)
    heart_div.after(comment_div)

    // 북마크
    const bookmark_div = document.createElement('div')
    bookmark_div.classList.add('bookmark_div')
    bookmark_div.innerHTML = '<i class="far fa-bookmark"></i>'
    comment_div.after(bookmark_div)

    // 댓글창 ////////////////////////////////////////
    const feed_comment = document.createElement('div')
    feed_comment.classList.add('feed_comment')
    feed_functionbar.after(feed_comment)
    feed_comment.style.marginTop = '4.7em'

    const comment_read_container = document.createElement('div')
    comment_read_container.classList.add('comment_read_container')
    feed_comment.appendChild(comment_read_container)
    comment_read_container.style.marginLeft = '-21em'

    const comment_read = document.createElement('div')
    comment_read.classList.add('comment_read')
    comment_read_container.appendChild(comment_read)
    comment_read.style.height = '13.3em'

    /* 댓글이 있을 경우와 없는 경우를 ajax로 작업해야합니다 
      // 태그 생성 + 댓글 값 표시 
      // 일반댓글
      const cmtReadDetail = document.createElement('div')
      cmtReadDetail.classList.add('cmtReadDetail')
      comment_read.appendChild(cmtReadDetail) */

    // 댓글 입력
    const comment_input = document.createElement('div')
    comment_input.classList.add('comment_input')
    feed_comment.after(comment_input)
    comment_input.style.left = '-21em'

    const input = document.createElement('input')
    input.placeholder = '댓글을 입력해주세요'
    comment_input.appendChild(input)

    const input_submit = document.createElement('input')
    input_submit.type = 'submit'
    input_submit.value = '입력'
    input.after(input_submit)
    input_submit.style.left = '1em'
    input_submit.style.top = '0em'
  }

  // feedDetail close only
  const feed_overlay_close = document.querySelector('.fa-times')
  feed_overlay_close.onclick = function () {
    feedDetailClass.querySelectorAll('*').forEach((test) => test.remove())

    feed_DetailOverlay.style.display = 'none'
  }
}
