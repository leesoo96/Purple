// feedDetail
let feed_DetailOverlay = document.querySelector('.feed_overlay')
const feedDetailClass = document.querySelector('.feedDetail')

function feedDetail(e) {
  feed_DetailOverlay.style.display = 'block' // feedDetail show

  const feed_container = e.parentNode

  const feedDetail_img = document.createElement('div')
  feedDetail_img.classList.add('feedDetail_img')
  feedDetailClass.appendChild(feedDetail_img)

  const detail_previous = document.createElement('div')
  detail_previous.classList.add('detail_previous')
  feedDetail_img.appendChild(detail_previous)

  detail_previous.innerHTML = '<i class="fas fa-chevron-left"></i>'

  const detailImgClass = document.createElement('div')
  detailImgClass.classList.add('detail_Img')
  detail_previous.after(detailImgClass)

  let feedImg = feed_container.querySelectorAll('.feed_imgList img')
  if (feedImg.length > 0) {
    for (let i = 0; i < feedImg.length; i++) {
      let detailImg = feedImg[i].cloneNode(false)
      detailImgClass.appendChild(detailImg)
    }

    const detail_next = document.createElement('div')
    detail_next.classList.add('detail_next')
    detailImgClass.after(detail_next)
    detail_next.innerHTML = '<i class="fas fa-chevron-right"></i>'

    const feedDetail_contents_container = document.createElement('div')
    feedDetail_contents_container.id = 'feedDetail_contents_container'
    detail_next.after(feedDetail_contents_container)

    const feedDetail_contents = document.createElement('div')
    feedDetail_contents.classList.add('feedDetail_contents')
    feedDetail_contents_container.appendChild(feedDetail_contents)

    const feed_title = document.createElement('div')
    feed_title.classList.add('feed_title')
    feedDetail_contents.appendChild(feed_title)

    // if문 써서 프로필 사진 in
    let feed_detail_userImg = document.createElement('div')
    feed_detail_userImg.classList.add('feed_detail_userImg')
    feed_title.appendChild(feed_detail_userImg)
    feed_detail_userImg.innerHTML =
      '<img src="resources/img/common/basic_profile.png" alt="기본프로필사진">'

    const user_id_span = document.createElement('span')
    let user_id = feed_container.querySelector('span[name="user_id"]')
    user_id_span.innerHTML = user_id.innerText
    feed_detail_userImg.after(user_id_span)

    const feed_writedate_span = document.createElement('span')
    let feed_writedate = feed_container.querySelector(
      'span[name="feed_writedate"]'
    )
    feed_writedate_span.innerHTML = feed_writedate.innerText
    user_id_span.after(feed_writedate_span)

    feed_title_i_div = document.createElement('div')
    feed_writedate_span.after(feed_title_i_div)
    feed_title_i_div.innerHTML =
      '<i class="far fa-bookmark"></i> <i class="fas fa-times"></i>'

    const feed_overlay_close = document.querySelector('.fa-times')
    // feedDetail close only
    feed_overlay_close.onclick = function () {
      feed_DetailOverlay.style.display = 'none'
    }

    const feed_content = document.createElement('div')
    feed_content.classList.add('feed_content')
    feed_title.after(feed_content)

    const feed_content_Hashtag_p = document.createElement('p')
    feed_content_Hashtag_p.classList.add('hashtag_p')
    feed_content.appendChild(feed_content_Hashtag_p)

    // undefined 왜왜ㅙㅙㅙㅗ애ㅙㅐ
    let feed_content_hashtag = feed_container.querySelectorAll(
      'a[name="feed_content_hashtag"]'
    )
    for (let j = 0; j < feed_content_hashtag.length; j++) {
      feed_content_Hashtag_p.append(feed_content_hashtag.innerText)
    }

    const feed_content_div = document.createElement('div')
    feed_content_div.classList.add('feed_content_div')
    feed_content_Hashtag_p.after(feed_content_div)
    let feed_content_name = feed_container.querySelector(
      'p[name="feed_content"]'
    )
    feed_content_div.appendChild(feed_content_name)

    const feed_functionbar = document.createElement('div')
    feed_content_div.after(feed_functionbar)

    const heart_div = document.createElement('div')
    heart_div.classList.add('heart_div')
    heart_div.innerHTML = '<i class="far fa-heart"></i>'

    let heartValue = feed_container.querySelector('span[name="heartValue"]')

    const heart_span = document.createElement('span')
    heart_span.innerHTML = heartValue.innerText
    heart_div.appendChild(heart_span)

    feed_functionbar.appendChild(heart_div)

    const comment_div = document.createElement('div')
    comment_div.classList.add('comment_div')

    comment_div.innerHTML = '<i class="fal fa-comment"></i>'

    let cmtValue = feed_container.querySelector('span[name="cmtValue"]')

    const comment_span = document.createElement('span')
    comment_span.innerHTML = cmtValue.innerText
    comment_div.appendChild(comment_span)

    heart_div.after(comment_div)

    // 댓글창
    const feed_comment = document.createElement('div')
    feed_comment.classList.add('feed_comment')
    feed_functionbar.after(feed_comment)

    const comment_read_container = document.createElement('div')
    comment_read_container.classList.add('comment_read_container')
    feed_comment.appendChild(comment_read_container)

    const comment_read = document.createElement('div')
    comment_read.classList.add('comment_read')
    comment_read_container.appendChild(comment_read)

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
  }
}
