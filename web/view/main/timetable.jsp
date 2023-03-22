<%-- 
    Document   : timetable
    Created on : Mar 20, 2023, 1:25:57 AM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/dateTag.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('img/necoarc sleep.gif'); background-size: 100% 100%; background-attachment: fixed">
        <audio preload="auto" loop src="heh/nyanyanya.mp3" autoplay>
        </audio>
        <script>

function setCookie(c_name,value,exdays)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value=escape(value) + ((exdays===null) ? "" : "; expires="+exdate.toUTCString());
    document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
    var i,x,y,ARRcookies=document.cookie.split(";");
    for (i=0;i<ARRcookies.length;i++)
    {
      x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
      y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
      x=x.replace(/^\s+|\s+$/g,"");
      if (x===c_name)
        {
        return unescape(y);
        }
      }
}

var song = document.getElementsByTagName('audio')[0];
var played = false;
var tillPlayed = getCookie('timePlayed');
function update()
{
    if(!played){
        if(tillPlayed){
        song.currentTime = tillPlayed;
        song.play();
        played = true;
        }
        else {
                song.play();
                played = true;
        }
    }

    else {
    setCookie('timePlayed', song.currentTime);
    }
}
setInterval(update,0);
</script>
        <div class="row" style="font-family: Comic Sans MS; height: 40px; width: 1100px; margin-top: 30px; margin-left: 200px; display: flex">
            <div class="col-md-6" style="text-align: left; display: flex">
                    <h3 style="margin-top: 8px; margin-left: 20px"><strong>View Schedule</strong></h3></a>
                </div>
                <div class="col-md-6" style="margin-left: 680px">
                    <c:forEach items="${requestScope.stu}" var="s" varStatus="loop">          
                        <button style="font-family: Comic Sans MS; background-color: rgb(67, 205, 128); color: white">
                            <a style="color: white" href="student/info">${s.account.accname}</a>
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
        <form style="font-family: Comic Sans MS; margin-left: 200px" action="timetable" method="GET">
            <c:forEach items="${requestScope.stu}" var="s" varStatus="loop">          
                <h1>Activities for ${s.code} (${s.name})</h1>
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
                    <table> 
                        <tr>
                            <th rowspan="2"></th>
                                <c:forEach items="${requestScope.dates}" var="d">
                                <td><b style="text-transform: uppercase"><my:dateTag value="${d}" type="EEE"></my:dateTag></b></td>
                                </c:forEach>
                        </tr>
                        <tr>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td><b style="text-transform: uppercase"><my:dateTag value="${d}" type="dateMonth"></my:dateTag></b></td>
                                </c:forEach>
                        </tr>
                        <c:forEach items="${requestScope.slots}" var="slot"> 
                            <tr>
                                <td>Slot ${slot.id}</td>
                                <c:forEach items="${requestScope.dates}" var="d">
                                    <td style="width: 120px">
                                        <c:forEach items="${requestScope.s.groups}" var="g">
                                            <c:forEach items="${g.sessions}" var="ses" varStatus="loop">
                                                <c:if test="${ses.date eq d and ses.slot.id eq slot.id}">
                                                    <a href="check?course=${ses.course.id}">${ses.course.code}</a><br/>
                                                    at ${ses.room.name} <br/>
                                                    <c:if test="${ses.status eq true}">
                                                        <c:if test="${ses.attendance.status eq true}">
                                                            <b style="color: rgb(67, 205, 128)">(Attended)</b><br/>
                                                        </c:if>
                                                        <c:if test="${ses.attendance.status eq false}">
                                                            <b style="color: red">(Absent)</b><br/>
                                                        </c:if>
                                                    </c:if>
                                                    <c:forEach items="${requestScope.stu}" var="s" varStatus="loop">          
                                                        <c:if test="${ses.status eq false}">
                                                            <b style="color: red">(Not yet)</b><br/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <button style="background-color: rgb(67, 205, 128); color: white">
                                                        ${ses.slot.name}
                                                    </button>
                                                </c:if>
                                            </c:forEach>
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
