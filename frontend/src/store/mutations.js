import Constant from '../Constant.js'

export default {
  [Constant.USER]: (state, payload) => {
    console.log('[mutations.js] id: ', payload.id)
    console.log('[mutations.js] name: ', payload.name)
    console.log('[mutations.js] auth_token: ', payload.token)

    localStorage.setItem('id', payload.id)
    localStorage.setItem('name', payload.name)
    localStorage.setItem('auth_token', payload.token)

    // state.user.id = localStorage.getItem('id')
    // console.log('[mutations.js] state.user.id: ', state.user.id)

    // state.user.name = localStorage.getItem('name')
    // console.log('[mutations.js] state.user.name: ', state.user.name)
  },
  [Constant.HEADER]: (state, payload) => {
    state.header = payload
  },
  [Constant.GNB]: (state, payload) => {
    state.gnb.menu = payload.menu
  },
  [Constant.LIST]: (state, payload) => {
    state.boardlist = payload.list

    Object.keys(payload.paging).forEach(function (key) {
      state.paging[key] = payload.paging[key]
    })
  },
  [Constant.WRITE]: () => {

  },
  [Constant.VIEW]: (state, payload) => {
    state.boardview = payload.result
  },
  [Constant.MODIFY]: () => {

  },
  [Constant.DELETE]: () => {

  },
  [Constant.CONFIRMID]: (state, payload) => {
    console.log('[Constant.CONFIRMID]: ', payload.result)
    state.confirmResult = payload.result
  },
  [Constant.REGISTER]: () => {

  }
}
