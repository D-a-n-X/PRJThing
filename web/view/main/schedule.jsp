<%-- 
    Document   : schedule
    Created on : Mar 20, 2023, 4:18:00 AM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row" style="font-family: Comic Sans MS; height: 40px; width: 1100px; margin-top: 30px; margin-left: 200px; display: flex">
            <div class="col-md-6" style="text-align: left; display: flex">
                    <h3 style="margin-top: 8px; margin-left: 20px"><strong>View Schedule</strong></h3></a>
                </div>
                <div class="col-md-6" style="margin-left: 680px">
                    <c:forEach items="${requestScope.lec}" var="l" varStatus="loop">          
                        <button style="font-family: Comic Sans MS; background-color: rgb(67, 205, 128); color: white">
                            <a style="color: white" href="lecturer/info?lecturer=${l.id}">${l.account.accname}</a>
                        </button>
                    </c:forEach>
                    |
                    <button style="background-color: rgb(67, 205, 128)">
                        <a style="font-family: Comic Sans MS; color: white" href="logout">Logout</a>
                    </button>
                    |
                    <c:forEach items="${requestScope.camps}" var="cs" varStatus="loop">          
                        <button style="background-color: rgb(67, 205, 128); color: white">
                            <div style="font-family: Comic Sans MS">${cs.name}</div>
                        </button>
                    </c:forEach>
        </div>
        </div>
        <form style="font-family: Comic Sans MS; margin-left: 200px" action="schedule" method="GET">
            <c:forEach items="${requestScope.lec}" var="l" varStatus="loop">          
                <h1>Activities for ${l.code} (${l.name})</h1>
            </c:forEach>
            <table>
                <thead>
                    <tr>
                        <th style="text-align: left">From: <input style="font-family: Comic Sans MS" type="date" name="from" required=""/><br/>
                            To: <input style="font-family: Comic Sans MS" type="date" name="to" required=""/><br/>
                        </th>
                        <th>
                            <input style="font-family: Comic Sans MS; height: 45px" type="submit" value="Enter"/>
                        </th>
                    </tr>
                </thead>
            </table>
        </form>
        <div style="font-family: Comic Sans MS; margin-left: 200px">
                <c:if test="${requestScope.dates ne null}">
                    <table style="border: 2px solid black;" border="1px"> 
                        <tr style="background-color: orange">
                            <td></td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td><fmt:formatDate value="${d}" type="date"/><br/><fmt:formatDate value="${d}" pattern="EEEE"/>
                                </td>
                            </c:forEach>

                        </tr>
                        <c:forEach items="${requestScope.slots}" var="slot"> 
                            <tr>
                                <td style="background-color: rgb(234, 234, 234)">Slot ${slot.id}</td>
                                <c:forEach items="${requestScope.dates}" var="d">
                                    <td style="width: 120px">
                                        <c:forEach items="${requestScope.l.sessions}" var="ses">
                                            <c:if test="${ses.date eq d and ses.slot.id eq slot.id}">
                                                <a href="session/info?session=${ses.id}">${ses.course.code}</a><br/>
                                                at ${ses.room.name} <br/>
                                                <c:if test="${ses.status eq true}">
                                                    <a style="color: blue" href="attendance?sesid=${ses.id}">(Update)</a>
                                                </c:if>
                                                <c:if test="${ses.status eq false}">
                                                    <a style="color: red" href="attendance?sesid=${ses.id}">(Take attendance)</a>
                                                </c:if>
                                                <button style="background-color: rgb(67, 205, 128); color: white">
                                                    ${ses.slot.name}
                                                </button>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
    </body>
</html>
