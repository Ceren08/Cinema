package com.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.MediaType;


@RestController
@RequestMapping(value="/rest")
public class MainRestController {
	
	@Autowired
	private com.cinema.dao.SalonDao SalonDao;
	
	@RequestMapping(value = "/salonList", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List salonlarListesi() {
		return SalonDao.read();
	}
	
	

}
