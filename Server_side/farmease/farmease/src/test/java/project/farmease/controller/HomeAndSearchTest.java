package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.farmease.models.Hostuser;

public class HomeAndSearchTest {

	HomeAndSearch h;
	List<Hostuser> l; 
	Hostuser h1 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "pune", 222, null);
	
	@Test
	void searchforservicestrue()
	{
		//l = new ArrayList<Hostuser>();
		l = h.searchforservice("tractor","pune");
		assertEquals(true, l.contains(h1));
	}
	
	@Test
	void searchforservicesfalse()
	{
		l = h.searchforservice("tractor","hyd");
		assertEquals(0, l.size());
	}
}

