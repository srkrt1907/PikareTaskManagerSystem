package com.pikare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pikare.dao.TaskDao;
import com.pikare.model.FilterClass;
import com.pikare.model.Task;

@Controller
@RequestMapping("/data")
public class TaskController {
	
	@Autowired
	TaskDao taskDao;
	
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/taskAll", method = RequestMethod.GET)
	@ResponseBody List<FilterClass> taskGetir(@RequestParam(value= "user") String user)
	{
		List<FilterClass> list = taskDao.getCountClose("","");
		return list;
	}
	
	

}
