<template>
  <div>

    <!-- 搜索框 -->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入班级" suffix-icon="el-icon-message" class="ml-5" v-model="classname"></el-input>
      <el-input style="width: 200px" placeholder="请输入工号" suffix-icon="el-icon-position" class="ml-5" v-model="adminid"></el-input>
      <el-button class="ml-5" type="primary" @click="init">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>
    <!-- 按钮 -->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="password" label="密码" width="140">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="classname" label="班级">
      </el-table-column>
      <el-table-column prop="adminid" label="工号">
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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
          :current-page="pageNumber"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!-- 信息提示框 -->
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" :label-width="formLabelWidth">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-input v-model="form.classname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工号" :label-width="formLabelWidth">
          <el-input v-model="form.adminid" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dissave">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name:'User',
  data() {
    return {
      tableData: [],
      total:0,
      dialogFormVisible:false,
      headerBg: 'headerBg',
      form:{},
      formLabelWidth:'70px',
      pageNumber:1,
      pageSize:10,
      username:'',
      classname:'',
      adminid:'',
      multipleSelection:[]
    }
  },
  created(){
    this.init()
  },
  methods: {
    //初始化加载数据
    init(){
      this.$http.get("/admin/page",{
        params:{
          pageNumber:this.pageNumber,
          pageSize:this.pageSize,
          username:this.username,
          classname:this.classname,
          adminid:this.adminid
        }
      }
      ).then(res=>{
        // console.log(res);
        this.tableData=res.data.records
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
    // 重置搜索框
    reset(){
      this.username=''
      this.classname=''
      this.adminid=''
      this.init()
    },
    // 新增显示
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    // 保存添加数据
    save(){
      this.$http.post("/admin",this.form).then(res=>{
        if(res.data){
          this.$message.success('保存成功')
          this.dialogFormVisible=false
          this.init() //重新加载数据
        }else{
          this.$message.error("保存失败")
        }

      })
    },
    // 编辑取消
    dissave(){
      this.dialogFormVisible=false
      this.init()
    },
    // 修改用户数据
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    // 删除用户
    handleDel(id){
      this.$http.delete("/admin/"+id).then(res=>{
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
      this.$http.post("/admin/del/batch",ids).then(res=>{
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