<template>
  <div class="module-right-body">
    <el-container>
      <el-main style="padding:20px 0 0 10px;height:600px">
        <el-form :model="PointForm" :rules="rules" label-width="auto" ref="ruleForm" size="small" class="demo-ruleForm" >
            <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            >
            <img v-if="PointForm.img" :src="PointForm.img" class="point-image">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <el-row class="rowclass">
            <el-col :span="12">
              <el-form-item label="编号:" prop="pointid">
                <el-input v-model="PointForm.pointid" style="width:125px" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="名称:" prop="name">
                <el-input v-model="PointForm.pointname" style="width:125px" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="rowclass">
            <el-col :span="12">
              <el-form-item label="区域:" prop="area">
                <el-select v-model="PointForm.area" placeholder="区域" style="width:125px">
                  <el-option label="东校区" value="东校区"></el-option>
                  <el-option label="中校区" value="中校区"></el-option>
                  <el-option label="西校区" value="西校区"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="大类:" prop="typebig">
                <el-select v-model="PointForm.typebig" placeholder="大类" style="width:125px">
                  <el-option label="风景" value="风景"></el-option>
                  <el-option label="建筑" value="建筑"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="rowclass">
            <el-col :span="12">
              <el-form-item label="小类:" prop="typesmall">
                <el-select v-model="PointForm.typesmall" placeholder="小类" style="width:125px">
                  <el-option label="水景" value="水景"></el-option>
                  <el-option label="公园" value="公园"></el-option>
                  <el-option label="树林" value="树林"></el-option>
                  <el-option label="广场" value="广场"></el-option>
                  <el-option label="道路" value="道路"></el-option>
                  <el-option label="教学" value="教学"></el-option>
                  <el-option label="办公" value="办公"></el-option>
                  <el-option label="宿舍" value="宿舍"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row  class="rowclass">
            <el-form-item label="简介" prop="descri">
              <el-input type="textarea" v-model="PointForm.descri" :rows="5"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </el-main>
      <el-footer style="padding:20px 0 0 10px;">
        <el-button type="primary" round class="pointbutton" @click="submit">提交</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Bus from './EventBus.js'
  export default {
    props:['Points'],
    data() {
      return {
        PointForm: {
          pointid: '',
          username:'',
          pointname: '',
          img:'',
          area: '',
          typebig: '',
          typesmall: '',
          descri: ''
        },
        GetModelInfo:{
          username:'',
          modelid:'',
        },
        user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
        flag:false,
        rules: {
          pointid: [
            { required: true, message: '请输入编号', trigger: 'blur' },
            // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          typebig: [
            { required: true, message: '请选择', trigger: 'change' }
          ],
          typesmall: [
            { required: true, message: '请选择', trigger: 'change' }
          ],
          area: [
            {  required: true, message: '请选择', trigger: 'change' }
          ],
          descri: [
            { required: true, message: '请填写简介', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      changefid(){
        Bus.$on('share',(val)=>{
          if(!this.flag){
            this.PointForm.pointid=val.value+1
            this.PointForm.pointname=this.Points[val.value].name
          }
          // this.getinfo(this.user.username,val.value)
          this.flag=false
        })
      },
      submit(){
        this.PointForm.username=this.user.username
        this.$http.post("/pointsubmit",this.PointForm).then(res=>{
        if(res.data){
          this.$message.success('提交成功')
          this.GetModelInfo.username=this.user.username
          this.GetModelInfo.modelid=parseInt(this.PointForm.pointid)+5
          this.$http.post("/getmodel",this.GetModelInfo).then(res=>{
            if(res.data){
              this.$message.success('获得新勋章')
            }
          })
        }else{
          this.$message.error("提交失败")
        }
      })
      },
      handleAvatarSuccess(res){
        this.PointForm.img=res
      }
    },
    created() {
      this.changefid()
    },
  }
</script>

<style lang='scss'>
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
    width: 350px;
    height: 270px;
    line-height: 300px;
    text-align: center;
  }
.point-image{
  width: 100%;
  height: 270px;
}
.pointbutton{
    position:relative;
    width: 100px;
    height: 40px;
    left: 70%;
    bottom: 30%;
  }
</style>