package cn.tedu.web;

import cn.tedu.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * servletContext域
 * 范围：整个web应用共享
 * 问题：共享数据容易发生冲突
 * <p>
 * 会话域：session(服务器端)和cookie(本地端)
 * 场景：浏览器与服务器实现一个功能，产生有多次请求
 * 从第一次请求开始到最后一次请求结束，被称为一次会话
 * session：是java web提供的解决会话数据存储相关的技术，存储在服务器端；
 * 它为每一个客户创建各自的session对象，存储会话数据,避免混乱
 * cookie：是java web 提供的解决会话数据存储的技术
 * cookie是客户端技术，将会话产生的数据存储在客户端
 * cookie基于set—Cookie响应头和cookie请求工作的
 */
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 禁止浏览器的缓存
        response.setIntHeader("Expires", -1); // -1代表禁止缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        // 生成验证码
        VerifyCode vc = new VerifyCode();
        // 放置验证码到响应中
        vc.drawImage(response.getOutputStream());
        // 打印验证码
        String code = vc.getCode();
        System.out.println(code);
        // 将code存储到session域中
        HttpSession session = request.getSession();
        session.setAttribute("valistr", code);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
