<%-- 
    Document   : attendance
    Created on : Mar 20, 2023, 4:18:32 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('img/necoarc walc.gif'); background-attachment: fixed; background-size: 100% 100%">
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
                <a href="schedule"><h3 style="margin-left: 20px ;margin-top: 8px;"><strong>Schedule</strong></h3></a>
                <h3 style="margin-top: 8px; margin-left: 20px"><strong>Take Attendance/<strong></h3></a>
            </div>
            <div class="col-md-6" style="margin-left: 640px">
                <c:forEach items="${requestScope.lec}" var="l" varStatus="loop">          
                    <button style="font-family: Comic Sans MS; background-color: rgb(67, 205, 128); color: white">
                        <a style="color: white" href="lecturer/info?lecturer=${l.id}">${l.account.accname}</a>
                    </button>
                </c:forEach>
                |
                <button style="background-color: rgb(67, 205, 128)">
                    <a style="font-family: Comic Sans MS; color: white" href="http://localhost:9999/PRJThing/logout">Logout</a>
                </button>
                |
                <c:forEach items="${requestScope.camps}" var="cs" varStatus="loop">          
                    <button style="background-color: rgb(67, 205, 128); color: white">
                        <div style="font-family: Comic Sans MS">${cs.name}</div>
                    </button>
                </c:forEach>
            </div>
        </div>
        <form style="font-family: Comic Sans MS" action="attendance" method="POST"> 
                <table style="margin-left: 200px; margin-top: 50px">
                    <thead>
                        <tr>
                            <th style="width: 80px; text-align: left;">INDEX</th>
                            <th style="width: 180px; text-align: left;">IMAGE</th>
                            <th style="width: 140px; text-align: left;">CODE</th>
                            <th style="width: 280px; text-align: left;">NAME</th>
                            <th style="width: 200px; text-align: left;">STATUS</td>
                            <th style="width: 200px; text-align: left;">COMMENT</td>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                            <tr>
                                <td style="text-align: center"><strong>${loop.index +1}</strong></td>
                                <td><img src="${a.student.img}" alt="alt"/></td>
                                <td>${a.student.code}</td>
                                <td>${a.student.name}</td>
                                <td>
                                    <input type="radio" 
                                           <c:if test="${!a.status}">
                                               checked="checked" 
                                           </c:if>
                                           name="status${a.student.id}" value="Absent"/> Absent
                                    <input type="radio" 
                                           <c:if test="${a.status}">
                                               checked="checked" 
                                           </c:if>
                                           name="status${a.student.id}" value="Attended" /> Attended
                                </td>
                                <td>
                                    <input type="hidden" name="sid" value="${a.student.id}"/>
                                    <input type="hidden" name="aid${a.student.id}" value="${a.id}"/>
                                    <input type="text" name="description${a.student.id}" value="${a.description}"/></td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="sessionid" value="${param.sesid}"/>
                <input style="width: 60px; height: 40px; margin-left: 300px" type="submit" value="Save"/>
            </form>
    </body>
</html>
