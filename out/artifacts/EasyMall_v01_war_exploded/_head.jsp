<%@ page import="cn.tedu.entity.User" %>
<%@ page import="cn.tedu.utils.JDBCUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>

<div id="common_head">
    <div id="line1">
        <div id="content">
            <%-- 检查登录 --%>
            <% String username = "";
                String password = "";
                boolean confirm = false; // 校验结果
                if (session.getAttribute("loginUser") == null) {
                    // 1.检查cookie是否需要自动登录
                    Cookie[] cookies = request.getCookies();
                    Cookie unameC = null;
                    Cookie pwdC = null;
                    if (cookies != null) {
                        for (Cookie c : cookies) {
                            if ("username".equals(c.getName())) {
                                unameC = c;
                            }
                            if ("password".equals(c.getName())) {
                                pwdC = c;
                            }
                        }
                    }

                    if (unameC != null) {        // 接收、解码
                        username = URLDecoder.decode(unameC.getValue(), "utf-8");
                    }
                    if (pwdC != null) {        // 接收、解码
                        password = URLDecoder.decode(pwdC.getValue(), "utf-8");
                    }
                    // 2.校验密码
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
                            confirm = true;// 校验成功
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    } finally {
                        // 关闭资源
                        JDBCUtils.closeAll(conn, ps, rs);
                    }
                }
                if (session.getAttribute("loginUser") != null || confirm) {
            %>
            欢迎回来,<span style="color:blue "><%= session.getAttribute("loginUser") == null ? username :
                ((User) (session.getAttribute("loginUser"))).getUsername()%></span>
            &nbsp;&nbsp;|&nbsp;&nbsp; <a style="color: red" href="<%= request.getContextPath()%>/servlet/LogoutServlet">[注销]</a>
            <% } else {%>
            <a href="<%= request.getContextPath()%>/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%= request.getContextPath()%>/regist.jsp">注册</a>
            <% }%>
        </div>
    </div>
    <div id="line2">
        <img id="logo" src="<%= request.getContextPath()%>/img/head/logo.jpg"/>
        <input type="text" name=""/>
        <input type="button" value="搜索"/>
        <span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="#">我的购物车</a>
		</span>
        <img id="erwm" src="<%= request.getContextPath()%>/img/head/qr.jpg"/>
    </div>
    <div id="line3">
        <div id="content">
            <ul>
                <li><a href="#">首页</a></li>
                <li><a href="#">全部商品</a></li>
                <li><a href="#">手机数码</a></li>
                <li><a href="#">电脑平板</a></li>
                <li><a href="#">家用电器</a></li>
                <li><a href="#">汽车用品</a></li>
                <li><a href="#">食品饮料</a></li>
                <li><a href="#">图书杂志</a></li>
                <li><a href="#">服装服饰</a></li>
                <li><a href="#">理财产品</a></li>
            </ul>
        </div>
    </div>
</div>
