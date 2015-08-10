
package com.coul.common.exception;

/**
 * 
 */
public interface IException {
    
    public static final String SUCCESS                     = "0000";    // 成功
    public static final String UNKNOWN_EXCEPTION           = "-PB10000"; // 未知异常
                                                                         
    // 系统错误（PB1000~PB1999）
    public static final String CONFIG_FILE_ERROR           = "PB1000";  // 加载配置文件[{cfgFile}]失败
    public static final String CLASS_INSTANCE_ERROR        = "PB1001";  // 类[{className}]实例化异常
    public static final String CLASS_NOTFOUND              = "PB1002";  // 类[{className}]不存在
    public static final String FTP_CONNECT_ERROR           = "PB1003";  // FTP[{ftpKey}]连接失败
    public static final String FTP_LOGIN_ERROR             = "PB1004";  // FTP[{ftpKey}]登陆失败
    public static final String FTP_IO_ERROR                = "PB1005";  // FTP[{ftpKey}]IO异常
    public static final String FTP_UPLOAD_ERROR            = "PB1006";  // FTP[{ftpKey}]上传文件[{ftpFile}]失败
    public static final String FTP_DOWNLOAD_ERROR          = "PB1007";  // FTP[{ftpKey}]下载文件[{ftpFile}]失败
    public static final String FTP_RENAME_ERROR            = "PB1008";  // FTP[{ftpKey}]更改文件[{ftpFile}]为[{ftpNewFile}]失败
    public static final String FILE_NOT_EXISTS             = "PB1009";  // 文件[{file}]不存在
    public static final String FILE_EMPTY                  = "PB1010";  // 文件[{file}]内容为空
                                                                         
    public static final String POOL_NOT_EXISTS             = "PB1011";  // 线程池[{poolName}]不存在
    public static final String POOL_INTERRUPTED            = "PB1012";  // 线程池[{poolName}]服务请求中断
    public static final String POOL_EXECUTION_ERROR        = "PB1013";  // 线程池[{poolName}]服务执行异常!{errMsg}
    public static final String POOL_TIMEOUT                = "PB1014";  // 线程池[{poolName}]服务调用超时
    public static final String POOL_REJECTED               = "PB1015";  // 线程池[{poolName}]拒绝请求[{errMsg}]
    public static final String POOL_ERROR                  = "PB1016";  // 线程池[{poolName}]服务调用异常[{errMsg}]
                                                                         
    public static final String CACHE_NOT_EXISTS            = "PB1017";  // 缓存对象[{cacheKey}]不存在
    public static final String CACHE_LOAD_INTERRUPTED      = "PB1018";  // 缓存对象[{cacheKey}]加载冲突
    public static final String CACHE_LOAD_ERROR            = "PB1019";  // 缓存对象[{cacheKey}]加载异常
    public static final String CACHE_REMOVE_ERROR          = "PB1020";  // 缓存对象[{cacheKey}]删除异常
    public static final String CACHE_SAVE_ERROR            = "PB1021";  // 缓存对象[{cacheKey}]保存异常
                                                                         
    public static final String WSAPP_GENMAC_ERROR          = "PB1022";  // 生成MAC失败
    public static final String WSAPP_XML_EMPTY             = "PB1023";  // XML报文为空
    public static final String WSAPP_XML_PARSE_ERROR       = "PB1024";  // 报文格式错误!{errMsg}
    public static final String WSAPP_XML_TCPCONT_ERROR     = "PB1025";  // 控制头报文异常!{errMsg}
    public static final String WSAPP_SERVICE_CONFIGERROR   = "PB1026";  // 服务尚未注册【BUSCODE:{busCode},SERVICECODE:{serviceCode}】
    public static final String WSAPP_SERVICE_ERROR         = "PB1027";  // 服务异常!
    public static final String WSAPP_SERVICE_RUNERROR      = "PB1028";  // 调用服务组件异常:{errMsg}
    public static final String WSAPP_CLIENT_ERROR          = "PB1029";  // 调用webservice失败！{errMsg}
    public static final String WSAPP_SERVICE_ROUTEERROR    = "PB1030";  // {busCode}服务路由地址配置异常!
                                                                         
    public static final String TUXEDO_CONNECT_ERROR        = "PB1031";  // 获取tuxedo[{svc}]连接失败!{errMsg}
    public static final String TUXEDO_TPCALL_ERROR         = "PB1032";  // 调用tuxedo服务[{svc}]失败!{errMsg}
                                                                         
    public static final String CRYPT_ERROR                 = "PB1033";  // 加密异常
    public static final String DATABASE_ERROR              = "PB1034";  // 数据库操作异常![$cityId]{errMsg}
                                                                         
    // 业务规则错误（PB2000~PB2999）
    public static final String WSAPP_SERVICE_DENY          = "PB2000";  // 服务停用!{errMsg}
    public static final String WSAPP_SERVICE_NOAUTH        = "PB2001";  // 没有权限!{errMsg}
    public static final String WSAPP_EXCEPTION_ERROR       = "PB2002";  // 执行表达式异常！{errMsg}
    public static final String WSAPP_OSINFO_ERROR          = "PB2003";  // 获取信息项值异常！
                                                                         
    // 提示信息（PB3000~PB3999）
    
    // 输入参数错误（PB4000~PB4999）
    public static final String WSAPP_SERVICE_VERERROR      = "PB4000";  // 输入的服务版本号【{inputVer}】与当前服务版本号【{curVer}】不一致
    public static final String WSAPP_SERVICE_SIGNERROR     = "PB4001";  // 数字签名不正确!
    public static final String WSAPP_SERVICE_MACERROR      = "PB4002";  // MAC不正确!
    public static final String WSAPP_XML_VALIDATE_ERROR    = "PB4003";  // 通过XSD验证XML格式：{errMsg}
    public static final String WSAPP_SERVICE_SYSIDERROR    = "PB4004";  // 落地方系统编码{dstSysId}错误,应该{sysId}！
    public static final String WSAPP_SERVICE_SYSORGIDERROR = "PB4005";  // 落地方系统机构{dstOrgId}错误,应该{sysId}！
    public static final String WSAPP_INPUTPARAM_EMPTY      = "PB4006";  // 输入参数[{paramName}]缺失或者为空！
                                                                         
}
