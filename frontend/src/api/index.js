// api/index.js

import CONF from '../Config.js'
import axios from 'axios'

export default {
  login (payload) {
    return axios.post(CONF.LOGIN, payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })

    /*
    return axios.post(CONF.LOGIN, {
      id: id,
      password: password
    })
    */

    /*
    return axios.post(CONF.LOGIN, payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    */
  },
  list (path, keyword, option, index) {
    return axios.get(CONF.LIST.replace('^path', path), {
      params: {
        keyword: keyword,
        option: option,
        page: index
      }
    })
  },
  /*
  write (path, id, name, category, subject, content) {
  */
  write (path, payload) {
    /*
    for (var key of payload.entries()) {
      console.log('[api/index.js] ' + key[0] + ': ' + key[1])
    }
    */

    /*
    return axios({
      method: 'post',
      url: CONF.WRITE.replace('^path', path),
      data: payload,
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    */
    console.log('##### path: ', path)
    console.log(CONF.WRITE.replace('^path', path))
    return axios.post(CONF.WRITE.replace('^path', path), payload, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },
  view (path, number) {
    return axios.get(CONF.VIEW.replace('^path', path), {
      params: {
        number: number
      }
    })
  },
  modify (path, number, id, name, category, subject, content) {
    return axios.post(CONF.MODIFY.replace('^path', path), {
      number: number,
      id: id,
      name: name,
      category: category,
      subject: subject,
      content: content
    })
  },
  delete (path, number, id) {
    return axios.get(CONF.DELETE.replace('^path', path), {
      params: {
        number: number,
        id: id
      }
    })
  }
}
