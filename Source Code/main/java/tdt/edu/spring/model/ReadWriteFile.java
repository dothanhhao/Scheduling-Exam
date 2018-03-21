package tdt.edu.spring.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteFile {
	
	private static final int	FIRST_FILE = 0;
	private static final int	SECOND_FILE = 1;
	
	private ArrayList<Student> list_Student;				// Contains all student
	private ArrayList<manageSubject> list_ManageSubject;	// Contains all Subject and info list Student that learning this Subject
	private ArrayList<Room> list_Room;						// Contains all Room 
	private ArrayList<String> listIDSubject;				// Contains all ID Subject
	
	public ReadWriteFile(){
		this.list_ManageSubject = new ArrayList<manageSubject>();
		this.list_Student = new ArrayList<Student>();
		this.list_Room = new ArrayList<Room>();
		this.listIDSubject = new ArrayList<>();
	}

	/*
	 * Method: BuildGraph
	 * Create vertex and add all edge into Graph
	 */
	public Graph buildGraph(Graph g){
		for(int i = 0; i < this.list_Student.size(); i++){
			for(int index = 0 ; index < this.list_Student.get(i).getListSubject().size(); index++){
				for(int subIndex = index + 1; subIndex < this.list_Student.get(i).getListSubject().size(); subIndex++){
					int row = this.indexIDSubject(this.listIDSubject, this.list_Student.get(i).getListSubject().get(index).getIdSubject());
					int col = this.indexIDSubject(this.listIDSubject, this.list_Student.get(i).getListSubject().get(subIndex).getIdSubject());
					g.addEdge(row, col);
				}
			}
		}
		return g;
	}
	
	/*
	 * Method : readRoomFile
	 * Read data from File
	 */
	public void readFromFile(String fileName,int numFile) throws IOException{
		
		ArrayList<Room> lstRoom = new ArrayList<Room>();
		FileInputStream fis = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook (fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator ite = sheet.rowIterator();
		ite.next();
		while(ite.hasNext()){
			Row row = (Row) ite.next();
			Iterator<Cell> cite = row.cellIterator();
			String line = "";
			while(cite.hasNext()){
				Cell c = cite.next();
				line += c.toString() + "\t";
			}
			if(line != ""){
				if(numFile == SECOND_FILE){
					String[] splitString = line.split("\t");
					this.list_Room.add(new Room(splitString[0],Integer.parseInt(splitString[1]),splitString[2]));
				}
				else if(numFile == FIRST_FILE){
					addDataOfSubject(line);
				}
			}
		}
		workbook.close();
		fis.close();
	}
	
	
	private void addDataOfSubject(String line){
		
		String[] splitLine = line.split("\t");

		if(splitLine[1].length() >= 5 && !splitLine[1].substring(0, 5).equals("Đồ án")){
			//Add all ID Subject that we have.
			if(!this.listIDSubject.contains(splitLine[0]))
				this.listIDSubject.add(splitLine[0]);
			//***************************************
			
			
			//Creating Subject with data in one line
			int subGroup = -1;
			if( !splitLine[3].equals(""))
				subGroup = Integer.parseInt(splitLine[3]);
			Subject tmpSub = new Subject(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]),subGroup);
			//*******************************************************************************************************
			
			
			//Check student is contained in list_Student : if not --> add student, else --> add subject into object student in list
			int isContainsStu = -1;
			for(int i = 0; i < this.list_Student.size();i++){
				if(this.list_Student.get(i).getMSSV().equals(splitLine[5])){
					isContainsStu = i;	break;
				}
			}
			if(isContainsStu == -1){
				Student tmpStu = new Student(splitLine[6] + " " + splitLine[7],splitLine[5],splitLine[4],splitLine[4]+"@student.tdt.edu.vn");
				tmpStu.addSubject(tmpSub);
				this.list_Student.add(tmpStu);
			}
			else
				this.list_Student.get(isContainsStu).addSubject(tmpSub);
			//*******************************************************************************************************
			
			
			//Check manageSubject is contained in list_Manage : if not --> add manageSubject, else --> add idStudent into object manageSubject in list
			int isContainsSub = -1;
			for(int i = 0; i < this.list_ManageSubject.size(); i++){
				if(this.list_ManageSubject.get(i).getTmpSubject().getIdSubject().equals(tmpSub.getIdSubject())
						&& this.list_ManageSubject.get(i).getTmpSubject().getGroup() == tmpSub.getGroup()
						&& this.list_ManageSubject.get(i).getTmpSubject().getSubGroup() == tmpSub.getSubGroup()){
					isContainsSub = i; break;
				}
			}
			if(isContainsSub == -1){
				manageSubject tmpMSub = new manageSubject(tmpSub);
				tmpMSub.addID(splitLine[5]);
				this.list_ManageSubject.add(tmpMSub);
			}
			else
				this.list_ManageSubject.get(isContainsSub).addID(splitLine[5]);
			//*******************************************************************************************************
		}
	}
	
	
	/*
	 * Method : indexIDSubject
	 * return : index of ID Subject in listID and enter on graph
	 */
	private int indexIDSubject(ArrayList<String> listIDSubject, String idSub){
		for(int i = 0; i < listIDSubject.size(); i++){
			if(listIDSubject.get(i).equals(idSub))
				return i;
		}
		return -1;
	}

	public ArrayList<Student> getList_Student() {
		return list_Student;
	}

	public ArrayList<manageSubject> getList_ManageSubject() {
		return list_ManageSubject;
	}

	public ArrayList<Room> getList_Room() {
		return list_Room;
	}

	public ArrayList<String> getListIDSubject() {
		return listIDSubject;
	}

	@Override
	public String toString() {
		return "ReadWriteFile [list_Student=" + list_Student + ", list_ManageSubject=" + list_ManageSubject
				+ ", list_Room=" + list_Room + ", listIDSubject=" + listIDSubject + "]";
	}
}
