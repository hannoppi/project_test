import Vue from 'vue'
import Router from 'vue-router'

import Modify from '@/components/container/member/Modify.vue'
import Login from '@/components/container/member/Login.vue'
import Register from '@/components/container/member/Register.vue'
import Id from '@/components/container/member/Id.vue'
import Password from '@/components/container/member/Password.vue'

import NoticeList from '@/components/container/notice/NoticeList.vue'
import NoticeWrite from '@/components/container/notice/NoticeWrite.vue'
import NoticeView from '@/components/container/notice/NoticeView.vue'
import NoticeModify from '@/components/container/notice/NoticeModify.vue'

import BoardList from '@/components/container/board/BoardList.vue'
import BoardWrite from '@/components/container/board/BoardWrite.vue'
import BoardView from '@/components/container/board/BoardView.vue'
import BoardModify from '@/components/container/board/BoardModify.vue'

import GalleryList from '@/components/container/gallery/GalleryList.vue'
import GalleryWrite from '@/components/container/gallery/GalleryWrite.vue'
import GalleryView from '@/components/container/gallery/GalleryView.vue'
import GalleryModify from '@/components/container/gallery/GalleryModify.vue'

import DownloadList from '@/components/container/download/DownloadList.vue'
import DownloadWrite from '@/components/container/download/DownloadWrite.vue'
import DownloadView from '@/components/container/download/DownloadView.vue'
import DownloadModify from '@/components/container/download/DownloadModify.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    { path: '*', component: Login },
    { path: '/member/modify', name: 'Modify', component: Modify, meta: { requiresAuth: true } },
    { path: '/member/login', name: 'Login', component: Login, meta: { requiresAuth: true } },
    { path: '/member/register', name: 'Register', component: Register, meta: { requiresAuth: true } },
    { path: '/member/id', name: 'Id', component: Id, meta: { requiresAuth: true } },
    { path: '/member/password', name: 'Password', component: Password, meta: { requiresAuth: true } },

    { path: '/notice/list', name: 'NoticeList', component: NoticeList },
    { path: '/notice/write', name: 'NoticeWrite', component: NoticeWrite },
    { path: '/notice/view/:number', name: 'NoticeView', component: NoticeView, props: true },
    { path: '/notice/modify/:number', name: 'NoticeModify', component: NoticeModify, props: true },

    { path: '/board/list/', name: 'BoardList', component: BoardList },
    { path: '/board/write', name: 'BoardWrite', component: BoardWrite },
    { path: '/board/view/:number', name: 'BoardView', component: BoardView, props: true },
    { path: '/board/modify/:number', name: 'BoardModify', component: BoardModify, props: true },

    { path: '/gallery/list/', name: 'GalleryList', component: GalleryList },
    { path: '/gallery/write', name: 'GalleryWrite', component: GalleryWrite },
    { path: '/gallery/view/:number', name: 'GalleryView', component: GalleryView, props: true },
    { path: '/gallery/modify/:number', name: 'GalleryModify', component: GalleryModify, props: true },

    { path: '/download/list/', name: 'DownloadList', component: DownloadList },
    { path: '/download/write', name: 'DownloadWrite', component: DownloadWrite },
    { path: '/download/view/:number', name: 'DownloadView', component: DownloadView, props: true },
    { path: '/download/modify/:number', name: 'DownloadModify', component: DownloadModify, props: true }
  ]
})

router.beforeEach(function (to, from, next) {
  let token = localStorage.getItem('auth_token')

  if (to.matched.some(function (routeInfo) {
    return !routeInfo.meta.requiresAuth
  })) {
    // 로그인이 필요한 페이지입니다. 토큰을 확인합니다.
    // 토큰은 존재하지 않고 to.path가 로그인 페이지(/member/login)가 아닙니다.
    if (!token && to.path !== '/member/login') {
      if (!token) {
        // 토큰이 존재하지 않습니다. 로그인 페이지(/member/login)로 이동합니다.
        next('/member/login')
      } else {
        // 토큰이 존재합니다. 관리자 페이지(/notice/list)로 이동합니다.
        next('/notice/list')
      }
    } else {
      // 토큰은 존재하고 로그인 페이지(/member/login)는 아닙니다.

      if (token && to.path === '/') next('/notice/list') // 토큰이 존재하고 to.path가 / 일 경우..
    }
  } else {
    // 로그인이 필요없는 페이지입니다. 토큰을 확인합니다.

    if (token) next('/notice/list') // 토큰이 존재합니다. 관리자 페이지(/notice/list)로 이동합니다.
  }

  next()
})

export default router
