package com.zzy.zzypicturebackend.aop;

import com.zzy.zzypicturebackend.annotation.AuthCheck;
import com.zzy.zzypicturebackend.exception.BusinessException;
import com.zzy.zzypicturebackend.exception.ErrorCode;
import com.zzy.zzypicturebackend.model.entity.User;
import com.zzy.zzypicturebackend.model.enums.UserRoleEnum;
import com.zzy.zzypicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private UserService userService;

    /**
     * 执行拦截
     * @param joinPoint 切入点
     * @param authCheck 权限校验注解
     * @return
     * @throws Throwable
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable{
        String mustRole= authCheck.mustRole();
        RequestAttributes requestAttributes= RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request=((ServletRequestAttributes)requestAttributes).getRequest();

        //当前登录用户
        User loginUser=userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum=UserRoleEnum.getEnumByValue(mustRole);
        //不需要权限
        if (mustRoleEnum==null){
            return joinPoint.proceed();
        }
        //必须有该权限才能通过
        //获取当前用户具有的权限
        UserRoleEnum userRoleEnum=UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        //没有权限，拒绝
        if (userRoleEnum==null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //要求必须有管理员权限，但用户没有管理员权限，拒绝
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)&&!UserRoleEnum.ADMIN.equals(userRoleEnum)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //通过权限，放行
        return joinPoint.proceed();
    }
}
