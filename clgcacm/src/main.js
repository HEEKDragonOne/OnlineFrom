import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElIcons from '@element-plus/icons-vue'
import request from "./request/request.js";


const app = createApp(App)

for (const icname in ElIcons) {
    app.component(icname, ElIcons[icname])
}
app.config.globalProperties.$http = request
app.use(ElementPlus)
app.use(router)
app.use(store)
app.mount('#app')
