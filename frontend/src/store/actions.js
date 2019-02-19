// store/actions.js

import Constant from '../Constant.js'
import API from '../api/index.js'

import router from '../router'

export default {
  // [Constant.STRING]
  [Constant.LOGIN]: (store, payload) => {
    API.login(payload).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.HEADER, false)

        store.commit(Constant.USER, { id: response.data.result[0].id, name: response.data.result[0].name, token: response.data.result[0].accessToken })

        router.push({name: 'NoticeList'})
      }
    })
  },
  [Constant.LIST]: (store, payload) => {
    let str = store.state.gnb.menu
    console.log('[Constant.LIST] str: ', str)

    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`
    console.log('[Constant.LIST] uri: ', uri)

    API.list(str, payload.keyword, payload.option, payload.index).then((response) => {
      if (response.status === 200) store.commit(Constant.LIST, { result: response.data.result, paging: response.data.paging })
    })
  },
  [Constant.WRITE]: (store, payload) => {
    let str = store.state.gnb.menu
    console.log('[Constant.WRITE] str: ', str)

    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`
    console.log('[Constant.WRITE] uri: ', uri)

    // API.write('notice', payload).then((response) => {
    API.write(str, payload).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.WRITE)

        // router.push({name: 'NoticeList'})
        router.push({name: uri})
      }
    })
  },
  [Constant.VIEW]: (store, payload) => {
    let str = store.state.gnb.menu
    console.log('[Constant.VIEW] str: ', str)

    API.view(str, payload.number).then((response) => {
      if (response.status === 200) store.commit(Constant.VIEW, { result: response.data.result })
    })
  },
  [Constant.MODIFY]: (store, payload) => {
    let str = store.state.gnb.menu
    console.log('[Constant.MODIFY] str: ', str)

    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`
    console.log('[Constant.MODIFY] uri: ', uri)

    API.modify(str, payload.number, payload.id, payload.name, payload.category, payload.subject, payload.content).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.MODIFY)

        router.push({name: uri})
      }
    })
  },
  [Constant.DELETE]: (store, payload) => {
    let str = store.state.gnb.menu
    console.log('[Constant.MODIFY] str: ', str)

    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`
    console.log('[Constant.MODIFY] uri: ', uri)

    API.delete(str, payload.number, payload.id).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.DELETE)

        router.push({name: uri})
      }
    })
  }
}
