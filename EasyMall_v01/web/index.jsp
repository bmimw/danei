<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cn.tedu.utils.JDBCUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.tedu.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/index.css"/>
    <title>欢迎光临EasyMall</title>
</head>
<body>
<%--引入头部--%>
<%@include file="_head.jsp" %>
<div id="index">
    <%-- 顶部横栏 --%>
    <div id="line1">
        <a href="<%= request.getContextPath()%>/product_detail.jsp"><img
                src="<%= request.getContextPath()%>/img/index/banner_big.jpg"/></a>
    </div>
    <%-- 商品列表 --%>
    <div class="row">

        <% List<Product> items = (List) request.getAttribute("productlist");
            for (Product p : items) {%>
        <div class="col-4">
            <a href="<%= request.getContextPath()%>/ProductDetail/<%= p.getId()%>"><img
                    src="<%= request.getContextPath()%><%= p.getImage()%>1.jpg"/></a>
        </div>
        <%}%>
    </div>
    <%--<div id="line2">
        <img id="line2_1" src="<%= request.getContextPath()%>/img/index/adv1.jpg"/>
        <img id="line2_2" src="<%= request.getContextPath()%>/img/index/adv2.jpg"/>
        <img id="line2_3" src="<%= request.getContextPath()%>/img/index/adv_l1.jpg"/>
    </div>
    <div id="line3">
        <img id="line3_1" src="<%= request.getContextPath()%>/img/index/adv3.jpg"/>
        <img id="line3_2" src="<%= request.getContextPath()%>/img/index/adv4.jpg"/>
        <div id="line3_right">
            <img id="line3_3" src="<%= request.getContextPath()%>/img/index/adv_l2.jpg"/>
            <img id="line3_4" src="<%= request.getContextPath()%>/img/index/adv_l3.jpg"/>
        </div>
    </div>--%>
    <%-- 底部横栏 --%>
    <div id=" line4">
        <img src="<%= request.getContextPath()%>/img/index/217.jpg"/>
    </div>
    <%-- 底部 --%>
    <div id="line5">
        <span id="line5_1">
					<img src="<%= request.getContextPath()%>/img/index/icon_g1.png"/>&nbsp;&nbsp;500强企业 品质保证
				</span>
        <span id="line5_2">
					<img src="<%= request.getContextPath()%>/img/index/icon_g2.png"/>&nbsp;&nbsp;7天退货 15天换货
                </span>
        <span id="line5_3">
					<img src="<%= request.getContextPath()%>/img/index/icon_g3.png"/>&nbsp;&nbsp;100元起免运费
				</span>
        <span id="line5_4">
					<img src="<%= request.getContextPath()%>/img/index/icon_g4.png"/>&nbsp;&nbsp;448家维修网点 全国联保
				</span>
    </div>
</div>
<%--引入尾部--%>
<%@include file="_foot.jsp" %>
</body>
</html>

