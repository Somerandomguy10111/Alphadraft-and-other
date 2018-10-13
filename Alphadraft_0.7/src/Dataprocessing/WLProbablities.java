package Dataprocessing;

import classes.Team;


public class WLProbablities {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Team[] getPropabilites(String[] Teams,Team[] teamarray){
		
		teamarray = Methods.arrageTeam(Teams, teamarray);
		
		for (int i = 0;i < Methods.getObjectcountT(teamarray);i +=2){
			teamarray[i].PofL = 1/(1 + Math.pow(10,(teamarray[i].Elo-teamarray[i+1].Elo)/400));
			teamarray[i].PofW = 1- teamarray[i].PofL;
			teamarray[i+1].PofW = teamarray[i].PofL;
			teamarray[i+1].PofL = teamarray[i].PofW;
		}
		return teamarray;
	}
}