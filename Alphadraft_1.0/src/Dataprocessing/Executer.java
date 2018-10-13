package Dataprocessing;

import java.io.IOException;
import Webinteraction.*;
import classes.*;

public class Executer {

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		
		
		// IMPORTANT NOTE: Match history input has to match the contestgroupid for the algorithm to work (also, never update java)
		// Match history input
		
		// Log out in any case
		try {
			
			// Get Contest Ids based on Input
			String sessionkey = Methods.getAlphadraftsessionkey();
			String [] Contestdata = Contests.getContests(sessionkey);
			
		String season1 = "";
		String season2 = "";
		String season3 = "";
			
//		NA LCS
		if (Contestdata[2].contains("NA LCS")){
		season1 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/North_America/2015_Season/Spring_Season/Match_History";
		season2 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/North_America/2015_Season/Summer_Season/Match_History";
		season3 = "http://lol.esportspedia.com/wiki/League_Championship_Series/North_America/2016_Season/Spring_Season/Match_History";
		}
		
//		EU LCS Strings
		else if (Contestdata[2].contains("EU LCS")){
		season1 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Spring_Season/Match_History";
		season2 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History";
		season3 = "http://lol.esportspedia.com/wiki/League_Championship_Series/Europe/2016_Season/Spring_Season/Match_History";
		}
		
//		EU CS strings
		else if (Contestdata[2].contains("EU CS")){
		season1 = "http://lol.esportspedia.com/wiki/2015_EU_Challenger_Series/Spring_Season/Match_History";
		season2 = "http://lol.esportspedia.com/wiki/2015_EU_Challenger_Series/Summer_Season/Match_History";
		season3 = "http://lol.esportspedia.com/wiki/EU_Challenger_Series/2016_Season/Spring_Season/Match_History";
		}
		
		else if (Contestdata[2].contains("NA CS")){
//		NA CS strings
		season1 = "http://lol.esportspedia.com/wiki/2015_NA_Challenger_Series/Spring_Season/Match_History";
		season2 = "http://lol.esportspedia.com/wiki/2015_NA_Challenger_Series/Summer_Season/Match_History";
		season3 = "http://lol.esportspedia.com/wiki/NA_Challenger_Series/2016_Season/Spring_Season/Match_History";
		}
		
		else if (Contestdata[2].contains("LPL")){
//		LPL Strings
		season1 = "http://lol.esportspedia.com/wiki/2015_LPL/Spring/Regular_Season/Match_History";
		season2 = "http://lol.esportspedia.com/wiki/2015_LPL/Summer/Regular_Season/Match_History";
		season3 = "http://lol.esportspedia.com/wiki/LPL/2016_Season/Spring_Season/Match_History";
		}
		
		
		// Alphadraft contestgroup
		int contestgroupid = Integer.parseInt(Contestdata[0]);
//		int contestgroupid = 1112;
		
		// Alphadraft contest 
		int contestid = Integer.parseInt(Contestdata[1]);
//		int contestid = 98902;
		
		// Get Player name, cost, team
		Player[] Playerarray = AlphadraftData.getAlphadraftData(sessionkey,contestgroupid);
		PTNameAssignment.getPTNameAssignment(Playerarray,sessionkey,contestgroupid);
		Methods.logout(0.005);
		
		// Get Contest Matchups
		String[] Teams = Matchups.getMatchups(sessionkey,contestid);
		
		// Get Player stats and merge with Alphadraft data
//		Player[] FirstPlayerstats = PlayerStats.getStats(season1, null);
//		Player[] SecondPlayerstats = PlayerStats.getStats(season2, FirstPlayerstats);
		Player[] ThirdPlayerstats = PlayerStats.getStats(season3, null);
		Playerarray = Methods.mergePlayerList(ThirdPlayerstats ,Playerarray);
		
		// Create Elo ranks for teams
		Team[] firstSeason =  EloRanking.createEloRanking(season1,null);
		Team[] secondSeason = EloRanking.createEloRanking(season2,firstSeason);
		Team[] thirdSeason = EloRanking.createEloRanking(season3,secondSeason);
		
		// Calculates the PofW and PofL for the Teams participating in the contest
		thirdSeason = WLProbablities.getPropabilites(Teams, thirdSeason);
		
		// Calculates Expectedscore based on Avgloosepoints, Avgwinpoints Player.Team.PofW, Player.Team.PofL
		Playerarray = Expectedpoints.getExpectedpoints(Playerarray, thirdSeason);
		
		// Find the Best Lineup based on Cost and Expectedpoints
		Lineup FinalDestination = BestLineup.getBestLineup(Playerarray);
		
		// Print out the best Lineup
		Methods.PrintLineupdata(FinalDestination);
		
		}
		// Log out of Alphadraft
		finally{
		Methods.logout(0.005);
		}
	}
}