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
    <body style="font-family: Comic Sans MS; text-align: center;">
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
        <img src="img/necoarc dance 3.gif" alt="alt"/>
    </body>
</html>
