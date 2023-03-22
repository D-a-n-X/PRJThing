<%-- 
    Document   : report
    Created on : Mar 23, 2023, 3:14:51 AM
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
                    <h3 style="margin-top: 8px; margin-left: 20px"><strong>View Report</strong></h3></a>
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
<div style = "font-family: Comic Sans MS">
                    <h1>Attendance summary report</h1>
                    <table>
                         <thead>
                              <tr style="background-color: rgb(0, 179, 255);">
                                   <th style="width: 50px; text-align: left;">INDEX</th>
                                   <th style="width: 180px; text-align: left;">IMAGE</th>
                                   <th style="width: 100px; text-align: left;">CODE</th>
                                   <th style="width: 200px; text-align: left;">FULL NAME</th>
                                        <c:forEach items="${requestScope.ss}" var="ss" varStatus="loop">
                                        <th>Slot ${loop.index + 1}</th>
                                        </c:forEach>
                                   <th style="width: 170px; text-align: center;">ABSENT</th>
                              </tr>
                         </thead>
                         <tbody>
                              <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                              <script>
                                   var absent = 0;
                              </script>
                              <tr>
                                   <td>${loop.index + 1}</strong></td>
                                   <td><img src="${s.img}" alt="alt"/></td>
                                   <td>${s.code}</strong></td>
                                   <td>${s.name}</strong></td>
                                   <c:forEach items="${requestScope.att}" var="att" varStatus="loops">
                                        <c:if test="${att.student.id eq s.id}">
                                             <c:if test="${att.session.status eq true}">
                                                  <c:if test="${att.status eq true}">
                                                       <td style="text-align: center"><b style="color: #5cb85c">P</b><br/></td>
                                                       </c:if>
                                                       <c:if test="${att.status eq false}">
                                                       <td style="text-align: center"><b style="color: red">A</b><br/></td>
                                                  <script>
                                                       absent = absent + 1;
                                                  </script>
                                             </c:if>
                                        </c:if>
                                        <c:if test="${att.session.status eq false}">
                                             <td style="text-align: center"><b>_</b><br/></td>
                                             </c:if>
                                        </c:if>
                                   <script>
                                        var count = "${loops.index+1}";
                                   </script>
                              </c:forEach>
                              <script>
                                   var average = absent / count * 100;
                                   var round = Math.round((average + Number.EPSILON) * 100) / 100;
                              </script>    
                              <td style="text-align: center"><strong>
                                        <script>
                                             document.write(round + "%");
                                        </script>
                                   </strong>
                              </td>
                              </tr>
                         </c:forEach>
                         </tbody>
                    </table>
                    <h2>If a student were absent from class more than 20% of slots, they would <strong style ="color:red">NOT PASS</strong> the course</h2>
               </div>
          </div>
    </body>
</html>
