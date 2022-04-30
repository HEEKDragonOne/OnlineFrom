<template>
  <div>
    <div style="margin-left: 15px;margin-top: 15px">
      <el-select
          style="margin-left: 5px"
          v-model="typeState"
          filterable
          allow-create
          default-first-option
          :reserve-keyword="false"
          placeholder="请输入或选择类型"
      >
        <el-option
            v-for="item in typeForm"
            :key="item.typeId"
            :label="item.typename"
            :value="item.typeId"
        >
        </el-option>
      </el-select>
<!--      <el-button type="primary" style="margin-left: 5px" @click="network">-->
<!--        <el-icon style="vertical-align: middle;">-->
<!--          <search/>-->
<!--        </el-icon>-->
<!--        <span style="vertical-align: middle;"> 查看 </span>-->
<!--      </el-button>-->
    </div>

    <div class="showTab">
<!--      年级-->
      <Grade :typeID="typeState" />
      <!--      学校-->
      <School :typeID="typeState" />
<!--      专业-->
      <Profession :typeID="typeState" />
    </div>

  </div>

</template>

<script>
import Grade from '../../../components/gradeShow.vue'
import Profession from '../../../components/professionalShow.vue'
import School from '../../../components/schoolShow.vue'
import {ElMessage} from "element-plus";

export default {
  name: "index",
  components:{
    Grade,Profession,School
  },
  data(){
    return{
      typeForm: [], //物品类型列表
      typeState: '请选择报名表', //选中的物品类型编号
    }
  },
  methods:{
    network(){
      console.log(this.typeState)
    },
    itemTypeGet() { //获取物品类型数据
      this.$http.get("/itemType/itemTypeAll").then(res => {
        if (res.statusCode == '200') {
          this.typeForm = res.data
        }
      }).catch(() => {
        ElMessage.error('物品类型加载失败！')
      })
    },
  },
  created() {
    this.itemTypeGet()
  }
}
</script>

<style scoped>
.showTab{
  margin-top: 50px;
  margin-left: 75px;
}
</style>
