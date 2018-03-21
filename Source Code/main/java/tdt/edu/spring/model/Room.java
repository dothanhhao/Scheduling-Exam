package tdt.edu.spring.model;

import java.util.ArrayList;

/**
 * 
 * @author HaoDo
 *	Notes: H---  Họa thất
 *		   LT--- Lý thuyết
 *		   PM --- Phòng máy
 */
public class Room {

	private String idRoom;
	private int quantity;
	private String feature;
	
	private ArrayList<String> listIDStu;
	private ArrayList<Subject> infoSub;
	
	public Room(String idRoom, int quantity,String feature){
		this.idRoom = idRoom;
		this.quantity= quantity;
		this.feature = feature;
		this.listIDStu = new ArrayList<String>();
		this.infoSub = new ArrayList<Subject>();
	}
	
	public Room(Room another){
		this.idRoom = another.idRoom;
		this.quantity = another.quantity;
		this.feature = another.feature;
		this.listIDStu = new ArrayList<String>();
		this.infoSub = new ArrayList<Subject>();
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
	
	public ArrayList<String> getListIDStu() {
		return listIDStu;
	}

	public ArrayList<Subject> getInfoSub() {
		return infoSub;
	}
	
	public void addID(String id){
		this.listIDStu.add(id);
	}
	
	public void addSub(Subject sub){
		this.infoSub.add(sub);
	}
	
	
}
