import java.io.IOException;
import java.io.BufferedReader;
import classes.*;

public class PlayerStats {

	public static void main(String[] args) throws IOException, NumberFormatException {
		PlayerStats.getStats("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History",null);
	}
		
	public static Player[] getStats(String site,Player[] PreviousSeason) throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub
        
		
		// declare variables
        String line;
        String data;
        int k = 0;
        boolean duplicate;
        Player[] Playerarray = new Player[100];
        Player[] Playersinmatch = new Player [11];
        String zws;
        String Playername;
        int k2;
        int k5;
        
        // create default Player objects
        
       BufferedReader br1 = Methods.establishConnection(site);
        
        while ((line = br1.readLine()) != null){
        	if (line.contains("http://matchhistory.na.leagueoflegends.com/en")) {
        		k2 = 0;
        	
        		String gamecode = line.substring(line.indexOf("TRLH3"),line.indexOf(">Link</a>")-1);
        		System.out.println(gamecode); 
                BufferedReader br2 = Methods.establishConnection("https://acs.leagueoflegends.com/v1/stats/game/" + gamecode);
                
            	data = br2.readLine();
            	
            	// replace keywords to make data navigateable
                data = Methods.replaceString(data, "participantId");
                data = Methods.replaceString(data, "profileIcon");
                	
                	// create Player objects, give them name
                	for (int i2= 1;i2 <= 10;i2++){
                		duplicate = false;
                		zws =  data.substring(data.indexOf("partic" + (31+i2) + "ipantId"),data.indexOf("profi" + i2 + "leIcon")+20);
                		Playername = zws.substring(zws.indexOf("player")+25,zws.indexOf("profi" + i2 + "leIcon")-3);
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
                if (Methods.containsIgnoreCase(data,Playerarray[i4].name)){
                	Playerarray[i4].MatchNr++;
                	}
                }
                	// collect data from each game 2.zws
                for (int i2 = 2; i2 <= 29;i2=i2+3){
                	k5 = (i2+1)/3;
                	for (int k3 = 1;k3 <= k ; k3++){
                		if (Playerarray[k3].name.equals(Playersinmatch[k5].name)){
                			k2 = k3;
                	}
                }
                	zws =  data.substring(data.indexOf("partic" + i2 +"ipantId"),data.indexOf("partic" + (i2+3) +"ipantId"));
                	System.out.println("Game Won:" + zws.substring(zws.indexOf("win")+5,zws.indexOf("item0")-2));
                	
                	if (zws.substring(zws.indexOf("pantId")+12,zws.indexOf("item0")-2).equals("true")){
                		Playerarray[k2].wonmatches++;
                		Playerarray[k2].winkills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].windeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].winassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                		Playerarray[k2].winscore[Playerarray[k2].MatchNr] = Playerarray[k2].winkills[Playerarray[k2].MatchNr]*2 - Playerarray[k2].windeaths[Playerarray[k2].MatchNr] + Playerarray[k2].winassists[Playerarray[k2].MatchNr];
                	}
                	else {
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
        // longest time needed here for finding out how to write "easier" and "available"
        for (int i6 = 1;i6 <= k;i6++){
        	for (int i7 = 1; i7 <= Playerarray[i6].loosescore.length-1;i7++){
            	Playerarray[i6].avgscore += (double) Playerarray[i6].loosescore[i7] + (double) Playerarray[i6].winscore[i7];
        	}
        	Playerarray[i6].avgscore = Playerarray[i6].avgscore / Playerarray[i6].MatchNr;
        	System.out.println(Playerarray[i6].MatchNr);
        	System.out.println(Playerarray[i6].name);
        	System.out.println(Playerarray[i6].avgscore);
        }
        
        return Playerarray;
	}
}