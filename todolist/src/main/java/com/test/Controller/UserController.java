package com.test.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.test.Dao.IUserDao;
import com.test.Dao.Impl.UserDaoImpl;
import com.test.Model.Student;
import com.test.Model.User;
@Controller
public class UserController extends AbstractController {
	
	@Resource
	private IUserDao userDaoImpl;
	
	private static final String SUCCESS = "{'code':'success'}";
	private static final String ERROR = "{'code':'error'}";
	
	@RequestMapping("/user")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
			return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody User user){
		System.out.println("1111");
		user=userDaoImpl.Checklogin(user.getUserCode(), user.getPassWord());
		if(user!=null){
////			model.addAttribute(user);
			System.out.println("成功");
		return SUCCESS;
//			
	}
		System.out.println("失败");
		return ERROR;
		
	}
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public @ResponseBody String register(@RequestBody User user){
		System.out.println("111111111111111111");
		User u=userDaoImpl.Register(user.getUserCode(), user.getPassWord());
		System.out.println("2222222222");
		if(u==null){
			userDaoImpl.addUser(user);
			System.out.println("注册成功");
			return SUCCESS;
		}
		System.out.println("注册失败");
		return ERROR;
		
	}
}


