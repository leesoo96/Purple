'use strict'

function makeFeedAjax(category, page_count, url) {
  return new Promise(function (resolve) {
    let params = {
      category: category,
      page_count: page_count,
    }
    fetchAjax(params, 'post', url, resolve)
  })
}

function makeFeed(myJson, cotainer) {
  if (myJson.result.length === 0) {
    page_count--
    return
  }
  
  for (let i = 0; i < myJson.result.length; i++) {
    // feed_container 생성
    let feed_containerEle = document.createElement('div')
    feed_containerEle.className = 'feed_container'

    // feedtitle 생성
    let feed_titleEle = document.createElement('div')
    feed_titleEle.className = 'feed_title'
    feed_containerEle.appendChild(feed_titleEle)
    let imgEle = document.createElement('img')
    imgEle.src = `/resources/img/common/basic_profile.png`
    if (myJson.result[i].user_profileimg !== null) {
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

    if (myJson.result[i].feed_userpk == document.querySelector('#user_pk').value) {
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
      feedMenuLi1.setAttribute(
        'onclick',
        `delFeed(${myJson.result[i].feed_pk})`
      )
      feedMenuUl.appendChild(feedMenuLi1)
    }

    // 이미지
    if (myJson.result[i].media_url.length > 0) {
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
      for (let j = 0; j < myJson.result[i].media_url.length; j++) {
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
    if (myJson.result[i].hashtag_ctnt.length > 0) {
      for (let k = 0; k < myJson.result[i].hashtag_ctnt.length; k++) {
        let hashtagA = document.createElement('a')
        let hashtag_ctnt = `${myJson.result[i].hashtag_ctnt[k].hashtag_ctnt}`
        hashtag_ctnt = hashtag_ctnt.split('#')[1]
        hashtagA.href = '/search/' + hashtag_ctnt
        hashtagA.innerText = myJson.result[i].hashtag_ctnt[k].hashtag_ctnt
        feed_contentDiv.appendChild(hashtagA)
      }
    }
    let detailDiv = document.createElement('div')
    detailDiv.setAttribute(
      'onclick',
      `feedDetail(this, ${myJson.result[i].feed_pk})`
    )
    feed_contentDiv.appendChild(detailDiv)

    let feed_ctntP = document.createElement('p')
    feed_ctntP.innerText = `${myJson.result[i].feed_ctnt}`
    detailDiv.appendChild(feed_ctntP)

    feed_containerEle.appendChild(feed_contentDiv)

    let feed_functionbarEle = document.createElement('div')
    feed_functionbarEle.className = 'feed_functionbar'

    let commentI = document.createElement('i')
    commentI.setAttribute(
      'onclick',
      `feedDetail(this, ${myJson.result[i].feed_pk})`
    )
    commentI.className = 'fal fa-comment'
    commentI.innerHTML = `${myJson.result[i].comment_count}`
    feed_functionbarEle.appendChild(commentI)

    let favoriteI = document.createElement('i')
    if (myJson.result[i].favorite_state === 1) {
      favoriteI.className = 'fas fa-heart'
    } else {
      favoriteI.className = 'far fa-heart'
    }
    favoriteI.innerHTML = `${myJson.result[i].favorite_count}`
    favoriteI.setAttribute(
      'onclick',
      `feedFavorite(this, ${myJson.result[i].feed_pk})`
    )
    feed_functionbarEle.appendChild(favoriteI)

    let bookmarkI = document.createElement('i')

    if (myJson.result[i].bookmark_state === 1) {
      bookmarkI.className = 'fas fa-bookmark'
    } else {
      bookmarkI.className = 'far fa-bookmark'
    }
    bookmarkI.setAttribute(
      'onclick',
      `feedBookmark(this, ${myJson.result[i].feed_pk})`
    )
    feed_functionbarEle.appendChild(bookmarkI)

    feed_containerEle.appendChild(feed_functionbarEle)

    cotainer.appendChild(feed_containerEle)
  }
}

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

function feedFavorite(e, feed_pk) {
  let favorite_state = 0
  const function_bar = e.parentNode

  let favoriteI = function_bar.querySelector('.fa-heart')
  if (favoriteI.className === 'fas fa-heart') {
    favorite_state = 1
  } else {
    favorite_state = 0
  }
  let params = {
    favorite_feedpk: feed_pk,
    favorite_state,
  }
  fetchAjax(params, 'post', '/feed/favorite', (myJson) => {
    if (myJson.result.favorite_state == 0) {
      favoriteI.className = 'far fa-heart'
    } else {
      favoriteI.className = 'fas fa-heart'
      sendAlarm(2, feed_pk, e.parentNode.parentNode.querySelector('span').innerText)
    }
    favoriteI.innerHTML = myJson.result.favorite_count
  })
}

// Feed Bookmark
function feedBookmark(e, feed_pk) {
  let bookmark_state = 0
  const function_bar = e.parentNode
  let bookmarkI = function_bar.querySelector('.fa-bookmark')
  if (bookmarkI.className === 'fas fa-bookmark') {
    bookmark_state = 1
  } else {
    bookmark_state = 0
  }
  let params = {
    bookmark_feedpk: feed_pk,
    bookmark_state,
  }
  fetchAjax(params, 'post', '/feed/bookmark', (myJson) => {
    if (myJson.result.bookmark_state == 0) {
      bookmarkI.className = 'far fa-bookmark'
    } else {
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