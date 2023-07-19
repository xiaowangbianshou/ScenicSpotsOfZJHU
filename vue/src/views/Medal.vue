<template>
  <div>

    <!-- 搜索框 -->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
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
    </div>
    <!-- 表格 -->
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="name" label="勋章名称" width="140">
      </el-table-column>
      <el-table-column prop="type" label="勋章类型" width="120">
      </el-table-column>
      <el-table-column prop="condit" label="获得条件">
      </el-table-column>
      <el-table-column label="启用">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>
        </template>
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
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!-- 信息提示框 -->
    <el-dialog title="勋章信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small">
        <el-form-item label="勋章名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="勋章类型" :label-width="formLabelWidth">
          <el-input v-model="form.type" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="获得条件" :label-width="formLabelWidth">
          <el-input v-model="form.condit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="是否禁用" :label-width="formLabelWidth">
          <el-input v-model="form.enable" autocomplete="off"></el-input>
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
      pageNum:1,
      pageSize:10,
      username:'',
      classname:'',
      studentid:'',
      multipleSelection:[]
    }
  },
  created(){
    this.init()
  },
  methods: {
    //初始化加载数据
    init(){
      this.$http.get("/medal/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
        }
      }
      ).then(res=>{
        // console.log(res);
        this.tableData=res.data.records.reverse()
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
      this.classname=''
      this.studentid=''
      this.init()
    },
    // 新增显示
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    // 保存添加数据
    save(){
      this.$http.post("/medal",this.form).then(res=>{
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
      this.$http.delete("/medal/"+id).then(res=>{
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
      this.$http.post("/medal/del/batch",ids).then(res=>{
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