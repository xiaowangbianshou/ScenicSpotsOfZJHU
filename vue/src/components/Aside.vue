<template>
  <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
    <el-menu :default-openeds="['1','2']" style="min-height: 100%; overflow-x: hidden"
                background-color="rgb(48, 65, 86)"
                text-color="#fff"
                active-text-color="#ffd04b"
                :collapse-transition="false"
                :collapse="isCollapse"
                router
        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/avatar.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
            <b style="color: white" v-show="logoTextShow">后台管理系统</b>
          </div>
          <el-menu-item index="/admin">
              <i class="el-icon-house"></i>
              <span slot="title">主页</span>
              </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">系统管理</span>
            </template>
          <el-submenu index='2-1'>
            <template slot="title">人员管理</template>
            <el-menu-item index="/admin/adminer">
              <i class="el-icon-user"></i>
              <span slot="title">管理员管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/user">
              <i class="el-icon-user"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
            <!-- <el-menu-item index="/admin/role">
              <i class="el-icon-s-custom"></i>
              <span slot="title">角色管理</span>
            </el-menu-item> -->
            <el-menu-item index="/admin/menu">
              <i class="el-icon-menu"></i>
              <span slot="title">菜单管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index='2-2'>
            <template slot="title">勋章管理</template>
            <el-menu-item index="/admin/medal">
              <i class="el-icon-trophy"></i>
              <span slot="title">勋章开放管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/getmedal">
              <i class="el-icon-trophy"></i>
              <span slot="title">勋章获得管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index='2-3'>
            <template slot="title">景点管理</template>
            <el-menu-item index="/admin/point">
              <i class="el-icon-location-outline"></i>
              <span slot="title">景点管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/pointsubmit">
              <i class="el-icon-location-outline"></i>
              <span slot="title">景点提交管理</span>
            </el-menu-item>
          </el-submenu>
            
            <el-menu-item index="/admin/file">
              <i class="el-icon-folder"></i>
              <span slot="title">文件管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">留言</span>
            </template>
            <el-menu-item index="/admin/comment">
              <i class="el-icon-chat-dot-square"></i>
              <span slot="title">留言管理</span>
              </el-menu-item>
          </el-submenu>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name:'Aside',
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean,
    sideWidth:Number
  }
}
</script>

<style>

</style>