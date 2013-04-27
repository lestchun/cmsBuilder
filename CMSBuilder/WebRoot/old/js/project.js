var project = {
		projectAddUrl:'addProject',
		addProject:function(){
			project.sdata=!1;
			utils.chgPanel("newJsp/addProject.jsp");
		},
		init : function() {
			$("#showProjectGrid").datagrid({
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
					project.sdata=r;
					utils.chgPanel("newJsp/addProject.jsp");
				},
				toolbar : [ {
					text : '新建',
					iconCls:'icon-add',
					handler : project.addProject
				}, {
					text : '移除',
					iconCls:'icon-remove',
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
			});
		}
	};

$(function(){
	project.init();
});
