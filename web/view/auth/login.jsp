<%-- 
    Document   : login
    Created on : Mar 19, 2023, 10:53:38 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="font-family: Comic Sans MS">
        <form action="login" method = "POST" style="text-align: center; margin-top: 200px">
            <p>
                Username: <input type="text" id="username" name="username" placeholder="Username" required style="font-family: Comic Sans MS"><i class="validation"><span></span><span></span></i>
            </p>
            <p>
                Password: <input type="password" id="password" name="password" placeholder="Password" required style="font-family: Comic Sans MS"><i class="validation"><span></span><span></span></i>
            </p>
            <p>
                <select name="campus" class="btn btn-default" style="font-family: Comic Sans MS; color: gray" required>
                    <option selected="selected" value="">Select Campus</option>
                    <option value="1">FU-Hoà Lạc</option>
                    <option value="2">FU-Hồ Chí Minh</option>
                    <option value="3">FU-Đà Nẵng</option>
                    <option value="4">FU-Cần Thơ</option>
                    <option value="5">FU-Quy Nhơn</option>
                </select>
            </p>
            <p>
                <input type="submit" id="login" value="Login" style="font-family: Comic Sans MS">
            </p>
        </form>
    </body>
</html>
