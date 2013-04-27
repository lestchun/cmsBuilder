/**
 * 通过表单的Id获得表单里面不为空的键值对
 * @param formId
 * @returns 不为空的键值对
 */
utils={
		
		chgPanel:function(url){
			$("#index_showpanel").panel("refresh",url);
		},
		getObjForm:function (formId){
			var str = 	$("#"+formId).serialize()  ;
			
			str=str.split("&");
			
			var obj={};
			
			for(var i=0;i<str.length;i++){
				
				var os =  str[i].split("=");
				
				if(""!=os[1]){
					obj[os[0]]=decodeURI(os[1]);
				}
				
			}
			return obj;
		},
		formReset:function (formId,data){
			var obj=utils.getObjForm(formId);
			if(!data){
				data={};
			}
			for(var s in obj){
				if(!data[s]){
					data[s]="";
				}
			}
			$("#"+formId).form("load",data);
		},
		upload:function ( fromId,fid,cb,errorCB){
			 
		    var options = {
		        url : "upload?&fid="+fid,
		        type : 'POST',
		        cache:true,
		        dataType : "json",
		        success : function(data) {
		    		if(data && data.resultCode == 1000){
		                if(cb)
		                	cb(data);
		            }else if(data.resultCode==1001){
		            	$.messager.alert("提示",data.msg ,"warning"); 
		            	if(errorCB)
		            		errorCB();
		            }
		        },
		        error : function(data){
		        	$.messager.alert("提示","上传文件错误！","warning"); 
		        	if(errorCB)
		        		errorCB();
		        }
		    };
		  $('#'+fromId).ajaxSubmit(options);
		}
};





