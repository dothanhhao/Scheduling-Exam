package tdt.edu.spring.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Schedule {

	private ArrayList<Day> listDay;
	
	private static final int MIDTERM_EXAM = 0;
	private static final int FINAL_EXAM = 1;
	private static final int WEEK_TWO = 10;
	
	private ArrayList<ArrayList<String>> loadInfoSub;
	
	public Schedule(int numDay,ArrayList<Room> listRoom){
		listDay = new ArrayList<Day>();
		
		int nCa = -1;
		if(numDay == 7)	nCa = 10;
		else	nCa = 4;
		
		for(int i = 0; i < numDay; i++)
			listDay.add(new Day(nCa,listRoom));
		loadInfoSub = new ArrayList<ArrayList<String>>();
	}
	
	public void scheduleExam(ArrayList<manageSubject> listManaSub,int[] colors, String[] idSub,int typeExam) throws Exception{
		
		boolean[] Done = new boolean[colors.length];
		for(int i = 0 ; i < Done.length;i++)
			Done[i] = false;
		
		int beginCa = 0;
		int day = 0;
		int countColor = -1;
	
		for(int i = 0 ; i < idSub.length; i++){
			
			if(Done[i] == false){
				
				countColor = countColor + 1;
				String idSubject = idSub[i];
				int color = colors[i];
				
				//Finding Subject have same color
				ArrayList<String> sameColor = new ArrayList<>();
				sameColor.add(idSubject);
				for(int index = 0 ; index < colors.length; index++){
					if(index != i && colors[index] == color)
						sameColor.add(idSub[index]);
				}
				//**************************************************
				
				//Check type of Exam
				if(typeExam == MIDTERM_EXAM){
					if( i == 0){
						this.buildScheDule(listManaSub, sameColor, 3 , beginCa,MIDTERM_EXAM);
					}
					else{
						int tmpCa = beginCa;
						if(day == 3 ) 
							tmpCa++;
						this.buildScheDule(listManaSub, sameColor, day, tmpCa,MIDTERM_EXAM);
						day++;
					}
				}
				
				else if(typeExam == FINAL_EXAM){
						this.buildScheDule(listManaSub, sameColor, day, beginCa,FINAL_EXAM);
						day++;
				}
				//****************************************************************************
				
				//Check all Subject that is scheduled
				for(int index = 0; index < sameColor.size(); index++){
					for(int subindex = 0 ; subindex < idSub.length ; subindex++){
						if(sameColor.get(index).equals(idSub[subindex])){
							Done[subindex] = true;
							break;
						}
					}
				}
				//************************************************
				
				//Re-setup day
				if(typeExam == MIDTERM_EXAM && day >= this.listDay.size()){
					day = 0;	beginCa++;
				}
				else if (typeExam == FINAL_EXAM ){
			
					if(day == WEEK_TWO ){
						if(beginCa == (this.getDay(0).getListCa().size() - 1)){
							beginCa = 0;
						}
						else {
							day = 0;	beginCa++;
						}
					}
					
					else if(day >= this.listDay.size()){
						day = WEEK_TWO;
						beginCa++;
					}
					
				}
				//******************************
			}
		}
		
	}
	
	
	/*
	 * Build schedule for each Ca in Day with List subject that same color
	 */
	private void buildScheDule(ArrayList<manageSubject> listManaSub,
			ArrayList<String> sameColor,int tmpDay,int tmpCa, int typeExam) throws Exception{
		
		for(int index = 0; index < sameColor.size(); index++){
		
			ArrayList<manageSubject> relevantSub = new ArrayList<>();
			
			for(int subIndex = 0 ; subIndex < listManaSub.size(); subIndex++){
				if(listManaSub.get(subIndex).getTmpSubject().getIdSubject().equals(sameColor.get(index)))
					relevantSub.add(listManaSub.get(subIndex));
			}
			
			boolean isDone = false;
	
			int limitDay = 0;
			do{
				
				isDone = this.listDay.get(tmpDay).XepCa(relevantSub, tmpCa,typeExam);
				if(isDone == false)	tmpCa++;
				
				if(tmpCa >= this.listDay.get(0).getListCa().size()){
					tmpDay++;
					tmpCa = 0;
					limitDay++;
				}
				
				if(tmpDay >= this.listDay.size()){
					tmpDay = 0;	tmpCa = 0;
				}
				
				if(limitDay == 7 && typeExam == MIDTERM_EXAM)	isDone = true;
					
				
				if(limitDay == 13 && typeExam == FINAL_EXAM)	isDone = true;

				
			}while(isDone == false);
			
		}
	}
	
	
	public Day getDay(int index){
		return this.listDay.get(index);
	}
	
	public ArrayList<Day> getListDay() {
		return listDay;
	}
	
	public ArrayList<String> GUIStudent(ArrayList<Student> list_Student, int typeExam, int startDay, String monthYear){
		int tmpStarDay = startDay;
		String timeSetup = "45 phút";
		if(typeExam == 1)
			timeSetup = "90 phút";
		ArrayList<String> saveInfo = new ArrayList<>();
		
		for(int i = 0 ; i < list_Student.size(); i++){
			String tmp = list_Student.get(i).getMSSV() + ":\n";
			for(int day = 0 ; day < this.listDay.size(); day++){
				for( int ca = 0; ca < this.listDay.get(day).getListCa().size(); ca++){
					Ca tmpCa = this.listDay.get(day).getListCa().get(ca);
					boolean check = false;
					for(int indexRoom = 0 ; indexRoom < tmpCa.gethasSub().size(); indexRoom++){
						Room tmpRoom = tmpCa.gethasSub().get(indexRoom);
						for(int index = 0; index < tmpRoom.getListIDStu().size(); index++){
							if(tmpRoom.getListIDStu().get(index).equals(list_Student.get(i).getMSSV())){
								String thu = "Thứ " + (day + 2);
								if(typeExam == 0 && (day + 2 ) == 8)
									thu = "Chủ nhật";
								else if(typeExam == 1 && (day >=6))
									thu = "Thứ " + ((day % 6) + 2);
								tmp += thu + ", Ngày : " + (tmpStarDay + monthYear) + ", Giờ : " + this.listDay.get(day).getTime(ca) + "," 
										+ tmpRoom.getInfoSub().get(index).toString()
										+ " , Phòng : " + tmpRoom.getIdRoom() + " ,Thời lượng : " + timeSetup +"\n";
								check = true;
								break;
							}
						}
						if( check == true) break;
					}
				}
				tmpStarDay++;
			}
			tmpStarDay = startDay;
			saveInfo.add(tmp);
		}
		return saveInfo;
	}
	
	public ArrayList<ArrayList<String>> findInfoStudent(ArrayList<Student> list_Student, int typeExam,String mssv, int startDay, String monthYear){
		int tmpStarDay = startDay;
		String timeSetup = "45 phút";
		if(typeExam == 1)
			timeSetup = "90 phút";
		ArrayList<ArrayList<String>> saveInfo = new ArrayList<ArrayList<String>>();
		
		for(int i = 0 ; i < list_Student.size(); i++){
			if(list_Student.get(i).getMSSV().equals(mssv)){
				
				for(int day = 0 ; day < this.listDay.size(); day++){
					for( int ca = 0; ca < this.listDay.get(day).getListCa().size(); ca++){
						Ca tmpCa = this.listDay.get(day).getListCa().get(ca);
						boolean check = false;
						for(int indexRoom = 0 ; indexRoom < tmpCa.gethasSub().size(); indexRoom++){
							Room tmpRoom = tmpCa.gethasSub().get(indexRoom);
							for(int index = 0; index < tmpRoom.getListIDStu().size(); index++){
								if(tmpRoom.getListIDStu().get(index).equals(list_Student.get(i).getMSSV())){
									
									ArrayList<String> saveInFoStudent = new ArrayList<String>();
									String thu = "Thứ " + (day + 2);
									if(typeExam == 0 && (day + 2 ) == 8)
										thu = "Chủ nhật";
									else if(typeExam == 1 && (day >=6))
										thu = "Thứ " + ((day % 6) + 2);
									saveInFoStudent.add(thu);
									saveInFoStudent.add( "Ngày : " + (tmpStarDay + "/"+ monthYear));
									saveInFoStudent.add("Giờ : " + this.listDay.get(day).getTime(ca));
									saveInFoStudent.add("Mã môn: " +  tmpRoom.getInfoSub().get(index).getIdSubject());
									saveInFoStudent.add("Tên môn: " +  tmpRoom.getInfoSub().get(index).getNameSubject());
									saveInFoStudent.add("Nhóm: " + tmpRoom.getInfoSub().get(index).getGroup());
									saveInFoStudent.add("Tổ: " + tmpRoom.getInfoSub().get(index).getSubGroup());
									saveInFoStudent.add("Phòng : " + tmpRoom.getIdRoom());
									saveInFoStudent.add("Thời lượng : " + timeSetup);
									
									saveInfo.add(saveInFoStudent);
									check = true;
									break;
								}
							}
							if( check == true) break;
						}
					}
					tmpStarDay++;
				}
			}
		}
		return saveInfo;
	}
	
	/*
	 * Write Subject into xlsx File
	 */
	public void writeSubject(ArrayList<manageSubject> mana,int typeExam,String fileName, int startDay, String monthYear) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet(fileName);
	
		//Create First Row
		String[] title = {"Mã môn","Tên môn","Nhóm","Tổ","Thứ","Ngày","Giờ thi","Time","Phòng","Sức chứa","Sỉ số"};
		Row row = sheet.createRow(0);
		Cell perCell;
		for(int i = 0 ;i < title.length;i++){
			perCell = row.createCell(i);
			perCell.setCellValue(title[i]);
		}
		String timeSetup = "45 phút";
		if(typeExam == 1)
			timeSetup = "90 phút";
		
		int countRow = 1;
		int tmpStartDay = startDay;
		
		for(int i = 0 ; i < mana.size(); i++){
		
			manageSubject tmp = mana.get(i);
			boolean nextSub = false;
			for(int day = 0 ; day < this.listDay.size(); day++){
				for( int ca = 0; ca < this.listDay.get(day).getListCa().size(); ca++){
					Ca tmpCa = this.listDay.get(day).getListCa().get(ca);
					
					for(int indexRoom = 0 ; indexRoom < tmpCa.gethasSub().size(); indexRoom++){
						Room tmpRoom = tmpCa.gethasSub().get(indexRoom);
						for(int index = 0; index < tmpRoom.getInfoSub().size(); index++){
							if(tmpRoom.getInfoSub().get(index).getIdSubject().equals(mana.get(i).getTmpSubject().getIdSubject())){
								if(isRoom(tmpRoom.getInfoSub(),mana.get(i))){
									ArrayList<String> saveTmp = new ArrayList<String>();
									String thu = "Thứ " + (day + 2);
									if(typeExam == 0 && (day + 2 ) == 8)
										thu = "Chủ nhật";
									else if(typeExam == 1 && (day >=6)){
										if(day == 6)
											thu = "Chủ nhật";
										else
											thu = "Thứ " + ((day % 6) + 2);
									}
									
									row = sheet.createRow(countRow);
									perCell = row.createCell(0);
									perCell.setCellValue(tmp.getTmpSubject().getIdSubject());
									saveTmp.add(tmp.getTmpSubject().getIdSubject());
									
									perCell = row.createCell(1);
									perCell.setCellValue(tmp.getTmpSubject().getNameSubject());
									saveTmp.add(tmp.getTmpSubject().getNameSubject());
									
									perCell = row.createCell(2);
									perCell.setCellValue(tmp.getTmpSubject().getGroup());
									saveTmp.add(tmp.getTmpSubject().getGroup() + "");
									
									perCell = row.createCell(3);
									if(tmp.getTmpSubject().getSubGroup() != -1){
										perCell.setCellValue(tmp.getTmpSubject().getSubGroup());
									}
									saveTmp.add(tmp.getTmpSubject().getSubGroup() + "");
									
									perCell = row.createCell(4);
									perCell.setCellValue(thu);
									saveTmp.add(thu);
									
									perCell = row.createCell(5);
									perCell.setCellValue((tmpStartDay + "/"+ monthYear));
									saveTmp.add((tmpStartDay + "/"+ monthYear));
									
									perCell = row.createCell(6);
									perCell.setCellValue(this.listDay.get(day).getTime(ca));
									saveTmp.add(this.listDay.get(day).getTime(ca));
									
									perCell = row.createCell(7);
									perCell.setCellValue(timeSetup);
									saveTmp.add(timeSetup);
									
									perCell = row.createCell(8);
									perCell.setCellValue(tmpRoom.getIdRoom());
									saveTmp.add(tmpRoom.getIdRoom());
									
									perCell = row.createCell(9);
									perCell.setCellValue( tmpRoom.getQuantity());
									saveTmp.add(tmpRoom.getQuantity() + "");
									
									perCell = row.createCell(10);
									perCell.setCellValue( tmpRoom.getListIDStu().size());
									saveTmp.add( tmpRoom.getListIDStu().size() + "");
									
									this.loadInfoSub.add(saveTmp);
									countRow++;
									nextSub = true;
									break;
								}
								
							}
						}
						
					}
					if( nextSub == true) break;
				}
				if( nextSub == true) break;
				tmpStartDay++;
			}
			tmpStartDay = startDay;
		}

		FileOutputStream fos =new FileOutputStream(new File("E:/Resources/"+fileName));
        workbook.write(fos);
        fos.close();
	}
	
	/*
	 * Check that the room is used for Subject exam
	 */
	private boolean isRoom(ArrayList<Subject> listSub, manageSubject tmp){
		ArrayList<Subject> save = new ArrayList<Subject>();
		if(save.isEmpty())
			save.add(listSub.get(0));
		
		for(int i = 1; i < listSub.size(); i++){
			boolean check = true;
			for(int j = 0 ; j < save.size();j++){
				if(listSub.get(i).getIdSubject().equals(save.get(j).getIdSubject()) &&
						listSub.get(i).getGroup() == save.get(j).getGroup() && 
								listSub.get(i).getSubGroup() == save.get(j).getSubGroup()){
					check = false;
					break;
				}
			}
			if(check){
				save.add(listSub.get(i));
			}
		}
		
		int[] count = new int[save.size()];
		for(int i = 0 ; i < save.size(); i++){
			for(int j = 0; j < listSub.size(); j++){
				if(listSub.get(j).getIdSubject().equals(save.get(i).getIdSubject()) &&
						listSub.get(j).getGroup() == save.get(i).getGroup() && 
								listSub.get(j).getSubGroup() == save.get(i).getSubGroup()){
					count[i]++;
				}
			}
		}
		int max = count[0];
		int saveIndex = 0;
		for(int i = 0 ; i < count.length; i++){
			if(max < count[i]){
				max = count[i];
				saveIndex = i;
			}
		}
		
		if(save.get(saveIndex).getIdSubject().equals(tmp.getTmpSubject().getIdSubject())
				&& save.get(saveIndex).getGroup() == tmp.getTmpSubject().getGroup()
				&& save.get(saveIndex).getSubGroup() == tmp.getTmpSubject().getSubGroup())
			return true;
		return false;
	}

	public ArrayList<ArrayList<String>> getLoadInfo(){
		return this.loadInfoSub;
	}
}
