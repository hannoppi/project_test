import Constant from '../Constant.js'
import API from '../api/index.js'

import router from '../router'

export default {
  [Constant.LOGIN]: (store, payload) => {
    return API.login(payload).then((response) => {
      let result

      if (response.status === 200) {
        result = response.data.result

        if (result !== -1 && result !== 0) {
          store.commit(Constant.HEADER, false)
          // store.commit(Constant.USER, { id: response.data.result[0].id, name: response.data.result[0].name, token: response.data.result[0].accessToken })

          store.commit(Constant.USER, { id: response.data.id, name: response.data.name, token: response.data.accessToken })

          router.push({ name: 'NoticeList' })
        }
      }

      return result
    })
  },
  [Constant.LIST]: (store, payload) => {
    let str = store.state.gnb.menu
    // let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`

    API.list(str, payload.keyword, payload.option, payload.index).then((response) => {
      if (response.status === 200) store.commit(Constant.LIST, { list: response.data.list, paging: response.data.paging })
    })
  },
  [Constant.WRITE]: (store, payload) => {
    let str = store.state.gnb.menu
    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`

    API.write(str, payload).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.WRITE)

        router.push({ name: uri })
      }
    })
  },
  [Constant.VIEW]: (store, payload) => {
    let str = store.state.gnb.menu

    API.view(str, payload.number).then((response) => {
      if (response.status === 200) store.commit(Constant.VIEW, { result: response.data.result })
    })
  },
  [Constant.MODIFY]: (store, payload) => {
    let str = store.state.gnb.menu
    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`

    API.modify(str, payload.number, payload.id, payload.name, payload.category, payload.subject, payload.content).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.MODIFY)

        router.push({ name: uri })
      }
    })
  },
  [Constant.DELETE]: (store, payload) => {
    let str = store.state.gnb.menu
    let uri = `${str.substring(0, 1).toUpperCase()}${str.substring(1, str.length).toLowerCase()}List`

    API.delete(str, payload.number, payload.id).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.DELETE)

        router.push({ name: uri })
      }
    })
  },
  [Constant.CONFIRMID]: (store, payload) => {
    return API.confirmId(payload.id).then((response) => {
      let result

      if (response.status === 200) {
        result = response.data.result[0].data

        console.log('(confirmId) response.data: ', response.data)
        store.commit(Constant.CONFIRMID, { result: response.data.result[0].data })
      }

      return result
    })
  },
  [Constant.REGISTER]: (store, payload) => {
    API.register(payload).then((response) => {
      if (response.status === 200) {
        store.commit(Constant.REGISTER)

        router.push({ name: 'Login' })
      }
    })
  }
}
