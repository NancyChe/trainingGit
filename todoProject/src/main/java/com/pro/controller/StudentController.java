package com.pro.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.pro.pojo.Student;
import com.pro.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController extends AbstractController {
	@Resource
	private StudentService studentService;
	
	@RequestMapping("nancy.do")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("studList");
	}
	
	@ResponseBody
	@RequestMapping(value="/stlist.do")
	public List<Student> findStudList(){
		return studentService.getAllList();
	}
}

