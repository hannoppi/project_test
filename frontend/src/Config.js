// Config.js

const BASE = '/api'

export default {
  LOGIN: BASE + '/admin/vuejs/login.action',
  LIST: BASE + '/admin/vuejs/^path/list.do',
  WRITE: BASE + '/admin/vuejs/^path/write.action',
  VIEW: BASE + '/admin/vuejs/^path/view.do',
  MODIFY: BASE + '/admin/vuejs/^path/modify.action',
  DELETE: BASE + '/admin/vuejs/^path/delete.action'
}
