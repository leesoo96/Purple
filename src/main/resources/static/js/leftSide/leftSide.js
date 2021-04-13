let logoutButton = document.querySelector('.logout')

logoutButton.addEventListener('click', function () {
  fetch(`/oauth2Typ`)
    .then((res) => res.json())
    .then((myJson) => {
      switch (myJson.result) {
        case '1':
          location.href = myJson.url
          break
        case '3':
        case '4':
          window.open(myJson.url, '', 'width=500, height=400', '_blank')

        case '2':

        default:
          location.href = '/logout'
      }
    })
})
