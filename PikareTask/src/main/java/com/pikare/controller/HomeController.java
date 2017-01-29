package com.pikare.controller;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pikare.dao.EforDao;
import com.pikare.dao.KategoriDao;
import com.pikare.dao.TaskDao;
import com.pikare.dao.UserDao;
import com.pikare.dao.login.LoginDao;
import com.pikare.dao.login.LoginDaoImpl;
import com.pikare.model.Efor;
import com.pikare.model.Kategori;
import com.pikare.model.Task;
import com.pikare.model.UserRole;
import com.pikare.model.Users;
import com.pikare.session.PikareSession;

@Controller
public class HomeController {
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	PikareSession pikareSession;
	
	@Autowired
	KategoriDao kategoriDao;
	
	@Autowired
	LoginDaoImpl loginDaoImpl;
	
	@Autowired
	EforDao eforDao;
	
	private static final Logger logger = Logger.getLogger(HomeController.class); 
	
	
	public TaskDao getTaskDao() {
		return taskDao;
	}
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	ModelAndView giris()
	{
		logger.info(" project started");
		return new ModelAndView("/login/login");
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	ModelAndView giris2()
//	{
//		return new ModelAndView("/login/login");
//	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login2(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView model = new ModelAndView("/login/login");
		if (error != null) {
			model.addObject("error", "Kullanıc Adı yada şifre hatalı!");
		}

		if (logout != null) {
			model.addObject("msg", "Başarılı bir şekilde çıkış yapıldı.");
		}

		return model;

	}

	@RequestMapping(value = "/secure/taskEkle", method = RequestMethod.GET)
	ModelAndView taskEkleGet()
	{
		ModelAndView model = new ModelAndView("taskEkle");
		model.addObject("task", new Task());

		List<Users> users = userDao.getByUserRoles();
		model.addObject("users", users);
		
		List<Kategori> kategori = kategoriDao.get();
		model.addObject("kategori", kategori);
		
		List<String> efor = kategoriDao.getEforHarf();
		model.addObject("eforList", efor);
		
		return model;
	}
	
	@RequestMapping(value = "/secure/newUser", method = RequestMethod.GET)
	ModelAndView newUser()
	{
		ModelAndView model = new ModelAndView("registerNewUser"); 
		
		Users user = new Users();
		
		model.addObject("user", user);
		model.addObject("saveorupdate","new");
		return model;
	}
	
	@RequestMapping(value = "/secure/listUser", method = RequestMethod.GET)
	ModelAndView userList()
	{
		ModelAndView model = new ModelAndView("userList");	
		List<Users> users = userDao.getAllUser();
		model.addObject("userList", users);
		return model;
	}
	
	
	
	@RequestMapping(value = "/secure/userUpdate", method = RequestMethod.GET)
	ModelAndView newUser2(@RequestParam(value = "username") String username)
	{
		ModelAndView model = new ModelAndView("registerNewUser");
		List<Users> users = userDao.getAllUser();
		Users user = loginDao.findByUserName(username);
		
		//get User roles and set
		for(int i = 0; i< user.getUserRole().size();i++)
			user.getRoles().add(user.getUserRole().get(i).getRole());
		//
		model.addObject("userList", users);
		model.addObject("user", user);
		model.addObject("saveorupdate","update");
		return model;
	}
	
	
	@RequestMapping(value = "/secure/newUser", method = RequestMethod.POST)
	ModelAndView newUserPost(@ModelAttribute(value="user") Users user)
	{
		ModelAndView model = new ModelAndView("registerNewUser");
	
		
		
		for(int i = 0; i<user.getRoles().size();i++)
			user.getUserRole().add(new UserRole(user,user.getRoles().get(i)));
		
		//get User roles and set

		if(user.getId() <= 0)
		{
			userDao.register(user);
			model.addObject("msg", "başarılı bir şekilde kayıt edildi. ");
			model.addObject("saveorupdate","new");
		}
		else
		{
			userDao.update(user);
			model.addObject("msg", "başarılı bir şekilde kayıt edildi. ");
			model.addObject("saveorupdate","update");
		}
		return model;
	}
	
	@RequestMapping(value="/secure/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
//	@RequestMapping(value = "/secure/home8555", method = RequestMethod.GET)
//	ModelAndView home()
//	{
//		
//		ModelAndView model2 = new ModelAndView("home");
//		model2.addObject("weekList", taskDao.getWeek());	
//		
//		List<Users> users = userDao.getByUserRoles();
//		model2.addObject("users", users);
//		
//		
//		List<Kategori> kategoriler = kategoriDao.get();
//		model2.addObject("kategoriler", kategoriler);
//		
//		List<String> anakategori = kategoriDao.getAnaKategori();
//		model2.addObject("anakategori", anakategori);
//		
//		if(pikareSession.getUsername().isEmpty())
//		{
//			setSession();
//		}
//		
//
//		//LineBasicData lineBasicData = new LineBasicData(taskDao.getCountClose("","")); 
//		
//		
//		model2.addObject("task", new Task());
//		model2.addObject("week", "");
//		model2.addObject("taskSahibi", "");
//		model2.addObject("kategori", "");
//		return model2;
//	}

	
	@RequestMapping(value = "/secure/home2", method = RequestMethod.GET)
    public ModelAndView controllerMethod(@RequestParam("taskWeek") String week,
    		@RequestParam("taskSahibi") String user,
    		@RequestParam(value = "kategori" , defaultValue ="" ) String kategori,
    		@RequestParam("anakategori") String anakategori,
    		@RequestParam("status" ) String status,
    		@RequestParam(value= "firstdate" , defaultValue ="") String firstdate,
    		@RequestParam(value= "lastdate" , defaultValue ="") String lastdate
)
	{	
		int hafta = 0;
		int yil = 0;
		if(!week.isEmpty())
		{
			String[] temp = week.split("-");
			yil = Integer.parseInt(temp[0]);
			hafta = Integer.parseInt(temp[1]);
		}
		
		List<Task> task = taskDao.getByWeek(hafta,yil,user,kategori,anakategori,status,"","");
		ModelAndView model2 = new ModelAndView("home");
		model2.addObject("Task", task);
		model2.addObject("weekList", taskDao.getWeek());
		model2.addObject("week", week);
		model2.addObject("taskSahibi", user);
		model2.addObject("anakategori", anakategori);
		model2.addObject("status", status);
		
		List<Users> users = userDao.getByUserRoles();
		model2.addObject("users", users);
		model2.addObject("sayi" , task.size());
		
		List<String> anakategoriList = kategoriDao.getAnaKategori();
		
		
		
		model2.addObject("anakategoriList", anakategoriList);
		
		return model2;
	}
	
	@RequestMapping(value = "/secure/task", method = RequestMethod.GET)
	ModelAndView tasklar()
	{
		ModelAndView model = new ModelAndView("task");
		
		if(pikareSession.getUsername().isEmpty())
		{
			setSession();
		}
		
		List<Task> list = taskDao.listTasks();
		model.addObject("Task", list);
		return model;
	}
	
	@RequestMapping(value = "/secure/taskupdate", method = RequestMethod.GET)
	ModelAndView taskupdate(@RequestParam(value = "taskid") String taskNo)
	{
		if(pikareSession.getUsername().isEmpty())
		{
			setSession();
		}
		
		ModelAndView model = new ModelAndView("taskEkle");
		
		List<Users> users = userDao.getByUserRoles();
		model.addObject("users", users); 
		
		
		List<Kategori> kategori = kategoriDao.get();
		model.addObject("kategori", kategori);
		
		List<String> efor = kategoriDao.getEforHarf();
		model.addObject("eforList", efor);
		
		Task task = taskDao.getTaskById(taskNo);
		if((pikareSession.getRole().equals("ROLE_USER") || pikareSession.getRole().equals("ROLE_ADMIN")) && task.getCloseWeek() == null )
		{
			task.setCloseWeek(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		}
		if(pikareSession.getRole().equals("ROLE_PO") && task.getAssigmnetDate() == null)
		{
			task.setAssigmnetDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		}
			
		
		if(pikareSession.getRole().equals("ROLE_USER") && task.getCloseWeek() == null)
		{
			task.setCloseWeek(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		}
		
		model.addObject("task", task);
		model.addObject("saveorupdate", "update");
	
		return model;
	}
	
	@RequestMapping(value = "/secure/taskEkle", method = RequestMethod.POST)
	@Transactional
	ModelAndView taskEklePost(
			@ModelAttribute(value="task") Task task)
	{
		ModelAndView model = new ModelAndView();

		List<Users> users = userDao.getByUserRoles();
		model.addObject("users", users);
		
		List<Kategori> kategori = kategoriDao.get();
		model.addObject("kategori", kategori);
		
		List<String> efor = kategoriDao.getEforHarf();
		model.addObject("eforList", efor);
		
		if(pikareSession.getUsername().isEmpty())
		{
			setSession();
		}
		
		try {
			if(task.getId() > 0)
			{
				if(pikareSession.getRole().equals("ROLE_PO") || pikareSession.getName().equals("admin")  || !task.getTaskSahibi().isEmpty() && pikareSession.getName().toUpperCase().equals(task.getTaskSahibi()) || !pikareSession.getRole().equals("ROLE_USER"))
				{
					if(pikareSession.getRole().equals("ROLE_ADMIN"))						
					{	
						if(!task.getStatus().equals("CLOSED"))
							task.setCloseWeek(null);
					}
					if(pikareSession.getRole().equals("ROLE_PO"))
					{
						if(!task.getStatus().equals("OPEN") && !task.getStatus().equals("CLOSED") )
						{
							task.setAssigmnetDate(null);
							task.setCloseWeek(null);
						}
					}
						
					
					taskDao.updateTask(task);
					model.addObject("msg", "Guncelleme Başarili bir şekilde güncellendi.");
				}
					else
					model.addObject("msg", "Size yada kimseye Atanmamiş durumdaki taski güncelleyemezsiniz.");
				

				model.addObject("saveorupdate", "update");
				model.setViewName("taskEkle");
			}	
			else
			{
				if(pikareSession.getRole().equals("ROLE_PO"))
				{
					task.setCloseWeek(null);
					task.setAssigmnetDate(null);
					taskDao.addTask(task);
					model.addObject("task",new Task());
					model.setViewName("taskEkle");
					model.addObject("msg", "Başarili bir şekilde kayıt edildi.");
				}
				else
				{
					model.addObject("msg", "Task Ekleme işlemi yapmak için yetkili degilsiniz");
				}
			}
			
		} catch (Exception e) {
			System.out.println("error + " +e);
			model.addObject("msg", "işlem gerçekleşirken hata meydana geldi.");
		}
		return model;
	}
	
		
		@RequestMapping(value = "secure/myTask", method = RequestMethod.GET)
		public ModelAndView myTask() {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("my name " + auth.getName());
			System.out.println("yourname name " + pikareSession.getUsername());
			
			if(pikareSession.getUsername().isEmpty())
			{
				setSession();
			}
			
		    List<Task> task = taskDao.getTaskByUser(pikareSession.getName().toUpperCase());
		    
		    List<Task> openTask = new ArrayList<Task>();
		    List<Task> closedTask = new ArrayList<Task>();
		    for(int i = 0 ; i< task.size();i++)
		    {
		    	Task _task = task.get(i);
		    	if(_task.getStatus().equals("OPEN"))
		    		openTask.add(_task);
		    	else 
		    		closedTask.add(_task);
		    		
		    }
			
			ModelAndView model = new ModelAndView("mytask");
			model.addObject("openTask",openTask);
			model.addObject("closedTask",closedTask);

			return model;
		}
		
		@RequestMapping(value = "secure/changePassword", method = RequestMethod.GET)
		public ModelAndView changePassword() {
			
			return new ModelAndView("changePass");	
		}
		
		@RequestMapping(value = "secure/changePass", method = RequestMethod.POST)
		public ModelAndView changePass(@RequestParam(value = "changePass") String pass,final RedirectAttributes redirectAttributes) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();			
			userDao.changePassword(auth.getName(), pass);
			redirectAttributes.addFlashAttribute("msg", "şifreniz degiştirilmiştir");
			ModelAndView model = new ModelAndView("redirect:changePassword");
			model.addObject("msg", "şifreniz degiştirilmiştir");
			return model;
		}
		
		@RequestMapping(value = "secure/display", method = RequestMethod.GET)
		public ModelAndView display() {

			ModelAndView model = new ModelAndView("display");
			List<Kategori> attributeValue =  kategoriDao.get();
			model.addObject("kategoriler", attributeValue);
			return model;
		}
		
		@RequestMapping(value = "secure/home", method = RequestMethod.GET)
		public ModelAndView deneme() {
			
			if(pikareSession.getUsername().isEmpty())
			{
				setSession();
			}
			
			ModelAndView model2 = new ModelAndView("home");
			model2.addObject("weekList", taskDao.getWeek());	
			
			List<Users> users = userDao.getByUserRoles();
			model2.addObject("users", users);
			
			
			List<String> anakategori = kategoriDao.getAnaKategori();
			model2.addObject("anakategoriList", anakategori);
			
			return model2;
		}
		
		void setSession()
		{
			if(pikareSession.getUsername().isEmpty())
			{
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			    String name = auth.getName(); //get logged in username
			    Users user = loginDao.findByUserName(name);
			    
			    pikareSession.setName(user.getName());
				pikareSession.setSurname(user.getSurname());
				pikareSession.setUsername(user.getUsername());
				pikareSession.setRole(user.getUserRole().get(0).getRole());
			}
		}	
}

