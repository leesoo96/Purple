let search_contentEle = document.querySelector('.search-form')
const search_content = document.querySelector('.search_content')
const search_input = document.querySelector('.search-input')

//유저 검색 리스트

function enterkey() {
  let search_check = document.querySelector('input[name="type"]:checked').value
  const form = document.querySelector('.search-form')

  if (search_check == 0) {
    let param = {
      search_user_id: form.search_input.value,
    }
    fetch('/search/searchUser', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if(myJson.length == 0) {
          search_content.querySelectorAll('*').forEach((test) => test.remove())
          let nosearchDiv = document.createElement('div')
          nosearchDiv.innerHTML = '검색 결과가 없습니다.'
          search_content.appendChild(nosearchDiv)
          return
        }
        search_content.querySelectorAll('*').forEach((test) => test.remove())
        user_list(myJson)
      })
  } else if (search_check == 1) {
    let param = {}
    fetch('/search/searchFeed', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then((myJson) => {
        console.log(myJson)
        search_content.querySelectorAll('*').forEach((test) => test.remove())
      })
      .then((myJson) => {})
  } else if (search_check == 2) {
    let param = {}
    fetch('/search/searchHashtag', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    })
      .then((res) => res.json())
      .then((myJson) => {
        search_content.querySelectorAll('*').forEach((test) => test.remove())
      })
      .then((myJson) => {})
  }

  function user_list(myJson) {
    let search_table = document.createElement('table')
    for (let i = 0; i < myJson.length; i++) {
      let search_tr = document.createElement('tr')
      search_table.appendChild(search_tr)

      let search_td = document.createElement('td')
      search_tr.appendChild(search_td)

      let user_img = document.createElement('img')
      if (myJson[i].user_profileimg == null) {
        user_img.src = '/resources/img/common/basic_profile.png'
      } else {
        user_img.src = `${myJson[i].user_profileimg}`
      }
      user_img.id = 'profileImg'
      user_img.alt = '기본프로필사진'
      search_td.appendChild(user_img)

      let user_id = document.createElement('a')
      user_id.href = `/userpage/${myJson[i].user_id}`
      search_td.appendChild(user_id)

      let user_id_span = document.createElement('span')
      user_id_span.id = 'writer'
      user_id_span.innerHTML = `@${myJson[i].user_id}`
      user_id.appendChild(user_id_span)

      console.log(`${myJson[i].user_bio}`)
      let user_bio = document.createElement('span')
      user_bio.id = 'content'
      user_bio.innerHTML = `${myJson[i].user_bio}`
      if(myJson[i].user_bio === null) {
        user_bio.innerHTML = '상태 메세지가 없습니다.'
      } 
      search_td.appendChild(user_bio)
      let friend_pk = `${myJson[i].user_pk}`
      let addFriendParam = {
        user_pk: document.querySelector('#user_pk').value,
        friend_pk: friend_pk,
      }
      console.log(addFriendParam.friend_pk)
      fetch('/layout/friendCheck', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(addFriendParam),
      })
        .then((res) => res.json())
        .then((myJson) => {
          if (myJson.result == 1) {
            let user_tag = document.createElement('div')

            user_tag.setAttribute('onclick', `addFriendFunc(${friend_pk})`)
            search_td.appendChild(user_tag)

            let user_teg_i = document.createElement('i')
            user_teg_i.className = 'fas fa-user-plus'
            user_tag.appendChild(user_teg_i)
          }
        })
      search_content.appendChild(search_table)
    }
  }
}
function addFriendFunc(friend_pk) {
  if (confirm('친구 추가 하시겠습니까?')) {
    let addFriendParam = {
      user_pk: document.querySelector('#user_pk').value,
      friend_pk: friend_pk,
    }
    fetch('/layout/addNewFriend', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(addFriendParam),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result === 1) {
          alert('친구 등록이 완료되었습니다.')
          return
        }
        alert('이미 등록된 친구입니다.')
      })
  }
}

function feed(myJson) {
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

  document.addEventListener('DOMContentLoaded', async function () {
    // HTML과 script가 로드된 시점에 발생하는 이벤트.
    await makeFeedAjax(
      document.querySelector('select[name="feed"]').value,
      page_count
    ).then((myJson) => {
      makeFeed(myJson)
    })
    page_count++
    await ajax()
    function ajax() {
      if (document.body.scrollHeight <= windowHeight) {
        makeFeedAjax(
          document.querySelector('select[name="feed"]').value,
          page_count
        ).then((myJson) => {
          makeFeed(myJson)
        })
        page_count++
      }
    }
  })

  document
    .querySelector('select[name="feed"]')
    .addEventListener('change', () => {
      page_count = 0

      document
        .querySelector('#feed')
        .querySelectorAll('*')
        .forEach((test) => test.remove())

      makeFeedAjax(
        document.querySelector('select[name="feed"]').value,
        page_count
      )
        .then((myJson) => {
          makeFeed(myJson)
          page_count++
        })
        .then(() => {
          let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
          let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이
          if (scrollLocation + windowHeight >= fullHeight) {
            makeFeedAjax(
              document.querySelector('select[name="feed"]').value,
              page_count
            ).then((myJson) => {
              makeFeed(myJson)
            })
            page_count++
          }
        })
    })

  document.addEventListener('scroll', () => {
    let scrollLocation = document.documentElement.scrollTop // 현재 스크롤바 위치
    let fullHeight = document.body.scrollHeight // 스크롤 포함 전체 길이

    if (scrollLocation + windowHeight >= fullHeight) {
      makeFeedAjax(
        document.querySelector('select[name="feed"]').value,
        page_count
      ).then((myJson) => {
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
        page_count: page_count,
      }
      fetch('/feed', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(params),
      })
        .then((res) => res.json())
        .then(function (myJson) {
          resolve(myJson)
        })
    })
  }
  const feedEle = document.querySelector('#feed')
  function makeFeed(myJson) {
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

      let feedMenuI = document.createElement('i')
      feedMenuI.className = 'fas fa-ellipsis-h'
      feed_titleEle.appendChild(feedMenuI)

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
          hashtagA.setAttribute('href', '#')
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

      feedEle.appendChild(feed_containerEle)
    }
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
    fetch('/feed/favorite', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result.favorite_state == 0) {
          favoriteI.className = 'far fa-heart'
        } else {
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
    if (bookmarkI.className === 'fas fa-bookmark') {
      bookmark_state = 1
    } else {
      bookmark_state = 0
    }
    let params = {
      bookmark_feedpk: feed_pk,
      bookmark_state,
    }
    fetch('/feed/bookmark', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    })
      .then((res) => res.json())
      .then((myJson) => {
        if (myJson.result.bookmark_state == 0) {
          bookmarkI.className = 'far fa-bookmark'
        } else {
          bookmarkI.className = 'fas fa-bookmark'
        }
      })
  }
}
