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

import tdt.edu.spring.dao.DataResultScheduling;
import tdt.edu.spring.model.InfoStuForGUI;
import tdt.edu.spring.model.InfoSubForGUI;
import tdt.edu.spring.model.Schedule;
import tdt.edu.spring.model.Student;
import tdt.edu.spring.model.manageSubject;





 
@Controller

@RequestMapping("/Trangchu")
public class HomeController {
	private static String msg="";
	
   
   @RequestMapping("")
   public String page_Trangchu(Model model) {
       System.out.println("Trang chu");
       return "home";
       
   }
   @RequestMapping(value="/Giaovien", method = RequestMethod.GET)
   public ModelAndView page_Teacher(Model model) {
       System.out.println("Trang giao vien");
    
       
     
       return new ModelAndView("teacher");
       
   }
   
   @RequestMapping(value="/Giaovien/xemlich", method = RequestMethod.GET)
   public ModelAndView page_Teacher(Model model,HttpServletRequest request,HttpSession session) {
       System.out.println("Trang giao vien");
       session =  request.getSession();
       System.out.println("GoTeacher");
       
	   	if(session.getAttribute("Table_MidTerm") == null){
	 	   System.out.println("session null");
	 	   return new ModelAndView("redirect:/Xeplich") ;
	   	}
	   	
	   	InfoSubForGUI rsShedulingSubject = null;
	   	try {
	   		Schedule Table_MidTerm =(Schedule) session.getAttribute("Table_MidTerm");
	   		Schedule Table_Final =(Schedule) session.getAttribute("Table_Final");
	   		
	   		
			DataResultScheduling data = new DataResultScheduling(Table_MidTerm, Table_Final);
			
			
			data.getInfoSubject(0);
			data.getInfoSubject(1);
			rsShedulingSubject = data.getRsShedulingSubject();
			
		
	   	} catch (Exception e) {
			// TODO Auto-generated catch block
	   		msg="Sự cố";
	   		
			return new ModelAndView("resultteacher","msg",msg) ;
			
		}
	   	
	   
	   	return new ModelAndView("resultteacher","data_Subject", rsShedulingSubject);
       //return new ModelAndView("teacher");
       
   }
   
   @RequestMapping(value="/Giaovien/exportfile", method = RequestMethod.GET)
   public ModelAndView export_File(Model model,HttpServletRequest request,HttpSession session) {
       
       System.out.println("Xuat file");
	   	session =  request.getSession();
	   	
	   	if(session.getAttribute("Table_MidTerm") == null){
	    	   System.out.println("session null");
	    		System.out.println("3");
	    	   return new ModelAndView("redirect:/Xeplich") ;
	    	   
	    }
		try {
	   		Schedule Table_MidTerm =(Schedule) session.getAttribute("Table_MidTerm");
	   		Schedule Table_Final =(Schedule) session.getAttribute("Table_Final");
	   		ArrayList<Student> Table_ListStudent = (ArrayList<Student>) session.getAttribute("Table_ListStudent");
	   		ArrayList<manageSubject> Table_ListSubject = (ArrayList<manageSubject>) session.getAttribute("Table_ListSubject");
			DataResultScheduling data = new DataResultScheduling(Table_MidTerm, Table_Final, Table_ListStudent, Table_ListSubject);
			msg="Thành công";
			data.writeFile();
	   	} catch (Exception e) {
			// TODO Auto-generated catch block
	   		msg="Sự cố";
		}
    
      return new ModelAndView("redirect:/Trangchu/Giaovien/xemlich","msg",msg);
       
     
      
       
   }
   
   @RequestMapping(value="/Hocvien", method = RequestMethod.GET)
   public ModelAndView page_Student(Model model) {
       System.out.println("Trang hoc vien");
       Student sv =new Student();
       
     
       return new ModelAndView("student","form_sinhvien",sv);
       
   }
   
   @RequestMapping(value = "/Hocvien", method = RequestMethod.POST)
   public ModelAndView updata_page_Student(HttpServletRequest request,HttpSession session, //
		   @ModelAttribute Student sinhvien,Model model) {
	   	System.out.println("Trang ket qua lich thi");
	   	System.out.println(sinhvien);
	   	session =  request.getSession();
	   	
	   	if(session.getAttribute("Table_MidTerm") == null){
	    	   System.out.println("session null");
	    		System.out.println("3");
	    	   return new ModelAndView("redirect:/Scheduling") ;
	    	   
	    }
	   	InfoStuForGUI rsShedulingStudent = null;
	   	try {
	   		Schedule Table_MidTerm =(Schedule) session.getAttribute("Table_MidTerm");
	   		Schedule Table_Final =(Schedule) session.getAttribute("Table_Final");
	   		ArrayList<Student> Table_ListStudent = (ArrayList<Student>) session.getAttribute("Table_ListStudent");
	   		ArrayList<manageSubject> Table_ListSubject = (ArrayList<manageSubject>) session.getAttribute("Table_ListSubject");
			DataResultScheduling data = new DataResultScheduling(Table_MidTerm, Table_Final, Table_ListStudent, Table_ListSubject);
			
			if(data.exitsStudent(sinhvien.getMSSV())){
			
				
				rsShedulingStudent = data.getInfoStudent(sinhvien.getMSSV());
				
				
			}else{
				msg="Không tồn tại sinh viên hoặc bạn nhập sai, vui lòng nhập lại";
				
				return new ModelAndView("student","msg",msg) ;
			}
	   	} catch (Exception e) {
			// TODO Auto-generated catch block
	   		msg="Sự cố";
	   		
			return new ModelAndView("student","msg",msg) ;
			
		}
	   	
	   
	   	return new ModelAndView("resultstudent","data_Student", rsShedulingStudent);
	       
       
   }
   
}