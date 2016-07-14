<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.xadzkd.templet.*"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据上传</title>
    <style type="text/css">

        #progressBar{
            width:200px;
            height:12px;
            background:#FFFFFF;
            border:1px solid #000000;
            padding: 1px;
        }

        #progressBarItem{
            width:30%;
            height:100%;
            background:#FF0000;

        }
        #form_upload td{
            line-height:35px;
            padding-top:10px;

        }
        body{
            font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
        }
        #templet{
            display: none;
        }
    </style>
</head>
<body style="background-color:#f5f5f5; padding-top:5px; padding-left:5px">
<iframe width=0 height=0 name=upload_iframe></iframe>
<table class="inputtable">
    <tr>
        <td><input type="button" value="下载模板" onclick="showTemplet()" style="height:30px;"></td>
    </tr>
    <tr>
        <td id="templet">
            模板类型：
            <select id="templetType">
                <option value="0">---请选择---</option>
                <option  value="1">先进个人模板</option>
                <option  value="2">先进集体模板</option>
                <option  value="3">个人账号模板</option>
                <option  value="4">集体账号模板</option>
            </select>
            <input type="button" class="import" value="下载" onclick="downloadTemplet()">
        </td>
    </tr>
</table>
<form action="/ModelWorker/user/upload.do" method="post" enctype="multipart/form-data" target=upload_iframe onsubmit="showStatus();">
    <table id="form_upload" style="padding-left:20px">
        <td>选择需要导入的文件：</td>
        </tr>
        <td><input type="file" name="file" accept="application/msexcel"/></td>
        <td><div id="status" style="display:none">
            <div id="progressBar"><div id="progressBarItem"></div></div>
        </div>
        </td>
        <td><div style="display:none; font-size:13px" id="process">1%</div></td>
        <tr>
            <td><input type="submit" style="width:80px;height:30px;" value="上传文件" id="uploadButton"/></td>
        </tr>
    </table>

</form>
<form action="">
    <table class="inputtable" style="margin-top:10px;">
        <tr>
            <td class="leftlabel">导入数据类型：</td>
            <td class="rightlabel">
                <select id="type">
                    <option value="0">---请选择---</option>
                    <option  value="1">先进个人信息</option>
                    <option  value="2">先进集体信息</option>
                    <option  value="3">个人账号信息</option>
                    <option  value="4">集体账号信息</option>
                </select>
                <input type="button" class="import" value="导入" disabled="disabled" id="resolve" onclick="importData()">
            </td>
        </tr>
    </table>
</form>
</body>

<script type="text/javascript" src="/ModelWorker/static/js/common/jquery-2.1.4.js"></script>
<script type="text/javascript">
    var _finished = true;
    function showStatus(){
        _finished = false;
        $('#status').css({display:"block"});
        $('#progressBarItem').css({width:"1%"});
        $('#uploadButton').attr("disabled","disabled");
        $('#process').css({display:"block"});
        setTimeout("requestStatus()", 500);

    }
    function requestStatus(){
        if(_finished) return;
        $.post("/ModelWorker/user/progress?" + new Date().getTime(),{},function(data){

            var json = jQuery.parseJSON(data);

            $('#progressBarItem').css({width:''+json.precent+'%'});
            $("#process").text(" "+json.precent+"%"+" " + json.speed + "M/S");

            if(json.precent == "100"){
                _finished = true;
                $('#process').text(" 100% 完成");
                $('#resolve').prop("disabled",false);
                $('#uploadButton').prop("disabled",false);
            }
            setTimeout("requestStatus()", 500);
        });
    }
</script>
<script type="text/javascript">
    function showTemplet()
    {
        $("#templet").fadeIn(200);
    }
    function downloadTemplet()
    {
        switch($("#templetType option:selected").attr("value"))
        {
            case "0":
                alert("请选择模板类型");
                break;
            case "1":
                location.href="/ModelWorker/DataImprotAndExport/DownloadTemplet?typeName=AdvancedPerson";
                break;
            case "2":
                location.href="/ModelWorker/DataImprotAndExport/DownloadTemplet?typeName=AdvancedGroup";
                break;
            case "3":
                location.href="/ModelWorker/DataImprotAndExport/DownloadTemplet?typeName=UserAccount";
                break;
            case "4":
                location.href="/ModelWorker/DataImprotAndExport/DownloadTemplet?typeName=GroupAccount";
                break;
        }

    }
</script>
<script type="text/javascript">
    function importData()
    {
        switch($("#type option:selected").attr("value"))
        {
            case "0":
                alert("请选择导入类型");
                break;
            case "1":
                var pattern=1;
                $.get("/ModelWorker/DataImprotAndExport/ImportAdvancedPersonData",{type:pattern},function(data){
                    var result=jQuery.parseJSON(data);
                    if(result.stauts=="error")
                        location.href="/ModelWorker/DataImprotAndExport/Error";
                    else alert("成功导入");
                });
                break;
            case "2":
                var pattern=1;
                $.get("/ModelWorker/DataImprotAndExport/ImportAdvancedGroupData",{type:pattern},function(data){
                    var result=jQuery.parseJSON(data);
                    if(result.stauts=="error")
                        location.href="/ModelWorker/DataImprotAndExport/Error";
                    else alert("成功导入");
                });
                break;
            case "3":
                var pattern=2;
                $.get("/ModelWorker/DataImprotAndExport/ImportUserAccountData",{type:pattern},function(data){
                    var result=jQuery.parseJSON(data);
                    if(result.stauts=="error")
                        location.href="/ModelWorker/DataImprotAndExport/Error";
                    else alert("成功导入");
                });
                break;
            case "4":
                var pattern=1;
                $.get("/ModelWorker/DataImprotAndExport/ImportGroupAccountData",{type:pattern},function(data){
                    var result=jQuery.parseJSON(data);
                    if(result.stauts=="error")
                        location.href="/ModelWorker/DataImprotAndExport/Error";
                    else alert("成功导入");
                });
                break;
        }
    }
</script>
</html>