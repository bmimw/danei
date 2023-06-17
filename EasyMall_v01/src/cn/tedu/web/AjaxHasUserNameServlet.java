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

public class AjaxHasUserNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 配置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取前端数据
        String username = request.getParameter("username");
        // 检查用户名是否存在
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
                response.getWriter().write("用户名已存在");
            } else {
                // 用户名可用
                response.getWriter().write("用户名可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(conn, ps, rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
