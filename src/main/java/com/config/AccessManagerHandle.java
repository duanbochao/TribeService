package com.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/6 21:04
 */
public class AccessManagerHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("utf-8");
        PrintWriter out = resp.getWriter();
        out.write("权限不足请联系管理员");
        out.flush();
        out.close();
    }
}
