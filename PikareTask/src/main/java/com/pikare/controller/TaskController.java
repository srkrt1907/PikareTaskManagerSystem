package com.pikare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pikare.dao.KategoriDao;
import com.pikare.dao.TaskDao;
import com.pikare.dao.TaskInfoDao;
import com.pikare.dao.UserDao;
import com.pikare.dao.YonetWifiDaoImp;
import com.pikare.model.M2MModel;
import com.pikare.model.Task;
import com.pikare.model.TaskInfo;
import com.pikare.model.Users;
import com.pikare.model.YonetWifi;
import com.pikare.session.GenericResponse;
import com.pikare.session.M2MBean;

@Controller
@RequestMapping("/data")
public class TaskController {
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	YonetWifiDaoImp wifi;
	
	@Autowired
	KategoriDao kategoriDao;
	
	@Autowired
	TaskInfoDao taskInfoDao;
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/filtrele", method = RequestMethod.GET)
	@ResponseBody List<GenericResponse> taskGetir(@RequestParam(value= "kisi") String kisi,
			@RequestParam(value= "hafta") String week,
			@RequestParam(value= "kategori") String kategori,
			@RequestParam(value= "firstdate") String firsdate,
			@RequestParam(value= "lastdate") String lastdate
			)
	{
		//List list = taskDao.Filtrele(kisi);
		
		int hafta = 0;
		int yil = 0;
		if(!week.isEmpty())
		{
			String[] temp = week.split("-");
			yil = Integer.parseInt(temp[0]);
			hafta = Integer.parseInt(temp[1]);
		}
		
		
		List allTask =  taskDao.getAllOpenTask();
		
		ArrayList<String> closedTask = taskDao.getClosedTaskJDBC(kisi, hafta, yil, firsdate, lastdate, kategori);
		
		//List closeTask = taskDao.getClosedTask(kisi, hafta,yil, firsdate, lastdate, kategori);
		List assignWeek = taskDao.getOpenTask(kisi, hafta,yil,  firsdate, lastdate, kategori);	
		List pendingTask = taskDao.getPendingTask(kisi, hafta,yil,  firsdate, lastdate, kategori);	
		List<Users> users = userDao.getByUserRoles();
		
		List<String> efor = kategoriDao.getEforHarf();

		List<GenericResponse> responseList = new ArrayList<GenericResponse>();
		
		
		Iterator itr = users.listIterator();
		while(itr.hasNext())
		{
			Object[] object = (Object[])itr.next();	
			Users user = (Users)object[0];
					
			boolean bulundu = false;
			GenericResponse response = new GenericResponse();
			response.setName(user.getName());
			for(int i = 0 ; i< allTask.size() ; i++)
			{
				Object[] obj = (Object[])allTask.get(i);
				String name = (String)obj[0];
				
				if(name.equals(user.getName()))
				{
					bulundu = true;
					Long sayi = (Long)obj[1];
					response.setAll(sayi.toString());
				}			
			}
			if(!bulundu)
				response.setAll(new String("0"));
			else
				bulundu = false;
			
//			for(int i = 0 ; i< closeTask.size() ; i++)
//			{
//				Object[] obj = (Object[])closeTask.get(i);
//				String name = (String)obj[0];
//				
//				if(name.equals(user.getName()))
//				{
//					bulundu = true;
//					Long sayi = (Long)obj[1];
//					response.setClose(sayi.toString());
//				}			
//			}
//			if(!bulundu)
//				response.setClose(new String("0"));
//			else
//				bulundu = false;
//			
//			
			for(int i = 0 ; i< closedTask.size() ; i++)
			{
				
				String name = closedTask.get(i);
								
				if(name.equals(user.getName()))
				{
					bulundu = true;
					String sayi = closedTask.get(i + 1);
					String getEfor = closedTask.get(i+ 2);
					response.setClose(sayi.toString());
					response.setEfor(getEfor);
				}
				i++;
				i++;
			}
			if(!bulundu)
			{
				response.setClose(new String("0"));
				response.setEfor("0");
			}
			else
				bulundu = false;
			
			for(int i = 0 ; i< assignWeek.size() ; i++)
			{
				Object[] obj = (Object[])assignWeek.get(i);
				String name = (String)obj[0];
				
				if(name.equals(user.getName()))
				{
					bulundu = true;
					Long sayi = (Long)obj[1];
					response.setOpen(sayi.toString());
				}			
			}
			if(!bulundu)
				response.setOpen(new String("0"));
			else
				bulundu = false;
			
			for(int i = 0 ; i< pendingTask.size() ; i++)
			{
				Object[] obj = (Object[])pendingTask.get(i);
				String name = (String)obj[0];
				
				if(name.equals(user.getName()))
				{
					bulundu = true;
					Long sayi = (Long)obj[1];
					response.setPending(sayi.toString());
				}			
			}
			if(!bulundu)
				response.setPending(new String("0"));
			else
				bulundu = false;
			
			responseList.add(response);
			
		}	
		return responseList;
	}
	
	@RequestMapping(value = "/taskliste", method = RequestMethod.GET)
    public @ResponseBody List<Task> controllerMethod(@RequestParam("hafta") String week,
    		@RequestParam("kisi") String user,
    		@RequestParam(value = "kategori" , defaultValue="") String anakategori,
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
		
		List<Task> task = taskDao.getByWeek(hafta,yil,user,"",anakategori,status,firstdate,lastdate);

		return task;
	}
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public 
	ModelAndView uploadFileHandler(
			@RequestParam("file") MultipartFile file , final RedirectAttributes redirectAttributes) {

		M2MBean m2mBean = new M2MBean();
		boolean response = m2mBean.upload(file, "");
		if(response)
			redirectAttributes.addFlashAttribute("msg", "Dosya Başarili şekilde yüklendi");
		else
			redirectAttributes.addFlashAttribute("msg", "Dosya yüklenirken hata olustu");
		
		ModelAndView model = new ModelAndView("redirect:../secure/m2m");
		return model;
	}
	
	@RequestMapping(value = "/uploadFileAndFind",headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody
	List<M2MModel> uploadFile(@RequestParam("file") MultipartFile file) {
		
		List<M2MModel> response = new ArrayList<M2MModel>();
		M2MBean m2mBean = new M2MBean();
		List<M2MModel> m2m = m2mBean.Oku(file);
		if(m2m != null)
		{
			response = m2mBean.find(m2m);
		}
		return response;
	}
	
	@RequestMapping(value = "/wifiDelete")
	public @ResponseBody
	String yonetWifiDelet(@RequestParam("id") int id) {
		
		YonetWifi yonett = wifi.getByID(id);
		boolean dgr = wifi.delete(yonett);
		return "" +dgr;
	}
	
	@RequestMapping(value = "/wifiAll" ,  produces = "application/json")
	public @ResponseBody
	List<YonetWifi> wifiAll() {	
		List<YonetWifi> yonett = wifi.getAll();
		return yonett;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/datatables")
	public @ResponseBody
	String Datatables(HttpServletRequest request , HttpServletResponse response ) {
		
		String name = request.getQueryString();
		name = name.replace("%22", " ");
		JSONObject jsonObject = new JSONObject(name);
        String action = jsonObject.get("action").toString();
        String data = jsonObject.get("data").toString();
        
        JSONObject json12 = new JSONObject(data);
        
        YonetWifi _wifi = new YonetWifi();
        
        Iterator keys = json12.keys();
        while(keys.hasNext()) {
            // loop to get the dynamic key
            String currentDynamicKey = (String)keys.next();

            // get the value of the dynamic key
            JSONObject currentDynamicValue = json12.getJSONObject(currentDynamicKey);
            
            String id = currentDynamicValue.get("id").toString();
            String portalname = currentDynamicValue.get("name").toString();
            String sunucu1 = currentDynamicValue.get("sunucu1").toString();
            String sunucu2 = currentDynamicValue.get("sunucu2").toString();
            String webServis = currentDynamicValue.get("webServis").toString();
            
            int _id = Integer.parseInt(id);
            _wifi.setId(_id);
            _wifi.setName(portalname);
            _wifi.setSunucu1(sunucu1);
            _wifi.setSunucu2(sunucu2);
            _wifi.setWebServis(webServis);
            break;
            // do something here with the value...
        }
        
        if(action.equals("create"))
        {
        	wifi.save(_wifi);
        }
        else if(action.equals("edit"))
        {
        	wifi.update(_wifi);
        }
        else if(action.equals("remove"))
        {
        	wifi.delete(_wifi);
        }
        
		return name;
	}
	@RequestMapping(value = "updateTaskInfo", method = RequestMethod.GET)
	@ResponseBody	String updateTaskInfo(@RequestParam(value = "id") String id ,
			@RequestParam(value = "taskInfo") String infoName,
			@RequestParam(value = "taskId") String infoId , 
			@RequestParam(value = "taskNo") String taskNo  
			) {
			
		TaskInfo taskInfo = new TaskInfo();
		int _id = Integer.parseInt(id);
		taskInfo.setId(_id);
		taskInfo.setInfoId(infoId);
		taskInfo.setInfoName(infoName);
		taskInfo.setTaskNo(taskNo);
		
		if(taskInfoDao.update(taskInfo))
			return "1";
		else
			return "0";
		
	}
	@RequestMapping(value = "deleteTaskInfo", method = RequestMethod.GET)
	@ResponseBody	String deleteTaskInfo(@RequestParam(value = "id") String id ,
			@RequestParam(value = "taskInfo") String infoName,
			@RequestParam(value = "taskId") String infoId , 
			@RequestParam(value = "taskNo") String taskNo  
			) {
			
		TaskInfo taskInfo = new TaskInfo();
		int _id = Integer.parseInt(id);
		taskInfo.setId(_id);
		taskInfo.setInfoId(infoId);
		taskInfo.setInfoName(infoName);
		taskInfo.setTaskNo(taskNo);
		
		if(taskInfoDao.delete(taskInfo))
			return "1";
		else
			return "0";
		
	}
	
	@RequestMapping(value = "updateYonetWifi", method = RequestMethod.GET)
	@ResponseBody	String updateYonetWifi(@RequestParam(value = "id") String id ,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "sunucu1") String sunucu1 , 
			@RequestParam(value = "sunucu2") String sunucu2,
			@RequestParam(value = "webServis") String webServis,
			@RequestParam(value = "user") String user,
			@RequestParam(value = "type") String type
			) {
			
		YonetWifi yonetWifi = new YonetWifi();
		int _id = Integer.parseInt(id);
		yonetWifi.setId(_id);
		yonetWifi.setName(name);
		yonetWifi.setSunucu1(sunucu1);
		yonetWifi.setSunucu2(sunucu2);
		yonetWifi.setWebServis(webServis);

		
		if(wifi.update(yonetWifi))
			return "1";
		else
			return "0";
		
	}
	
	@RequestMapping(value = "deleteYonetWifi", method = RequestMethod.GET)
	@ResponseBody	String deleteYonetWifi(@RequestParam(value = "id") String id ,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "sunucu1") String sunucu1 , 
			@RequestParam(value = "sunucu2") String sunucu2,
			@RequestParam(value = "webServis") String webServis,
			@RequestParam(value = "user") String user,
			@RequestParam(value = "type") String type
			
			) {
			
		YonetWifi yonetWifi = new YonetWifi();
		int _id = Integer.parseInt(id);
		yonetWifi.setId(_id);
		yonetWifi.setName(name);
		yonetWifi.setSunucu1(sunucu1);
		yonetWifi.setSunucu2(sunucu2);
		yonetWifi.setWebServis(webServis);
		
		if(wifi.delete(yonetWifi))
			return "1";
		else
			return "0";
		
	}
	
	
	

}
