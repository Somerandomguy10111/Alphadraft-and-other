package Dataprocessing;

import classes.*;


public class Expectedpoints {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public static Player[] getExpectedpoints(Player[] playerarray,Team[] teamarray){
		
		// Assign PT
			for (int i = 0;i < Methods.getObjectcountP(playerarray);i++){
				for (int i2 = 0;i2 < Methods.getObjectcountT(teamarray);i2++){
					if (playerarray[i].team.name.equalsIgnoreCase(teamarray[i2].name)){
						// only place where statistics actually happen
						playerarray[i].team = teamarray[i2];
						playerarray[i].expectedpoints = playerarray[i].winscoreavg * playerarray[i].team.PofW
								+ playerarray[i].loosescoreavg * playerarray[i].team.PofL;
					}
				}
			}
		return playerarray;
	}
}
