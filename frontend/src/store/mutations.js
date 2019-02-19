// store/mutations.js

import Constant from '../Constant.js'

export default {
  [Constant.USER]: (state, payload) => {
    localStorage.setItem('id', payload.id)
    localStorage.setItem('name', payload.name)
    localStorage.setItem('auth_token', payload.token)

    // localStorage.setItem('id', payload.id)
    state.user.id = localStorage.getItem('id')
    console.log('(mutations.js) state.user.id: ', state.user.id)

    // localStorage.setItem('name', payload.name)
    state.user.name = localStorage.getItem('name')
    console.log('(mutations.js) state.user.name: ', state.user.name)
  },
  [Constant.HEADER]: (state, payload) => {
    state.header = payload
  },
  [Constant.GNB]: (state, payload) => {
    state.gnb.menu = payload.menu
  },
  [Constant.LIST]: (state, payload) => {
    state.boardlist = payload.result
    console.log('(mutations.js) [Constant.LIST] state.boardlist: ', state.boardlist)

    /*
    state.paging.listCount = payload.paging.listCount
    console.log('(mutations.js) [Constant.LIST] state.paging.listCount: ', state.paging.listCount)

    state.paging.currentPage = payload.paging.currentPage
    console.log('(mutations.js) [Constant.LIST] state.paging.currentPage: ', state.paging.currentPage)

    state.paging.maxPage = payload.paging.maxPage
    console.log('(mutations.js) [Constant.LIST] state.paging.maxPage: ', state.paging.maxPage)

    state.paging.startPage = payload.paging.startPage
    console.log('(mutations.js) [Constant.LIST] state.paging.startPage: ', state.paging.startPage)

    state.paging.endPage = payload.paging.endPage
    console.log('(mutations.js) [Constant.LIST] state.paging.endPage: ', state.paging.endPage)
    */
  },
  [Constant.WRITE]: () => {
    console.log('(mutations.js) [Constant.WRITE]')
  },
  [Constant.VIEW]: (state, payload) => {
    state.boardview = payload.result
    console.log('(mutations.js) [Constant.VIEW] state.boardview: ', state.boardview)
  },
  [Constant.MODIFY]: () => {
    console.log('(mutations.js) [Constant.MODIFY]')
  },
  [Constant.DELETE]: () => {
    console.log('(mutations.js) [Constant.DELETE]')
  }
}
