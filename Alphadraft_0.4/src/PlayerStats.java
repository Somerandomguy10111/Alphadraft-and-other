import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PlayerStats {

	
	static class Player{
		String name;
		String team;
		int[] Winkills = new int [30];
		int[] Windeaths = new int [30];
		int[] Winassists= new int [30];
		int[] Loosekills = new int [30];
		int[] Loosedeaths = new int [30];
		int[] Looseassists = new int [30];
		int MatchNr = 0;
		int ReihnfolgeNr;
		double Winscoreavg;
		double Loosescoreavg;
		int Place;
	}
	

	
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		

		
     /* String asdf = "asdfa";
		asdf = asdf.replaceFirst("a","amk");
		System.out.println(asdf);
	 */	

		URL url1 = new URL("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History");
		URLConnection con1 = url1.openConnection();
        con1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con1.connect();
        InputStream is1 =con1.getInputStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
        
        String line;
        String gamecode;
        String data;
        int i = 0;
        int k = 0;
        boolean duplicate;
        Player[] Playerarray = new Player[60];
        Player[] Playersinmatch = new Player [11];
        String zws;
        String Playername;
        int k2;
        int k5;
        
        //create default Player objects

       
        while ((line = br1.readLine()) != null){
        	if (line.contains("http://matchhistory.na.leagueoflegends.com/en")) {
        		i = 0;
        		k2 = 0;
        	
        		gamecode = line.substring(line.indexOf("TRLH3"),line.indexOf(">Link</a>")-1);
        		//reset place of all players (or don't)
        		System.out.println(gamecode); 
        		URL url2 = new URL ("https://acs.leagueoflegends.com/v1/stats/game/" + gamecode);
        		URLConnection con2 = url2.openConnection();
                con2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                con2.connect();
                InputStream is2 =con2.getInputStream();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                
                
            	data = br2.readLine();
            	//manipulate br input to make navigateable
                	while (data.contains("participantId")){
                		i++;
                		data = data.replaceFirst("participantId", "participant" + i + "Id");
//                		System.out.println("participant" + i + "Id");
                	}
                	i = 0;
                	while (data.contains("profileIcon")){
                		i++;
                		data = data.replaceFirst("profileIcon", "profile" + i + "Icon");
//                		System.out.println("profile" + i + "Icon");
                	}
                	//create Player objects, give them name
                	for (int i2= 1;i2 <= 10;i2++){
                		duplicate = false;
                		zws =  data.substring(data.indexOf("participant" + (31+i2) + "Id"),data.indexOf("profile" + i2 + "Icon")+20);
                		Playername = zws.substring(zws.indexOf("player")+25,zws.indexOf("profile" + i2 + "Icon")-3);
                		System.out.println(Playername);
                		
                	if (k != 0){
                    	for (int i3 = 1; i3 <= k;i3++){
                    		if (Playername.equals(Playerarray[i3].name)){
                    				duplicate = true;
                    				
                    		}
                    	}
                	}
                    		if (!duplicate){
                    			k++;
                    			Playerarray[k] = new Player();
                    			Playerarray[k].name = Playername;
                    		}
                    	Playersinmatch[i2] = new Player();
                    	Playersinmatch[i2].name = Playername;
                	}
                	
             for (int i4 = 1;i4 <= k;i4++) {
                if (data.contains(Playerarray[i4].name)){
                	Playerarray[i4].MatchNr++;
                	}
                }
                	//collect data from each game 2.zws
                for (int i2 = 2; i2 <= 29;i2=i2+3){
                	k5 = (i2+1)/3;
                	for (int k3 = 1;k3 <= k ; k3++){
                		if (Playerarray[k3].name.equals(Playersinmatch[k5].name)){
                			k2 = k3;
                	}
                }
                	zws =  data.substring(data.indexOf("participant" + i2 +"Id"),data.indexOf("participant" + (i2+3) +"Id"));
                	System.out.println("Game Won:" + zws.substring(zws.indexOf("win")+5,zws.indexOf("item0")-2));
//                	System.out.println(i2);
                	
                	if (zws.substring(zws.indexOf("Id")+12,zws.indexOf("item0")-2).equals("true")){
                		System.out.println("Winkills: " + zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		System.out.println(k2);
                		Playerarray[k2].Winkills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].Windeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].Winassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                	}
                	else {
                		System.out.println("Loosekills: " + zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		System.out.println(k2);
                		System.out.println("MatchNr: " + Playerarray[k2].MatchNr);
                		Playerarray[k2].Loosekills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].Loosedeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].Looseassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                	}
                	
                }
        	}
        }
        
        for (int i2= 1;i2 <= 50;i2++) {
        	System.out.println(Playerarray[i2].name);
        	System.out.println(Playerarray[i2].MatchNr);
        	System.out.println(Playerarray[i2].Loosekills[2]);
        	System.out.println(Playerarray[i2].Loosedeaths[2]);
        	System.out.println(Playerarray[i2].Looseassists[2]);
        	
        }
	}
}