package cn.tedu.web;

import cn.tedu.entity.User;
import cn.tedu.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置字符编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取前端数据
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String valistr = request.getParameter("valistr").trim();
        String remname = request.getParameter("remname");// 记住用户名？true:""
        String autoLogin = request.getParameter("autologin");// 30天内自动登录
        valistr = valistr.toLowerCase();// 验证码不区分大小写
        // 获取session中的验证码
        String velistr2 = (String) request.getSession().getAttribute("valistr");

        // 前端数据校验
        if (username == null || "".equals(username)) {
            request.setAttribute("msg", "请输入用户名");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if (password == null || "".equals(password)) {
            request.setAttribute("msg", "请输入密码");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if (valistr == null || "".equals(valistr)) {
            request.setAttribute("msg", "请输入验证码");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if (valistr == null || "".equals(valistr) || velistr2 == null || "".equals(velistr2) ||
                !valistr.equals(velistr2.toLowerCase())) {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        } else {
            // 验证成功，删除session中存储的验证码
            request.getSession().removeAttribute("valistr");
        }

        // 后端密码校验
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            // 处理查询结果
            if (rs.next()) {
                // 是否记住用户名
                if ("true".equals(remname)) {
                    // 记住，添加cookie
                    Cookie cookie = new Cookie("remname", URLEncoder.encode(username, "utf-8"));// 编码传输
                    cookie.setPath(request.getContextPath() + "/login.jsp");
                    cookie.setMaxAge(60 * 60 * 24 * 14);// 十四天有效
                    response.addCookie(cookie);// cookie保存到本地
                } else {
                    // 取消记住，删除cookie
                    Cookie cookie = new Cookie("remname", "");
                    cookie.setPath(request.getContextPath() + "/login.jsp");
                    cookie.setMaxAge(0);// 立即失效
                    response.addCookie(cookie);// cookie保存到本地
                }
                // 是否30天内自动登录 1.登录状态 2.密码是否正确 3.每个页面都内看到登录状态
                if ("true".equals(autoLogin)) {
                    // 自动登录，添加cookie
                    // 用户名cookie
                    Cookie unamec = new Cookie("username", URLEncoder.encode(username, "utf-8"));// 编码传输
                    unamec.setPath(request.getContextPath() + "/");
                    unamec.setMaxAge(60 * 60 * 24 * 30);// 三十天有效
                    response.addCookie(unamec);// cookie保存到本地
                    // 密码cookie
                    Cookie pwdc = new Cookie("password", URLEncoder.encode(password, "utf-8"));// 编码传输
                    pwdc.setPath(request.getContextPath() + "/");
                    pwdc.setMaxAge(60 * 60 * 24 * 30);// 三十天有效
                    response.addCookie(pwdc);// cookie保存到本地
                } else {
                    // 取消自动登录，删除cookie
                    Cookie unamec = new Cookie("username", "");
                    unamec.setPath(request.getContextPath() + "/");
                    unamec.setMaxAge(0);// 立即失效
                    response.addCookie(unamec);// cookie保存到本地
                    Cookie pwdc = new Cookie("password", "");
                    pwdc.setPath(request.getContextPath() + "/");
                    pwdc.setMaxAge(0);// 立即失效
                    response.addCookie(pwdc);// cookie保存到本地
                }

                // 将查询到的用户信息封装到User对象中
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                // 将User对象装配到session域，供前端使用
                request.getSession().setAttribute("loginUser", user);
                // 登录完成，进入主页
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                // 密码校验失败,返回登录页
                request.setAttribute("msg", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            JDBCUtils.closeAll(conn, ps, rs);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}