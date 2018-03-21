package tdt.edu.spring.model;

import java.util.ArrayList;
import java.util.List;

public class manageSubject {

	private Subject tmpSubject;
	private List<String> listIDStudent;
	
	public manageSubject(){
		this.tmpSubject = new Subject();
		this.listIDStudent = new ArrayList<>();
	}
	
	public manageSubject(Subject another){
		this.tmpSubject = new Subject(another);
		this.listIDStudent = new ArrayList<>();
	}
	
	public Subject getTmpSubject() {
		return tmpSubject;
	}

	public void setTmpSubject(Subject tmpSubject) {
		this.tmpSubject = tmpSubject;
	}
	
	public void addID(String idStudent){
		if(!this.listIDStudent.contains(idStudent))
			this.listIDStudent.add(idStudent);
	}
	
	public List<String> getListIDStudent() {
		return listIDStudent;
	}
	
	public String toString(){
		return "Subject : " + this.tmpSubject.getNameSubject() ;
	}

}
