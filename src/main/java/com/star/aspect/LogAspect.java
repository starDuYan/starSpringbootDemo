package com.star.aspect;

import com.star.annotation.LogMysql;
import com.star.annotation.LogOracle;
import com.star.bean.Syslog;
import com.star.dao.SyslogDao;
import com.star.util.HttpContextUtils;
import com.star.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.aspect
 * @date 2020/04/05 15:53
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SyslogDao sysLogDao;
    /**mysql log注解*/
    @Pointcut("@annotation(com.star.annotation.LogMysql)")
    public void pointcut() { }
    /**oracle log注解*/
    @Pointcut("@annotation(com.star.annotation.LogOracle)")
    public void pointcutOracle() { }
    /**
    * @Description: 保存mysql数据库操作记录
    * @Param: 
    * @return: 
    * @Author: duxing
    * @Date: 2020/04/05
    */
    @Around("pointcut()")
    public Object aroundMysql(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveMysqlLog(point, time);
        return result;
    }
    /**
    * @Description: 保存oracle数据库操作数据
    * @Param: 
    * @return: 
    * @Author: duxing
    * @Date: 2020/04/05
    */
    @Around("pointcutOracle()")
    public Object aroundOracle(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveOracleLog(point, time);
        return result;
    }

    private void saveMysqlLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Syslog sysLog = new Syslog();
        LogMysql logAnnotation = method.getAnnotation(LogMysql.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名 存入sessionID
        sysLog.setUsername("stardu:"+"mysql:"+request.getSession().getId());
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // 保存系统日志
        sysLogDao.saveSyslogMysql(sysLog);
    }
    private void saveOracleLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Syslog sysLog = new Syslog();
        LogOracle logAnnotation = method.getAnnotation(LogOracle.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名 存入sessionID
        sysLog.setUsername("stardu:"+"oracle:"+request.getSession().getId());
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // 保存系统日志
        sysLogDao.saveSyslogOracle(sysLog);
    }
}