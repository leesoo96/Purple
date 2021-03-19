'use strict'

const add_friend_btn = document.querySelector('#add_friend_btn')

add_friend_btn.addEventListener('click', () =>{
    addFriendAjax()

    function addFriendAjax() {
        let user_pk = document.querySelector('#user_pk').value
        let friend_pk = document.querySelector('#friend_pk').value

        let addFriendParam = {
            user_pk: user_pk,
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
                if(myJson.result === 1) {
                    alert('친구 등록이 완료되었습니다.')
                    return
                }
                alert('이미 친구입니다.')
            })
            
    }

})