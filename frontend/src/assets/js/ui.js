/* ---------- ---------- ---------- ---------- ----------
홈페이지 (http://www.homepage.com/)
최종 수정일:

알림:
---------- ---------- ---------- ---------- ---------- */

var UI = (function () {
  /*
  var confirmId = function () {
    console.log('(confirmId) 아이디 중복확인을 클릭하였습니다.')

    var frm = arguments[0]
    var url = '/member/confirmId?id='

    if (frm.id.value === '') {
      alert('사용할 아이디를 입력해주세요.')

      return false
    }

    console.log('frm.id.value: ', frm.id.value)

    url += frm.id.value
    console.log('(confirmId) url: ', url)

    window.open(url, '_blank', 'toolbar=no, menubar=no, scrollbar=yes, resizable=no, width=480, height=320')
  } // confirmId
  */
  var confirmId = function () {
    console.log('confirmId()')
  } // confirmId

  var confirmSuccess = function () {
    console.log('(confirmSuccess) 확인을 클릭하였습니다.')

    var id = arguments[0]

    opener.document.getElementById('id').value = id
    opener.document.getElementById('confirmId').value = id

    self.close()
  } // confirmSuccess

  var confirmFrm = function () {
    console.log('(confirmFrm) 등록을 클릭하였습니다.')

    var frm = arguments[0]

    if (frm.confirmId.value.length === 0) {
      alert('(confirmFrm) 아이디 중복확인을 해주세요.')

      frm.confirmId.focus()

      return false
    }

    if (frm.password.value.length === 0) {
      alert('(confirmFrm) 패스워드를 입력해주세요.')

      frm.confirmId.focus()

      return false
    } else {
      if (frm.password.value !== frm.passwordCheck.value) {
        alert('(confirmFrm) 입력한 패스워드가 일치하지 않습니다.')

        frm.passwordCheck.focus()

        return false
      }
    }

    if (frm.quiz.value.length === 0) {
      alert('(confirmFrm) 퀴즈를 입력해주세요.')

      frm.quiz.focus()

      return false
    }

    if (frm.name.value.length === 0) {
      alert('(confirmFrm) 이름을 입력해주세요.')

      frm.name.focus()

      return false
    }

    frm.submit()
  } // confirmFrm

  var boardDelete = function () {
    var url = arguments[0]
    console.log('url: ', url)

    if (confirm('삭제 후 복구할 수 없습니다.\n정말로 삭제하시겠습니까?')) {
      location.href = url
    }
  } // boardDelete

  var designSelect = function () {
    // var selector = arguments[0].selector
    var evntTrgt = null
    var selected = null

    document.querySelector('.design_select select') && document.querySelector('.design_select select').addEventListener('change', function (event) {
      evntTrgt = event.target
      selected = evntTrgt[evntTrgt.selectedIndex].text

      evntTrgt.parentElement.children[1].innerText = selected
    })
  }

  var inputDefaultValue = function () {
    var i
    var inputfield = document.querySelectorAll(arguments[0].selector)

    for (i = 0; i < inputfield.length; i++) {
      inputfield[i].addEventListener('focus', function () {
        if (this.value === this.defaultValue) this.previousSibling.style.display = 'none'
      })

      inputfield[i].addEventListener('blur', function () {
        if (this.value === '') this.previousSibling.style.display = 'block'
      })
    }
  }

  return {
    callConfirmId: function () {
      return confirmId(arguments[0])
    },
    callConfirmSuccess: function () {
      return confirmSuccess(arguments[0])
    },
    callConfirmFrm: function () {
      return confirmFrm(arguments[0])
    },
    callBoardDelete: function () {
      return boardDelete(arguments[0])
    },
    callDesignSelect: function () {
      return designSelect(arguments[0])
    },
    callInputDefaultValue: function () {
      return inputDefaultValue(arguments[0])
    }
  }
}()) // UI

document.addEventListener('DOMContentLoaded', function () {
  // console.log('(Event) DOMContentLoaded')

  UI.callDesignSelect({selector: '#selectCategory'})

  UI.callInputDefaultValue({selector: '.inputfield_global'})

  document.querySelector('.confirmId') && document.querySelector('.confirmId').addEventListener('click', function () {
    var register = document.register

    UI.callConfirmId(register) // 회원가입: 아이디 중복확인
  }) // confirmId

  document.querySelector('form[name=\'egister\']') && document.querySelector('form[name=\'register\']').addEventListener('submit', function (event) {
    event.preventDefault()

    var register = document.register

    UI.callConfirmFrm(register) // 회원가입: 등록
  }) // confirmFrm

  document.querySelector('.button_close') && document.querySelector('.button_close').addEventListener('click', function (event) {
    this.ownerDocument.getElementsByClassName('layer_wrapper')[0].setAttribute('class', 'layer_wrapper')
  })

  document.querySelector('.link_thumbnail') && document.querySelector('.link_thumbnail').addEventListener('click', function (event) {
    var imgSrc = event.target.src

    document.querySelector('.layer_wrapper').classList.add('open')
    document.querySelector('.layer_wrapper').querySelector('.img_preview').setAttribute('src', imgSrc)
  })

  document.querySelector('.success') && document.querySelector('.success').addEventListener('click', function () {
    UI.callConfirmSuccess('') // 회원가입: 아이디 중복확인 성공
  }) // confirmSuccess

  document.querySelector('.button_comment') && document.querySelector('.button_comment').addEventListener('click', function () {
    var query = {
      board: document.querySelector('input[name = "board"]').value,
      id: document.querySelector('input[name = "id"]').value,
      name: document.querySelector('input[name = "name"]').value,
      content: document.querySelector('textarea[name = "comment"]').value,
      reference: document.querySelector('input[name = "reference"]').value,
      level: document.querySelector('input[name = "level"]').value
    }

    var xhr = new XMLHttpRequest()

    xhr.onreadystatechange = function () {
      console.log('onreadystatechange')

      console.log('xhr.readyState: ', xhr.readyState)
      if (xhr.readyState !== 4) {
        // readyState 4: 완료
        return
      }

      console.log('xhr.status: ', xhr.status)

      if (xhr.status === 200) {
        // status 200: 성공
        console.log('xhr.responseText: ', xhr.responseText)

        var data = JSON.parse(xhr.responseText)
        console.log('data: ', data)

        var result = data.result
        var html = ''
        var i
        var j
        var together

        console.log('result: ', result)

        if (result !== undefined) {
          for (i = 0; i < result.length; i++) {
            html += '<li class="item">'

            for (j = 0; j < result[i].value.length; j++) {
              together = result[j].value[j].together
              console.log('together: ', together)

              html += '<div>board: <em class="commentBoard">' + result[j].value[j].board + '</em></div>'
              html += '<div>number: <em class="commentNumber">' + result[j].value[j].number + '</em></div>'
              html += '<div>id: <em class="commentId">' + result[j].value[j].id + '</em></div>'
              html += '<div>name: <em class="commentName">' + result[j].value[j].name + '</em></div>'
              html += '<div>content: <em class="commentContent">' + result[j].value[j].content + '</em></div>'
              html += '<div>reference: <em class="commentReference">' + result[j].value[j].reference + '</em></div>'
              html += '<div>level: <em class="commentLevel">' + result[j].value[j].level + '</em></div>'
              html += '<div>sequence: <em class="commentSequence">' + result[j].value[j].sequence + '</em></div>'
              html += '<div>together: <em class="commentTogether">' + result[j].value[j].together + '</em></div>'
              html += '<div>regdate: <em class="commentRegdate">' + result[j].value[j].regdate + '</em></div>'
              html += '<button type="button" class="button_activate">답변</button>'
              html += '<hr />'
              html += '<div class="wrap_activate"></div>'
            }

            html += '</li>'
          }

          console.log('html: ', html)

          document.querySelector('.comment_list').appendChild(html)
        }
      } else {
        console.log('xhr.status: ', xhr.status)
        console.log('xhr.statusText: ', xhr.statusText)
      }
    }

    xhr.open('POST', '/admin/board/comment.action')
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    xhr.send('board=' + query.board + '&id=' + query.id + '&name=' + query.name + '&content=' + query.content + '&reference=' + query.reference + '&level=' + query.level)
  })
})
