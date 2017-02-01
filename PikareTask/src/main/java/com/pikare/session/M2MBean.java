package com.pikare.session;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.pikare.controller.HomeController;
import com.pikare.model.M2MModel;


public class M2MBean {
	
	private static final Logger logger = Logger.getLogger(M2MBean.class);
	
	public List<M2MModel> Oku(MultipartFile _file)
	{
		 List<String> bslgc = new ArrayList<String>();
         List<String> bitis = new ArrayList<String>();
         List<M2MModel> m2m = new ArrayList<M2MModel>();
		 try
	        {
			   System.out.println("");
			   
	            //FileInputStream file = new FileInputStream((File)_file);
			   byte  [] byteArr=_file.getBytes();
			   InputStream inputStream = new ByteArrayInputStream(byteArr);
 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            
	           
	            
	            int baslangic = 0;
	            int sayi = 0;
	            String bsl = "";
	            String bts = "";
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                double dgr = 0;
	                double ID = 0;
	                Cell cellA = row.getCell(0);
	                Cell cellB = row.getCell(1);
	                Cell cellC = row.getCell(2); 
	                
	                
	                if(cellC == null)
	                	continue;
	                    
	                dgr = cellC.getNumericCellValue();
	                System.out.println("deger = " + row.getRowNum());
	                String dgrStr = cellB.getStringCellValue();
	                
	                 
	               
	                //ID = Double.parseDouble(dgrStr);
	                
	                if(baslangic == 0 && (int)dgr == 0)
                    {
	                	bsl = dgrStr;
                    	baslangic = 1;
                    }
                    else if(baslangic == 1 && (int)dgr == 1)
                    {
                    	bts = dgrStr;
                    	baslangic = 0;
                    }
                    else if(baslangic == 0 && (int)dgr == 1)
                    {
                    	bsl = dgrStr;
                    	bts = dgrStr;
                    	baslangic = 0;
                    }
	                if(!bts.isEmpty() && !bsl.isEmpty())
	                	{
	                	 M2MModel model = new M2MModel();
	                	 model.setBas(bsl);
	                	 model.setBit(bts);
	                	 bsl = "";
	                	 bts = "";
	                	 m2m.add(model);
	                	}
	            }
	           // file.close();
	            //System.out.println("toplam baslangic = " + bslgc.size() );
                //System.out.println("toplam baslangic = " + bitis.size() );        
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	            return null;     
	        }
		 return m2m;
	}
	
	public List<M2MModel> find(List<M2MModel> model)
	{
		
		String rootPath = System.getProperty("catalina.home");
		File directory = new File(rootPath + File.separator + "m2mFiles"); 
    	File[] fList = directory.listFiles();
    	
    	for(int j = 0 ; j < model.size() ; j++)
    	{
    		int dgr = 0;
	    	for(int i=0 ;i < fList.length ; i++)
	    	{
	    		logger.warn("aranan file " + fList[i].getAbsolutePath());
	    		ArrayList<String> veri=new ArrayList<String>();
	            try {
	            	//String fileAdres = "C:/Dosyalar/" + fList[i].getName().toString(); 
	                FileInputStream fstr=new FileInputStream(fList[i].getAbsolutePath());
	                DataInputStream dstr=new DataInputStream(fstr);
	                BufferedReader brd=new BufferedReader(new InputStreamReader(dstr));
	                String s;
	                while((s=brd.readLine())!=null)
	                    {veri.add(s);}
	                    brd.close();
	                     
	                for (int k = 0; k < veri.size(); k++) {
	                    String aray[]=veri.get(k).split(" ");
	                     
	                
	                for (int z = 0; z <aray.length; z++) {
	                    if(aray[z].equals(model.get(j).getBas().toString()))
	                    {   
	                    	model.get(j).setSonuc("bulundu..");
	                    	System.out.println("bulundu");
	                    	logger.warn("bulundu");
	                    	dgr = 1;
	                    	break;
	                    }
	                }
	                
	                	
	                }
	            } catch (IOException e) {
	            	logger.error("bulunan hata " , e);
	                e.printStackTrace();
	            }
		    }
	    	if(dgr == 0)
	    		{
	    		model.get(j).setSonuc("Bulunamadi");
	    		logger.warn("bulunamadi");
            	System.out.println("bulunamadi");
	    	}
    	}	
		return model;		
	}
	
	public boolean upload(MultipartFile file , String name)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = "D://deneme";
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "m2mFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				System.out.println(file.getOriginalFilename());

				System.out.println("Server File Location="
						+ serverFile.getAbsolutePath());

				return true;
			} catch (Exception e) {
				System.out.println("You failed to upload " + name + " => " + e.getMessage());
				return false;			
			}
		} else {
			return false;
		}
	}

}
