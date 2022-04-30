<template>
  <div>
    <el-row :gutter="12"  class="echar">
      <el-col :span="20">
        <el-card shadow="always">
          <div id="professional" style="width: 100%;height: 500px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {ElMessage} from "element-plus";

export default {
  name: "professionalShow",
  props:{
    typeID: String
  },
  data(){
    return{
      typebh: this.typeID
    }
  },
  watch:{
    typeID(newVal, oldVal) {
      this.typebh = oldVal
      this.typebh = newVal;
      this.getShow()
    },
  },
  methods:{
    getShow(){
      var chartDom = document.getElementById('professional');
      var myChart = echarts.init(chartDom);
      var option = {
        title: {
          text: '专业分布情况',
          subtext: '数据统计-墨水记忆',
          textStyle: {
            color: '#aa31cb'
          },
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: function(arg){
            return arg.name+":\n"+arg.value+" \n"+"占比:"+arg.percent+"%"
          }
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          // data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
          {
            name: '专业分布',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            selectedMode: 'multiple',
            selectedOffset: 30
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);

      this.$http.get("/item/getProfessionByTypeId/"+this.typebh).then(res => {
        if (res.statusCode == '200') {
          console.log(res.data)
          // option.xAxis.data = res.data.x
          option.series[0].data = res.data
          myChart.setOption(option);
        }else{
          ElMessage.error('加载失败！')
        }
      }).catch(() => {
        ElMessage.error('数据加载失败！')
      })
      option && myChart.setOption(option);
    }
  }
}
</script>

<style scoped>
.echar{
  margin: 25px 0px;
}
</style>
