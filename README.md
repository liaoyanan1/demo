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
### 5. b-service 端口 9090 -9093
#### 提供b服务接口
### 6. oauth2 端口 8899
#### 提供授权 生成jks  keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore mytest.jks -storepass mypass
#### 导出公钥 keytool -list -rfc --keystore mytest.jks | openssl x509 -inform pem -pubkey
#### 拷贝创建public.txt文件
#### mytest.jks 和 public.txt 放入认证中心 resource资源路径
