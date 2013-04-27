<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	var project = {
		projectAddUrl:'addProject',
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
			},
			toolbar : [ {
				text : '添加',
				handler : function() {

				}
			}, {
				text : '删除',
				handler : function() {
					var obj=$("#showProjectGrid").datagrid("getSelected");
					if(!obj){
						$.messager.alert("提示","你没有选择一行记录" ,"warning");
						return ;
					}else{
						$.post("deleteProject/"+obj['id'],function(data){
							if(data.resultCode=='1001'){
								$.messager.alert("提示",data.msg ,"warning");
							}else{
								$("#showProjectGrid").datagrid("reload");
							}
						});
					}
				}
			} ],
			url :'listProject' 
		}),
		uploadImage : function() {
			utils.upload("subForm", "w_img", function(data) {
				$("#logo_img").attr("src", data.rows.url);
				$("#logo_url").val(data.rows.url);
			});
		},
		saveProject : function() {
			var obj = utils.getObjForm("subForm");
			$.post(project.projectAddUrl,obj,function(data){
				if(!data||data.resultCode ==1001){
					$.messager.alert("提示",data.msg ,"warning");
				}else{
	 				document.getElementById("subForm").reset();
	 				$("#showProjectGrid").datagrid("reload");
				}
			});
		},
		init : function() {

		}

	};
</script>

<form id="subForm" enctype="multipart/form-data" method="post">
	<input type="hidden" name=id> 
	<input type="hidden" name="logo" id="logo_url">
	<table style="width: 100%; height: 100%">
		<tr>
			<td align="center">
				<table>
					<tr>
						<td align="right">项目LOGO：</td>
						<td colspan="3"><img src="" id="logo_img"> <input
							type="file" name="f"> <input type="button"
							onclick="project.uploadImage()" value="提交"></td>
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
						<td align="center" colspan="4"><input type="button"
							value="保存" onclick="project.saveProject()"></td>
					</tr>
				</table>
			</td>
			<td style="width: 300px">
				<table id="showProjectGrid"></table>
			</td>
		</tr>
	</table>
</form>
