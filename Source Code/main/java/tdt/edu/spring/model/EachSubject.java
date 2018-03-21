package tdt.edu.spring.model;

public class EachSubject {

	private String idMonHoc;
	private String nameMon;
	private String thu;
	private String ngay;
	private String gioThi;
	private String time;
	
	public EachSubject(String idMonHoc, String nameMon, String thu,String ngay, String gioThi, String time) {
		this.idMonHoc = idMonHoc;
		this.nameMon = nameMon;
		this.thu = thu;
		this.ngay = ngay;
		this.gioThi = gioThi;
		this.time = time;

	}

	public String getIdMonHoc() {
		return idMonHoc;
	}

	public void setIdMonHoc(String idMonHoc) {
		this.idMonHoc = idMonHoc;
	}

	public String getNameMon() {
		return nameMon;
	}

	public void setNameMon(String nameMon) {
		this.nameMon = nameMon;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getGioThi() {
		return gioThi;
	}

	public void setGioThi(String gioThi) {
		this.gioThi = gioThi;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	@Override
	public String toString() {
		return "EachSubject [idMonHoc=" + idMonHoc + ", nameMon=" + nameMon + ", thu=" + thu + ", ngay=" + ngay
				+ ", gioThi=" + gioThi + ", time=" + time + "]";
	}
}
