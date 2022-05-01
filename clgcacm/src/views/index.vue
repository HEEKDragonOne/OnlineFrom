<template>
  <div style="margin-left: 13%">
    <el-row :gutter="12">
      <el-col :span="20" align="center" style="margin: 15px 0px">
        <el-card shadow="always">
          <h1>墨水记忆-在线报名系统</h1>
          <h3>{{ titleName }}</h3>
          <span>报名时间：{{ begin }} 至 {{ end }}</span> <br>
        </el-card>
        <el-card shadow="always" style="margin-top: 5px">
          <h3>注意事项</h3>
          <span>{{ contentMSG }}</span>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12">
      <el-col :span="20">
        <el-card v-if="flagShow" shadow="always">

          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" :disabled="!flagShow">
            <el-form-item label="报名表号" v-show="false">
              <el-input v-model="ruleForm.typeid"></el-input>
            </el-form-item>
            <el-form-item label="学号" prop="itemId">
              <el-input v-model="ruleForm.itemId" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-select v-model="ruleForm.sex" placeholder="请选择性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="院校" prop="size">
              <el-select v-model="ruleForm.size" placeholder="请选择院校">
                <el-option label="理工" value="成都理工大学工程技术学院"></el-option>
                <el-option label="师院" value="乐山师范学院"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="年级" prop="itemCount">
              <el-select v-model="ruleForm.itemCount" placeholder="请选择年级">
                <el-option :label="grades1" :value="grades1"></el-option>
                <el-option :label="grades2" :value="grades2"></el-option>
                <el-option :label="grades3" :value="grades3"></el-option>
                <el-option :label="grades4" :value="grades4"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="专业" prop="description">
              <el-radio-group v-model="ruleForm.description" @change="changePro">
                <el-radio label="软件工程">软件工程</el-radio>
                <el-radio label="计算机科学与技术">计算机科学与技术</el-radio>
                <el-radio label="0">其他</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="其他专业" prop="description2" v-if="flagPro">
              <el-input
                  placeholder="请输入专业"
                  v-model="ruleForm.description2"
                  clearable>
              </el-input>
            </el-form-item>

            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="ruleForm.phone" placeholder="请输入联系方式"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="ruleForm.email" placeholder="请输入邮箱，用于接受是否报名成功等信息"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">报名</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>

<!--              根据当前报名表的编号查询所有报名信息-->
              <el-button @click="this.$router.push('/userShow/'+this.applyId)">查看报名信息</el-button>

            </el-form-item>
          </el-form>

        </el-card>
        <el-card v-else shadow="always" align="center">
          <body><h3>活动报名已结束（或等待几秒或刷新试试）</h3>
          <div class="container">
            <img class="bg" src="./404.png"/>
          </div>
          </body>
        </el-card>

      </el-col>
    </el-row>

    <el-dialog
        v-model="dialogVisible"
        title="提示"
        width="30%"
        :before-close="handleClose"
    >
      <span>提交后将无法修改或再次提交，请确保填写的信息正确哦！将会通过邮件发送的方式来确保您的邮箱是否有效。</span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="submitOK">确定</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import {ElMessage} from 'element-plus'

export default {
  name: "index",
  data() {
    const validPhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入电话号码'))
      } else if (value.length != 11) { //判断用户输入的值是否符合规则
        callback(new Error('请输入正确的11位手机号码'))
      } else {
        callback()
      }
    }
    return {
      contentMSG:'无',
      applyId:'',
      ruleForm: {},
      flagPro: false,
      dialogVisible: false,
      titleName: '暂无',
      begin: '暂无',
      end: '暂无',
      typeid: '',
      flagShow: true,
      grades1: new Date().getFullYear()-4,
      grades2: new Date().getFullYear()-3,
      grades3: new Date().getFullYear()-2,
      grades4: new Date().getFullYear()-1,
      rules: {
        itemId: [
          {required: true, message: '请输入学号', trigger: 'blur'},
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        itemCount: [
          {required: true, message: '请选择年级', trigger: 'change'}
        ],
        description: [
          {required: true, message: '请选择专业', trigger: 'change'}
        ],
        description2: [
          {required: true, message: '请输入专业', trigger: 'change'}
        ],
        phone: [
          {required: true, trigger: 'blur', validator: validPhone},
        ],
        email: [
          {required: true, message: '请输入邮箱地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
        ],
        size: [
          {required: true, message: '请选择学校', trigger: 'change'}
        ],
      }
    };
  },
  methods: {
    checkTime(){
      if(this.end =='暂无') this.flagShow = false
      else{
        if(new Date() > new Date(this.end)){
          this.flagShow = false
        }else{
          this.flagShow = true
        }
      }

    },
    DS(){
      window.setInterval(() => {
        // setTimeout是自带清除定时器
        setTimeout(()=> {
          this.checkTime()
        }, 0)
      }, 1000)
    },
    getDes(applyType){//通过报名表编号获取备注信息
      this.$http.get("/itemType/getDescription/"+applyType).then(res => {
        if (res.statusCode == '200') {
          this.contentMSG = res.data
        }
      }).catch(() => {
        ElMessage.error('注意事项数据加载失败,请刷新！')
      })
    },
    getTableMsg() { //获取标题和时间
      this.$http.get("/itemType/getInfoOneMsg").then(res => {
        if (res.statusCode == '200') {
          this.ruleForm.typeid = res.data.typeId
          this.applyId = res.data.typeId
          this.getDes(res.data.typeId)
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
    submitOK() { //真正提交处理
      this.$http.post("/item/save", this.ruleForm).then(res => {
        if (res.statusCode == '401') {
          ElMessage.error('对不起！您被限制参加此类比赛。')
          this.handleClose()
          this.dialogFormVisible = false
          this.ruleForm = {}
          this.getTableMsg()
        } else if (res.statusCode == '402') {
          ElMessage({
            message: '不能重复报名！',
            type: 'warning',
          })
          this.handleClose()
          this.dialogFormVisible = false
          this.ruleForm = {}
          this.getTableMsg()
        } else if (res.statusCode == '200') {
          ElMessage({
            message: '提交成功！',
            type: 'success',
          })
          this.handleClose()
          this.dialogFormVisible = false
          this.ruleForm = {}
          this.getTableMsg()
        } else {
          ElMessage.error('提交失败！请检查邮箱是否正确。')
        }
      }).catch(() => {
        ElMessage.error('提交失败！')
      })
    },
    handleClose() { //提交的弹窗提示
      this.dialogVisible = false
    },
    changePro(val) { //专业选择其他控制输入框
      if (val == 0) {
        this.flagPro = true;
      } else {
        this.flagPro = false;
      }
    },
    submitForm(formName) { //提交表单校验
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogVisible = true
          // alert('submit!');
        } else {
          ElMessage({
            message: '请完善表格内容！',
            type: 'error',
          })
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    this.getTableMsg()
    this.checkTime()
    this.DS()
  }
}
</script>

<style scoped>
.container{max-width:90%;margin:0 auto;padding:80px 0px; }
img{-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;}
.bg{display:block;max-width:100%;margin:0px auto;margin:40px auto;}
.btn{width:400px;margin:0 auto;max-width:100%;margin-top:40px;}
.btn a{float:left;text-decoration:none;width:46.5%;border:1px solid #5298ff;background:#5298ff;color:#FFF;display:block;height:46px; line-height:44px;text-align:center;font-size:16px;border-radius:3px;overflow:hidden;}
.btn .goindex{margin-right:7%;}
.btn .lx{border: 1px solid #d8d8d8;background:#ffffff;color:#8c8c8c;}
</style>
