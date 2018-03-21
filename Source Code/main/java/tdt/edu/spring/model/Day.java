package tdt.edu.spring.model;

import java.util.ArrayList;

public class Day {

	private static final int DISTANCE_MIDTERM = 15;
	private static final int DISTANCE_FINAL = 30;
	private static final int TIME_MIDTERM = 45;
	private static final int TIME_FINAL = 90;
	
	private ArrayList<Ca> listCa;
	private ArrayList<Double> time;
	
	public Day(int numCa, ArrayList<Room> listRoom){
		time = new ArrayList<Double>();
		listCa = new ArrayList<Ca>();
		for(int i = 0 ; i < numCa; i++){
			listCa.add(new Ca(listRoom));
		}
		this.setTime();
	}

	/*
	 * Setup time for each Ca
	 */
	public void setTime(){
		double timeStart = 7;
		int minutes = 0;
		
		int timeDistance = -1;
		int timeOneCa = -1;
		
		if(listCa.size() == 10){
			timeDistance = DISTANCE_MIDTERM;		timeOneCa = TIME_MIDTERM;
		}
		else{
			timeDistance = DISTANCE_FINAL;		timeOneCa = TIME_FINAL;
		}
			
		for(int i = 0 ; i < listCa.size(); i++){
			time.add(timeStart);
			minutes = minutes + timeOneCa + timeDistance;
			timeStart = timeStart + minutes/60 + (minutes%60)/100.0;
			minutes = (minutes%60)/100;
			if(timeStart >= 11 && timeStart <= 12)
				timeStart = 13;
		}
	}
	
	public String getTime(int index){
		int minutes = (int)((this.time.get(index) * 100) % 100);
		String minu = "";
		if(minutes  < 10)
			minu = "0" + minutes;
		return  (int)(this.time.get(index) % 24) + ":" + minu;
	}
	
	public Ca getCa(int index){
		return this.listCa.get(index);
	}
	
	public ArrayList<Ca> getListCa(){
		return this.listCa;
	}
	
	
	@Override
	public String toString() {
		return "Day [listCa=" + listCa + ", time=" + time + "]";
	}

	/*
	 * Add Subject for Ca
	 */
	public boolean XepCa(ArrayList<manageSubject> sub, int ca,int typeExam) throws Exception{
		return this.getCa(ca).addSubject(sub,typeExam);
	}
	
	
}
