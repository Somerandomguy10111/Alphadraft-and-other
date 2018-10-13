import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class EloSystem {
	
	static class Team {
		double Teamelo = 1000;
		String teamname = "Team";
	}


	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		
		Team[] teamarray = new Team[11];
		String[] Namearray = new String[11];
		int Matchanzahl = 0;
		String line = null;
		
		URL url = new URL("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History");
		URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        
        int k = 0;
        while ((line = br.readLine()) != null){
        	if (line.contains("<tr style=\"background-color")) {
        		Matchanzahl++;
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
	    		   line = br.readLine();
	    		   i++;
					 if (i == j){
						 if (k <= 10) {
							 
							 boolean useable = true;
							 
							teamarray[k] = new Team();
							Namearray[k] = new String();
						
					    for (int k2 = 0;k2 <= 10;k2++){
					    	if (line.substring(line.indexOf("wiki")+5,line.indexOf("title")-2).equals(Namearray[k2]))
					    	{
					    		useable = false;
					    	}
					    }
					    	if (useable == true){
					    teamarray[k].teamname = line.substring(line.indexOf("wiki")+5,line.indexOf("title")-2);
					    Namearray[k] =  line.substring(line.indexOf("wiki")+5,line.indexOf("title")-2);
						    	  System.out.println(teamarray[k].teamname);
						    	  k++;
						    	  System.out.println(k);
						    	  //establishes teams from k= 1 to k = 10
					    		}
						 	}
					 	}
	    	   		}
        		}
        	}
        }
        
        URL url1 = new URL("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History");
		URLConnection con1 = url1.openConnection();
        con1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con1.connect();
        InputStream is1 =con1.getInputStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
        
        String line1;
        System.out.println("Matchanzahl ist " + Matchanzahl);
        
        while ((line1 = br1.readLine()) != null) {
        	if (line1.contains("<tr style=\"background-color")){

        		String Colour = null;
        		
        		Colour = (line1.substring(line1.length()-8, line1.length()-2));
        		//System.out.println(Colour);
        			if (Colour.equals("9bd6ff"))
        			{
        				//System.out.println("Blue");
        				Colour = "Blue";
        				//reads Colour red or blue
        			}
        			else
        			{
        				Colour = "Red";
        				//System.out.println("Red");
        			}
        		
        		 Team Looser = new Team();
	    		 Team Winner = new Team();
	    		 
	    		 
        		int j = 0;
        		int i = 0;
        		
        		for (int sw = 1;sw <= 2;sw++){
     	    	   switch (sw){
        			
        			case 1:  j = 5;
        					 k++;
        				     break;
        				     
        			case 2:  j = 7;
        					 k++;
        					 break;
        	    		   }

     	    	   while (i < j ) {
     	    		   line1 = br1.readLine();
     	    		   i++;
     	    		   
     	    		  		if (i == j){
     	    			 
     	    		  			if (Colour.equals("Blue") && i == 5){
     	    		  				for (int k2 = 0;k2 <= 10;k2++){
     	    		  					if (line1.substring(line1.indexOf("wiki")+5,line1.indexOf("title")-2).equals(teamarray[k2].teamname)){
     	    		  						Winner = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Red") && i == 5){
     	    		  				for (int k2 = 0;k2 <= 10;k2++){
     	    		  					if (line1.substring(line1.indexOf("wiki")+5,line1.indexOf("title")-2).equals(teamarray[k2].teamname)){
     	    		  						Looser =  teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Blue") && i == 7){
     	    		  				for (int k2 = 0;k2 <= 10;k2++){
     	    		  					if (line1.substring(line1.indexOf("wiki")+5,line1.indexOf("title")-2).equals(teamarray[k2].teamname)){
     	    		  						Looser = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Red") && i == 7){
     	    		  				for (int k2 = 0;k2 <= 10;k2++){
     	    		  					if (line1.substring(line1.indexOf("wiki")+5,line1.indexOf("title")-2).equals(teamarray[k2].teamname)){
     	    		  						Winner = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			//Theory of ELO
     	    		  			// Winning prediction: Expectedscore= 1/(1+10^((Ra-RB)/400)
     	    		  			// Score Update:       Rnew = Rold + k*(Score - Expectedscore)
     	    		  			
     	    		  			double Winnerteameloprecalc = Winner.Teamelo;
     	    		  			Winner.Teamelo = Winner.Teamelo + 50*(1 -(1/(1+  Math.pow(10,(Winner.Teamelo-Looser.Teamelo)/400))));
     	    		  			Looser.Teamelo = Looser.Teamelo + 24*(0 -(1/(1+  Math.pow(10,(Looser.Teamelo-Winnerteameloprecalc)/400))));
     	    		  			//System.out.println(Winner.Teamelo);
     	    		  			//System.out.println(Looser.Teamelo);
     	    		  			
     	    		  			for (int k3 = 0;k3 < 10;k3++){
     	    		  				if (Winner.teamname == teamarray[k3].teamname){
     	    		  					teamarray[k3].Teamelo = Winner.Teamelo;
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			for (int k2 = 0;k2 < 10;k2++){
     	    		  				if (Looser.teamname == teamarray[k2].teamname){
     	    		  					teamarray[k2].Teamelo = Looser.Teamelo;
     	    		  				}
     	    		  			}
     	    		  		}
     	    	   		} 
        			}		    	   
        		} 
        	}
        	for (int k2 = 0;k2 < 10;k2++){
        		System.out.println(teamarray[k2].teamname);
        		System.out.println(teamarray[k2].Teamelo);
  		} 
	}
}