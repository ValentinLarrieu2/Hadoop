/*
 * ListElement class is composed of a List of 2 Strings (2 coordinates) and a value. 
 * The coordinates form an unique couple
 */
public class ListElement {

	private int [] key = new int [] {0,0};
	private String value="";
	
	public ListElement (){
	}
	
	public ListElement (int coord1,int coord2, String value){
		this.value=value;
		this.key[0]=coord1;
		this.key[1]=coord2;		
	}

	public int[] getKey() {
		return key;
	}

	public void setKey(int[] key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setFirstCoord(int coord) {
		this.key[0]=coord;
	}
	public void setSecondCoord(int coord) {
		this.key[1]=coord;
	}
	public int getFirstCoord() {
		return key[0];
	}
	public int getSecondCoord() {
		return key[1];
	}
}
