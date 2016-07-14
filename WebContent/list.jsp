<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="static/css/Main_Frame.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="static/js/common/jquery.js"></script>
<script type="text/javascript" src="static/js/common/common.js"></script>

<style type="text/css">
    .ldzfc {
    width: 100%;
    height: 200px;
    border: 1px solid #CCC;
    margin-top: 8px;
   }

   .ldzfc_top {
    width: 100%;
    height: 24px;
    text-align:center;
}

  #demo {
    background: #FFF none repeat scroll 0% 0%;
    overflow: hidden;
    border: 1px dashed #CCC;
    width: 1000px;
    margin: 3px auto;
}
  #indemo {
    float: left;
    width: 800%;
}

#demo1 {
    float: left;
}

#demo2 {
    float: left;
}

        div{
            border:1px solid red;
        }
        .header{
            height: 100px;
            background-color: #f8f8f8;
            text-align: center;
            line-height: 100px;
        }
        .main{
            background-color: #f8f8f8;
            margin:10px 0;
            border:1px solid #000;
            padding:10px;

        }
        .left,.right{
            width: 400px;
            height:200px;
            float: left;
            margin-left:100px;
        }
        .footer{
            height:100px;
            line-height: 100px;
            text-align: center;
            background-color: #f8f8f8;
        }
        .clear{
            clear: both;
            border:none;
        }
        .leftdiv_table,.rightdiv_table{
        width: 380px;
        height: 180px;
        margin-left: 10px;
        margin-top: 10px;
        margin-bottom: 10px;
        margin-right: 10px;
        }
</style>
</head>
<body>
  <div style="background-color: #FE8833;text-align:center;">
  <img src="static/image/laomo/gg.jpg" width="100%" height="108px"/>
  </div> 
  
<div class="main">
    <div class="left">
       <table class="leftdiv_table" >
          <tr>
          <td>
           <img src="static/image/laomo/lmgs.PNG" width="107px" height="27px"/>        
          </td>
          </tr>
          <tr><td><a href="">开往春天的地铁司机——廖明 </a></td></tr>
          <tr><td><a href="">红线女</a> </td></tr>
          <tr><td><a href="">印象樊锦诗</a></td></tr>
          <tr><td><a href="">李桓英：人生只有拼搏，才是生命的最好延长</a> </td></tr>
          <tr><td><a href="">杂交水稻之父-袁隆平</a> </td></tr>
          <tr><td><a href="">当代毕昇 王选</a> </td></tr>
       </table>
    </div>
    <div class="right">
          <table class="rightdiv_table" >
          <tr><td>
            <img src="static/image/laomo/gzdt.png" width="107px" height="27px"/> 
          </td>      
          </tr>
          <tr><td><a href="">郴州北湖区劳模有保障更有新作为 </a></td></tr>
          <tr><td><a href="">无锡市全面展开2015年劳模休养和健康体检活动</a></td></tr>
          <tr><td><a href="">四川健全完善关爱服务帮扶救助劳模政策制度 </a></td></tr>
          <tr><td><a href="">宿迁市总工会“过程服务”提升困难劳模幸福指数 </a></td></tr>
          <tr><td><a href="">黄山市市直特殊困难劳模慰问活动走向制度化和规范化</a></td></tr>
       </table>
    </div>
    <div class="clear"></div>
</div>


  <div class="ldzfc">
  <div class="ldzfc_top"><img src="static/image/laomo/ldzfc.jpg" width="100%" height="24px"/></div>
  <div id="demo">
      <div id="indemo">
      <div id="demo1">
        <a href="#"><img src="static/image/laomo/关羽.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/伏皇后.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/甄姬.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/曹昂.PNG" height="150" border="0"/></a>  
        <a href="#"><img src="static/image/laomo/曹丕.PNG" height="150" border="0"/></a>
        <a href="#"><img src="static/image/laomo/郭嘉.PNG" height="150" border="0"/></a>
        <a href="#"><img src="static/image/laomo/刘备.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/马超.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/司马懿.PNG" height="150" border="0"/></a>  
        <a href="#"><img src="static/image/laomo/张郃.PNG" height="150" border="0"/></a>
      </div>
     <div id="demo2">
        <a href="#"><img src="static/image/laomo/关羽.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/伏皇后.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/甄姬.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/曹昂.PNG" height="150" border="0"/></a>  
        <a href="#"><img src="static/image/laomo/曹丕.PNG" height="150" border="0"/></a>
        <a href="#"><img src="static/image/laomo/郭嘉.PNG" height="150" border="0"/></a>
        <a href="#"><img src="static/image/laomo/刘备.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/马超.PNG" height="150" border="0"/></a> 
        <a href="#"><img src="static/image/laomo/司马懿.PNG" height="150" border="0"/></a>  
        <a href="#"><img src="static/image/laomo/张郃.PNG" height="150" border="0"/></a> 
     </div>
  </div>
 </div>

<script>
  <!--
  var speed=20;
  var tab=document.getElementById("demo"); 
  var tab1=document.getElementById("demo1");  
  var tab2=document.getElementById("demo2");
  tab2.innerHTML=tab1.innerHTML;
  function Marquee(){
  if(tab2.offsetWidth-tab.scrollLeft<=0)  
  tab.scrollLeft-=tab1.offsetWidth
  else{
  tab.scrollLeft++;
  }  
  }  
  var MyMar=setInterval(Marquee,speed);  
  tab.onmouseover=function() {clearInterval(MyMar)};
  tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
-->
</script> 
</div> 
</body>
</html>