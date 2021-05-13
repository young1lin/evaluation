# 准备工作

更改对应 MySQL 密码，导入 db.sql 的内容


# 访问地址
## Swagger 访问地址
http://localhost:8888/swagger-ui.html

http://localhost:8888

http://localhost:8888/api/startEvaluate?serialNum=898989ssad&userId=1&winNum=12

# 打包命令

## 方式一
mvn package -DskipTests
## 方式二
mvn package -Dmaven.test.skip=true

# 包名解释
## /api
api 调用所需关键类
## /common
通用包，含返回类型等常量类
## /pages
index controller 相关页面类
## /websocket
和 webSocket Client 交互的相关类
# 如果在MacOS下启动的话
启动非常慢的原因为未设置host
## 解决步骤如下
1.  打开terminal，输入hostname
2.  复制获得的值
3.  sudo vi /etc/hosts
4.  将每个localhost后面加上获得的hostname即可(<font color='red'>中间是tab键隔开，不是空格</font>)。


