package Webinteraction;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import Dataprocessing.*;



public class ExcelPlayerdata {

     
	 public static void main (String[] args) throws IOException, WriteException, BiffException, NumberFormatException {
		 ExcelPlayerdata.getExcelPlayerdata();
	 }
	
    public static void getExcelPlayerdata() throws IOException, WriteException, BiffException, NumberFormatException{

    	String site = "http://lol.gamepedia.com/Category:Player";
    	int k = 0;
    	
    	//Create Excel table
  	  WritableWorkbook wworkbook;
	      wworkbook = Workbook.createWorkbook(new File("PlayerStats.xls"));
	      WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
	      Label label1 = new Label(1 ,0 , "Games");
	      wsheet.addCell(label1);
	      Label label2 = new Label(2 ,0 , "Kills");
	      wsheet.addCell(label2);
	      Label label3 = new Label(3 ,0 , "Deaths");
	      wsheet.addCell(label3);
	      Label label4 = new Label(4 ,0 , "Assists");
	      wsheet.addCell(label4);
	      Label label5 = new Label(6 ,0 , "Avg Score");
	      wsheet.addCell(label5);
	      

	while (site != null){
	    	   
    //Getting the URL for our 2nd stream
    String line1 = null;
    String Player = null;
    
      BufferedReader br1 = Methods.establishConnection(site);
      //Input Stream naming variables
      
      int i2 = -10000;
      while ((line1 = br1.readLine()) != null) {
    	  if (line1.contains("/index.php?title=Category:") && line1.contains("next")){
    		  site = "http://lol.gamepedia.com/" + line1.substring(line1.indexOf("index")-1, line1.indexOf("title=\"")-2);
    		  System.out.println(site);
        		i2 = 0;
    	  }
    	  
    	 i2++;
  	  	if (i2 >= 5) {
  	  		if (!line1.contains("style")){
    	  Player = line1.substring(line1.indexOf("/", 0)+1,line1.indexOf("title")-2);
    	  
    	  	if (Player.equals("Bubbadub") || Player.equals("FORG1VEN") || Player.equals("Genja") 
    	  			|| line1.contains("edirect-in-category")|| Player.equals("Edge")) {
    	  		System.out.println(Player + "not included");
    	  	}
    	  	
    	  	else {
    	  		
        int j = 0;
        int i = 0;
        // Testing phas String Stat = null;
	    // Get the input stream through URL Connection
		BufferedReader br = Methods.establishConnection("http://lol.gamepedia.com/" + Player);
		
		
		
        String line;
        while ((line = br.readLine()) != null) {
    	if (line.contains("Total")){
    		
    		k++;
    		i = 0;
    		
    		//Note Player name and Formula for his score
    		  Label Playername = new Label(0 ,k , Player);
		      wsheet.addCell(Playername);
		      Label Totalscore = new Label(6 ,k , "=(C" + Integer.toString(k) + "+D" + Integer.toString(k) +  "+E" + Integer.toString(k) + ")/B" +Integer.toString(k));
		      wsheet.addCell(Totalscore);
		      System.out.println("= (C" + Integer.toString(k) + "+D" + Integer.toString(k) +  "+E" + Integer.toString(k) + ")/B" + Integer.toString(k));
		      
    	       for (int sw = 1;sw<5;sw++){
    	    	   switch (sw){
       			
       			case 1:  j = 2;
       					 //Stat = "Games";
       				     System.out.println(Player);
       				     break;
       			case 2:  j = 10;
       					 //Stat = "Kills";
       					 break;
       			case 3:  j = 12;
       					 //Stat = "Deaths";
       					 break;
       			case 4:  j = 14;
       					 //Stat = "Assists";
       					 break;
       	    		   }

    	    	   //writing out Stat
    	    	   while (i != j+1) {
    	    		   line = br.readLine();
    	    		   i++;
    	    	
    					 if (i == j){
    					        //System.out.println(Stat +":" + line.substring(line.length()-2, line.length())); Test
    					        //Note the number of Games,Kills,Deaths and Assists in that order in excel
    						 
    					 	int k2 = 0;
    							for (int sw3 = 0;sw3 <= 10;sw3++)
    							{	
    						      if  ((line.substring(line.length()-1, line.length())).contains(Integer.toString(sw3)))
    						      {
    						    	  k2++;
    						      }
    						 }
    					
    						   
    						     if (k2 > 0)
    						     {
    						    	 //from l168 to here totally unelegant workaround to check if string contains numbers
    						    
    						      if ((line.substring(line.length()-2, line.length())).contains(">")){
    						      Number number = new Number(sw, k ,Integer.parseInt (line.substring(line.length()-1, line.length())));  
    						      wsheet.addCell(number);
    						      }
    						      else{
    						      Number number = new Number(sw, k ,Integer.parseInt (line.substring(line.length()-2, line.length()))); 
    						      wsheet.addCell(number);
    						      
    						      						}
    						     					}
    					 						}
    	    	   							}
    	       							}
    								}      
        						}
    	  					}
  	  					}
  	  				}
  	  			}
      		}
         
       wworkbook.write();
	   wworkbook.close();
    }
}