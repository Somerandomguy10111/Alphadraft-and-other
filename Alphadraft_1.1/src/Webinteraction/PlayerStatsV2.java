package Webinteraction;

import java.io.IOException;
import java.io.BufferedReader;

import classes.*;
import Dataprocessing.*;

public class PlayerStatsV2 {

	public static void main(String[] args) throws IOException, NumberFormatException {
		PlayerStats.getStats("http://lol.esportspedia.com/wiki/League_Championship_Series/North_America/2016_Season/Summer_Season/Match_History",null);
	}
		
	public static Player[] getStats(String site, Player[] previousSeason) throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub
        
		
		// declare variables
        String line;
        String data;
        int k = 0;
        boolean duplicate;
        Player[] Playerarray = new Player[100];
        Player[] Playersinmatch = new Player [10];
		String[] Namearray = new String [10];
		String[] Teamorder = new String[300];
        String zws;
        String Playername;
        int k2;
        int i0 = 0;
        int k5;
        int Matchanzahl = 0;
        
        // create default Player objects
        
       System.out.println(site); 
       BufferedReader br1 = Methods.establishConnection(site);
        
       // begin copypase
       while ((line = br1.readLine()) != null){
       	if (line.contains("<tr style=\"background-color")) {
       		int i = 0;
       		int j = 0;
       		
       			for (int sw = 1;sw <= 2;sw++){
       			switch (sw){
       		case 1:  j = 5;
       			 	break;
			case 2:  j = 7;
					 break;
	    		   }

	    	   while (i <= j ) {
	    		   line = br1.readLine();
	    		   i++;
					 if (i == j){
						 Teamorder[i0] = line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3);
						 i0++;
						 if (k < 10) {
							 
							 boolean useable = true;
					    	
							Playerarray[k] = new Player();
							Namearray[k] = new String();
							
						//  for lcs: establishes teams from k= 0 to k = 9
							
					    for (int k3 = 0;k3 < 10;k3++){
					    	if (line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3).equals(Namearray[k3]))
					    	{
					    		useable = false;
					    	}
					    }
					    	if (useable == true){
					    Playerarray[k].name = line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3);
					    Namearray[k] =  line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3);
						    	  System.out.println(Playerarray[k].name);
						    	  System.out.println(k);
						    	  k++;
					    	}
						 }
					 }
	    	   	}
       		}
       	}
    }
       
       k--;
       
       for (int i = 0;i < Teamorder.length;i++){
    	   System.out.println(Teamorder[i]);
    	   System.out.println(i);
       }
       
       //end copypaste
       String team1;
       String team2;
      
       br1 = Methods.establishConnection(site);
        while ((line = br1.readLine()) != null){
        	if (line.contains("http://matchhistory.na.leagueoflegends.com/en")) {

        		try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	    Matchanzahl++;
        		k2 = 0;
        	
        		System.out.println(site);
        		// get server and gamecodes
        		String servercode = line.substring(line.indexOf("/",line.indexOf("match-details"))+1,line.indexOf("/",line.indexOf("/",line.indexOf("match-details"))+1));
        		System.out.println(servercode);
        		String gamecode = line.substring(line.indexOf(servercode),line.indexOf("&amp"));
        		System.out.println(gamecode); 
        		
        		
        		

        		BufferedReader	br2 = Methods.establishConnection("https://acs.leagueoflegends.com/v1/stats/game/" + gamecode);

                try {
                    Thread.sleep((long) (0));
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                
            	data = br2.readLine();
            	
            	team1 = Teamorder[Matchanzahl*2-2];
            	System.out.println(team1);
            	team2 = Teamorder[Matchanzahl*2-1];
            	System.out.println(team2);
            	
            	String zws1 = data.substring(data.indexOf("\"win\""), data.lastIndexOf("200,\"win\""));
            	String zws2 = data.substring(data.indexOf("200,\"win\""), data.indexOf("participantId"));
            	
            	
            	//scrape player score and assign to winscore or losscore
            	for (int i = 0;i < Methods.getObjectcountP(Playerarray)/6;i++){
            	if (team1.equals(Playerarray[i].name) && zws1.substring(7,zws1.indexOf("firstBlood")-3).equals("Win")){
            		Playerarray[i].MatchNr++;
            		Playerarray[i].wonmatches++;
            		Playerarray[i].winscore[Playerarray[i].MatchNr] = Integer.parseInt(zws1.substring(zws1.indexOf("towerKills")+12, zws1.indexOf("inhibitorKills")-2))
            																+ Integer.parseInt(zws1.substring(zws1.indexOf("dragonKills")+13, zws1.indexOf("vilemawKills")-2))*2
            																+ Integer.parseInt(zws1.substring(zws1.indexOf("baronKills")+12, zws1.indexOf("dragonKills")-2))*3;
            		}
            	
            	if (team2.equals(Playerarray[i].name) && zws2.substring(zws2.indexOf("200,\"win\"")+11,zws2.indexOf("firstBlood")-3).equals("Win")){
            		Playerarray[i].MatchNr++;
            		Playerarray[i].wonmatches++;
        			Playerarray[i].winscore[Playerarray[i].MatchNr] = Integer.parseInt(zws1.substring(zws1.indexOf("towerKills")+12, zws1.indexOf("inhibitorKills")-2))
        																+ Integer.parseInt(zws1.substring(zws1.indexOf("dragonKills")+13, zws1.indexOf("vilemawKills")-2))*2
        																+ Integer.parseInt(zws1.substring(zws1.indexOf("baronKills")+12, zws1.indexOf("dragonKills")-2))*3;
            		}
            	
                if (team1.equals(Playerarray[i].name) && zws1.substring(7,zws1.indexOf("firstBlood")-3).equals("Fail")){
                	Playerarray[i].MatchNr++;
                	Playerarray[i].lostmatches++;
                	Playerarray[i].loosescore[Playerarray[i].MatchNr] = Integer.parseInt(zws1.substring(zws1.indexOf("towerKills")+12, zws1.indexOf("inhibitorKills")-2))
                																+ Integer.parseInt(zws1.substring(zws1.indexOf("dragonKills")+13, zws1.indexOf("vilemawKills")-2))*2
                																+ Integer.parseInt(zws1.substring(zws1.indexOf("baronKills")+12, zws1.indexOf("dragonKills")-2))*3;
                		}
                
                if (team2.equals(Playerarray[i].name) && zws2.substring(zws2.indexOf("200,\"win\"")+11,zws2.indexOf("firstBlood")-3).equals("Fail")){
                	Playerarray[i].MatchNr++;
                	Playerarray[i].lostmatches++;
            		Playerarray[i].loosescore[Playerarray[i].MatchNr] = Integer.parseInt(zws2.substring(zws2.indexOf("towerKills")+12, zws2.indexOf("inhibitorKills")-2))
            																+ Integer.parseInt(zws2.substring(zws2.indexOf("dragonKills")+13, zws2.indexOf("vilemawKills")-2))*2
            																+ Integer.parseInt(zws2.substring(zws2.indexOf("baronKills")+12, zws2.indexOf("dragonKills")-2))*3;
                		}
            	}
            	
            	
            	// replace keywords to make data navigateable
                data = Methods.replaceString(data, "participantId");
                data = Methods.replaceString(data, "profileIcon");
                	
                	// create Player objects, give them name
                	for (int i2 = 0;i2 < 10;i2++){
                		duplicate = false;
                		zws =  data.substring(data.indexOf("partic" + (31+i2) + "ipantId"),data.indexOf("profi" + i2 + "leIcon")+15);
                		Playername = zws.substring(zws.indexOf(" ")+1,zws.indexOf("profi" + i2 + "leIcon")-3);
                		System.out.println(Playername);
                		
                    	for (int i3 = 10; i3 < Methods.getObjectcountP(Playerarray);i3++){
                    		if (Playername.equalsIgnoreCase(Playerarray[i3].name)){
                    				duplicate = true;
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
                
              
             Playerarray = Methods.mergePlayerList(previousSeason, Playerarray);
                	
             for (int i4 = 0;i4 < Methods.getObjectcountP(Playerarray);i4++) {
                if (Methods.containsIgnoreCase(data,Playerarray[i4].name)){
                	Playerarray[i4].MatchNr++;
                	}
                }
              //work  
             // collect data from each game 2.zws
                for (int i2 = 1; i2 < 28;i2=i2+3){
                	k5 = (i2+2)/3;
                	for (int k3 = 0;k3 < Methods.getObjectcountP(Playerarray); k3++){
                		if (Playerarray[k3].name.equalsIgnoreCase(Playersinmatch[k5].name)){
                			k2 = k3;
                	}
                }
                	zws =  data.substring(data.indexOf("partic" + i2 +"ipantId"),data.indexOf("partic" + (i2+3) +"ipantId"));
                	System.out.println("Game Won:" + zws.substring(zws.indexOf("win")+5,zws.indexOf("item0")-2));
                	
                	
                	if (zws.substring(zws.indexOf("pantId")+16,zws.indexOf("item0")-2).equals("true")){
                		Playerarray[k2].wonmatches++;
                		Playerarray[k2].winkills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].windeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].winassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                		Playerarray[k2].wincreeps[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("neutralMinionsKilled\"")+22,zws.indexOf("neutralMinionsKilledTeamJungle\"")-2))
                				+ Integer.parseInt(zws.substring(zws.indexOf("totalMinionsKilled\":")+20,zws.indexOf("neutralMinionsKilled\"")-2));
                		Playerarray[k2].winscore[Playerarray[k2].MatchNr] = Playerarray[k2].winkills[Playerarray[k2].MatchNr]*3 - Playerarray[k2].windeaths[Playerarray[k2].MatchNr]
                				+ Playerarray[k2].winassists[Playerarray[k2].MatchNr]*2 + Playerarray[k2].wincreeps[Playerarray[k2].MatchNr] * 0.01 ;
                		
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Kills: " + Playerarray[k2].winkills[Playerarray[k2].MatchNr]);
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Deaths: " + Playerarray[k2].windeaths[Playerarray[k2].MatchNr]);
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Assists: " + Playerarray[k2].winassists[Playerarray[k2].MatchNr]);
                	}
                	else {
                		Playerarray[k2].lostmatches++;
                		Playerarray[k2].loosekills[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("kills")+7,zws.indexOf("deaths")-2));
                		Playerarray[k2].loosedeaths[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("deaths")+8,zws.indexOf("assists")-2));
                		Playerarray[k2].looseassists[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("assists")+9,zws.indexOf("largestKillingSpree")-2));
                		Playerarray[k2].loosecreeps[Playerarray[k2].MatchNr] = Integer.parseInt(zws.substring(zws.indexOf("neutralMinionsKilled\"")+22,zws.indexOf("neutralMinionsKilledTeamJungle\"")-2))
                				+ Integer.parseInt(zws.substring(zws.indexOf("totalMinionsKilled\":")+20,zws.indexOf("neutralMinionsKilled\"")-2));
                		Playerarray[k2].loosescore[Playerarray[k2].MatchNr] = Playerarray[k2].loosekills[Playerarray[k2].MatchNr]*3 - Playerarray[k2].loosedeaths[Playerarray[k2].MatchNr]
                				+ Playerarray[k2].looseassists[Playerarray[k2].MatchNr]*2 + Playerarray[k2].loosecreeps[Playerarray[k2].MatchNr] * 0.01 ;
                		
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Kills: " + Playerarray[k2].loosekills[Playerarray[k2].MatchNr]);
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Deaths: " + Playerarray[k2].loosedeaths[Playerarray[k2].MatchNr]);
                		System.out.println(Playerarray[k2].name + " MatchNr: " + Playerarray[k2].MatchNr + " Assists: " + Playerarray[k2].looseassists[Playerarray[k2].MatchNr]);
                	}
                }
        	}
        }
        
        for (int i6 = 0;i6 < k;i6++){
        	for (int i7 = 1; i7 < Playerarray[i6].winscore.length;i7++){
            	Playerarray[i6].winscoreavg += Playerarray[i6].winscore[i7];
        	}
        	Playerarray[i6].winscoreavg = Playerarray[i6].winscoreavg / Playerarray[i6].wonmatches;
        }
        
        for (int i6 = 0;i6 < k;i6++){
        	for (int i7 = 1; i7 <= Playerarray[i6].loosescore.length-1;i7++){
            	Playerarray[i6].loosescoreavg +=  Playerarray[i6].loosescore[i7];
        	}
        	if (Playerarray[i6].lostmatches != 0){
        	// dont divide trough 0
        	Playerarray[i6].loosescoreavg = Playerarray[i6].loosescoreavg / Playerarray[i6].lostmatches;
        	}
        	else {
        		// in they did never loose then improvise
        		Playerarray[i6].loosescoreavg = Playerarray[i6].winscoreavg/2;
        	}
        }
        
        // included for easier checking of procedure; avgscore is available online
        for (int i6 = 0;i6 < k;i6++){
        	for (int i7 = 0; i7 < Playerarray[i6].loosescore.length;i7++){
            	Playerarray[i6].avgscore += (double) Playerarray[i6].loosescore[i7] + (double) Playerarray[i6].winscore[i7];
        	}
        	
        	Playerarray[i6].avgscore = Playerarray[i6].avgscore / Playerarray[i6].MatchNr;
        	
        	System.out.println();
        	System.out.println("Name          : " + Playerarray[i6].name);
        	System.out.println("Number        : " + i6);
        	System.out.println("MatchNr       : " + Playerarray[i6].MatchNr);
        	System.out.println("Avgscore      : " + Playerarray[i6].avgscore);
        	System.out.println("Winscoreavgs  : " + Playerarray[i6].winscoreavg);
        	System.out.println("Loosescoreavg : " + Playerarray[i6].loosescoreavg);
        }
        return Playerarray;
	}
}