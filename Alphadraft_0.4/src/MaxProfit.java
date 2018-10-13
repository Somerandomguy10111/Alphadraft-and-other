//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Collections;

public class MaxProfit {


	
	    static class Player {
	    	double cost = (double) Math.random()*10000;
	    	double avgpoints =((double)(Math.random() * 50));
	    	String teamname;
	    	String player;
			String Role;
	    }
	    
	    static class Team {
			int cost = (int) Math.random()*3000;
			int avgpoints = (int )(Math.random() * 20);
			String teamname = "Team";
		}
	    
	    static class Collection{
	    	Player Toplaner;
	    	Player Jungler;
	    	Player Midlaner;
	    	Player Support;
	    	Player ADC;
	    	Player Flex;
	    	Team   Team;
	    	double Collectionpoints = 0;
	    	double Cost = 0;
	    }
	    
	 
		
     public static void main (String[] args) {
    	 
    	 int Playeranzahl = 50;
    	 int Teamanzahl = Playeranzahl/5;
    	 Player[] PlayerArray= new Player[Playeranzahl+1]; 
    	 Team[]   TeamArray	 = new Team[Teamanzahl+1];
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
        	 PlayerArray[i].player = "Player" + Integer.toString(i);
     	 }
     	 
     	for (int i = 1;i <= Teamanzahl;i++){
       	 TeamArray[i] = new Team(); 
       	 TeamArray[i].teamname = "Team" + Integer.toString(i);
     	} 
     	
    	 for (int i = 1;i <= Playeranzahl/5;i++){
    	 	for (int sws = 1; sws <= Playeranzahl/10;sws++){
    		 switch (sws){
    		 	case 1: PlayerArray[i*5-5+sws].Role = "Toplaner";
    		 			k2++;
    		 			ToplanerArray[k2] = PlayerArray[i];
    		 			break;
    		 	case 2: PlayerArray[i*5-5+sws].Role = "Jungler";
    		 			k3++;
    		 			JunglerArray[k3] = PlayerArray[i];
    		 			break;
    		 	case 3: PlayerArray[i*5-5+sws].Role = "Midlaner";
    		 			k4++;
    		 			MidlanerArray[k4] = PlayerArray[i];
    		 			break;
    		 	case 4:PlayerArray[i*5-5+sws].Role = "ADC";
    		 			k5++;
    		 			SupportArray[k5] = PlayerArray[i];
    		 			break;
    		 	case 5: PlayerArray[i*5-5+sws].Role = "Support";
    		 			k6++;
    		 			ADCArray[k6] = PlayerArray[i];
    		 			break;
    		 }
        	 	 }
   } 

    	 // finding the best team starts here:
    	 
    	 Collection bestCollection = new Collection();
    	 Collection currentCollection = new Collection();
    	 
    	 // choose toplaner
    	 for (int i = 1;i <= Playeranzahl/5;i++){
    		 currentCollection.Toplaner = ToplanerArray[i];
    		// choose Jungler
    	 	for (int i2 = 1;i2 <= Playeranzahl/5;i2++){
    	 		currentCollection.Jungler = JunglerArray[i];
    	 		// choose Midlaner
    	 		for (int i3 = 1;i3 <= Playeranzahl/5;i3++){
    	 			currentCollection.Midlaner = MidlanerArray[i];    
    	 			// choose Support
    	 			for (int i4 = 1;i4 <= Playeranzahl/5;i4++){
    	 				currentCollection.Support = SupportArray[i];
    	 				// choose ADC
    	 				for (int i5 = 1;i5 <= Playeranzahl/5;i5++){
    	 					currentCollection.ADC = ADCArray[i];
    	 					// choose Flex Player
    	 					for (int i6 = 1;i6 <= Playeranzahl;i6++){
    	 						while ((currentCollection.Toplaner == PlayerArray[i6]) || (currentCollection.Jungler == PlayerArray[i6]) ||
    	 							(currentCollection.Midlaner == PlayerArray[i6]) ||	(currentCollection.Support == PlayerArray[i6]) || (currentCollection.ADC == PlayerArray[i6])) {
    	 							if (i6 < 50){
    	 							i6++;
    	 							}
    	 							else 
    	 							break;
    	 						}
    	 						currentCollection.Flex = PlayerArray[i6];
    	 						// choose Team
    	 						for (int i7 = 1;i7 <= Teamanzahl;i7++){
    	 							currentCollection.Team = TeamArray[i7];
    	 							
    	 							// Team collected, check if best:
    	 							
    	 							// sum team costs
    	 				    		currentCollection.Cost = ToplanerArray[i].cost + JunglerArray[i2].cost+ MidlanerArray[i3].cost + 
    	 				    				SupportArray[i4].cost + ADCArray[i5].cost + PlayerArray[i6].cost + TeamArray[i7].cost;
    	 				    		 // sum team points
    	 				     		currentCollection.Collectionpoints = ToplanerArray[i].avgpoints + JunglerArray[i2].avgpoints + 
    	 				     				MidlanerArray[i3].avgpoints + SupportArray[i4].avgpoints + ADCArray[i5].avgpoints +
    	 				     				PlayerArray[i6].avgpoints + TeamArray[i7].avgpoints;
    	 				     		
    	 				     		// check if best team and affordable
    	 							if ((currentCollection.Cost <= 50000) && (currentCollection.Collectionpoints > bestCollection.Collectionpoints)){
    	 								bestCollection = currentCollection;
    	 								System.out.println("New best Team found with cost: " + bestCollection.Cost);
    	 								System.out.println("and points: " + bestCollection.Collectionpoints);
    	 							}
    	 						}
    	 					}
    	 				}
    	 			}
    	 		}
    	 	}
    	 }
    	 	 System.out.println("bestCollection");
	    	 System.out.println(bestCollection.Cost);
	    	 System.out.println("Punkte " + bestCollection.Collectionpoints);
     }
}