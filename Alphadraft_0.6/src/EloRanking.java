import java.io.IOException;
import java.io.BufferedReader;
import classes.*;


public class EloRanking {

	public static void main (String[] args) throws IOException, NumberFormatException {
		Team[] firstSeason =  EloRanking.createEloRanking("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History",null);
		EloRanking.createEloRanking("http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History",firstSeason);
		
	}
	

	public static Team [] createEloRanking(String site,Team[] PreviousSeason) throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub
		
		
		
		
		//  initialize variables and lists
		Team[] teamarray = new Team [10];
		String[] Namearray = new String [10];
		int Matchanzahl = 0;
		int k = 0;
		String line;
		
		//  log onto matchhistory website
        BufferedReader br = Methods.establishConnection(site);
        
        //  pick out lines containing match details
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
						 if (k < 10) {
							 
							 boolean useable = true;
							 
							teamarray[k] = new Team();
							Namearray[k] = new String();
							
						//  for lcs: establishes teams from k= 0 to k = 9
							
					    for (int k2 = 0;k2 < 10;k2++){
					    	if (line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3).equals(Namearray[k2]))
					    	{
					    		useable = false;
					    	}
					    }
					    	if (useable == true){
					    teamarray[k].name = line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3);
					    Namearray[k] =  line.substring(line.indexOf("title=\"")+7,line.indexOf("img")-3);
						    	  System.out.println(teamarray[k].name);
						    	  System.out.println(k);
						    	  k++;
					    		}
						 	}
					 	}
	    	   		}
        		}
        	}
        }
        
     if (PreviousSeason != null){
        for (int i = 0;i < teamarray.length;i++){
        	for (int i2 = 0;i2 < teamarray.length;i2++){
        		if (PreviousSeason[i2].name.equalsIgnoreCase(teamarray[i].name)){
        			teamarray[i].Elo = PreviousSeason[i2].Elo;
        			}
        		}
        	}
        }
     
     for (int i = 0;i < teamarray.length;i++){
    	 System.out.println(teamarray[i].name + ": " + teamarray[i].Elo);
     }
        
        
        BufferedReader br1 = Methods.establishConnection(site);
        
        String line1;
        System.out.println("Matchanzahl ist " + Matchanzahl);
        
        while ((line1 = br1.readLine()) != null) {
        	if (line1.contains("<tr style=\"background-color")){

        		String Colour = null;
        		
        		Colour = (line1.substring(line1.length()-8, line1.length()-2));
        		
        		//  reads Colour red or blue
        			if (Colour.equals("9bd6ff"))
        			{
        				Colour = "Blue";
        			}
        			else
        			{
        				Colour = "Red";
        			}
        		
        		 Team Looser = new Team();
	    		 Team Winner = new Team();
	    		 
	    		 
        		int j = 0;
        		int i = 0;
        		
        		for (int sw = 1;sw <= 2;sw++){
     	    	   switch (sw){
        			
        			case 1:  j = 5;
        				     break;
        				     
        			case 2:  j = 7;
        					 break;
        	    		   }

     	    	   while (i < j ) {
     	    		   line1 = br1.readLine();
     	    		   i++;
     	    		   
     	    		  		if (i == j){
     	    			 
     	    		  			if (Colour.equals("Blue") && i == 5){
     	    		  				for (int k2 = 0;k2 < k;k2++){
     	    		  					if (line1.substring(line1.indexOf("title=\"")+7,line1.indexOf("img")-3).equals(teamarray[k2].name)){
     	    		  						Winner = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Red") && i == 5){
     	    		  				for (int k2 = 0;k2 < k;k2++){
     	    		  					if (line1.substring(line1.indexOf("title=\"")+7,line1.indexOf("img")-3).equals(teamarray[k2].name)){
     	    		  						Looser =  teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Blue") && i == 7){
     	    		  				for (int k2 = 0;k2 < k;k2++){
     	    		  					if (line1.substring(line1.indexOf("title=\"")+7,line1.indexOf("img")-3).equals(teamarray[k2].name)){
     	    		  						Looser = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (Colour.equals("Red") && i == 7){
     	    		  				for (int k2 = 0;k2 < k;k2++){
     	    		  					if (line1.substring(line1.indexOf("title=\"")+7,line1.indexOf("img")-3).equals(teamarray[k2].name)){
     	    		  						Winner = teamarray[k2];
     	    		  					}
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			if (i == 7){
     	    		  			
     	    		  			// Theory of ELO
     	    		  			//  Winning prediction: Expectedscore= 1/(1+10^((Ra-Rb)/400)
     	    		  			//  Score Update:       Rnew = Rold + k*(Score - Expectedscore)
     	    		  			
     	    		  			Winner.PofW =  (1/(1 + Math.pow(10,(Looser.Elo-Winner.Elo)/400)));
     	    		  			Looser.PofL =  1 - Winner.PofW;
     	    		  			
     	    		  			System.out.println(Winner.name + ": "  + Winner.PofW);
     	    		  			System.out.println(Winner.Elo);
     	    		  			System.out.println(Looser.name +": " + Looser.PofL);
     	    		  			System.out.println(Looser.Elo);
     	    		  			System.out.println("");
     	    		  			
     	    		  			
     	    		  			Winner.Elo = Winner.Elo + 50*(1 - Winner.PofW);
     	    		  			Looser.Elo = Looser.Elo + 50*(0 - Looser.PofW);
     	    		  			
     	    		  			for (int k3 = 0;k3 < k;k3++){
     	    		  				if (Winner.name.equals(teamarray[k3].name)){
     	    		  					teamarray[k3].Elo = Winner.Elo;
     	    		  				}
     	    		  			}
     	    		  			
     	    		  			for (int k2 = 0;k2 < k;k2++){
     	    		  				if (Looser.name.equals(teamarray[k2].name)){
     	    		  					teamarray[k2].Elo = Looser.Elo;
     	    		  				}
     	    		  			}
     	    		  		}
     	    	   		} 
        			}		    	   
        		} 
        	}
        }
        	for (int k2 = 0;k2 < k;k2++){
        		System.out.println(teamarray[k2].name + ": " + teamarray[k2].Elo );
        		System.out.println();
          	} 
        	return teamarray;
	}
}