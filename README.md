此工程有6个module

command-module 为测试动态路由和测试Spring Session Redis 实现的缓存

common-module  为公库

discovery-module  为服务注册中心也是服务配置中心

gateway-service 为网关配置中心

order-module   为订单模块，订单服务提供者，用户服务消费者 增加Sharding-JDBC分库分表

user-module    为用户服务提供者

module启动顺序 先启动discovery-module服务注册中心 

然后启动redis（必须） 

再启动getway-service网关配置中心，其他随意

需要注意discovery-module 下的application.yml需要配合自己的环境配置，目前有3种配置方式，git(默认) native:file classpath

请求顺序

http://localhost/user/login?id=5   登录(get) 

http://localhost/order/getOrderByUserId?userId=5  获取数据(get) 测试RestTemplate

http://localhost/order/getOrderByUserIdV2?userId=5  获取数据(get) 测试feign

http://localhost/order/getOrderByUserIdV3?userId=5  获取数据(get) 测试session粘滞

http://localhost/order/add 添加订单数据(post)  测试Sharding-JDBC 分库分表
