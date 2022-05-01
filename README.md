![](https://img-blog.csdnimg.cn/810efae628814a69b5fbb9fd8e20da73.png)
## OnlineFrom
一个基于SpringBoot+Vue3实现的前后端分离的在线报名系统，可用于用户的信息收集。
前端利用Vite2+Vue3快速搭建。

本项目在 [FirstBlood](https://github.com/HEEKDragonOne/FirstBlood) 基础上进行开发。

## 涉及知识点
前端：Vue3、Element-Plus、Echarts、Axios、Vue-Router、Vite

后端：SpringBoot、MyBatis-Plus

JDK：1.8

MySQL：8.0

其他相关：Hutool工具、JWT、Email等。

## 注意事项
超级管理员创建管理员时，默认密码为123456。

## 功能描述
- 收集用户信息

- 添加前端用户自行查看所有报名信息内容，隐私信息进行特殊处理。

- 实行黑名单制度。在黑名单上的人将无法进行报名活动的提交。

- 相同用户在同一报名中只能提交一次。

- 在用户提交信息时，将会通过邮箱给用户发送邮件，以此方式保证用户为有效用户。用户也可以同这种方式来判断自己是否报名提交成功。

- 数据的可视化展示

等等。

## 功能截图

### 用户视角
活动期间。

![](https://img-blog.csdnimg.cn/9020df0ac3bb445a84928bae80cc2890.png)

活动结束。

![](https://img-blog.csdnimg.cn/9f66010b7bde4fb9a5c98730cd6e4567.png)

### 管理员视角
首页。

![](https://img-blog.csdnimg.cn/d3339d43f86d43908dfc46b5539ea1ef.png)

报名活动表的管理

![](https://img-blog.csdnimg.cn/e055aed0551143d1954cc8728d23e9ea.png)


查询某个报名活动的具体信息。

![](https://img-blog.csdnimg.cn/ee6e0250d96a4392940a3c0fc11ced9f.png)

黑名单用户管理。

![](https://img-blog.csdnimg.cn/a8295a71eb84454cb07867fcf39bbb7e.png)

根据某活动数据进行可视化展示。

![](https://img-blog.csdnimg.cn/93e21055f8ba4c0a9c067960816c01f0.png)

![](https://img-blog.csdnimg.cn/253a66b50ed74690a52764ddaa193586.png)
![](https://img-blog.csdnimg.cn/f81a04643b404c71b2522133157e42c8.png)

权限相关。角色管理和人员管理。

![](https://img-blog.csdnimg.cn/5fdf0260949c4778a49edb19d135ad28.png)
![](https://img-blog.csdnimg.cn/6ef032a71bd44d7d9159c26223859e3f.png)

修改密码。个人密码和他人密码。

![](https://img-blog.csdnimg.cn/8ce033026d0f464ea2da84594eb8ba25.png)
![](https://img-blog.csdnimg.cn/2e87630e2adc47358b1fea088ef6b92e.png)


## 部署步骤

### 安装数据库
执行sql文件中的建库建表语句即可。

默认超级管理员admin，密码123456。

### 部署后端
修改 `clgc-java` 中的， `application.yml` 中的配置。
- 邮箱配置：`username` 填写开启的邮箱；`password` 为授权码。

- 数据库配置：`datasource` 配置自己的数据库信息

### 部署前端
分为用户前端和后台前端。

#### 用户前端
利用的vite2+vue3实现。
- 在 `clgcacm` 目录下执行：`npm install` 安装依赖。执行：`npm run dev` 运行项目，项目运行在 8080 端口。

#### 后台前端
采用普通Vue3项目实现。
- 在 `schoolm` 目录下执行：`npm install` 安装依赖。 执行： `npm run serve` 运行项目，项目运行在 8002 端口。

