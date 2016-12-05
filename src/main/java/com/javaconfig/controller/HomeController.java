package com.javaconfig.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaconfig.dao.UserDao;
import com.javaconfig.model.User;

@Controller
public class HomeController {

	private static final Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/")
	public ModelAndView handleRequest() throws Exception {
		log.info("Welcome to HomeController.");
		List<User> userList = userDao.list();
		ModelAndView model = new ModelAndView("UserList");
		model.addObject("userList", userList);
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		userDao.test();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		log.info("Adding new user : " + user.getId());
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/");
	}
}
