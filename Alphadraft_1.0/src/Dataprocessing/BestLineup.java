package Dataprocessing;

import classes.*;


public class BestLineup {

	    
	    public static void main (String[] args) {
	    BestLineup.getBestLineup(null);
	    }
	    
		
     public static Lineup getBestLineup (Player[] Playerdata) {
    	 
    	 
    	 // in Alphadraft context a team is a player and is treated as such
    	 int Playeranzahl = Methods.getObjectcountP(Playerdata);
    	 int Teamanzahl = Playeranzahl/6;
    	 Player[] PlayerArray = Playerdata; 
    	 Player[] ToplanerArray = new Player[Playeranzahl/5];
    	 Player[] JunglerArray = new Player[Playeranzahl/5];
    	 Player[] MidlanerArray = new Player[Playeranzahl/5];
    	 Player[] SupportArray = new Player[Playeranzahl/5];
    	 Player[] ADCArray = new Player[Playeranzahl/5];
    	 Player[] TeamArray	 = new Player[Teamanzahl];
    	 
    	int k2 = 0;
     	int k3 = 0;
     	int k4 = 0;
     	int k5 = 0;
     	int k6 = 0;	
     	int k7 = 0;
     	
     	 for (int i = 0;i < Playeranzahl;i++){
     		 if (PlayerArray[i].role.equals("Toplaner")){
     			 ToplanerArray[k2] = PlayerArray[i];
     			k2++;
     		 }
     		if (PlayerArray[i].role.equals("Jungler")){
    			JunglerArray[k3] = PlayerArray[i];
    			k3++;
    		 }
     		if (PlayerArray[i].role.equals("Midlaner")){
    			 MidlanerArray[k4] = PlayerArray[i];
    			k4++;
    		 }
     		if (PlayerArray[i].role.equals("ADC")){
    			 ADCArray[k5] = PlayerArray[i];
    			k5++;
    		 }
     		if (PlayerArray[i].role.equals("Support")){
    			 SupportArray[k6] = PlayerArray[i];
    			k6++;
    		 }
     		if (PlayerArray[i].role.equals("Team")){
    			TeamArray[k7] = PlayerArray[i];
    			k7++;
    		 }
     	 }
     	  
     	  for (int i4 = 0 ;i4 < Methods.getObjectcountP(PlayerArray);i4++){

	        	System.out.println();
	        	System.out.println("Name          : " + PlayerArray[i4].name);
	        	System.out.println("Number        : " + i4);
	        	System.out.println("Cost          : " + PlayerArray[i4].cost);
	        	System.out.println("Expectedpoints: " + PlayerArray[i4].expectedpoints);
	        	System.out.println("Winscoreavgs  : " + PlayerArray[i4].winscoreavg);
	        	System.out.println("Loosescoreavg : " + PlayerArray[i4].loosescoreavg);
	        	System.out.println("Role          : " + PlayerArray[i4].role);
	        	System.out.println("Teamname      : " + PlayerArray[i4].team.name);
	        	System.out.println("P of Winning  : " + PlayerArray[i4].team.PofW);
	        	
	        	if (Double.isInfinite(PlayerArray[i4].expectedpoints)){
	        		PlayerArray[i4].expectedpoints = 0;
	        	}
	        	
	        }

    	 // finding the best team starts here:
    	 
    	 Lineup bestLineup = new Lineup();
    	 Lineup currentLineup = new Lineup();
    	 
    	 // choose toplaner
    	 for (int i = 0;i < Playeranzahl/6;i++){
    		 currentLineup.Toplaner = ToplanerArray[i];
    		// choose Jungler
    	 	for (int i2 = 0;i2 < Playeranzahl/6;i2++){
    	 		currentLineup.Jungler = JunglerArray[i2];
    	 		// choose Midlaner
    	 		for (int i3 = 0;i3 < Playeranzahl/6;i3++){
    	 			currentLineup.Midlaner = MidlanerArray[i3];    
    	 			// choose Support
    	 			for (int i4 = 0;i4 < Playeranzahl/6;i4++){
    	 				currentLineup.Support = SupportArray[i4];
    	 				// choose ADC
    	 				for (int i5 = 0;i5 < Playeranzahl/6;i5++){
    	 					currentLineup.ADC = ADCArray[i5];
    	 					// choose Flex Player
    	 					outerloop:
    	 					for (int i6 = 0;i6 < Playeranzahl;i6++){
    	 						while ((currentLineup.Toplaner == PlayerArray[i6]) || (currentLineup.Jungler == PlayerArray[i6]) ||
    	 							(currentLineup.Midlaner == PlayerArray[i6]) ||	(currentLineup.Support == PlayerArray[i6]) || 
    	 							(currentLineup.ADC == PlayerArray[i6] || PlayerArray[i6].role.equals("Team"))) {
    	 							if (i6 < Playeranzahl-1){
    	 							i6++;
    	 							}
    	 							else  {
    	 							break outerloop;
    	 							}
    	 						}
    	 						currentLineup.Flex = PlayerArray[i6];
    	 						// choose Team
    	 						for (int i7 = 0;i7 < Teamanzahl;i7++){
    	 							currentLineup.Team = TeamArray[i7];
    	 							
    	 							// Team collected, check if best:
    	 							
    	 							// sum team costs
    	 				    		currentLineup.Cost = ToplanerArray[i].cost + JunglerArray[i2].cost+ MidlanerArray[i3].cost + 
    	 				    				SupportArray[i4].cost
    	 				    				+ ADCArray[i5].cost 
    	 				    				+ PlayerArray[i6].cost
    	 				    				+ TeamArray[i7].cost;
    	 				    		 // sum team expectedpoints
    	 				     		currentLineup.expectedpoints = ToplanerArray[i].expectedpoints + JunglerArray[i2].expectedpoints + 
    	 				     				MidlanerArray[i3].expectedpoints + SupportArray[i4].expectedpoints + ADCArray[i5].expectedpoints +
    	 				     				PlayerArray[i6].expectedpoints + TeamArray[i7].expectedpoints;
    	 				     		
    	 				     		// check if best team and affordable
    	 							if ((currentLineup.Cost <= 50000) && (currentLineup.expectedpoints > bestLineup.expectedpoints)){
    	 								bestLineup.Toplaner = currentLineup.Toplaner;
    	 								bestLineup.Jungler = currentLineup.Jungler;
    	 								bestLineup.Midlaner = currentLineup.Midlaner;
    	 								bestLineup.ADC = currentLineup.ADC;
    	 								bestLineup.Support = currentLineup.Support;
    	 								bestLineup.Flex = currentLineup.Flex;
    	 								bestLineup.Team = currentLineup.Team;
    	 								bestLineup.expectedpoints = currentLineup.expectedpoints;
    	 								bestLineup.Cost = currentLineup.Cost;
    	 								
    	 								// this assignment is apparently  not the same bestLineup = currentLineup;
    	 								
    	 								System.out.println("New best Lineup found with cost: " + bestLineup.Cost);
    	 								System.out.println("and expectedpoints: " + bestLineup.expectedpoints);
    	 						}
    	 					}
    	 				}
    	 			}
    	 		}
    	 	}
    	 }
     } 
    	 
    	 System.out.println("bestLineup");
    	 System.out.println(bestLineup.Cost);
    	 System.out.println("Punkte " + bestLineup.expectedpoints);
    	 return bestLineup;
     }
}