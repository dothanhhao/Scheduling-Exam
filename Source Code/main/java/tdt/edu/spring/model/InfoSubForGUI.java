package tdt.edu.spring.model;

import java.util.ArrayList;
import java.util.Map;

public class InfoSubForGUI {
	
	private  ArrayList<EachSubject> perDay = new ArrayList<EachSubject>();
	private  ArrayList<EachSubject> perDayFinal= new ArrayList<EachSubject>();
	
	public InfoSubForGUI(){
		perDay = new ArrayList<EachSubject>();
		perDayFinal= new ArrayList<EachSubject>();
	}

	public void setValue(Map<String,ArrayList<String>> typeExam,int type){
		
		for(String a : typeExam.keySet()){
			ArrayList<String> tmp = typeExam.get(a);
			EachSubject sb = new EachSubject(tmp.get(0), tmp.get(1), tmp.get(4), tmp.get(5),tmp.get(6),tmp.get(7));
			if(type == 0)
				perDay.add(sb);
			else
				perDayFinal.add(sb);
		}
	}

	public ArrayList<EachSubject> getPerDay() {
		return perDay;
	}

	public ArrayList<EachSubject> getPerDayFinal() {
		return perDayFinal;
	}

	public void setPerDay(ArrayList<EachSubject> perDay) {
		this.perDay = perDay;
	}

	public void setPerDayFinal(ArrayList<EachSubject> perDayFinal) {
		this.perDayFinal = perDayFinal;
	}

	@Override
	public String toString() {
		return "InfoSubForGUI [perDay=" + perDay + ", perDayFinal=" + perDayFinal + "]";
	}
	
	
	
}
