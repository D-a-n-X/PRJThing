<%-- 
    Document   : timetable
    Created on : Mar 20, 2023, 1:25:57 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row" style="height: 40px; width: 1100px; margin-top: 30px; margin-left: 200px; display: flex">
            <div class="col-md-6" style="text-align: left; display: flex">
                    <h3 style="margin-top: 8px; margin-left: 20px"><strong>View Schedule</strong></h3></a>
                </div>
                <div class="col-md-6" style="margin-left: 680px">
                    <c:forEach items="${requestScope.student}" var="s" varStatus="loop">          
                        <button style="background-color: rgb(67, 205, 128); color: white">
                            <a style="color: white" href="student/info">${s.account.accountName}</a>
                        </button>
                    </c:forEach>
                    |
                    <button style="background-color: rgb(67, 205, 128)">
                        <a style="color: white" href="logout">Logout</a>
                    </button>
                    |
                    <c:forEach items="${requestScope.campus}" var="cs" varStatus="loop">          
                        <button style="background-color: rgb(67, 205, 128); color: white">
                            ${cs.name}
                        </button>
                    </c:forEach>
        </div>
    </body>
</html>
