<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>
    <title>EasyMall欢迎您登陆</title>
    <script type="application/javascript">
        function setMsg(id, msg, color = "red") {
            // 设置标签文本内容
            document.getElementById(id + "_msg").innerText = msg;
            document.getElementById(id + "_msg").style.color = color;
        }

        function checkNull(name, msg) {
            // 清空上次的提示消息
            setMsg(name, "")
            var v = document.getElementsByName(name)[0].value
            if (v == null || v === "") {
                setMsg(name, msg)
                return false // 数据为空，终止提交表单
            }
            return true // 数据不为空，继续提交表单
        }

        function changeImg(imgObj) {
            imgObj.src = "<%= request.getContextPath()%>/servlet/VerifyCodeServlet?time=" + new Date();
        }

        function checkLoginForm() {
            var canSub = true
            canSub = checkNull('username', '请输入用户名') & canSub
            canSub = checkNull('password', '请输入密码') & canSub
            return canSub;
        }
    </script>
</head>
<%
    Cookie[] cookies = request.getCookies();
    Cookie findC = null;
    Cookie unameC = null;
    Cookie pwdC = null;
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("remname".equals(c.getName())) {
                findC = c;
            }
            if ("username".equals(c.getName())) {
                unameC = c;
            }
            if ("password".equals(c.getName())) {
                pwdC = c;
            }
        }
    }
    String username = "";
    if (findC != null) {        // 接收、解码
        username = URLDecoder.decode(findC.getValue(), "utf-8");
    }
%>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="<%= request.getContextPath()%>/servlet/LoginServlet" method="POST" onsubmit="checkLoginForm()">
    <table>
        <tr>
            <td colspan="2" style="color: red;text-align: center">
                <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名:</td>
            <td><input type="text" name="username" onblur="checkNull('username','请输入用户名')"
                       value="<%=request.getParameter("username")==null?
                                   username:request.getParameter("username")%>"/>
            </td>
            <td>
                <span id="username_msg" style="color: red"></span>
            </td>
        </tr>
        <tr>
            <td class="tdx">密码:</td>
            <td><input type="password" name="password" onblur="checkNull('password','请输入密码')"/></td>
            <td>
                <span id="password_msg" style="color: red"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" onblur="checkNull('valistr','验证码不能为空')">
                <img id="yzm_img" src="<%= request.getContextPath()%>/servlet/VerifyCodeServlet"
                     onclick="changeImg(this)" style="cursor: pointer"/>
                <span style="color: red" id="valistr_msg"></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%if (findC != null) {%>
                       checked="checked"
                        <%}%>
                />记住用户名
                <input type="checkbox" name="autologin" value="true"
                        <% if (unameC != null && pwdC != null) { %>
                       checked="checked"
                        <%}%>
                />30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="登陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

