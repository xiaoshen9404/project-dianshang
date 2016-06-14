package com.ds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	@RequestMapping(value={"/",""})
	public String  index(){
		return "video/index";
	}
}
