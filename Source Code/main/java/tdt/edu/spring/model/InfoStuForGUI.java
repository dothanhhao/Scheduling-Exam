package tdt.edu.spring.model;

import java.util.ArrayList;

public class InfoStuForGUI {

	private Student infoStu;
	private ArrayList<EachDay> perDay = new ArrayList<EachDay>();
	private ArrayList<EachDay> perDayFinal= new ArrayList<EachDay>();
	
	public InfoStuForGUI(){
		super();
	}
	
	public InfoStuForGUI(Student infoStu) {
		this.infoStu = infoStu;
		this.perDay = new ArrayList<EachDay>();
	}


	public Student getInfoStu() {
		return infoStu;
	}


	public void setInfoStu(Student infoStu) {
		this.infoStu = infoStu;
	}


	public ArrayList<EachDay> getPerDay() {
		return perDay;
	}
	
	public void setPerDay(ArrayList<ArrayList<String>> info) {
		for(int i = 0 ; i < info.size(); i++){
			EachDay tmp = new EachDay(info.get(i).get(0),info.get(i).get(1),info.get(i).get(2),info.get(i).get(3),
					info.get(i).get(4),info.get(i).get(5),info.get(i).get(6),info.get(i).get(7),info.get(i).get(8));
			this.perDay.add(tmp);
		}
	}
	
	public ArrayList<EachDay> getPerDayFinal() {
		return perDayFinal;
	}
	
	public void setPerDayFinal(ArrayList<ArrayList<String>> info) {
		for(int i = 0 ; i < info.size(); i++){
			EachDay tmp = new EachDay(info.get(i).get(0),info.get(i).get(1),info.get(i).get(2),info.get(i).get(3),
					info.get(i).get(4),info.get(i).get(5),info.get(i).get(6),info.get(i).get(7),info.get(i).get(8));
			this.perDayFinal.add(tmp);
		}
	}

	@Override
	public String toString() {
		return "InfoStuForGUI [infoStu=" + infoStu + ", perDay=" + perDay + ", perDayFinal=" + perDayFinal + "]";
	}
	
	
	
	
}
