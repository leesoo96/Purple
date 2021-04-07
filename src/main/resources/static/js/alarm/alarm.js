readAlarm()
getAlarm()

function readAlarm() {
  fetch('/layout/readalarm/'+ user_pk.value)
  .then((res) =>res.json())
  .then((myJson) =>{
  })
}

const alarmTable = document.querySelector('.alarmTable')
function getAlarm() {
  fetch('/alarm/getalarm/' + user_pk.value)
  .then((res) => res.json())
  .then((myJson) => {
    for(let i = 0; i< myJson.length; i++){
      console.log(myJson[i])
      let tableTr = document.createElement('tr')
      if(myJson[i].alarm_category === 1){
        tableTr.setAttribute('onclick', `location.href="/userpage/${myJson[i].user_id}"`)
      } else {
        tableTr.setAttribute('onclick', `feedDetail(this, ${myJson[i].alarm_valuepk})`)
      }
      alarmTable.appendChild(tableTr)

      let imgTd = document.createElement('td')
      tableTr.appendChild(imgTd)

      let profileimg = document.createElement('img')
      profileimg.src = myJson[i].user_profileimg
      if(myJson[i].user_profileimg === null){
        profileimg.src = `/resources/img/common/basic_profile.png`
      }
      imgTd.appendChild(profileimg)

      let contentTd = document.createElement('td')
      tableTr.appendChild(contentTd)

      let content = document.createElement('span')
      if(myJson[i].alarm_category === 1){
        content.innerText = `${myJson[i].user_id} 님이 친구 추가를 하셨습니다.`
      } else if(myJson[i].alarm_category === 2) {
        content.innerText = `${myJson[i].user_id} 님이 게시글에 좋아요 표시를 하였습니다.`
      } else if (myJson[i].alarm_category === 3) {
        content.innerText = `${myJson[i].user_id} 님이 게시글에 댓글을 다셨습니다.`
      } else if (myJson[i].alarm_category === 4) {
        content.innerText = `${myJson[i].user_id} 님이 댓글에 댓글을 다셨습니다.`
      }
      contentTd.appendChild(content)

      let dateTd = document.createElement('td')
      tableTr.appendChild(dateTd)

      let date = document.createElement('small')
      date.innerText = `${myJson[i].alarm_date}`
      dateTd.appendChild(date)
    }
  })
}