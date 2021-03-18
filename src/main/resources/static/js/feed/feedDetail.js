// feedDetail close only
const feed_overlay_close = document.querySelector('.fa-times')
feed_overlay_close.onclick = function () {
  feed_overlay.style.display = 'none'
}

// feedDetail
const feed_overlay = document.querySelector('.feed_overlay')
const detail_previous = document.querySelector('.detail_previous')

function feedDetail(e) {
  feed_overlay.style.display = 'block' // feedDetail show

  const feed_container = e.parentNode

  let feedImg = feed_container.querySelectorAll('.feed_imgList img')
  let detailImgClass = document.createElement('div')

  detail_previous.after(detailImgClass)

  for (let i = 0; i < feedImg.length; i++) {
    let detailImg = feedImg[i].cloneNode(false)
    detailImgClass.append(detailImg)
  }

  const feedDetail_title = document.querySelector('.feedDetail_title')
  let feedDetail_profileImg = document.createElement('img')
  feedDetail_profileImg.innerHTML = (
    <img
      src="resources/img/common/basic_profile.png"
      alt="기본프로필사진"
    ></img>
  )

  const detailSpan = document.querySelector('span')
  detailSpan.innerText = feed_container.querySelector('span[name="user_id"]')
}
