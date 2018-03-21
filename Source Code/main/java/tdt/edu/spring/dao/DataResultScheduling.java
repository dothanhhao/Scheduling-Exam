package tdt.edu.spring.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tdt.edu.spring.model.InfoStuForGUI;
import tdt.edu.spring.model.InfoSubForGUI;
import tdt.edu.spring.model.Schedule;
import tdt.edu.spring.model.Student;
import tdt.edu.spring.model.manageSubject;
import tdt.edu.spring.system.ResultBuildSchedule;

public class DataResultScheduling {
	
	private static final int	TYPE_MIDTERM = 0;
	private static final int	TYPE_FINAL = 1;
	private static final String OUTPUTFILE_SUBJECT_MIDTERM = "SubjectFile_MidTerm.xlsx";
	private static final String OUTPUTFILE_SUBJECT_FINAL = "SubjectFile_Final.xlsx";
	private static final String MONTHYEARMIDTERRM = "10/2017";
	private static final int DAYEXAMMIDTEAM = 4;
	private static final String MONTHYEARFINAL = "12/2017";
	private static final int DAYEXAMFINAL= 7;
	
	private Schedule _MidTerm = null;;
	private Schedule _Final = null;
	private static ArrayList<Student> list_Student = null;
	private static ArrayList<manageSubject> list_Subject = null;
	private InfoStuForGUI rsShedulingStudent = new InfoStuForGUI();
	private InfoSubForGUI rsShedulingSubject = new InfoSubForGUI();
	
	public DataResultScheduling(Schedule _MidTerm, Schedule _Final) throws Exception{
		this._MidTerm =_MidTerm;
		this._Final = _Final;
	}
	
	public DataResultScheduling(Schedule _MidTerm, Schedule _Final, ArrayList<Student> list_Student , ArrayList<manageSubject> list_Subject) throws Exception{
		this._MidTerm =_MidTerm;
		this._Final = _Final;
		this.list_Student =list_Student;
		this.list_Subject =list_Subject;
	}
	
	public void getInfoSubject(int key){
		
		ArrayList<ArrayList<String>> loadInfoSub  = new ArrayList<ArrayList<String>>();
		Map<String, ArrayList<String>> mapSubject = new HashMap<String,ArrayList<String>>();
		
		
		if(key ==0){
			loadInfoSub = _MidTerm.getLoadInfo();
		}else{
			loadInfoSub = _Final.getLoadInfo();
		}

		for(ArrayList<String> s : loadInfoSub){
			if(!mapSubject.containsKey(s)){
				mapSubject.put(s.get(0), s);
			}
		}
		rsShedulingSubject.setValue(mapSubject, key);
	}
	
	public InfoStuForGUI getInfoStudent(String mssv){
		Student student = getStudent(mssv);
		rsShedulingStudent.setInfoStu(getStudent(mssv));
		
		ArrayList<ArrayList<String>> infoStudent  = new ArrayList<ArrayList<String>>();
		
		infoStudent = _MidTerm.findInfoStudent(list_Student, TYPE_MIDTERM, mssv,DAYEXAMMIDTEAM,MONTHYEARMIDTERRM);
		
		rsShedulingStudent.setPerDay(infoStudent);
		
		infoStudent = _Final.findInfoStudent(list_Student, TYPE_FINAL, mssv,DAYEXAMFINAL,MONTHYEARFINAL);
		rsShedulingStudent.setPerDayFinal(infoStudent);
		
		return rsShedulingStudent;
	}
	
	public Student getStudent(String mssv){
		
		for(Student st : list_Student){
			if(st.getMSSV().equals(mssv))
				return st;
		}
		return null;
	}
	public boolean exitsStudent(String mssv){
		if(getStudent(mssv) == null)
			return false;
		return true;
		
	}
	
	public void writeFile() throws Exception{
		//Write info Student to txt file
		BufferedWriter wrSVMT = new BufferedWriter(new FileWriter(new File("E:/Resources/LichThiSV_MidTerm.txt")));
		ArrayList<String> infoStuMidTerm = _MidTerm.GUIStudent(list_Student, TYPE_MIDTERM,DAYEXAMMIDTEAM,MONTHYEARMIDTERRM);
		for(int i = 0; i < infoStuMidTerm.size(); i++){
			wrSVMT.write(infoStuMidTerm.get(i) + "\n");
		}
		
		BufferedWriter wrSVFN = new BufferedWriter(new FileWriter(new File("E:/Resources/LichThiSV_Final.txt")));
		ArrayList<String>  infoStuFinal = _Final.GUIStudent(list_Student, TYPE_FINAL,DAYEXAMFINAL,MONTHYEARFINAL);
		for(int i = 0; i < infoStuMidTerm.size(); i++){
			wrSVFN.write(infoStuFinal.get(i) + "\n");
		}
		//****************************************************************************************
		
		//Write info all Subject to xlsx File
		_MidTerm.writeSubject(list_Subject, TYPE_MIDTERM, OUTPUTFILE_SUBJECT_MIDTERM,DAYEXAMMIDTEAM,MONTHYEARMIDTERRM);
		_Final.writeSubject(list_Subject, TYPE_FINAL, OUTPUTFILE_SUBJECT_FINAL,DAYEXAMFINAL,MONTHYEARFINAL);
		//******************************************************************
	}
	@Override
	public String toString() {
		return "DataResultScheduling [rsShedulingStudent=" + rsShedulingStudent + "]";
	}

	public InfoSubForGUI getRsShedulingSubject() {
		return rsShedulingSubject;
	}
	
}
