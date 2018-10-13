import java.io.IOException;

import classes.*;



public class PTAssignment {

	

		public static void main (String[] args) throws IOException, NumberFormatException {
			String sessionkey = Methods.getAlphadraftsessionkey();
			Player[] Playerarray = AlphadraftData.getAlphadraftData(sessionkey);
			PTAssignment.getPTAssignment(Playerarray,sessionkey);
			Methods.logout(0.005);
		}
		
		public static Player[] getPTAssignment (Player[] PlayerData,String sessionkey) throws IOException, NumberFormatException {
			//  TODO Auto-generated method stub

			// declare variables
		        Player[] Playerarray = PlayerData;

		System.out.println("scraping player teams");
			    
		for (int i2 = 1;i2 <= 60;i2++){
			    
			    // for loop starts here
			    // send a get request to contest site with sessionkey from Post request
			    String scrapeurl = "https://alphadraft.com/api/ui/player/getplayercarddetails/";
			    String payload = "{\"player_id\" : " + Playerarray[i2].id + ", \"group_id\" : 1064}";
	        
		        String data = Methods.AlphadraftPostRequest(scrapeurl, sessionkey, payload);

		        
		        // create Player objects with name
	            			String Playername =	Playerarray[i2].name;
	            			Playerarray[i2].teamname = data.substring(data.lastIndexOf("\"team_name\"")+14,data.lastIndexOf(Playername)-12);

			}
		
		for (int i6= 1;i6 <= 60;i6++){
        	System.out.println(i6);
        	System.out.println(Playerarray[i6].name);
        	System.out.println(Playerarray[i6].teamname);
        	System.out.println(Playerarray[i6].cost);
        	}
		
				return Playerarray;
		}
	}