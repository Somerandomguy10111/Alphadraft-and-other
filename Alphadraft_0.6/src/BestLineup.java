import classes.*;


public class BestLineup {


	    static class Lineup{
	    	Player Toplaner;
	    	Player Jungler;
	    	Player Midlaner;
	    	Player Support;
	    	Player ADC;
	    	Player Flex;
	    	Player   Team;
	    	double points;
	    	double Cost;
	    }
	    
	    public static void main (String[] args) {
	    BestLineup.getBestLineup(null);
	    }
	    
		
     public static Lineup getBestLineup (Player[] Playerdata) {
    	 
    	 
    	 // in Alphadraft context a team is a player and is treated as such
    	 int Playeranzahl = 50;
    	 int Teamanzahl = Playeranzahl/5;
    	 Player[] PlayerArray= new Player[Playeranzahl+1]; 
    	 Player[]   TeamArray	 = new Player[Teamanzahl+1];
    	 Player[] ToplanerArray = new Player[Playeranzahl/5+1];
    	 Player[] JunglerArray = new Player[Playeranzahl/5+1];
    	 Player[] MidlanerArray = new Player[Playeranzahl/5+1];
    	 Player[] SupportArray = new Player[Playeranzahl/5+1];
    	 Player[] ADCArray = new Player[Playeranzahl/5+1];
    	 
    	int k2 = 0;
     	int k3 = 0;
     	int k4 = 0;
     	int k5 = 0;
     	int k6 = 0;	
    	
     	 for (int i = 1;i <= Playeranzahl;i++){
        	 PlayerArray[i] = new Player(); 
        	 PlayerArray[i].name = "Player" + Integer.toString(i);
     	 }
     	 
     	for (int i = 1;i <= Teamanzahl;i++){
       	 TeamArray[i] = new Player(); 
       	 TeamArray[i].name = "Team" + Integer.toString(i);
     	} 
     	
    	 for (int i = 1;i <= Playeranzahl/5;i++){
    	 	for (int sws = 1; sws <= Playeranzahl/10;sws++){
    		 switch (sws){
    		 	case 1: PlayerArray[i*5-5+sws].role = "Toplaner";
    		 			k2++;
    		 			ToplanerArray[k2] = PlayerArray[i*5-5+sws];
    		 			break;
    		 	case 2: PlayerArray[i*5-5+sws].role = "Jungler";
    		 			k3++;
    		 			JunglerArray[k3] = PlayerArray[i*5-5+sws];
    		 			break;
    		 	case 3: PlayerArray[i*5-5+sws].role = "Midlaner";
    		 			k4++;
    		 			MidlanerArray[k4] = PlayerArray[i*5-5+sws];
    		 			break;
    		 	case 4:PlayerArray[i*5-5+sws].role = "ADC";
    		 			k5++;
    		 			SupportArray[k5] = PlayerArray[i*5-5+sws];
    		 			break;
    		 	case 5: PlayerArray[i*5-5+sws].role = "Support";
    		 			k6++;
    		 			ADCArray[k6] = PlayerArray[i*5-5+sws];
    		 			break;
    		}
       }
   } 

    	 // finding the best team starts here:
    	 
    	 Lineup bestLineup = new Lineup();
    	 Lineup currentLineup = new Lineup();
    	 
    	 // choose toplaner
    	 for (int i = 1;i <= Playeranzahl/5;i++){
    		 currentLineup.Toplaner = ToplanerArray[i];
    		// choose Jungler
    	 	for (int i2 = 1;i2 <= Playeranzahl/5;i2++){
    	 		currentLineup.Jungler = JunglerArray[i2];
    	 		// choose Midlaner
    	 		for (int i3 = 1;i3 <= Playeranzahl/5;i3++){
    	 			currentLineup.Midlaner = MidlanerArray[i3];    
    	 			// choose Support
    	 			for (int i4 = 1;i4 <= Playeranzahl/5;i4++){
    	 				currentLineup.Support = SupportArray[i4];
    	 				// choose ADC
    	 				for (int i5 = 1;i5 <= Playeranzahl/5;i5++){
    	 					currentLineup.ADC = ADCArray[i5];
    	 					// choose Flex Player
    	 					for (int i6 = 1;i6 <= Playeranzahl;i6++){
    	 						while ((currentLineup.Toplaner == PlayerArray[i6]) || (currentLineup.Jungler == PlayerArray[i6]) ||
    	 							(currentLineup.Midlaner == PlayerArray[i6]) ||	(currentLineup.Support == PlayerArray[i6]) || (currentLineup.ADC == PlayerArray[i6])) {
    	 							if (i6 < 50){
    	 							i6++;
    	 							}
    	 							else  {
    	 							break;
    	 							}
    	 						}
    	 						currentLineup.Flex = PlayerArray[i6];
    	 						// choose Team
    	 						for (int i7 = 1;i7 <= Teamanzahl; i7++){
    	 							currentLineup.Team = TeamArray[i7];
    	 							
    	 							// Team collected, check if best:
    	 							
    	 							// sum team costs
    	 				    		currentLineup.Cost = ToplanerArray[i].cost + JunglerArray[i2].cost+ MidlanerArray[i3].cost + 
    	 				    				SupportArray[i4].cost + ADCArray[i5].cost + PlayerArray[i6].cost + TeamArray[i7].cost;
    	 				    		 // sum team points
    	 				     		currentLineup.points = ToplanerArray[i].expectedpoints + JunglerArray[i2].expectedpoints + 
    	 				     				MidlanerArray[i3].expectedpoints + SupportArray[i4].expectedpoints + ADCArray[i5].expectedpoints +
    	 				     				PlayerArray[i6].expectedpoints + TeamArray[i7].expectedpoints;
    	 				     		
    	 				     		// check if best team and affordable
    	 							if ((currentLineup.Cost <= 50000) && (currentLineup.points > bestLineup.points)){
    	 								bestLineup.Toplaner = currentLineup.Toplaner;
    	 								bestLineup.Jungler = currentLineup.Jungler;
    	 								bestLineup.Midlaner = currentLineup.Midlaner;
    	 								bestLineup.ADC = currentLineup.ADC;
    	 								bestLineup.Support = currentLineup.Support;
    	 								bestLineup.Flex = currentLineup.Flex;
    	 								bestLineup.Team = currentLineup.Team;
    	 								bestLineup.points = currentLineup.points;
    	 								bestLineup.Cost = currentLineup.Cost;
    	 								
    	 								System.out.println("New best Lineup found with cost: " + bestLineup.Cost);
    	 								System.out.println("and points: " + bestLineup.points);
    	 						}
    	 					}
    	 				}
    	 			}
    	 		}
    	 	}
    	 }
     }
    	 System.out.println(bestLineup.Toplaner.role);
    	 System.out.println(bestLineup.Jungler.role);
    	 System.out.println(bestLineup.Midlaner.role);
    	 System.out.println(bestLineup.Support.role);
    	 System.out.println(bestLineup.ADC.role);
    	 System.out.println(bestLineup.Flex.role);
    	 System.out.println(bestLineup.Team.name);
    	 
    	 
    	 System.out.println("bestLineup");
    	 System.out.println(bestLineup.Cost);
    	 System.out.println("Punkte " + bestLineup.points);
    	 return bestLineup;
     }
}