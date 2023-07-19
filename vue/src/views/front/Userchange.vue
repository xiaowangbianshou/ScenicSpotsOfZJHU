<template>
  <el-card class="person-contain">
    <el-form :model="form" size="small">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:9090/file/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatar" :src="form.avatar" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="班级" :label-width="formLabelWidth">
        <el-input v-model="form.classname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学号" :label-width="formLabelWidth">
        <el-input v-model="form.studentid" autocomplete="off"></el-input>
      </el-form-item>
       <el-form-item label-width="200px">
        <el-button  @click="dissave">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name:'Userchange',
  data(){
    return{
      form:{},
      formLabelWidth:'70px',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created(){
    this.$http.get("/user/username/"+this.user.username).then(res=>{
      if(res.code === "200"){
        this.form =res.data
      }
    })
  },
  methods:{
    save(){
      this.$http.post("/user",this.form).then(res=>{
        if(res.data){
          this.$message.success('保存成功')
          this.$emit("refreshUser")
          // this.init() //重新加载数据
          this.$router.push('/home')
        }else{
          this.$message.error("保存失败")
        }

      })
    },
    dissave(){
      this.$router.push('/home')
    },
    handleAvatarSuccess(res){
      this.form.avatar = res
    }
  }
}
</script>

<style lang="scss" scoped>
  .person-contain{
    width: 500px;
    padding: 20px;
  }
  .avatar-uploader{
    text-align: center;
    padding-bottom: 20px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 138px;
    height: 138px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 138px;
    height: 138px;
    display: block;
  }
</style>>
