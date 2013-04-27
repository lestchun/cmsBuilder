<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var modul={
		modulGrid : $("#modulGrid").datagrid({
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
		init:function(){
			
		}	
			
			
	};
</script>

<table id="modulGrid"></table>