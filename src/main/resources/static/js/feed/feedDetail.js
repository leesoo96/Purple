"use strict"

// feed_content 클릭 이벤트
let feed_content = document.querySelector(".feed_content")
let feed_overlay = document.querySelector(".feed_overlay")
feed_content.onclick = function () {
  feed_overlay.style.display = "block"
}

const feed_overlay_close = document.querySelector(".fa-times")
feed_overlay_close.onclick = function () {
  feed_overlay.style.display = "none"
}
