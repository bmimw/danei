<%@ page import="cn.tedu.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyMall-商品详情</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/product_detail.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>
    <script>
        function subtract() {
            var quantityElement = document.getElementById("quantity");
            var quantity = parseInt(quantityElement.value);
            if (quantity > 0) {
                quantity--;
            }
            quantityElement.value = quantity;
        }

        function add() {
            var quantityElement = document.getElementById("quantity");
            var quantity = parseInt(quantityElement.value);
            quantity++;
            quantityElement.value = quantity;
        }

        // 在 DOMContentLoaded 事件中执行 JavaScript 代码
        document.addEventListener("DOMContentLoaded", function() {
            var priceElement = document.getElementById("price");
            var hiddenPriceElement = document.getElementById("hiddenPrice");

            // 设置隐藏字段的初始值为 <a> 元素的内容
            hiddenPriceElement.value = priceElement.textContent;

            // 在表单提交时更新隐藏字段的值
            document.getElementById("myForm").addEventListener("submit", function(event) {
                hiddenPriceElement.value = priceElement.textContent;
            });
        });
    </script>
</head>
<body>
<%--引入头部--%>
<%@include file="_head.jsp" %>

<div class="container">
    <div class="row" style="background: gainsboro;margin-top: 10px">
        <span style="margin-left: 400px;font-size: 15pt">商品详情</span>
    </div>

    <div class="row">
        <table style="margin-left: 400px">
            <tr>
                <% Product item = (Product) request.getAttribute("item");%>
                <td>
                    <div class="col-md-6">
                        <div data="1" class="img-big move-img col-md-12">
                            <img id="product-image-1-big" src="<%=request.getContextPath()%><%= item.getImage()%>1_big.png" class="img-responsive"/>
                        </div>
                        <div data="2" class="img-big move-img col-md-12">
                            <img id="product-image-2-big" src="<%=request.getContextPath()%><%= item.getImage()%>2_big.png" class="img-responsive"/>
                        </div>
                        <div data="3" class="img-big move-img col-md-12">
                            <img id="product-image-3-big" src="<%=request.getContextPath()%><%= item.getImage()%>3_big.png" class="img-responsive"/>
                        </div>
                        <div data="4" class="img-big move-img col-md-12">
                            <img id="product-image-4-big" src="<%=request.getContextPath()%><%= item.getImage()%>4_big.png" class="img-responsive"/>
                        </div>
                        <div data="5" class="img-big move-img col-md-12">
                            <img id="product-image-5-big" src="<%=request.getContextPath()%><%= item.getImage()%>5_big.png" class="img-responsive"/>
                        </div>
                        <div data="1" class="col-md-2 img-small">
                            <img id="product-image-1" src="<%=request.getContextPath()%><%= item.getImage()%>1.jpg" class="img-responsive"/>
                        </div>
                        <div data="2" class="col-md-2 img-small">
                            <img id="product-image-2" src="<%=request.getContextPath()%><%= item.getImage()%>2.jpg" class="img-responsive"/>
                        </div>
                        <div data="3" class="col-md-2 img-small">
                            <img id="product-image-3" src="<%=request.getContextPath()%><%= item.getImage()%>3.jpg" class="img-responsive"/>
                        </div>
                        <div data="4" class="col-md-2 img-small">
                            <img id="product-image-4" src="<%=request.getContextPath()%><%= item.getImage()%>4.jpg" class="img-responsive"/>
                        </div>
                        <div data="5" class="col-md-2 img-small">
                            <img id="product-image-5" src="<%=request.getContextPath()%><%= item.getImage()%>5.jpg" class="img-responsive"/>
                        </div>
                    </div>
                <td>
                <td>

                    <ul style="margin-bottom: 200px">
                        <li><%= item.getTitle() %></li>
                        <a id="price" name="price"><%= item.getPrice() %></a>
                    </ul>
                    <div class="row" style="text-align: center;margin-left: 30px">
                        <button type="button" class="btn btn-lg rounded-sm"
                                style="background: red;border: 0;color: white">加入购物车
                        </button>
                            <a>你需要购买的数量：</a>
                        <form action="<%= request.getContextPath()%>/servlet/PayServlet" method="POST" id="myForm">
                            <input type="hidden" id="hiddenPrice" name="hiddenPrice" value="">

                            <div>
                                <button type="button" onclick="subtract()">-</button>
                                <input type="text" id="quantity" name="quantity" value="0" readonly>
                                <button type="button" onclick="add()">+</button>
                            </div>
                        <button type="submit" class="btn btn-lg rounded-sm"
                                style="background: red;border: 0;color: white">立即购买
                        </button>
                        </form>
                    </div>
                </td>
            </tr>
        </table>
        <%--<div class="row">
            <div class="col-5">
                <img src="<%=request.getContextPath()%>/img/index/adv1.jpg">
            </div>
            <div class="col-7">
                <ul>
                    <li>标题：荣耀6plus</li>
                    <li>价格：9999￥</li>
                    <li>
                        <button type="button" class="btn rounded-sm"
                                style="background: red;border: 0">加入购物车
                        </button>
                        <button type="button" class="btn rounded-sm"
                                style="background: red;border: 0">立即购买
                        </button>
                    </li>
                </ul>
            </div>
        </div>--%>
    </div>
</div>

</body>
</html>
