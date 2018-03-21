package tdt.edu.spring.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Ca {
	
	private ArrayList<Room> hasSub;
	private ArrayList<Room> isEmpty;
	
	private static final int MIDTERM_EXAM = 0;
	private static final int FINAL_EXAM = 1;
	
	public Ca(ArrayList<Room> listRoom){
		this.hasSub = new ArrayList<Room>();
		this.isEmpty = new ArrayList<Room>();
		for(int i = 0 ; i < listRoom.size(); i++){
			isEmpty.add(new Room(listRoom.get(i)));
		}
	}
	
	/*
	 * add Subject into Room in this Ca
	 */
	public boolean addSubject(ArrayList<manageSubject> listSub, int typeExam) throws Exception{
		int numberStu = 0;
		int sumQuantity = 0;
		int decreaseQuantity = 0;
		
		//Set type of Room
		String tmp = "";
		if(typeExam == MIDTERM_EXAM){
			if(!listSub.get(0).getTmpSubject().subGroupisEmpty() 
					|| ( listSub.get(0).getTmpSubject().getNameSubject().length() >= 9
					&&	listSub.get(0).getTmpSubject().getNameSubject().substring(0,9).equals("Thực hành"))){
				tmp = "PM";
				decreaseQuantity = 5;
			}
			else{
				tmp = "LT";
				decreaseQuantity = 10;
			}
		}
		else if(typeExam == FINAL_EXAM){
			if(listSub.get(0).getTmpSubject().getNameSubject().length() >= 9
					&&	listSub.get(0).getTmpSubject().getNameSubject().substring(0,9).equals("Thực hành")){
				
				tmp = "PM";
				decreaseQuantity = 5;
			}
			else{
				tmp = "LT";
				decreaseQuantity = 10;
			}
		}

		ArrayList<Room> typeRoom = new ArrayList<Room>();
		for(int i = 0; i < this.getIsEmpty().size(); i++){
			if(this.getIsEmpty().get(i).getFeature().equals(tmp)){
				sumQuantity += this.getIsEmpty().get(i).getQuantity();
				typeRoom.add(this.getIsEmpty().get(i));
			}
		}
		//*************************************************************************************************
		
		//Save quantity Student in each group or sub-group of Subject
		ArrayList<String> listID = new ArrayList<String>();
		for(int i = 0 ; i < listSub.size(); i++){
			for(int j = 0 ; j < listSub.get(i).getListIDStudent().size(); j++){
					listID.add(listSub.get(i).getListIDStudent().get(j));
					numberStu++;
			}
			numberStu += decreaseQuantity;
		
		}
		//******************************************************************
		
		//Return false if can set Room
		if(numberStu > sumQuantity)			
			return false;
		//****************************
		
		
		//Find all appropriate Room that we need in Type Room
		ArrayList<Room> appropriateRoom = new ArrayList<Room>();
		appropriateRoom = this.choiceRoom(typeRoom, numberStu);
		//******************************************************
		
	
		//Set Room : with each Room, set all quantity and save it into hasSub list
		for(int i = 0; i < appropriateRoom.size(); i++){
			boolean isEmptyList = false;
			for(int j = 0; j < appropriateRoom.get(i).getQuantity() - decreaseQuantity; j++){
				String idStu = listID.remove(0);
				appropriateRoom.get(i).addID(idStu);
				appropriateRoom.get(i).addSub(listSub.get(indexIDinManageList(listSub,idStu)).getTmpSubject());
				if(listID.isEmpty()){
					isEmptyList = true;
					break;
				}
			}
			
			for(int index = 0; index < this.getIsEmpty().size(); index++){
				if(this.getIsEmpty().get(index).getIdRoom() == appropriateRoom.get(i).getIdRoom()){
					this.hasSub.add(this.isEmpty.remove(index));
					break;
				}
			}
			
			if(isEmptyList == true)	break;
		}
		//***********************************************************************
		return true;
	}
	
	private int indexIDinManageList(ArrayList<manageSubject> lstSub, String id){
		for(int i = 0 ; i < lstSub.size(); i++){
			for(int j = 0 ; j < lstSub.get(i).getListIDStudent().size(); j++){
				if(lstSub.get(i).getListIDStudent().get(j).equals(id))
					return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Room> gethasSub(){
		return this.hasSub;
	}
	
	public ArrayList<Room> getIsEmpty(){
		return this.isEmpty;
	}

	@Override
	public String toString() {
		return "Ca [hasSub=" + hasSub .toString()+ ", isEmpty=" + isEmpty.toString() + "]";
	}
	
	
	/*
	 * Using Greedy algorithm to choice Room that have appropriate quantity
	 * Method : similar ATM method
	 */
	public ArrayList<Room> choiceRoom(ArrayList<Room> typeRoom, int quantityStudent) throws Exception{
		
		//Save room is chosen
		ArrayList<Room> returnChoice = new ArrayList<Room>();
		
		//Save all different quantity
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i = 0; i < typeRoom.size(); i++){
			if(!tmp.contains(typeRoom.get(i).getQuantity())){
				tmp.add(typeRoom.get(i).getQuantity());
			}
		}
		
		//Convert all different quantity to Array
		int[] categoryQuantity = new int[tmp.size()];
		for(int i = 0; i < categoryQuantity.length; i++){
			categoryQuantity[i] = tmp.get(i);
		}
		
		//Sort quantity
		Arrays.sort(categoryQuantity);
		
		//Count available Room with each quantity
		int[] quantityRoom = new int[categoryQuantity.length];
		for(int i = 0; i < quantityRoom.length; i++){
			
			for(int j = 0 ; j < typeRoom.size(); j++){
				if(categoryQuantity[i] == typeRoom.get(j).getQuantity()){
					quantityRoom[i]++;
				}
			}
		}
		
		//Add appropriate Room
		for(int k = categoryQuantity.length - 1; k >= 0; k--){
			
			int needQuantity = quantityStudent / categoryQuantity[k];

			if(needQuantity > quantityRoom[k]){
				needQuantity = quantityRoom[k];
			}
			
			quantityRoom[k] -= needQuantity;
			quantityStudent -= categoryQuantity[k] * needQuantity;
			
			if(quantityStudent > 0 && k == 0 && quantityRoom[k] > 0){
				needQuantity++;
				quantityRoom[k]--;
			}
			
			else if(k > 0 && quantityStudent > categoryQuantity[k - 1] && quantityStudent < categoryQuantity[k] && 
					quantityRoom[k] > 0){
				needQuantity++;
				quantityRoom[k]--;
				quantityStudent = 0;
			}
			
			for(int i = 0; i < needQuantity; i++){
				for(int j = 0; j < typeRoom.size(); j++){
					if(typeRoom.get(j).getQuantity() == categoryQuantity[k]){
						returnChoice.add(typeRoom.remove(j));
						j--;
					}
				}
			}
			
			if(quantityStudent == 0){
				return returnChoice;
			}
			
		}
		
		return returnChoice;
	}
}
