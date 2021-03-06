# 对项目优化的一些思考
之前的博客项目属于是为了应付期末大作业而做的雏形，现在考虑对博客系统做出如下优化：
## 登陆模块利用拦截器进行拦截
之前为了简便，登陆模块是在前端进行拦截，当遇到伪造请求的情况时就无法处理了，现在考虑在后端也使用拦截器进行拦截。
## 利用ThreadLocal保存user信息
将用户信息保存在ThreadLocal中，当前线程可以随时访问用户信息，无需每次都请求数据库。
这里考虑在拦截器中植入ThreadLocal保存用户信息的功能。
还实现了线程隔离，但记得在每次使用完之后需要对value进行删除，防止内存泄漏。
## 增加缓存
为了实现较高的性能，考虑对数据进行缓存，但需要注意的是在写入数据时对缓存的操作（如何确保缓存与数据库不一致的问题）。
还有就是缓存的实现形式，在这里，缓存的主要实现形式是AOP + 注解。
## AOP实现日志
对项目需要进行日志处理，这一部分也可通过AOP来实现。
## 头像等图片或者文件可以上传到云服务器
目前对图片等信息未处理，日后考虑使用云服务器存储图片信息（数据库中保存对应资源的链接）。
## 登陆模块优化
登录模块现在只有登录登出修改密码功能，考虑是否可以结合短信验证码快捷登录以及找回密码等功能。
目前是没有找回密码功能的，主要考虑是，若是记不得密码了，重新申请一个账号即可，但考虑到原创性等还是应该添加找回密码功能，那如何找回密码就成为了需要解决的问题（数据库中需要对应存储一个信息用于找回密码，手机号或者邮箱）。
## 对点赞功能的优化
之前的点赞是通过表来完成的（本质上是Mysql），现在考虑使用Redis实现  --> 即先访问Redis，之后将Redis的数据写入到数据库中

暂时想到了这么多，之后有想法再写上去。
