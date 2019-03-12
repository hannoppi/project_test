// api/index.js

import CONF from '../Config.js'
import axios from 'axios'

export default {
  login (payload) {
    return axios.post(CONF.LOGIN, payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },
  list (path, keyword, option, index) {
    return axios.get(CONF.LIST.replace('$path', path), {
      params: { keyword: keyword, option: option, page: index }
    })
  },
  write (path, payload) {
    return axios.post(CONF.WRITE.replace('$path', path), payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },
  view (path, number) {
    return axios.get(CONF.VIEW.replace('$path', path), {
      params: { number: number }
    })
  },
  modify (path, number, id, name, category, subject, content) {
    return axios.post(CONF.MODIFY.replace('$path', path), { number: number, id: id, name: name, category: category, subject: subject, content: content })
  },
  delete (path, number, id) {
    return axios.get(CONF.DELETE.replace('$path', path), {
      params: { number: number, id: id }
    })
  },
  confirmId (id) {
    console.log('[api/index.js] (confirmId) id: ', id)

    return axios.get(CONF.CONFIRMID, { params: { id: id } })
  },
  register (payload) {
    console.log('[api.js] payload: ', payload)

    return axios.post(CONF.REGISTER, payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  }
}
