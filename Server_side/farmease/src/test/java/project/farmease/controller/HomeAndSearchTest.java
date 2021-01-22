package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import project.farmease.dto.Hostdto;
import project.farmease.pojo.Hostuser;

public class HomeAndSearchTest {

	//Logger logger = LogManager.getLogger(HomeAndSearchTest.class);
	static HomeAndSearch homeAndSearch;
	List<Hostuser> l;	
	
	@BeforeAll
	static void beforeAll() 
	{
		homeAndSearch = new HomeAndSearch();
	}
	
	@Test
	void searchforservice()
	{
		int count = 0;
		Hostuser hostuser = new Hostuser();
		hostuser.setEquipmenttype("tractor");
		hostuser.setLocation("pune");
		l = homeAndSearch.searchforservice(hostuser);
		for (Hostuser h : l) 
		{
			if (h.getEquipmenttype().equalsIgnoreCase("tractor") && h.getLocation().equalsIgnoreCase("pune")) 
			{
				count++;
			}
		}
		assertEquals(count, l.size());
	}
	
	@Test
	void searchforservicefailedcase()
	{
		Hostuser hostuser = new Hostuser();
		hostuser.setEquipmenttype("tract");
		hostuser.setLocation("pun");
		
		try {
			l = homeAndSearch.searchforservice(hostuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertEquals(0, l.size());
	}
	
	//@Test
	void sum()
	{
		int s = homeAndSearch.sum(-8,7);
		assertEquals(-1, s);
	}
}

