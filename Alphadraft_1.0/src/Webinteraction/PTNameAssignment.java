package Webinteraction;

import java.io.IOException;
import Dataprocessing.*;
import classes.*;



public class PTNameAssignment {

	

		public static void main (String[] args) throws IOException, NumberFormatException {
//			String sessionkey = Methods.getAlphadraftsessionkey();
			String sessionkey = "sessionid=ouvz5lnifyh62od982kv4l9fjjzqesnx; expires=Mon";
			Player[] Playerarray = AlphadraftData.getAlphadraftData(sessionkey,1091);
			Methods.getObjectcountP(Playerarray);
			PTNameAssignment.getPTNameAssignment(Playerarray,sessionkey,96279);
		}
		
		public static Player[] getPTNameAssignment (Player[] PlayerData,String sessionkey,int contestgroupid) throws IOException, NumberFormatException {
			//  TODO Auto-generated method stub

			// declare variables
		        Player[] Playerarray = PlayerData;

		System.out.println("scraping player teams");
			    
		System.out.println(Methods.getObjectcountP(PlayerData));
		
		for (int i2 = 0;i2 < Methods.getObjectcountP(PlayerData);i2++){
			    
			    // for loop starts here
			    // send a get request to contest site with sessionkey from Post request
			    String scrapeurl = "https://alphadraft.com/api/ui/player/getplayercarddetails/";
			    String payload = "{\"player_id\" : " + Playerarray[i2].id + " , \"group_id\" : " + contestgroupid + "}";
	        
		        String data = Methods.AlphadraftPostRequest(scrapeurl, sessionkey, payload);
		        
		        // create Player objects with name
	            String Playername =	Playerarray[i2].name;
	            Playerarray[i2].team.name = data.substring(data.lastIndexOf("\"team_name\"")+14,data.lastIndexOf(Playername)-12);

			}
		
		for (int i6= 0;i6 < Methods.getObjectcountP(PlayerData);i6++){
        	System.out.println();
        	System.out.println(Playerarray[i6].name);
        	System.out.println(Playerarray[i6].team.name);
        	System.out.println(Playerarray[i6].cost);
        	System.out.println(Playerarray[i6].role);
        	System.out.println(i6);
        	}
				return Playerarray;
		}
	}