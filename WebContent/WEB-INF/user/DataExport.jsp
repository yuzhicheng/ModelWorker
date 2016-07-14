<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.xadzkd.templet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="/ModelWorker/static/js/common/jquery-2.1.4.js"></script>
    <title></title>
    <style>
        .leftlabel{
            background-color: rgba(255, 83, 27, 0.9);
            width: 120px;
        }
        .rightlabel{
            width: 120px;
        }
        .button{
            border: none;;
        }
        #year{
            width: 240px;
        }
        input{
            width: 80px;
        }
        td{
            border: 1px solid;
            border-color: gray;
            text-align: center;
            height: 30px;
        }
        table{
            border-spacing: 10px;
            margin-left:10px;
        }
    </style>
</head>
<body style="background-color: ghostwhite">
<a style="margin-left: 14px;display: block;">·数据导出</a>
<a style="font-size: smaller;color: red;margin-left: 28px;;margin-top: 5px;display: block;">将指定时间段的数据导出到Excel</a>
<table class="inputtable">
    <tr>
        <td class="leftlabel">导出数据类型：</td>
        <td class="rightlabel">
            <select id="type" style="height: 90%;width: 96%">
                <option value="0">---请选择---</option>
                <option  value="1">先进个人信息</option>
                <option  value="2">先进集体信息</option>
            </select>
        </td>
    </tr>
    <tr>
        <td class="leftlabel">导出年份：</td>
        <td class="rightlabel">
            <select id="range" style="height: 90%;width: 96%">
                <option value="0">---请选择---</option>
                <option  value="1">历届</option>
                <option  value="2">时间段</option>
            </select>
        </td>
    </tr>
</table>
<table id="year" class="year" style="display: none;margin-left: 20px">
    <tr>
        <td style="background-color: rgba(255, 83, 27, 0.9);">
            起始年份:
        </td>
        <td style="border: none">
            <input value="" name="startYear" type="text" style="height:25px;width: 70px;"/>
        </td>
    </tr>
    <tr>
        <td style="background-color: rgba(255, 83, 27, 0.9);">
            终止年份:
        </td>
        <td style="border: none">
            <input value="" name="endYear" type="text" style="height:25px;width: 70px;"/>
        </td>
    </tr>
</table>
<input type="button" value="导出数据" class="Export year" onclick="exportData()" style="margin-left: 107px;height: 30px;">
</body>
<script>
    $("#range").change(function() {
        if($("#range option:selected").attr("value")==2){
            $(".year").fadeOut(300);
            $(".year").fadeIn(300);
        }
        else
        {
            $(".year").fadeOut(300);
            $(".Export").fadeIn(300);
            $("input[name='startYear']").val("");
            $("input[name='endYear']").val("");
        }
    });
</script>
<script>
    function exportData()
    {
        switch($("#type option:selected").attr("value"))
        {
            case "0":
                alert("请选择导出数据类型");
                break;
            case "1":
                switch($("#range option:selected").attr("value"))
                {
                    case "0":
                        alert("请选择导出年份");
                        break;
                    case "1":
                        location.href="/ModelWorker/DataImprotAndExport/ExportAdvancedPersonDataAll?fileName=历届先进个人信息";
                        break;
                    case "2":
                        var checkNum=/^[0-9]{1,}$/;
                        if(checkNum.test($("input[name='startYear']").val())&&checkNum.test($("input[name='endYear']").val()))
                        {
                            var startYear=parseInt($("input[name='startYear']").val());
                            var endYear=parseInt($("input[name='endYear']").val());
                            if(startYear>endYear)
                                alert("起始年份大于结束年份，请输入有效年份区间");
                            else
                                location.href="/ModelWorker/DataImprotAndExport/ExportAdvancedPersonDataByYear?fileName=年先进个人信息&startYear="+startYear+"&endYear="+endYear;
                        }
                        else alert("请输入正确的年份");
                        break;
                }
                break;
            case "2":
                switch($("#range option:selected").attr("value"))
                {
                    case "0":
                        alert("请选择导出年份");
                        break;
                    case "1":
                        location.href="/ModelWorker/DataImprotAndExport/ExportAdvancedGroupDataAll?fileName=历届先进集体信息";
                        break;
                    case "2":
                        var checkNum=/^[0-9]{1,}$/;
                        if(checkNum.test($("input[name='startYear']").val())&&checkNum.test($("input[name='endYear']").val()))
                        {
                            var startYear=parseInt($("input[name='startYear']").val());
                            var endYear=parseInt($("input[name='endYear']").val());
                            if(startYear>endYear)
                                alert("起始年份大于结束年份，请输入有效年份区间");
                            else
                                location.href="/ModelWorker/DataImprotAndExport/ExportAdvancedGroupDataByYear?fileName=年先进集体信息&startYear="+startYear+"&endYear="+endYear;
                        }
                        else alert("请输入正确的年份");
                        break;
                }
                break;
        }
    }
</script>
</html>