<%-- 
    Document   : invalid
    Created on : Mar 19, 2023, 11:46:51 PM
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
        <div style="text-align: center; margin-top: 200px">
            <h1 style="color: #ff6666">Invalid credentials</h1>
            <br/>
            <b style="color: #ff6666">You will be redirected to /login after <span id="time"></span> seconds</b>
        </div>
        <script>
            var count = 3;
            var time = document.getElementById('time');
            time.innerHTML = count;
            function counting()
            {
                count--;
                time.innerHTML = count;
                if (count <= 0)
                {
                    window.location.href = 'login';
                }
            }
            setInterval(counting, 1000);
        </script>
    </body>
</html>
