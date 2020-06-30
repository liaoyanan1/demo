#框架构建

# 1.discovery-service 端口 8761
#### 服务注册中心
# 2.config-service 端口 8888
#### 服务配置中心 
    需要配置的服务中的名字就是配置文件的文件名 spring.application.name = name   name.yml 
# 3. gateway-service 端口 9999
#### 网关
# 4. a-service 端口 8080 -8083
#### 提供a服务接口
# 5. b-service 端口 9090 -9093
#### 提供b服务接口
