<template>
  <el-container style="min-height: 100vh">
    <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" :sideWidth="sideWidth"></Aside>
    <el-container style="height:100%">
      <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapse="collapse" :collapseBtnClass="collapseBtnClass" :user="user"></Header>
      </el-header>
      <el-main>
        <router-view @refreshUser="getUser"></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
import Aside from '../components/Aside.vue'
import Header from '../components/Header.vue'
export default {
  name: 'Home',
  components:{
    Aside,
    Header
  },
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 230,
      logoTextShow: true,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    //初始化加载数据
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 230
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    getUser(){
      this.$http.get("/user/username/"+this.user.username).then(res=>{
        this.user=res.data
      })
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>