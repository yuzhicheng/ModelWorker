<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>先进集体统计信息</title>
<link href="/ModelWorker/static/css/Main_Frame.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.cookie.js"></script> 
<script type="text/javascript" src="/ModelWorker/static/js/common/common.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/build/dist/echarts.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/drawcircle.js"></script>
<script type="text/javascript">

    require.config({
        paths: {
            echarts: '/ModelWorker/static/js/build/dist/'
        }
    });
    
    var data_arr = [];  
    var text_arr = [];  
    
    <c:forEach items="${ nameList}" var="adv">
    text_arr.push('${adv}');
    </c:forEach>
    
    <c:forEach items="${valueList}" var="td">
    data_arr.push('${td}');
    </c:forEach>
    require(
            [
                'echarts',
                'echarts/chart/pie' 
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                	    title : {
                	        text: '先进集体数据统计',
                	       // subtext: '',
                	        x:'center'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'left',
                	        data: text_arr
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: false},
                	            dataView : {show: true, readOnly: true},
                	            magicType : {
                	                show: false, 
                	                type: ['pie', 'funnel'],
                	                option: {
                	                    funnel: {
                	                        x: '25%',
                	                        width: '50%',
                	                        funnelAlign: 'left',
                	                        max: 1548
                	                    }
                	                }
                	            },
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    series : [
                	        {
                	            name:'先进集体',
                	            type:'pie',
                	            radius : '68%',
                	            center: ['50%', '60%'],
                	            data:
                	            	(function(){
                	            		 
                                        var res = [];
                                        var len = text_arr.length;
                                        while (len--) {
                                        res.push({
                                        name: text_arr[len],
                                        value: data_arr[len]
                                        });
                                        }
                                        return res;
                                        })()
                	        }
                	    ]
                	};
        
                myChart.setOption(option); 
            }
        );
</script>
</head>

<body  class="nested">
	 <div class="maincontainer">
	 	<div class="columnconn"><span class="columntitle">先进集体统计信息</span></div>
	 	<div style="margin-top: 12px; margin-left: 12px;"> 
	 		<form action="/ModelWorker/user/groupstatistics" method="post">
	 			<select name="type" style="width:120px;height:39px;">
                        	<option value="0">请选择统计区间</option>
                        	<option value="1">按荣誉称号统计</option>
                 </select>
                 <input type="submit" value="统计" style="width:120px;height:38px;"></input> 			
	 		</form>
	 	</div>
	 	<div>
	 		<p style="margin-top: 12px; margin-left: 12px;">
	 			先进集体总数：${ groupsum } <br />
	 			<c:forEach items="${ titlenummap}" var="td">
	 				${ td.key  } : ${td.value } <br/>
	 			</c:forEach>
	 			
	 		</p>
	 		<p style="margin-top: 20px; margin-left: 20px;">  
             <div id="main" style="height:400px"></div>
            </p>
	 	</div>
	 </div>
</body>
</html>