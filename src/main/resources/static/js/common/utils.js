'use strict'

function fetchAjax(params, method, url, func){
    if(method === 'post') {
        fetch(url, {
            method,
            headers: {
                'Content-Type': 'application/json',
            },
        body: JSON.stringify(params),
        }).then(res => res.json())
        .then((myJson) => {
            func(myJson)
        })
    } else if (method === 'get') {
        fetch(url + params, {
            method,
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(res => res.json())
        .then((myJson) => {
            func(myJson)
        })
    }
}

function getTimeStamp() {
    var d = new Date()
    var s =
        leadingZeros(d.getFullYear(), 4) +
        '-' +
        leadingZeros(d.getMonth() + 1, 2) +
        '-' +
        leadingZeros(d.getDate(), 2) +
        ' ' +
        leadingZeros(d.getHours(), 2) +
        ':' +
        leadingZeros(d.getMinutes(), 2) +
        ':' +
        leadingZeros(d.getSeconds(), 2)

    return s
}
  
function leadingZeros(n, digits) {
    let zero = ''
    n = n.toString()

    if (n.length < digits) {
        for (let i = 0; i < digits - n.length; i++) zero += '0'
    }
    return zero + n
}

function getTextLength(str) {
  let len = 0
  for (let i = 0; i < str.length; i++) {
    if (escape(str.charAt(i)).length == 6) {
      len++
    }
    len++
  }
  return len
}
