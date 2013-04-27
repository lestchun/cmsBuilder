package com.jsu.lei.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsu.lei.model.Modul;
import com.jsu.lei.service.ModulService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"}) 
public class TestSpring   {
//	@Autowired ITestDao testDao;
	@Autowired ModulService mdao;
	@Test
	public void testLoad(){
		 
		Modul m= new Modul();
		m.setId(3);
		m.setComment("测试组件2");
		m.setName("继续测试");
		m.setMainClass("123454");
//		mdao.save(m);
		mdao.update(m);
//		System.out.println(m.getId());
		
//		System.out.println(cdao.findById(1));
//		System.out.println(cdao.listAll());
		
//		System.out.println(testDao.findById(null));
	}
}
