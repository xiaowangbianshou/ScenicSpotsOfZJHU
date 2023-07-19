<template>
  <div>
    <el-main>
      <el-timeline>
        <el-timeline-item v-for="comment in comments" :key="comment.id" placement="top">
          <el-card>
            <h4>{{comment.content}}</h4>
            <p>{{comment.username}}用户 提交于 {{comment.commentdate.join('/')}} {{comment.commenttime.join(":")}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </el-main>
    <el-footer>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 7, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          class="pagina">
      </el-pagination>
      <el-input
        type="textarea"
        :rows="5"
        placeholder="请输入内容"
        class="inputtext"
        v-model="commentinfo.content">
      </el-input>
      <el-button type="primary" round class="pointbutton" style='left:0' @click="submit">留言</el-button>
    </el-footer>
    
  </div>
</template>

<script>
export default {
  data(){
    return{
      comments:'',
      total:0,
      pageNum:1,
      pageSize:5,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      commentinfo:{
        username:'',
        content:'',
        commentdate:'',
        commenttime:''
      }
    }
  },
  created(){
    this.init()
  },
  methods: {
    init(){
      this.$http.get("/comment/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:''
        }
      }
      ).then(res=>{
        // // console.log(res.data.records[0].gettime.join('-'));
        this.comments=res.data.records
        this.total=res.data.total
      })
    },
    //修改当前页面显示条数
    handleSizeChange(val){ 
      this.pageSize=val
      this.init()
    },
    //修改当前页码
    handleCurrentChange(val){
      this.pageNumber=val
      this.init()
    },
    submit(){
      let date = new Date()
      this.commentinfo.commentdate=date.toLocaleDateString().split('/').map(i => parseInt(i, 0))
      this.commentinfo.commenttime=date.toLocaleTimeString().split(':').map(i => parseInt(i,0))
      this.commentinfo.username=this.user.username
      this.$http.post('/comment',this.commentinfo).then(res=>{
        if(res.data){
          this.$message.success('提交成功')
          this.init()
        }else{
          this.$message.error("提交失败")
        }}
      )
      this.init()
    }
  },
}
</script>

<style lang='scss' scope>
.pointbutton{
    // position:relative;
    width: 100px;
    height: 40px;
    left: 70%;
    bottom: 30%;
  }
.pagina{
  margin: 0 40%;
}
.inputtext{
  width: 500px;
  padding-top: 20px;
}
</style>