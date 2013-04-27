<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	var project = {
		projectAddUrl:'addProject.html',
		projectDeleteUrl:'',
		projectGrid : $("#showProjectGrid").datagrid({
			singleSelect:true,
			columns : [ [ {
				field : 'id',
				width : 80,
				checkbox : true
			}, {
				field : 'name',
				title : '项目名',
				width : 250
			} ] ],
			title : '项目列表',
			fit : true,
			method : 'post',
			onClickRow:function(i,r){
				utils.formReset("subForm",r);
				$("#logo_img").attr("src", decodeURIComponent(r.logo));
				project.rows=r;
			},
			toolbar : [ {
				text : '添加',
				handler : function() {
					project.rows=null;
					document.getElementById("subForm").reset();	
				}
			}, {
				text : '删除',
				handler : function() {
					var obj=$("#showProjectGrid").datagrid("getSelected");
					if(!obj){
						$.messager.alert("提示","你没有选择一行记录" ,"warning");
						return ;
					}else{
						$.post("deleteProject/"+obj['id']+".html",function(data){
							if(data.resultCode=='1001'){
								$.messager.alert("提示",data.msg ,"warning");
							}else{
								$("#showProjectGrid").datagrid("reload");
							}
						});
					}
				}
			} ],
			url :'listProject.html' 
		}),
		uploadImage : function() {
			utils.upload("subForm", "w_img", function(data) {
				$("#logo_img").attr("src", data.rows.url);
				$("#logo_url").val(data.rows.url);
			});
		},
		uploadJar:function(){
			
			utils.upload("jarForm", "t_pjar", function(data) {
				$("#beans").val(data.rows.url);
				alert("jar文件上传成功");
			});
			
		},
		saveProject : function() {
			var obj = utils.getObjForm("subForm");
			$.post(project.projectAddUrl,obj,function(data){
				if(!data||data.resultCode ==1001){
					$.messager.alert("提示",data.msg ,"warning");
				}else{
	 				//document.getElementById("subForm").reset();
	 				project.rows=data.rows;
	 				$("#showProjectGrid").datagrid("reload");
				}
			});
		},
		nextStep:function(){
			if(project.rows&&project.rows.id){
				var id=utils.getObjForm('subForm').id;
				utils.chgPanel('jsp/component.jsp');
			}else{
				alert("你没有选择一个工程,或者你的工程还没保存！请先保存");
			}
		},
		
		init : function() {
				
		}

	};
</script>

	<table style="width: 100%; height: 100%">
		<tr>
			<td align="center">
			<form id="jarForm" enctype="multipart/form-data" method="post">
			<table style="margin-left: -270px">
				<tr>
					<td align="right">项目bean：</td>
					<td ><input type="file" name="f"/> 
					<input type="button" onclick="project.uploadJar()" value="提交"></td>
				</tr>
			</table>
			</form>
			<form id="subForm" enctype="multipart/form-data" method="post">
				<input type="hidden" name=id> 
				<input type="hidden" name="logo" id="logo_url">
				<input type="hidden" name="beans" id="beans">
				<table >
					<tr>
						<td align="right">项目LOGO：</td>
						<td colspan="3">
						<img src="" id="logo_img"> 
						<input type="file" name="f"> 
						<input type="button" onclick="project.uploadImage()" value="提交"></td>
					</tr>
					<tr>
						<td align="right">项目名称：</td>
						<td colspan="3"><input name="name" style="width: 100%">
						</td>
					</tr>
					<tr>
						<td align="right">项目介绍：</td>
						<td colspan="3"><textarea rows="10" cols="100"
								name="descript"></textarea></td>
					</tr>
					<tr>
						<td align="right">项目访问地址:</td>
						<td colspan="3"><input name="addr" style="width: 100%">
						</td>
					</tr>
					<tr>
						<td align="right">数据库名称:</td>
						<td colspan="3"><input name="databseName" style="width: 100%">
						</td>
					</tr>
					<tr>
						<td align="right">项目数据库用户名:</td>
						<td><input name="username"></td>
						<td align="right">项目数据库密码:</td>
						<td><input name="password" type="password"></td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" value="保存" onclick="project.saveProject()">
							<input type="button" value="下一步" onclick="project.nextStep()">
						</td>
					</tr>
				</table>
				</form>
			</td>
			<td style="width: 300px">
				<table id="showProjectGrid"></table>
			</td>
		</tr>
	</table>
