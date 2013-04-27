<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery-easyui-1.3/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3/jquery.form.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3/datagrid-detailview.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3/tool.js"></script>
<link href="jquery-easyui-1.3/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="jquery-easyui-1.3/themes/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/public.css" type="text/css"></link>
<link rel="stylesheet" href="css/style.css" type="text/css"></link>
<title>CMS构建系统</title>
</head>
<body class="easyui-layout">  
    <div data-options="region:'north',title:'CMS构建系统',split:true" style="height:140px;">
    	<img src="images/jsu.png" alt="CMS构建系统"/>
		<div class="clearall"></div> 
    </div>  
    <div data-options="region:'center',title:'项目管理'" id="index_showpanel" style="padding:5px;background:#eee;">
   		<center>
   			<br/>
   			<br/>
   			<br/>
   			<br/>
   			<br/>
			<h1 style="font-size: 100">CMS构建系统</h1>  
			<a href="javascript:void(0)" onclick="utils.chgPanel('jsp/project.jsp');">进入项目管理</a>  		
   		</center>
    </div>
</body> 
</html>