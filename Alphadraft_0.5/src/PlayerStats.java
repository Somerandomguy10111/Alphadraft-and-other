import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PlayerStats {

	static class Player{
		String name;
		String teamname;
		String role;
    	double cost = (double) Math.random()*10000;
    	double expectedpoints =((double)(Math.random() * 50));
    	
		double winscoreavg;
		double loosescoreavg;
		double avgscore;
		int[] winkills = new int [200];
		int[] windeaths = new int [200];
		int[] winassists= new int [200];
		int[] winscore = new int [200];
		int[] loosekills = new int [200];
		int[] loosedeaths = new int [200];
		int[] looseassists = new int [200];
		int[] loosescore = new int [200];
		int wonmatches = 0;
		int lostmatches = 0;
		int MatchNr = 0;
	}
	

	public static void main(String[] args) throws IOException, NumberFormatException {
	//  TODO Auto-generated method stub
		PlayerStats.getStats("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History");
	}
	
	public static boolean containsIgnoreCase(String src, String what) {
	    final int length = what.length();
	    if (length == 0)
	        return true; // Empty string is contained

	    final char firstLo = Character.toLowerCase(what.charAt(0));
	    final char firstUp = Character.toUpperCase(what.charAt(0));

	    for (int i = src.length() - length; i >= 0; i--) {
	        // Quick check before calling the more expensive regionMatches() method:
	        final char ch = src.charAt(i);
	        if (ch != firstLo && ch != firstUp)
	            continue;

	        if (src.regionMatches(true, i, what, 0, length))
	            return true;
	    }

	    return false;
	}
	
	public static Player[] getStats(String site) throws IOException, NumberFormatException {

		URL url1 = new URL(site);
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
        Player[] Playerarray = new Player[100];
        Player[] Playersinmatch = new Player [11];
        String zws;
        String Playername;
        int k2;
        int k5;
        
        // create default Player objects

       
        while ((line = br1.readLine()) != null){
        	if (line.contains("http://matchhistory.na.leagueoflegends.com/en")) {
        		i = 0;
        		k2 = 0;
        	
        		gamecode = line.substring(line.indexOf("TRLH3"),line.indexOf(">Link</a>")-1);
        		System.out.println(gamecode); 
        		URL url2 = new URL ("https://acs.leagueoflegends.com/v1/stats/game/" + gamecode);
        		URLConnection con2 = url2.openConnection();
                con2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                con2.connect();
                InputStream is2 =con2.getInputStream();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
               
            	data = br2.readLine();
            	
            	// replace keywords to make data navigateable
                	while (data.contains("participantId")){
                		i++;
                		data = data.replaceFirst("participantId", "participant" + i + "Id");
                	}
                	
                	i = 0;
                	while (data.contains("profileIcon")){
                		i++;
                		data = data.replaceFirst("profileIcon", "profile" + i + "Icon");
                	}
                	
                	// create Player objects, give them name
                	for (int i2= 1;i2 <= 10;i2++){
                		duplicate = false;
                		zws =  data.substring(data.indexOf("participant" + (31+i2) + "Id"),data.indexOf("profile" + i2 + "Icon")+20);
                		Playername = zws.substring(zws.indexOf(" ")+1,zws.indexOf("profile" + i2 + "Icon")-3);
                		System.out.println(Playername);
                		
                	if (k != 0){
                    	for (int i3 = 1; i3 <= k;i3++){
                    		if (Playername.equalsIgnoreCase(Playerarray[i3].name)){
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
                if (containsIgnoreCase(data,Playerarray[i4].name)){
                	Playerarray[i4].MatchNr++;
                	}
                }
                	// collect data from each game 2.zws
                for (int i2 = 2; i2 <= 29;i2=i2+3){
                	k5 = (i2+1)/3;
                	for (int k3 = 1;k3 <= k ; k3++){
                		if (Playerarray[k3].name.equalsIgnoreCase(Playersinmatch[k5].name)){
                			k2 = k3;
                	}
                }
                	zws =  data.substring(data.indexOf("participant" + i2 +"Id"),data.indexOf("participant" + (i2+3) +"Id"));
                	System.out.println("Game Won:" + zws.substring(zws.indexOf("win")+5,zws.indexOf("item0")-2));
                	
                	if (zws.substring(zws.indexOf("Id")+12,zws.indexOf("item0")-2).equals("true")){
//                		System.out.println("winkills: " + zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
//                		System.out.println(k2);
                		Playerarray[k2].wonmatches++;
                		Playerarray[k2].winkills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].windeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].winassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                		Playerarray[k2].winscore[Playerarray[k2].MatchNr] = Playerarray[k2].winkills[Playerarray[k2].MatchNr]*2 - Playerarray[k2].windeaths[Playerarray[k2].MatchNr] + Playerarray[k2].winassists[Playerarray[k2].MatchNr];
                	}
                	else {
//                		System.out.println("loosekills: " + zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
//                		System.out.println(k2);
//                		System.out.println("MatchNr: " + Playerarray[k2].MatchNr);
                		Playerarray[k2].lostmatches++;
                		Playerarray[k2].loosekills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].loosedeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].looseassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                		Playerarray[k2].loosescore[Playerarray[k2].MatchNr] = Playerarray[k2].loosekills[Playerarray[k2].MatchNr]*2 - Playerarray[k2].loosedeaths[Playerarray[k2].MatchNr] + Playerarray[k2].looseassists[Playerarray[k2].MatchNr];
                	}
                }
        	}
        }
        
        for (int i6 = 1;i6 <= k;i6++){
        	for (int i7 = 1; i7 <= Playerarray[i6].winscore.length-1;i7++){
            	Playerarray[i6].winscoreavg += (double) Playerarray[i6].winscore[i7];
        	}
        	Playerarray[i6].winscoreavg = Playerarray[i6].winscoreavg / Playerarray[i6].wonmatches;
        }
        
        for (int i6 = 1;i6 <= k;i6++){
        	for (int i7 = 1; i7 <= Playerarray[i6].loosescore.length-1;i7++){
            	Playerarray[i6].loosescoreavg += (double) Playerarray[i6].loosescore[i7];
        	}
        	Playerarray[i6].loosescoreavg = Playerarray[i6].loosescoreavg / Playerarray[i6].lostmatches;
        }
        
        // included for easier checking of procedure; avgscore is available online
        // longest time needed here for finding out how to write easier and available
        for (int i6 = 1;i6 <= k;i6++){
        	for (int i7 = 1; i7 <= Playerarray[i6].loosescore.length-1;i7++){
            	Playerarray[i6].avgscore += (double) Playerarray[i6].loosescore[i7] + (double) Playerarray[i6].winscore[i7];
        	}
        	Playerarray[i6].avgscore = Playerarray[i6].avgscore / Playerarray[i6].MatchNr;
        	System.out.println(Playerarray[i6].name);
        	System.out.println(Playerarray[i6].MatchNr);
        	System.out.println(Playerarray[i6].avgscore);
        }
        return Playerarray;
	}
}