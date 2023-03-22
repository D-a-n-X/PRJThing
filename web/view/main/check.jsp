<%-- 
    Document   : check
    Created on : Mar 22, 2023, 11:26:21 PM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/tlds/dateTag.tld" prefix="my"%>
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
        <div class="row" style="margin-top: 50px; display: flex">
                <div>
                    <table style=" border: 2px solid black">
                        <caption style="text-align: center; font-size: large"><h1>Select a campus/program, term,<br/> course ...</h1></caption>
                        <thead style="background-color: rgb(0, 179, 255);">
                            <tr>
                                <th scope="col" style="width: 100px; text-align: left;">Campus</th>
                                <th scope="col" style="width: 100px; text-align: left;">Term</th>
                                <th scope="col" style="width: 420px; text-align: left;">Course</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td valign="top">
                                    <table border="1">
                                        <tr>
                                            <c:forEach items="${requestScope.camps}" var="cs" varStatus="loop">
                                                <strong>${cs.name}</strong>
                                            </c:forEach>
                                        </tr>
                                    </table> 
                                </td>
                                <td valign="top"></td>
                                <td valign="top">
                                    <table border="1">
                                        <tr>
                                            <c:forEach items="${requestScope.courses}" var="c" varStatus="loop">
                                                <a style="margin-bottom: 5px" href="checkAtt?course=${c.id}" name="course"><strong>${c.name}</strong> </a><strong>(${c.code})</strong><br/>
                                            </c:forEach>
                                        </tr>
                                    </table> 
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <table style=" border: 2px solid black">
                        <caption style="text-align: center; font-size: large; margin-bottom: 24px"><h1>... then see report</h1></caption>
                        <br/>
                        <thead style="background-color: rgb(0, 179, 255);">
                            <tr>
                                <th scope="col" style="height: 50px;width: 40px; text-align: left;">NO</th>
                                <th scope="col" style="width: 170px; text-align: left;">DATE</th>
                                <th scope="col" style="width: 110px; text-align: left;">SLOT</th>
                                <th scope="col" style="width: 60px; text-align: left;">ROOM</th>
                                <th scope="col" style="width: 100px; text-align: left;">LECTURER</th>
                                <th scope="col" style="width: 80px; text-align: left;">GROUP NAME</th>
                                <th scope="col" style="width: 120px; text-align: left;">ATTENDANCE STATUS</th>
                                <th scope="col" style="width: 120px; text-align: left;">LECTURER'S COMMENT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <script>
                                var absent = 0;
                            </script>
                            <c:forEach items="${requestScope.ses1}" var="ss" varStatus="loop">
                                <tr>
                                    <td valign="top" style="height: 50px">
                                        ${loop.index+1}
                                    </td>
                                    <td valign="top">
                                        <button style="background-color: #337ab7; color: white; border: 0px">
                                            <my:dateTag value="${ss.date}" type="EEEE"></my:dateTag> <my:dateTag value="${ss.date}"></my:dateTag>
                                            </button>
                                        </td>
                                        <td valign="top">
                                            <button style="background-color: #d9534f; color: white; border: 0px">
                                            ${ss.slot.id}_${ss.slot.name}
                                        </button>
                                    </td>
                                    <td valign="top">${ss.room.name}</td>
                                    <td valign="top">${ss.lecturer.code}</td>
                                    <td valign="top">${ss.group.name}</td>
                                    <td valign="top">
                                        <c:if test="${ss.status eq true}">
                                            <c:if test="${ss.attendance.status eq true}">
                                                <b style="color: rgb(67, 205, 128)">Present</b><br/>
                                            </c:if>
                                            <c:if test="${ss.attendance.status eq false}">
                                                <b style="color: red">Absent</b><br/>
                                                <script>
                                                    absent = absent + 1;
                                                </script>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ss.status eq false}">
                                            <b style="color: black">Future</b><br/>
                                        </c:if>
                                    </td>
                                    <td valign="top">${ss.attendance.description}</td>
                                </tr>
                                <script>
                                    var count = "${loop.index+1}";
                                </script>
                            </c:forEach>
                            <script>
                                var average = absent / count * 100;
                            </script>
                        </tbody>
                        <tfoot>                       
                            <td colspan="8">
                                <h3>                                
                                    <script>
                                        document.write("ABSENT: " + average + "% ABSENT SO FAR(" + absent + " ABSENT ON " + count + " TOTAL)");
                                    </script>
                                </h3>
                            </td>
                        </tfoot>
                    </table>
                </div>
            </div>
    </body>
</html>
