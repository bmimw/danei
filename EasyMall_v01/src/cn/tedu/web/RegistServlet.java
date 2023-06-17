package cn.tedu.web;

import cn.tedu.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 0.设置编码格式
        request.setCharacterEncoding("UTF-8");
        // 设置响应编码类型
        response.setContentType("text/html;charset=utf-8");

        // 1.获取表单提交的数据
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String password2 = request.getParameter("password2").trim();
        String nickname = request.getParameter("nickname").trim();
        String email = request.getParameter("email").trim();
        String valistr = request.getParameter("valistr").trim();
        System.out.println("用户名:" + username + "\t密码:" + password +
                "\t确认密码:" + password2 + "\t昵称:" + nickname +
                "\t邮箱:" + email + "\t验证码:" + valistr);

        // 2.校验数据信息
        // 校验用户名是否为空
        if (username == null || "".equals(username)) {
            request.setAttribute("msg", "用户名不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验密码是否为空
        if (password == null || "".equals(password)) {
            request.setAttribute("msg", "密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验确认密码是否为空
        if (password2 == null || "".equals(password2)) {
            request.setAttribute("msg", "确认密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验两次密码是否一致
        if (!password.equals(password2)) {
            request.setAttribute("msg", "两次输入密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验昵称是否为空
        if (nickname == null || "".equals(nickname)) {
            request.setAttribute("msg", "昵称不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验邮箱是否为空
        if (email == null || "".equals(email)) {
            request.setAttribute("msg", "邮箱不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验验证码是否为空
        if (email == null || "".equals(email)) {
            request.setAttribute("msg", "验证码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验邮箱格式
        if (email.matches("/^\\w+@\\w+(\\.\\w+)+$/")) {
            request.setAttribute("msg", "邮箱格式不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        // 校验验证码
        String valistr2 = (String) request.getSession().getAttribute("valistr");
        valistr2 = valistr2.toLowerCase(); // 转为小写，让验证码校验不区分大小写
        if (valistr == null || "".equals(valistr) ||
                valistr2 == null || "".equals(valistr2) ||
                !valistr2.equals(valistr.toLowerCase())) {
            request.setAttribute("msg", "验证码不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        } else {
            // 验证成功，删除存储的验证码
            request.getSession().removeAttribute("valistr");
        }
        // 3.检查用户名是否存在
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            // 处理结果
            if (rs.next()) {
                // 用户名已存在
                request.setAttribute("msg", "用户名已存在");
                request.getRequestDispatcher("/regist.jsp").forward(request, response);
                return; // 程序到此为止
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(conn, ps, rs);
        }
        // 校验邮箱是否可用
        conn = null;
        ps = null;
        rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                // 邮箱已被使用
                request.setAttribute("msg", "该邮箱已被使用");
                request.getRequestDispatcher("/regist.jsp").forward(request, response);
                return; // 程序到此为止
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCUtils.closeAll(conn, ps, rs);
        }
        // 4.注册用户名到数据库
        conn = null;
        ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("insert into user values (null,?,?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setString(4, email);
            // 执行插入语句
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCUtils.closeAll(conn, ps, null);
        }

        // 5.回到主页
        // 重定向到主页
        // response.sendRedirect(request.getContextPath() + "/index.jsp");
        // 三秒后，刷新到主页
        response.getWriter().write("注册成功，3秒后跳转到主页");
        response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
