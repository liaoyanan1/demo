#                                                                     框架构建

### 1.discovery-service 端口 8761
#### 服务注册中心
### 2.config-service 端口 8888
#### 服务配置中心 
    需要配置的服务中的名字就是配置文件的文件名 spring.application.name = name   name.yml 
### 3. gateway-service 端口 9999
#### 网关
### 4. a-service 端口 8080 -8083
#### 提供a服务接口 
#### 开启swagger2接口测试
### 5. b-service 端口 9090 -9093
#### 提供b服务接口
#### 开启Hoverfly测试服务
#### 开启hystrix断路器
### 6. oauth2 端口 8899
#### 提供授权 生成jks  keytool -genkeypair -alias volunteer -keyalg RSA -keypass volunteer123 -keystore volunteer.jks -storepass volunteer123
#### 导出公钥 keytool -list -rfc --keystore volunteer.jks | openssl x509 -inform pem -pubkey


oauth2修改地址后数据库中要添加对应的返回地址不然报错
security:
  oauth2:
    client:
      accessTokenUri: http://192.168.3.103:9999/gateway/oauth2/oauth/token
      userAuthorizationUri: http://192.168.3.103:9999/gateway/oauth2/oauth/authorize
      client-id: login
      client-secret: 123456
    resource:
      userInfoUri: http://192.168.3.103:9999/gateway/oauth2/user/me
      preferTokenInfo: false
 数据库中 oauth_client_details表中的authorized_grant_types字段中
 加入 http://192.168.3.103:9999/login  
 浏览器输入的是localhost访问的话 数据库需添加 
    http://localhost:9999/login 
数据库导入 在根目录下ssotest.sql
git 配置文件根目录 demoConfig-master.zip

启动顺序 先启动config 然后启动 discovery 其他随意