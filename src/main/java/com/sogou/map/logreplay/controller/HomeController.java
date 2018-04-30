package com.sogou.map.logreplay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/**
	 * ����·������ת����/home.htm
	 */
	@RequestMapping("/")
	public String toHome() {
		return "redirect:home.htm";
	}

	/**
	 * ��ҳ������ת����jsp
	 */
	@RequestMapping("/**/*.htm")
	public String toJsp(HttpServletRequest request) {
		return request.getRequestURI().replace(request.getContextPath(), "").replaceAll("\\.htm$", "");
	}

}
