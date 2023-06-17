<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/regist.css"/>
    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>

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

        function checkForm() {
            var canSub = true
            canSub = checkNull("username", "用户名不能为空") && canSub
            canSub = checkNull("password", "密码不能为空") && canSub
            canSub = checkNull("password2", "确认密码不能为空") && canSub
            canSub = checkNull("nickname", "昵称不能为空") && canSub
            canSub = checkNull("email", "邮箱不能为空") && canSub
            canSub = checkNull("valistr", "验证码不能为空") && canSub
            // 密码校验
            var psw1 = document.getElementsByName("password")[0].value
            var psw2 = document.getElementsByName("password2")[0].value
            if (psw1 != null && psw1 != "" && psw2 != null && psw2 != "" && psw1 != psw2) {
                setMsg("password2", "两次密码不一致")
                canSub = false;
            }
            var email = document.getElementsByName("email")[0].value
            if (email != null && email != "" && !/^\w+@\w+(\.\w+)+$/.test(email)) {
                setMsg("email", "邮箱格式不正确")
                canSub = false;
            }
            return canSub

        }

        // 确认密码输入框失去焦点的数据校验
        function checkPassword2() {
            setMsg("password2", "")
            var psw1 = document.getElementsByName("password")[0].value
            var psw2 = document.getElementsByName("password2")[0].value
            if (psw2 == null || psw2 == "") {
                setMsg("password2", "确认密码不能为空")
                return; // 提前结束
            }
            if (psw1 != null && psw1 != "" && psw2 != null && psw2 != "" && psw1 != psw2) {
                setMsg("password2", "两次密码不一致")
                return; // 提前结束
            }
        }

        // 邮箱输入框失去焦点的数据校验
        function checkEmail() {
            setMsg("email", "")
            var email = document.getElementsByName("email")[0].value
            if (email == null || email == "") {
                setMsg("email", "邮箱不能为空")
                return; // 提前结束
            }
            if (email != null && email != "" && !/^\w+@\w+(\.\w+)+$/.test(email)) {
                setMsg("email", "邮箱格式不正确")
                return; // 提前结束
            }
            $.get("<%=request.getContextPath()%>/servlet/AjaxHasEmailServlet", {"email": email}, function (data) {
                setMsg("email", data, data == "邮箱可用" ? "green" : "red")
            })
        }

        // 失去焦点，校验用户名是否为空，是否存在
        function checkUsername() {
            var isNotNull = checkNull("username", "用户名不能为空")
            if (isNotNull) {
                // 用户名不为空再校验是否存在
                var username = document.getElementsByName("username")[0].value
                $.get("<%=request.getContextPath()%>/servlet/AjaxHasUserNameServlet", {"username": username}, function (data) {
                    setMsg("username", data, data == "用户名可用" ? "green" : "red")
                });
            }
        }

        function changeImg(imgObj) {
            imgObj.src = "<%= request.getContextPath()%>/servlet/VerifyCodeServlet?time=" + new Date();
        }

    </script>
</head>
<body>
<h1>欢迎注册EasyMall</h1>
<form action="<%= request.getContextPath()%>/servlet/RegistServlet" method="POST" onsubmit="return checkForm()">
    <table>
        <tr>
            <td colspan="2" style="color: red;text-align: center">
                <%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" onblur="checkUsername()"
                       value="<%= request.getParameter("username") == null ? "" : request.getParameter("username")%>">
                <span style="color: red" id="username_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" onblur="checkNull('password','密码不能为空')">
                <span style=" color: red" id="password_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" onblur="checkPassword2()">
                <span style="color: red" id="password2_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" onblur="checkNull('nickname','昵称不能为空')"
                       value="<%= request.getParameter("nickname") == null ? "" : request.getParameter("nickname")%>">
                <span style="color: red" id="nickname_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" onblur="checkEmail()"
                       value="<%= request.getParameter("email") == null ? "" : request.getParameter("email")%>">
                <span style="color: red" id="email_msg"></span>
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
            <td colspan="2" style="text-align: center">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

