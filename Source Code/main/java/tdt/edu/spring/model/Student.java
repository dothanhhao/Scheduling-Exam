package tdt.edu.spring.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;
	private String MSSV;
	private String lop;
	private String mail;
	private List<Subject> listSubject;
	
	public Student(){
		this.name = "";
		this.MSSV = "";
		this.lop = "";
		this.mail = "";
		this.listSubject = new ArrayList<>();
	}
	
	public Student(String name , String MSSV, String lop, String mail){
		this.name = name;
		this.MSSV = MSSV;
		this.lop = lop;
		this.mail = mail;
		listSubject = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", MSSV=" + MSSV + ", lop=" + lop + ", mail=" + mail + ", listSubject="
				+ listSubject + "]";
	}

	public void addSubject(Subject tmp){
		this.listSubject.add(tmp);
	}

	public List<Subject> getListSubject() {
		return listSubject;
	}

}
