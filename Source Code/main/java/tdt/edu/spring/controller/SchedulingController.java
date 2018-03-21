package tdt.edu.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import tdt.edu.spring.dao.MyUploadForm;
import tdt.edu.spring.system.ResultBuildSchedule;



 
@Controller
@RequestMapping("/Xeplich")

public class SchedulingController {
	
	
 
	private static String msg="";
	
	
   // Phương thức này được gọi mỗi lần có Submit.
   @InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       Object target = dataBinder.getTarget();
       if (target == null) {
           return;
       }
       System.out.println("Target=" + target);
 
       if (target.getClass() == MyUploadForm.class) {
  
           // Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
           dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
       }
   }
   
  
   // GET: Hiển thị trang form upload
   @RequestMapping(value = "", method = RequestMethod.GET)
   public String uploadMultiFileHandler(Model model) {
 
       MyUploadForm myUploadForm = new MyUploadForm();
       model.addAttribute("myUploadForm", myUploadForm);
 
       return "scheduling";
   }
  
   // POST: Sử lý Upload
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ModelAndView uploadMultiFileHandlerPOST(HttpServletRequest request,HttpSession session, //
           Model model, //
           @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
 
       return this.doUpload(request,session, model, myUploadForm);
 
   }
 
   private ModelAndView doUpload(HttpServletRequest request,HttpSession session ,Model model, //
           MyUploadForm myUploadForm) {
 
        
       // Thư mục gốc upload file.
       
       File uploadRootDir = new File("E:\\UpLoad");
       // Tạo thư mục gốc upload nếu nó không tồn tại.
       if (!uploadRootDir.exists()) {
           uploadRootDir.mkdirs();
       }
       CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
       //
       List<File> uploadedFiles = new ArrayList<File>();
       for (CommonsMultipartFile fileData : fileDatas) {
           // Tên file gốc tại Client.
           String name = fileData.getOriginalFilename();
           System.out.println("Client File Name = " + name);
 
           if (name != null && name.length() > 0) {
               try {
                   // Tạo file tại Server.
                   File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                   
                   
                   if(serverFile.getName().substring(serverFile.getName().lastIndexOf(".")+1).equals("xlsx")){
                	   
                       // Luồng ghi dữ liệu vào file trên Server.
                       BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                       stream.write(fileData.getBytes());
                       stream.close();
                       //
                       uploadedFiles.add(serverFile);
                       System.out.println("Write file: " + serverFile);
                   }else{
                	   msg="File is not type file.xlsx";
                   }
               } catch (Exception e) {
                   System.out.println("Error Write file: " + name);
               }
           }
       }
     
       
       
       
       if(!uploadedFiles.isEmpty() && uploadedFiles.size()>=2){
    	   try {
    		   ResultBuildSchedule rs = new ResultBuildSchedule(uploadedFiles.get(0).getName(),uploadedFiles.get(1).getName());
    		   
    		   session.setAttribute("Table_MidTerm", rs.get_MidTerm());
    		   session.setAttribute("Table_Final", rs.get_Final());
    		   session.setAttribute("Table_ListStudent", rs.getList_Student());
    		   session.setAttribute("Table_ListSubject", rs.getList_ManageSubject());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView("scheduling") ;
			} catch (NullPointerException e) {
				System.out.print("NullPointerException caught");
				return new ModelAndView("scheduling") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return new ModelAndView("scheduling") ;
			}
       }else{
    	   	   msg="Vui lòng chọn đủ 2 file dạng .xlsx";
        	   return new ModelAndView("scheduling","msg",msg) ;
           
       }
       
		
       return new ModelAndView("redirect:/Trangchu");
        
   }
   
  
   
   
}