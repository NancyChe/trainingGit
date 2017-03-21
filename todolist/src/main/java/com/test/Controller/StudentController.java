package com.test.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.test.Dao.Impl.StudentDaoImpl;
import com.test.Model.Student;
import com.test.Model.User;

@Controller
public class StudentController extends AbstractController {

	private static int id;

	public StudentController() {
	}

	private StudentDaoImpl studentDaoImpl;

	public StudentDaoImpl getStudentDaoImpl() {
		return studentDaoImpl;
	}

	@Resource
	public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
		this.studentDaoImpl = studentDaoImpl;
	}

	@RequestMapping("/nancy.do")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("student");
	}

	@ResponseBody
	@RequestMapping(value = "/stlist.do")
	public List<Student> getAll() {
		return studentDaoImpl.findAllList();
	}

	@ResponseBody
	@RequestMapping(value="/findinfo.do",method=RequestMethod.POST)
	public Student getById(int id){
		return studentDaoImpl.findById(id);
	}

	@ResponseBody
	@RequestMapping(value="/stadd.do", method = RequestMethod.POST)
	public int save(@RequestBody Student stud) {
		return studentDaoImpl.studAdd(stud);
	}

//	@JsonCreator
	@RequestMapping(value="/stupdate.do", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody Student stud) {
		System.out.println("student update");
		studentDaoImpl.studUpdate(stud);
		return "{'code':'success'}";
	}

	@ResponseBody
	@RequestMapping(value="/stdelete.do", method = RequestMethod.GET)
	public int delete(int id) 
	{
		return studentDaoImpl.studDelete(id);
	}

}
