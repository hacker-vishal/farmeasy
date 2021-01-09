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

import project.farmease.models.Hostuser;

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
		l = homeAndSearch.searchforservice("tractor","pune");
		for (Hostuser hostuser : l) 
		{
			if (hostuser.getEquipmenttype().equalsIgnoreCase("tractor") && hostuser.getLocation().equalsIgnoreCase("pune")) 
			{
				count++;
			}
		}
		assertEquals(count, l.size());
	}
	
	@Test
	void searchforservicefailedcase()
	{
		//int count = 0;
		try {
			l = homeAndSearch.searchforservice("tract","bhopal");
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

