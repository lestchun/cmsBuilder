package com.jsu.lei.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CreateTable {
	public static void main(String[] args) throws Exception {
		
		HSSFWorkbook wk= new HSSFWorkbook(CreateTable.class.getClassLoader().getResourceAsStream("数据库设计.xls"));
		
		HSSFSheet sh=wk.getSheetAt(0);
		
		int n=sh.getLastRowNum();
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<=n;i++){
			HSSFRow row= sh.getRow(i);
			
			String ename=row.getCell(0).toString();
			String cname=row.getCell(1).toString();
			String type=row.getCell(2).toString();
			String comment=null==row.getCell(3)?"":row.getCell(3).toString();
			
			if("表名".equals(ename)){
				if(i!=0){
					sb.append("  PRIMARY KEY (`id`)  );");
				}
				sb.append("\nCREATE TABLE `cmscreate`.`"+cname+"`(    ");
			}else if("id".equals(ename)){
				sb.append("\n`id` INT NOT NULL AUTO_INCREMENT COMMENT '"+comment+"',  ");
			}else{
				sb.append("\n`"+ename+"` "+type+" COMMENT '"+comment+"',  ");
			}
		}
		sb.append("  PRIMARY KEY (`id`)  );");
		System.out.println(sb);
	}
}
