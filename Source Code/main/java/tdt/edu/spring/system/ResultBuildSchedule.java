package tdt.edu.spring.system;

import java.util.*;

import tdt.edu.spring.model.Graph;
import tdt.edu.spring.model.ReadWriteFile;
import tdt.edu.spring.model.Schedule;
import tdt.edu.spring.model.Student;
import tdt.edu.spring.model.manageSubject;

import java.io.*;

public class ResultBuildSchedule {

	private static final String KETQUADANGKY = "KetQuaDangKyMonHoc.xlsx";
	private static final String	DANHSACHPHONG = "DanhSachPhongThi.xlsx";
	private static final String OUTPUTFILE_SUBJECT_MIDTERM = "SubjectFile_MidTerm.xlsx";
	private static final String OUTPUTFILE_SUBJECT_FINAL = "SubjectFile_Final.xlsx";
	private static final String MONTHYEARMIDTERRM = "10/2017";
	private static final int DAYEXAMMIDTEAM = 4;
	private static final String MONTHYEARFINAL = "12/2017";
	private static final int DAYEXAMFINAL= 7;
	
	private static final int	FIRST_FILE = 0;
	private static final int	SECOND_FILE = 1;
	private static final int	NUMDAY_MIDTERM = 7;
	private static final int	NUMDAY_FINAL =  13;
	private static final int	TYPE_MIDTERM = 0;
	private static final int	TYPE_FINAL = 1;
	
	
	private static Schedule _MidTerm;
	private static Schedule _Final;
	private static ArrayList<Student> list_Student;
	private static ArrayList<manageSubject> list_Subject;	
	private static Map<String, ArrayList<String>> mapSubjectMidTerm;
	private static Map<String, ArrayList<String>> mapSubjectFinal;
	
	public ResultBuildSchedule(String fileDK, String Room) throws Exception{
		processing(fileDK,Room);
	}
	
	public void processing(String fileDK, String Room ) throws Exception{
		
		//Load data from xlsx file
		ReadWriteFile io = new ReadWriteFile();
		io.readFromFile("E:\\UpLoad\\" +fileDK,FIRST_FILE);
		io.readFromFile("E:\\UpLoad\\"+ Room,SECOND_FILE);
		//******************************************
		
		//Build graph and coloring
		Graph g = new Graph(io.getListIDSubject());
		g = io.buildGraph(g);
		g.sortDegree();
		int[] colors = g.graphColoring();
		//******************************************
		
		
		//Save all ID subject that we have
		String[] idSub = new String[colors.length];
		for(int i = 0 ; i < g.getNumVertex(); i++)
			idSub[i] = g.getNodeVertex(i);
		//******************************************
		
		
		//Build Schedule with 2 part : mid term and final
		 _MidTerm = new Schedule(NUMDAY_MIDTERM,io.getList_Room());
		 _Final = new Schedule(NUMDAY_FINAL,io.getList_Room());
		_MidTerm.scheduleExam(io.getList_ManageSubject(), colors, idSub ,TYPE_MIDTERM);
		_Final.scheduleExam(io.getList_ManageSubject(), colors, idSub ,TYPE_FINAL);
		//*************************************************************************************
		
		list_Student = io.getList_Student();
		list_Subject = io.getList_ManageSubject();
		
		mapSubjectMidTerm = new HashMap<String,ArrayList<String>>();
		ArrayList<ArrayList<String>> loadInfoSub = _MidTerm.getLoadInfo();
		
		for(ArrayList<String> s : loadInfoSub){
			if(!mapSubjectMidTerm.containsKey(s)){
				mapSubjectMidTerm.put(s.get(0), s);
			}
		}
		
		loadInfoSub.clear();
		mapSubjectFinal = new HashMap<String,ArrayList<String>>();
		loadInfoSub = _Final.getLoadInfo();
		
		for(ArrayList<String> s : loadInfoSub){
			if(!mapSubjectFinal.containsKey(s)){
				mapSubjectFinal.put(s.get(0), s);
			}
		}
	}

	public static Schedule get_MidTerm() {
		return _MidTerm;
	}

	public static Schedule get_Final() {
		return _Final;
	}

	public static ArrayList<Student> getList_Student() {
		return list_Student;
	}
	
	public static ArrayList<manageSubject> getList_ManageSubject(){
		return list_Subject;
	}

	public static Map<String, ArrayList<String>> getMapSubjectMidTerm() {
		return mapSubjectMidTerm;
	}
	
	public static Map<String, ArrayList<String>> getMapSubjectFinal() {
		return mapSubjectFinal;
	}
}
