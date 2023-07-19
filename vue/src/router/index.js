import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store/store";

Vue.use(VueRouter)

const routes = [
    { path: '/', redirect: '/login' },
    {
        path: '/login',
        component: () =>
            import ('../views/front/UserLogin.vue')
    },
    {
        path: '/home',
        component: () =>
            import ('../views/front/Main.vue'),
        redirect: "/home/welcome",
        children: [{
                path: 'welcome',
                name: '主页',
                component: () =>
                    import ('../views/front/Welcome.vue')
            },
            {
                path: 'point',
                name: '景点',
                component: () =>
                    import ('../views/front/Point.vue')
            },
            {
                path: 'comment',
                name: '留言',
                component: () =>
                    import ('../views/front/Comment.vue')
            },
            {
                path: 'userinfo',
                name: '个人信息',
                component: () =>
                    import ('../views/front/Userchange.vue')
            },
        ]
    },
    {
        path: '/admin/login',
        component: () =>
            import ('../views/login.vue')
    },
    {
        path: '/register',
        component: () =>
            import ('../views/register.vue')
    },
    {
        path: '/admin',
        name: '后台',
        component: () =>
            import ('../views/Manage.vue'),
        redirect: "/admin/home",
        children: [{
                path: 'home',
                name: '主页',
                component: () =>
                    import ('../views/Home.vue')
            },
            {
                path: 'adminer',
                name: '管理员管理',
                component: () =>
                    import ('../views/Admin.vue')
            },
            {
                path: 'user',
                name: '用户管理',
                component: () =>
                    import ('../views/User.vue')
            },
            {
                path: 'person',
                name: '个人信息',
                component: () =>
                    import ('../views/Person.vue')
            },
            {
                path: 'role',
                name: '角色管理',
                component: () =>
                    import ('../views/Role.vue')
            },
            {
                path: 'menu',
                name: '菜单管理',
                component: () =>
                    import ('../views/Menu.vue')
            },
            {
                path: 'file',
                name: '文件管理',
                component: () =>
                    import ('../views/File.vue')
            },
            {
                path: 'medal',
                name: '奖牌管理',
                component: () =>
                    import ('../views/Medal.vue')
            },
            {
                path: 'getmedal',
                name: '奖牌获得管理',
                component: () =>
                    import ('../views/GetModel.vue')
            },
            {
                path: 'point',
                name: '景点管理',
                component: () =>
                    import ('../views/Points.vue')
            },
            {
                path: 'pointsubmit',
                name: '景点提交管理',
                component: () =>
                    import ('../views/Pointsubmit.vue')
            },
            {
                path: 'comment',
                name: '留言管理',
                component: () =>
                    import ('../views/Comment.vue')
            },
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name) // 设置当前的路由名称，为了在Header组件中去使用
    store.commit("setPath") // 触发store的数据更新
    next() // 放行路由
})

export default router