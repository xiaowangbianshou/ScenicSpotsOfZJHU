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
               @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="name" label="用户名" >
      </el-table-column>
      <el-table-column prop="description" label="描述">
      </el-table-column>
      <el-table-column label="操作"  width="280" align="center">
        <template slot-scope="scope">
          <el-button type="info" @click="selectMenu(scope.row.id)">分配菜单 <i class="el-icon-menu"></i></el-button>
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
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
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

    <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
      <el-tree
          :props="props"
          :data="menuData"
          show-checkbox
          node-key="id"
          ref="tree"
          :default-expanded-keys="[2, 3]"
          :default-checked-keys="[5]"   >
         <span class="custom-tree-node" slot-scope="{ node, data }">
            <span><i :class="data.icon"></i> {{ data.name }}</span>
         </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name:'Role',
  data() {
    return {
      tableData: [],
      total:0,
      dialogFormVisible:false,
      menuDialogVis:false,
      headerBg: 'headerBg',
      form:{},
      formLabelWidth:'70px',
      pageNum:1,
      pageSize:10,
      name:'',
      multipleSelection:[],
      menuData:[],
      roleId:0,
      props:{
        label:'name'
      }
    }
  },
  created(){
    this.init()
  },
  methods: {
    //初始化加载数据
    init(){
      this.$http.get("/role/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
        }
      }
      ).then(res=>{
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
    // 保存添加数据
    save(){
      this.$http.post("/role",this.form).then(res=>{
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
      this.form=row
      this.dialogFormVisible=true
    },
    // 删除用户
    handleDel(id){
      this.$http.delete("/role/"+id).then(res=>{
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
      this.$http.post("/role/del/batch",ids).then(res=>{
        if(res.code === "200"){
          this.$message.success('批量删除成功')
          this.init() //重新加载数据
        }else{
          this.$message.error("批量删除失败")
        }

      })
    },
    selectMenu(roleID){
      this.menuDialogVis=true
      this.roleId=roleID
      this.$http.get("/menu").then(res =>{this.menuData=res.data})
    },
    saveRoleMenu(){
      // console.log(this.$refs.tree.getCheckedKeys());
      this.$http.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
        if (res.code === '200') {
          this.$message.success("绑定成功")
          this.menuDialogVis = false

          // 操作管理员角色后需要重新登录
          if (this.roleFlag === 'ROLE_ADMIN') {
            this.$store.commit("logout")
          }

        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style>

</style>