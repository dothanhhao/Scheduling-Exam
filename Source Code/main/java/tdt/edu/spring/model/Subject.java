package tdt.edu.spring.model;

public class Subject {

	private String idSubject;
	private String nameSubject;
	private int group;
	private int subGroup;
	
	public Subject(){
		this.idSubject = "";
		this.nameSubject = "";
		this.group = -1;
		this.subGroup = -1;
	}
	
	public Subject(String idSubject, String nameSubject, int group, int subGroup){
		this.idSubject = idSubject;
		this.nameSubject = nameSubject;
		this.group = group;
		this.subGroup = subGroup;
	}
	
	public Subject(Subject another){
		this.idSubject = another.idSubject;
		this.nameSubject = another.nameSubject;
		this.group = another.group;
		this.subGroup = another.subGroup;
	}
	
	public String getIdSubject(){
		return this.idSubject;
	}
	
	public String getNameSubject(){
		return this.nameSubject;
	}
	
	public int getGroup(){
		return this.group;
	}
	
	public int getSubGroup(){
		return this.subGroup;
	}
	
	public void setIdSubject(String id){
		this.idSubject = id;
	}
	
	public void setName(String name){
		this.nameSubject = name;
	}
	
	public void setGroup(int group){
		this.group = group;
	}
	
	public void setSubGroup(int subgroup){
		this.subGroup = subgroup;
	}
	
	public boolean subGroupisEmpty(){
		return (this.getSubGroup() == -1);
	}

	@Override
	public String toString() {
		return "Mã môn: " + this.getIdSubject() +",Tên môn: " + this.getNameSubject() + ",Nhóm: " + this.getGroup()
		+ ",Tổ: " + this.getSubGroup();
	}
}
