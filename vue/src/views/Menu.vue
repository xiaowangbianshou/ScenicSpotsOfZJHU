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
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  
              row-key="id" default-expand-all @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="name" label="名称" >
      </el-table-column>
      <el-table-column prop="path" label="路径" >
      </el-table-column>
      <el-table-column label="图标" class-name="fontSize18" align="center" label-class-name="fontSize12">
        <template slot-scope="scope">
          <span :class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述">
      </el-table-column>
      <el-table-column label="操作"  width="320" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="addChildren(scope.row.id)" v-if="!scope.row.pid && !scope.row.path">新增子菜单 <i class="el-icon-plus"></i></el-button>
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
    <!-- 信息提示框 -->
    <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="路径" :label-width="formLabelWidth">
          <el-input v-model="form.path" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" :label-width="formLabelWidth">
          <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.value">
              <i :class="item.value" /> {{ item.name }}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="form.description" autocomplete="off"></el-input>
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
  name:'Menu',
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
      name:'',
      multipleSelection:[]
    }
  },
  created(){
    this.init()
  },
  methods: {
    //初始化加载数据
    init(){
      this.$http.get("/menu",).then(
        res=>{
        this.tableData=res.data
      })

      this.$http.get("/menu/icons").then(res => {
        this.options = res.data
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
      this.name=''
      this.init()
    },
    // 新增显示
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    //新增子菜单
    addChildren(pid){
      this.dialogFormVisible=true
      this.form={}
      if(pid){
        this.form.pid=pid
      }
    },
    // 保存添加数据
    save(){
      this.$http.post("/menu",this.form).then(res=>{
        if(res.code === "200"){
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
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible=true
       
    },
    // 删除用户
    handleDel(id){
      this.$http.delete("/menu/"+id).then(res=>{
        if(res.code === "200"){
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
      this.$http.post("/menu/del/batch",ids).then(res=>{
        if(res.code === "200"){
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
.headerBg {
  background: #eee!important;
}
.fontSize18{
  font-size: 18px;
}
.fontSize12{
  font-size: 12px;
}
</style>