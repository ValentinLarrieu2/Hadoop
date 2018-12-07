	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;

	
public class MapAndReduce {

	public static String csvFile = "fichierCSV.csv"; //the path of the CSV file
    public static String cvsSplitBy = ","; //Here we chose the , separator
    
	public static void map(int key,String value,ArrayList<ListElement> list){
		int column=0; //This counter will count the column number of the element
        String[] elements = value.split(cvsSplitBy);
        for(String element : elements){
        	column++; 
        	ListElement le = new ListElement(key,column,element); //We contruct our list element which is composed of a two element key (the coordinates) and a value represented by the contect of this element
        	list.add(le);//We add our element to the list 
            System.out.println("\n MAP LINE "+key+" element  : "+element+" numéro element : "+column+ " coord n1 = " + le.getFirstCoord()+ " coord n2 = " + le.getSecondCoord() );
        }
	}
	public static void reduce(int[] key,String value,ArrayList<ListElement> returnList){
		ListElement le = new ListElement();		
		//We invert the two cordinates
		le.setFirstCoord(key[1]); //X becomes Y 
		le.setSecondCoord(key[0]); //Y becomes X
		le.setValue(value); //We fully set our new element using the value
        System.out.println("\n REDUCE First Coord "+le.getFirstCoord()+" Second Coord  : "+le.getSecondCoord()+" Value : "+le.getValue());
		returnList.add(le); //We add the element to the return list
	}
	//As we can see our key values are there unique since they are the coordinate of the element. That means the sort and shuffle won't group the two same values
	
        
	public static void main(String[] args) {
        
		//The input reader is supposed to read the data and separate them legitimaly (here he would probably track the end of line char and split the lines)
		
        ArrayList <ListElement> list = new ArrayList(); //list will be the one containing the map return
        int countLine=0; //we need a line counter un order to set the elements for our mapper
        String line = ""; 
        
        //We read the CSV using the constant we defined at the start of this class
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	//while we are not at the end of the document
            while ((line = br.readLine()) != null) {
            		countLine++;
	                map(countLine,line,list);
                }
        } catch (IOException e) {
            e.printStackTrace();//in case of an error
        }
        ArrayList <ListElement> returnList = new ArrayList(); //List which will contain the result of the reduce
        //The sort and shuffle does there his job to sort and Give the work to the workes. Here the keys were made to be unic so two same element in a column will give two different ListElement
        
        //We use the reduce function to revert the coordinates
        for (ListElement le :  list){
            	int [] coord = new int [] {le.getFirstCoord(),le.getSecondCoord()};//our key are the coordinates
                reduce (coord,le.getValue(),returnList);
        }
        
        //After that the output writer should do his job and prepare the final content

	}
}
