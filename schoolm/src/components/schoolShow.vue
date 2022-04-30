<template>
  <div>
    <el-row :gutter="12" class="echar">
      <el-col :span="20">
        <el-card shadow="always">
          <div id="school" style="width: 100%;height: 500px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {ElMessage} from "element-plus";

export default {
  name: "schoolShow",
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
      var option = {
        color: ['#17abd5'],
        title: {
          text: '学院分布情况',
          subtext: '数据统计-墨水记忆',
          textStyle: {
            color: '#31b9cb'
          },
          left: 'center'
        },
        toolbox: {
          feature: {
            saveAsImage: {},
            dataView: {},
            magicType: {
              type: ['bar', 'line']
            }
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '数量',
            type: 'bar',
            itemStyle: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0,
                  color: 'red' //0%处的颜色
                },
                  {
                    offset: 1,
                    color: 'blue' //100%处的颜色
                  }
                ]
              }
            },
            markPoint: {
              data: [
                {
                  type: 'max',
                  name: '这是最大值'
                },
                {
                  type: 'min',
                  name: '这是最小值'
                }
              ]
            },
            markLine: {
              data: [
                {
                  type: 'average',
                  name: '这是平均值' //这个name并不会在页面中显示
                }
              ]
            },
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            },
            data: []
          }
        ]
      }

      var chartDom = document.getElementById('school');
      var myChart = echarts.init(chartDom);

      this.$http.get("/item/getSchoolByTypeId/"+this.typebh).then(res => {
        if (res.statusCode == '200') {
          option.xAxis.data = res.data.x
          option.series[0].data = res.data.y
          myChart.setOption(option);
        }else{
          ElMessage.error('加载失败！')
        }
      }).catch(() => {
        ElMessage.error('数据加载失败！')
      })
      option && myChart.setOption(option);
    }
  },
}
</script>

<style scoped>
.echar{
  margin: 25px 0px;
}
</style>
