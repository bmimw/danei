package cn.tedu.web;

import cn.tedu.entity.Product;
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
import java.util.ArrayList;

/**
 *
 */
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置字符编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取前端数据
        String pathInfo = request.getPathInfo();
        Product product =new Product();
        if (pathInfo != null) {
            // 去掉开头的斜杠和末尾的斜杠（如果有）
            String dynamicPart = pathInfo.substring(1).replaceAll("/$", "");
            // 在这里使用 dynamicPart 进行相应的操作
            // 连接数据库
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = JDBCUtils.getConnection();
                ps = conn.prepareStatement("select * from product where id=?");
                ps.setString(1,dynamicPart);
                rs = ps.executeQuery();
                while (rs.next()) {
                    //将数据进行一个封装
                    product.setId(rs.getInt("id"));
                    product.setPrice(rs.getInt("price"));
                    product.setTitle(rs.getString("title"));
                    product.setImage(rs.getString("image"));
                }
                if (product==null){
                    response.getWriter().write("数据错误，请勿使用非法数据！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                JDBCUtils.closeAll(conn, ps, rs);
            }
        }
        request.setAttribute("item",product);
        request.getRequestDispatcher("/product_detail.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}