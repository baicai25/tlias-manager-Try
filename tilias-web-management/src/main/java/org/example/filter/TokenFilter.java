package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //获取到请求路径
        String requestURI = request.getRequestURI();

        //判断是否是登录请求,看路径中有没有login,是就放行
        if (requestURI.contains("/login")) {
            log.info("登录操作直接放行");
            filterChain.doFilter(request, response);
            return;
        }

        //获取请求头中的token
        String token = request.getHeader("token");

        //判断token,不存在,证明没有登录,用户返回错误信息401状态码
        if (token == null || token.isEmpty()) {
            log.info("令牌为空,用户未登录,响应401");
            response.setStatus(401);
            return;
        }

        //存在就校验令牌,校验失败就返回错误信息401
        try {
            Claims claims = JwtUtils.parseToken(token);

            Integer empId = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(empId);//存入
            log.info("当前登录员工的Id: {},将其存入ThreadLocal",empId);

        } catch (Exception e) {
            log.info("令牌非法,用户未登录,响应401");
            response.setStatus(401);
            return;
        }

        //校验通过就放行
        log.info("令牌合法,放行");
        filterChain.doFilter(request, response);

        //删除TreadLocal中的Id,释放内存
        CurrentHolder.remove();
    }

}
