package com.nathan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.entity.User;
import com.nathan.service.RibbonHystrixService;
import com.nathan.service.RibbonService;

@RestController
public class RibbonController {

	@Autowired
	private RibbonService ribbonService;
	
	@Autowired
	private RibbonHystrixService ribbonHystrixService;

	@GetMapping("/ribbon/{id}")
	public User findById(@PathVariable Long id) {
		return this.ribbonService.findById(id);
	}
	
	@GetMapping("/ribbonHystrix/{id}")
	public User findById2(@PathVariable Long id) {
		return this.ribbonHystrixService.findById(id);
	}
}
