package org.example.aop;

import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知：记录日志
     */
    //环绕@Around要用Object result = joinPoint.proceed();和ProceedingJoinPoint
    @Around("@annotation(org.example.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {

        long begin = System.currentTimeMillis();

        // 执行原方法
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long cost = end - begin;


        // 假设操作人 ID 从 ThreadLocal 或 SecurityContext 中获取
        Integer operateUserId = getCurrentUserId();

        // 封装日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateUserId);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "null");
        operateLog.setCostTime(cost);

        log.info("记录操作日志------------: {}",operateLog);

        // 保存到数据库
        operateLogMapper.insert(operateLog);

        return result;
    }

    /**
     * 假设从 ThreadLocal 或 SecurityContextHolder 中获取用户 ID
     */
    private Integer getCurrentUserId() {
        // 示例写法，实际情况下你从登录的上下文取即可
        return CurrentHolder.getCurrentId(); // 假设操作人 ID = 1
    }
}
