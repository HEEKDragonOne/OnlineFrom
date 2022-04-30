import {createRouter, createWebHistory} from 'vue-router'
import {defineAsyncComponent} from "vue";
import request from "../request/request";

const router = createRouter({
    history: createWebHistory(),  // history 模式
    routes: [
        {
            path: '/',
            name: '首页',
            component: defineAsyncComponent(() => import('../views/index.vue')),
        }
    ]
})


export default router
