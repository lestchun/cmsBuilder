<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>
	<script type="text/javascript">
		var compont = {
			modulGrid : $("#modulList").datagrid({
				toolbar : [ {
					text : '添加模块',
					handler : function() {
						$("#modulDetialWin").window('open');
						utils.formReset("modelDetialForm");
					}
				}, {
					text : '移除模块', 
					handler : function() {

					}
				} ],
				onClickRow:function(i,r){
					 
					$("#columnList").datagrid({url:'listColumn/'+r.id+".html"});
					$("tt").tree({url:'listComponeClass/'+r.id+".html"});
					
				},
				onDblClickRow:function(i,r){
					$("#modulDetialWin").window('open');
					utils.formReset("modelDetialForm",r);
				},
				fitColumns : true,
				columns : [ [ {
					field : 'id',
					width : 40,
					checkbox : true
				}, {
					field : 'name',
					title : '模块名称',
					width : 80
				} ] ],
				
				view : detailview,
				detailFormatter : function(index, row) {
					return '<div class="ddv">' + row.comment + '</div>';
				},
				url : 'listModul/'+project.rows.id+".html",
				title : '模块列表',
				fit : true,
				method : 'post'
			}),
			modulDetialWin:$("#modulDetialWin").show().window({
				title:'模块详情',
				width:600,  
			    height:400,  
			    modal:true,
			    closed:true
				
			}),
			classCombo:$("#mainClass").combobox({
				url:"listProjectClass/"+project.rows.id+".html",
				valueField:'id',
				fit:true,
				textField:'value',
				onSelect: function(rec){  
					 
	        	}
			}),
			columnGrid : $("#columnList").datagrid({
				title : '属性列表',
				fit : true,
				method : 'post',
				//fitColumns:true,
				columns : [ [ {
					field : 'oname',
					width : 100,
					title : '类中名称'
				}, {
					field : 'ename',
					width : 100,
					editor : 'text',
					title : '显示名称'
				}, {
					field : 'showName',
					width : 100,
					editor : 'text',
					title : '中文名称'
				}, {
					field : 'ispre',
					width : 100,
					editor : 'text',
					title : '是否为主键',
					editor : {
						type : 'checkbox',
						options : {
							on : '是',
							off : '否'
						}
					}
				}, {
					field : 'defaultValue',
					width : 100,
					editor : 'text',
					title : '默认值'
				}, {
					field : 'width',
					width : 100,
					editor : 'text',
					title : '宽度'
				}, {
					field : 'editMethod',
					width : 100,
					editor : 'text',
					title : '编辑方式'
				}, {
					field : 'search',
					width : 100,
					editor : 'text',
					title : '搜索方式',
					editor : {
						type : 'combobox',
						options : {
							data : [ {
								label : 'java',
								value : 'Java'
							}, {
								label : 'perl',
								value : 'Perl'
							}, {
								label : 'ruby',
								value : 'Ruby'
							} ]
						}
					}
				}, {
					field : 'formatter',
					width : 100,
					editor : 'text',
					title : '格式化显示'
				},

				{
					title : '是否显示',
					field : 'isshow',
					width : 60,
					align : 'center',
					editor : {
						type : 'checkbox',
						options : {
							on : '显示',
							off : '隐藏'
						}
					}

				}

				] ]
			}),
			classMapping : $("#tt").tree({
				//url:'listEntityTree',
				title : "model属性列表",
				fit : true,
				onClick : function(node) {
				}
			}),
			saveModul:function(){
				var obj=utils.getObjForm("modelDetialForm");
				$.post("addModul.html",obj,function(data){
					
				});
			},
			init : function() {

			},
			
			preStep : function() {
				utils.chgPanel("jsp/project.jsp");
			},
			saveModul:function(){
				var obj=utils.getObjForm("#modelDetialForm");
				$.post("addModul.html",obj,function(data){
					
					if(data.resultCode=="1001"){
						alert(data.msg);
					}else{
						alert("修改成功");
					}
					
				});
				
			}

		};

		$(function() {
			compont.init();
		});
	</script>
	<table style="width: 100%; height: 100%">
		<tr>
			<td style="width: 200px">
				<table id="modulList"></table>
			</td>
			<td style="width: 200px">
				<ul id="tt"></ul>
			</td>
			<td>
				<table id="columnList"></table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
			<input type="button" value="上一步" onclick="compont.preStep()"/>
			<input type="button" value="生成代码"/></td>
		</tr>
	</table>
	<div id="modulDetialWin" style="display:none">
		<form   id="modelDetialForm">
			<input type="hidden" name="id">
			<table style="width:100%;height:100%">
				<tr>
					<td>模块名</td>
					<td><input name="name"/></td>
				</tr>			
				<tr>
					<td>模块说明</td>
					<td><textarea  name="comment"></textarea></td>
				</tr>	
				<tr>
					<td>主要的类</td>
					<td><input  id="mainClass" name="mainClass"></td>
				</tr>	
				<tr >
					<td colspan="2"><input  type="button" value="保存" onclick=""></td>
				</tr>	
			</table>
		</form>
	</div>
</body>
