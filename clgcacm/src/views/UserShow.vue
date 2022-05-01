<template>
  <div class="intemShow">

    <el-row :gutter="12">
      <el-col :span="24" align="center" style="margin: 15px 0px">
        <el-card shadow="always">
          <h1>墨水记忆-在线报名系统</h1>
          <h3>{{ titleName }}</h3>
          <span>报名时间：{{ begin }} 至 {{ end }}</span> <br>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always" style="margin-top: 15px">

          <!--    搜索框-->
          <div style="padding: 10px 0">
            <el-input style="width: 250px" placeholder="请输入姓名" :suffix-icon="Search" v-model="searchname"></el-input>

            <el-button type="primary" style="margin-left: 5px" @click="network">
              <el-icon style="vertical-align: middle;">
                <search/>
              </el-icon>
              <span style="vertical-align: middle;"> 搜索 </span>
            </el-button>
          </div>
          <!--127.0.0.1-->

          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="itemId" label="人员编号" width="250" sortable align="center"></el-table-column>
            <el-table-column prop="name" label="姓名" width="250" align="center"></el-table-column>
            <el-table-column prop="sex" label="性别" width="100" sortable align="center"></el-table-column>
            <el-table-column prop="itemAddTime" label="报名时间" width="200" sortable align="center"></el-table-column>
            <el-table-column prop="size" label="学校" width="200" sortable align="center"></el-table-column>
            <el-table-column prop="itemCount" label="年级" width="90" sortable align="center"></el-table-column>
            <el-table-column prop="description" label="专业" width="250" sortable align="center"></el-table-column>
            <el-table-column prop="phone" label="手机号" width="250" align="center"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="250" align="center"></el-table-column>

          </el-table>

          <el-config-provider :locale="locale">
            <div style="padding: 10px 0">
              <el-pagination
                  background
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  v-model:currentPage="pageNum"
                  :page-sizes="[15, 50, 100, 200]"
                  v-model:page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="total">
              </el-pagination>
            </div>
          </el-config-provider>

        </el-card>
      </el-col>
    </el-row>



  </div>
</template>

<script>
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
import {Search} from '@element-plus/icons-vue'
import {ElMessageBox, ElMessage} from 'element-plus'
export default {
  name: "index",
  components: {
    Search,
  },
  data() {
    return {
      titleName: '暂无',
      begin: '暂无',
      end: '暂无',
      contentMSG:'无',
      Search,
      locale: zhCn,
      tableData: [],
      total: 0, //数据总条数
      pageNum: 1, //当前页
      pageSize: 15, //页大小
      searchname: "", //搜索名称
      form: {}, //弹窗中的数据
      typeState: this.$route.params.applyID, //选中的报名表编号
      formLabelWidth: '100px'
    }
  },
  methods: {
    getTableMsg() { //获取标题和时间
      this.$http.get("/itemType/getInfoOneMsg").then(res => {
        if (res.statusCode == '200') {
          this.titleName = res.data.typename
          this.begin = res.data.beginTime
          this.end = res.data.endTime
        }else {
          this.flagShow = false
        }
      }).catch(() => {
        ElMessage.error('数据加载失败,请刷新！')
      })

    },
    handleSizeChange(val) { //页大小改变
      this.pageSize = val
      this.network()
    },
    handleCurrentChange(val) { //当前页改变
      this.pageNum = val
      this.network()
    },
    network() { //分页查询
      this.searchtype = this.typeState
      this.$http.get("/item/pageAll", {
        params: {
          pageNum: this.pageNum,
          PageSize: this.pageSize,
          itemname: this.searchname,
          searchtype: this.searchtype
        }
      }).then(res => {
        if (res.statusCode == '200') {
          this.total = res.data.total
          for(let it of res.data.records){
            it.phone = it.phone.slice(0,3) + '****' + it.phone.slice(it.phone.length - 4)
            it.email = it.email.slice(0,4) + '****' + it.email.slice(it.email.length - 8)
          }
          this.tableData = res.data.records
        }
      }).catch(() => {
        ElMessage.error('数据加载失败,请刷新！')
      })
    },
  },
  created() {
    this.network()
    this.getTableMsg()
  }
}
</script>

<style scoped>
.intemShow {
  margin-left: 15px;
}

</style>
