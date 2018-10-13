package Webinteraction;

import Dataprocessing.*;
import classes.*;
import java.io.IOException;


public class AlphadraftData {

	
	public static void main (String[] args) throws IOException, NumberFormatException {
		try {
//		String sessionkey = Methods.getAlphadraftsessionkey();
		String sessionkey = "sessionid=ouvz5lnifyh62od982kv4l9fjjzqesnx; expires=Mon";
		AlphadraftData.getAlphadraftData(sessionkey,1091);
		}
		finally {
	}
		
	}
	
	
	public static Player[] getAlphadraftData (String sessionkey,int contestgroupid) throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub

		// declare variables
		    Player[] Playerarray = new Player[60];
	        String Playername;
	        int Playerid;
	        int k = 0;
	        String cost;
	        String role;
	       
	        
	       String data = Methods.AlphadraftPostRequest("https://alphadraft.com/api/ui/player/getplayersforbatchcall_mvp/",sessionkey,"{\"contest_group\":"+ contestgroupid+ "}");
	        
	       // get amount of players
	       int Occ = Methods.getOccurences(data, "player_name");
	       
	        // replace keywords to navigate data
	       data = Methods.replaceString(data,"player_name");
	       data = Methods.replaceString(data,"player_handle");
	       data = Methods.replaceString(data,"cost");
	       data = Methods.replaceString(data,"fppg");
	       data = Methods.replaceString(data,"role");
	        
	        
	        // create Player objects with name
	        for (int i4= 0 ;i4 < Occ;i4++){
	        	if (i4 < 10 ){
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5)); 
	        	System.out.println(Playerid);
	        	Playername = data.substring(data.indexOf("playe" + i4 +"r_name")+16,data.indexOf("player" + i4 +"_handle")-4);
	        	role = data.substring(data.indexOf("ro" + i4 +"le")+8,data.indexOf("ro" + i4 +"le")+9);
	        	}
	        	
	        	else{
	        	Playername = data.substring(data.indexOf("playe" + i4 +"r_name")+17,data.indexOf("player" + i4 +"_handle")-4);
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5));
	        	role = data.substring(data.indexOf("ro" + i4 +"le")+9,data.indexOf("ro" + i4 +"le")+10);
	        	}
            			
            			Playerarray[k] = new Player();
            			Playerarray[k].name = Playername;
            			Playerarray[k].id = Playerid;
            			Playerarray[k].team = new Team();
            			

        	        	System.out.println(role);
            			
            			if (Integer.parseInt(role) == 1){
            				Playerarray[k].role = "Toplaner";
            			}
            			if (Integer.parseInt(role) == 2){
            				Playerarray[k].role = "Jungler";
            			}
            			if (Integer.parseInt(role) == 3){
            				Playerarray[k].role = "Midlaner";
            			}
            			if (Integer.parseInt(role) == 4){
            				Playerarray[k].role = "ADC";
            			}
            			if (Integer.parseInt(role) == 5){
            				Playerarray[k].role = "Support";
            			}
            			if (Integer.parseInt(role) == 7){
            				Playerarray[k].role = "Team";
            			}
            			
            			k++;
            		
        	}
	        
	        // find Player costs
	        for (int i7 = Occ;i7 < Occ*2;i7++){
	        	if (i7 < 100 ){
	        	cost = data.substring(data.indexOf("co"+ i7 + "st")+9,data.indexOf("co"+ i7 + "st")+13);
	        	}
	        	else{
	        	cost = data.substring(data.indexOf("co"+ i7 + "st")+10,data.indexOf("co"+ i7 + "st")+14);
	        	}
            	Playerarray[i7-Occ].cost = Integer.parseInt(cost);
        	}
	        
	        for (int i4 = 0 ;i4 < Occ;i4++){

	        	System.out.println();
	        	System.out.println(Playerarray[i4].name);
	        	System.out.println(i4);
	        	System.out.println(Playerarray[i4].cost);
	        	System.out.println(Playerarray[i4].id);
	        	System.out.println(Playerarray[i4].role);
	        	
	        }
			return Playerarray;
	}
}