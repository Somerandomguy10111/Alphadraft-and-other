package Dataprocessing;

import java.io.IOException;
import Webinteraction.*;
import classes.*;

public class Executer {

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		
		
		// IMPORTANT NOTE: Match history input has to match the contestgroupid for the algorithm to work (also, never update java)
		// Match history input
		
//		NA LCS
		String season1 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/North_America/2015_Season/Spring_Season/Match_History";
		String season2 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/North_America/2015_Season/Summer_Season/Match_History";
		String season3 = "http://lol.esportspedia.com/wiki/League_Championship_Series/North_America/2016_Season/Spring_Season/Match_History";

//		EU LCS Strings
//		String season1 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Spring_Season/Match_History";
//		String season2 = "http://lol.esportspedia.com/wiki/Riot_League_Championship_Series/Europe/2015_Season/Summer_Season/Match_History";
//		String season3 = "http://lol.esportspedia.com/wiki/League_Championship_Series/Europe/2016_Season/Spring_Season/Match_History";
		
//		EU CS strings
//		String season1 = "http://lol.esportspedia.com/wiki/2015_EU_Challenger_Series/Spring_Season/Match_History";
//		String season2 = "http://lol.esportspedia.com/wiki/2015_EU_Challenger_Series/Summer_Season/Match_History";
//		String season3 = "http://lol.esportspedia.com/wiki/EU_Challenger_Series/2016_Season/Spring_Season/Match_History";
		
		
//		LPL Strings
//		String season1 = "http://lol.esportspedia.com/wiki/2015_LPL/Spring/Regular_Season/Match_History";
//		String season2 = "http://lol.esportspedia.com/wiki/2015_LPL/Summer/Regular_Season/Match_History";
//		String season3 = "http://lol.esportspedia.com/wiki/LPL/2016_Season/Spring_Season/Match_History";
		
		// Alphadraft contestgroup
		int contestgroupid = 1112;
		
		// Alphadraft contest 
		int contestid = 98992;
		
		// Get Player name, cost, team
		String sessionkey = Methods.getAlphadraftsessionkey();
		Player[] Playerarray = AlphadraftData.getAlphadraftData(sessionkey,contestgroupid);
		PTNameAssignment.getPTNameAssignment(Playerarray,sessionkey,contestgroupid);
		Methods.logout(0.005);
		
		// Get Contest Matchups
		String[] Teams = Matchups.getMatchups(sessionkey,contestid);
		
		// Get Player stats and merge with Alphadraft data
		Player[] FirstPlayerstats = PlayerStats.getStats(season1, null);
		Player[] SecondPlayerstats = PlayerStats.getStats(season2, FirstPlayerstats);
		Player[] ThirdPlayerstats = PlayerStats.getStats(season3, SecondPlayerstats);
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
}