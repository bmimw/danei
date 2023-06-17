package cn.tedu.web;

import cn.tedu.entity.Product;
import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置字符编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> list=new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from product");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product =new Product();
                //将数据进行一个封装
                product.setId(rs.getInt("id"));
                product.setImage(rs.getString("image"));
                list.add(product);
            }
            if (list==null){
                response.getWriter().write("数据错误，请勿使用非法数据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeAll(conn, ps, rs);
        }
        request.setAttribute("productlist",list);
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
