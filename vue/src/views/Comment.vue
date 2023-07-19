<template>
  <div>

    <!-- 搜索框 -->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-button class="ml-5" type="primary" @click="init">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>
    <!-- 按钮 -->
    <div style="margin: 10px 0">
      <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我在想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定批量删除当前选择行吗？"
            @confirm="delBatch"
          >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <!-- 表格 -->
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="content" label="留言内容" width="120">
      </el-table-column>
      <el-table-column prop="commentdate" label="留言日期">
      </el-table-column>
      <el-table-column prop="commenttime" label="留言时间">
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我在想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除当前行吗？"
            @confirm="handleDel(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name:'User',
  data() {
    return {
      tableData: [],
      total:0,
      headerBg: 'headerBg',
      form:{},
      formLabelWidth:'70px',
      pageNum:1,
      pageSize:10,
      username:'',
      multipleSelection:[]
    }
  },
  created(){
    this.init()
  },
  methods: {
    //初始化加载数据
    init(){
      this.$http.get("/comment/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username
        }
      }
      ).then(res=>{
        this.tableData=res.data.records
        this.tableData.forEach(item=>{
          item.commentdate=item.commentdate.join('/')
          item.commenttime[0]=item.commenttime[0]<10?'0'+item.commenttime[0]:item.commenttime[0]
          item.commenttime[1]=item.commenttime[1]<10?'0'+item.commenttime[1]:item.commenttime[1]
          item.commenttime[2]=item.commenttime[2]<10?'0'+item.commenttime[2]:item.commenttime[2]
          item.commenttime=item.commenttime.join(':')
        })
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
      this.pageNum=val
      this.init()
    },
    // 重置搜索框
    reset(){
      this.username=''
      this.init()
    },
    // 删除用户
    handleDel(id){
      this.$http.delete("/comment/"+id).then(res=>{
        if(res.data){
          this.$message.success('删除成功')
          this.init() //重新加载数据
        }else{
          this.$message.error("删除失败")
        }

      })
    },
    // 表格内选框赋值
    handleSelectionChange(val){
      this.multipleSelection=val
    },
    //批量删除数据
    delBatch(){
      let ids = this.multipleSelection.map(v=>v.id)
      this.$http.post("/comment/del/batch",ids).then(res=>{
        if(res.data){
          this.$message.success('批量删除成功')
          this.init() //重新加载数据
        }else{
          this.$message.error("批量删除失败")
        }

      })
    }
  }
}
</script>

<style>

</style>