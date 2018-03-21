package tdt.edu.spring.model;

public class EachDay {
	
	private String thu;
	private String ngay;
	private String time;
	private String idSub;
	private String nameSub;
	private String group;
	private String subGroup;
	private String room;
	private String timmeExam;
	
	public EachDay(String thu, String ngay, String time, String idSub, String nameSub, String group, String subGroup,
			String room, String timmeExam) {
		super();
		this.thu = thu;
		this.ngay = ngay;
		this.time = time;
		this.idSub = idSub;
		this.nameSub = nameSub;
		this.group = group;
		this.subGroup = subGroup;
		this.room = room;
		this.timmeExam = timmeExam;
	}
	
	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIdSub() {
		return idSub;
	}

	public void setIdSub(String idSub) {
		this.idSub = idSub;
	}

	public String getNameSub() {
		return nameSub;
	}

	public void setNameSub(String nameSub) {
		this.nameSub = nameSub;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTimmeExam() {
		return timmeExam;
	}

	public void setTimmeExam(String timmeExam) {
		this.timmeExam = timmeExam;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	@Override
	public String toString() {
		return "EachDay [thu=" + thu + ", ngay=" + ngay + ", time=" + time + ", idSub=" + idSub + ", nameSub=" + nameSub
				+ ", group=" + group + ", subGroup=" + subGroup + ", room=" + room + ", timmeExam=" + timmeExam + "]";
	}

}
