import java.io.IOException;
import classes.*;


public class AlphadraftData {

	
	public static void main (String[] args) throws IOException, NumberFormatException {
		String sessionkey = Methods.getAlphadraftsessionkey();
		AlphadraftData.getAlphadraftData(sessionkey);
	}
	
	
	public static Player[] getAlphadraftData (String sessionkey) throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub

		// declare variables
		    Player[] Playerarray = new Player[61];
	        String Playername;
	        int Playerid;
	        int k = 0;
	        String cost;
	       
	        
	       String data = Methods.AlphadraftPostRequest("https://alphadraft.com/api/ui/player/getplayersforbatchcall_mvp/",sessionkey,"{\"contest_group\":1064}");
	        
	        // replace keywords to navigate data
	       data = Methods.replaceString(data,"player_name");
	       data = Methods.replaceString(data,"player_handle");
	       data = Methods.replaceString(data,"cost");
	       data = Methods.replaceString(data,"fppg");
	        
	        
	        // create Player objects with name
	        for (int i4= 1 ;i4 <= 60;i4++){
	        	if (i4 < 10 ){
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5)); 
	        	System.out.println(Playerid);
	        	Playername = data.substring(data.indexOf("playe" + i4 +"r_name")+16,data.indexOf("player" + i4 +"_handle")-4);
	        	}
	        	
	        	else{
	        	Playername = data.substring(data.indexOf("playe" + i4 +"r_name")+17,data.indexOf("player" + i4 +"_handle")-4);
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5));
	        	System.out.println(Playerid);
	        	}
            			k++;
            			Playerarray[k] = new Player();
            			Playerarray[k].name = Playername;
            			Playerarray[k].id = Playerid;
            		
        	}
	        
	        // find Player costs
	        for (int i7= 61;i7 <= 120;i7++){
	        	if (i7 < 100 ){
	        	cost = data.substring(data.indexOf("co"+ i7 + "st")+9,data.indexOf("co"+ i7 + "st")+13);
	        	}
	        	else{
	        	cost = data.substring(data.indexOf("co"+ i7 + "st")+10,data.indexOf("co"+ i7 + "st")+14);
	        	}
            	Playerarray[i7-60].cost = Integer.parseInt(cost);
        	}
	        
			return Playerarray;
	        
	}
}