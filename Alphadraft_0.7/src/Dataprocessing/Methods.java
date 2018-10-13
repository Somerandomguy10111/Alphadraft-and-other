package Dataprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import classes.*;


public class Methods {

	
	public static String getAlphadraftsessionkey(){
		String sessionkey = "init";
    	String zws;
		try {
	        URL loginurl = new URL("https://alphadraft.com/api/ui/auth/loginuser/");

	        
	        // place email and password here
	        String payload="{\"email_address\" : \"daniel.hollarek@googlemail.com\", \"password\" : \"amkamina123\"}";
	        System.out.println(payload);
	        
	        // create connection object
	        HttpURLConnection connection = (HttpURLConnection) loginurl.openConnection();
	        
	        // adjust connection properties
	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("content-type", "text/plain;charset=UTF-8");
	        connection.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        writer.write(payload);
	        writer.close();
	        
	        
	        try {

	        	System.out.println("Printing Response Header: cookie");

	        	// get sessionkey
	        	zws = connection.getHeaderField("set-cookie");
	        	sessionkey = zws.substring(0, zws.indexOf(","));
	        		System.out.println(sessionkey);
	        	

	        	System.out.println("\nGet Response Header By Key ...\n");
	        	String server = connection.getHeaderField("Server");

	        	if (server == null) {
	        		System.out.println("Key 'Server' is not found!");
	        	}
	        	else {
	        		System.out.println("Server - " + server);
	        	}

	        	System.out.println("\n Done");

	            }
	        catch (Exception e) {
	        	e.printStackTrace();
	            }
	        
	        System.out.println("Login sucessful");
	        
	    } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	}
		return sessionkey;
	}
	
	public static String AlphadraftPostRequest(String scrapeurl,String sessionkey,String requestpayload) throws IOException, NumberFormatException {
		
		StringBuffer jsonString = new StringBuffer();
		
		
		// send a get request to contest site with sessionkey from Post request
	    URL scrapesite = new URL(scrapeurl);
	    HttpURLConnection connection = (HttpURLConnection) scrapesite.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
        connection.setRequestProperty("cookie","__cfduid=d305c5172431e651eb75169885ba615631451920253; barc-SI=616c9ae633e59d67e418506c7b6ea7fdb"
        		+ "1b08dc1ac9b93ca4af0d968c95b354d6425eff1d7c92c4ba6a4ad07d77989da79071b051502c6c59f60a4559db992bcdb180e0876b143e"
        		+ "a5cd8f6d6259f3250;_dc_gtm_UA-57311355-1=1; _gat=1; _ga=GA1.2.1884699382.1451920256;"
        		+ sessionkey);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(requestpayload);
        writer.close();
        
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
    }
        br.close();
        
        String response = jsonString.toString();
        
        // returns server response
        return response;
	}

	public static String AlphadraftGetRequest(String scrapeurl,String sessionkey) throws IOException, NumberFormatException {
		
		StringBuffer jsonString = new StringBuffer();
		
		
		// send a get request to contest site with sessionkey from Post request
	    URL scrapesite = new URL(scrapeurl);
	    HttpURLConnection connection = (HttpURLConnection) scrapesite.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
        connection.setRequestProperty("cookie","__cfduid=d305c5172431e651eb75169885ba615631451920253; barc-SI=616c9ae633e59d67e418506c7b6ea7fdb"
        		+ "1b08dc1ac9b93ca4af0d968c95b354d6425eff1d7c92c4ba6a4ad07d77989da79071b051502c6c59f60a4559db992bcdb180e0876b143e"
        		+ "a5cd8f6d6259f3250;_dc_gtm_UA-57311355-1=1; _gat=1; _ga=GA1.2.1884699382.1451920256;"
        		+ sessionkey);
        
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
    }
        br.close();
        
        String response = jsonString.toString();
        
        // returns server response
        return response;
	}
		
	public static String replaceString(String data,String toReplace){
		 int i = 0;
	        while (data.contains(toReplace)){
    		data = data.replaceFirst(toReplace , toReplace.substring(0, (toReplace.length()/2)) + i + toReplace.substring((toReplace.length()/2), toReplace.length()));
    		i++;
    	}
	        return data;
	}
	
	public static BufferedReader establishConnection(String site) throws IOException, NumberFormatException {
		URL url = new URL(site);
		URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br;
	}
	
	public static void logout(double wait)  throws IOException, NumberFormatException {
		
		// logout user 
        // thread sleep to simulate normal session duration 0.1 = between 0 and 6 minutes
        double rand = (double) Math.random()*wait;
        try {
            Thread.sleep((long) (rand*24*60*1000));
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        URL url = new URL("https://alphadraft.com/api/ui/auth/logoutuser/");
		URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
        con.connect();
        System.out.println("Logout succesful!");
	}

	public static boolean containsIgnoreCase(String src, String what) {
	    final int length = what.length();
	    if (length == 0)
	        return true; // Empty string is contained

	    final char firstLo = Character.toLowerCase(what.charAt(0));
	    final char firstUp = Character.toUpperCase(what.charAt(0));

	    for (int i = src.length() - length; i >= 0; i--) {
	        // Quick check before calling the more expensive regionMatches() method:
	        final char ch = src.charAt(i);
	        if (ch != firstLo && ch != firstUp)
	            continue;

	        if (src.regionMatches(true, i, what, 0, length))
	            return true;
	    }

	    return false;
	}
		
	public static Team[] mergeTeamList(Team[] previousSeason, Team[] currentSeason){
	
	// Gives the Elo values of one season on to the next
	Team[] output =  currentSeason;
	if (previousSeason != null){
		for (int i = 0;i < Methods.getObjectcountT(currentSeason);i++){
			for (int i2 = 0;i2 < Methods.getObjectcountT(previousSeason);i2++){
			if (currentSeason[i].name.equalsIgnoreCase(previousSeason[i2].name)){
				currentSeason[i] = previousSeason[i2];
				}
			}
		}
	}
		return output;
	}
	
	public static Player[] mergePlayerList(Player[] PlayerData, Player[] AlphadraftData){

	// merges the Player list from Alphadraft with the Match history list
	if (PlayerData != null){
		for (int i = 0;i < Methods.getObjectcountP(AlphadraftData);i++){
			for (int i2 = 0;i2 < Methods.getObjectcountP(PlayerData);i2++){
				if (AlphadraftData[i].name.equalsIgnoreCase(
						PlayerData[i2].name)){
					AlphadraftData[i].avgscore = PlayerData[i2].avgscore;
					AlphadraftData[i].winscoreavg = PlayerData[i2].winscoreavg;
					AlphadraftData[i].loosescoreavg = PlayerData[i2].loosescoreavg;
				}
			}
		}
	}
		return AlphadraftData;
}
	
	public static int getObjectcountP(Player[] Playerarray){
		// returns the amout of slots in the array that are filled up
		
		int k = 0;
		for (int i = 0;i <= Playerarray.length-1;i++) {
			if (Playerarray[i] != null){
				k++;
			}
		}
	return k;
	}

	public static int getObjectcountT(Team[] Teamarray){
		// returns the amout of slots in the array that are filled up
		
		int k = 0;
		for (int i = 0;i < Teamarray.length;i++) {
			if (Teamarray[i] != null){
				k++;
			}
		}
	return k;
	}
	
	public static int getObjectcountStr(String[] Stringarray){
		// returns the amout of slots in the array that are filled up
		
		int k = 0;
		for (int i = 0;i < Stringarray.length;i++) {
			if (Stringarray[i] != null){
				k++;
			}
		}
	return k;
	}
	
	public static int getOccurences(String data, String toReplace) {
		 int i = 0;
	        while (data.contains(toReplace)){
 		i++;
 		data = data.replaceFirst(toReplace , toReplace.substring(0, (toReplace.length()/2)) + i + toReplace.substring((toReplace.length()/2), toReplace.length()));
 	}
	        return i;
	}
	
	public static Team[] arrageTeam (String[] Teams,Team[] teamarray){
		
		// arrange the teamarray to put the teamarray in the order they are matched up against
		for(int i = 0;i < Methods.getObjectcountT(teamarray);i++){
			for (int i2 = 0;i2 < Methods.getObjectcountStr(Teams);i2++){
				if (Teams[i].equalsIgnoreCase(teamarray[i2].name)){
					System.out.println(Teams[i]);
					System.out.println(Methods.getObjectcountT(teamarray));
					Team zws = teamarray[i];
					teamarray[i] = teamarray[i2];
					teamarray[i2] = zws;
				}	
			}
		}
		return teamarray;
	}
	
	
	public static Player[] mergePlayerLists(Player[][] PlayerData){

		// merge all playerdata into one list
				for (int i = 0;i < PlayerData.length-1;i++){
					for (int i2 = 0;i2 < Methods.getObjectcountP(PlayerData[i+1]);i2++){
						for (int i3 = 0;i3 < Methods.getObjectcountP(PlayerData[i]);i3++){
					if (PlayerData[i+1][i2].name.equalsIgnoreCase(PlayerData[i][i3].name)){
						
						PlayerData[i+1][i2].avgscore += PlayerData[i][i3].avgscore;
						PlayerData[i+1][i2].winscoreavg += PlayerData[i][i3].winscoreavg;
						PlayerData[i+1][i2].loosescoreavg += PlayerData[i][i3].loosescoreavg;
						PlayerData[i+1][i2].wonmatches += PlayerData[i][i3].wonmatches;
						PlayerData[i+1][i2].lostmatches += PlayerData[i][i3].lostmatches;
						PlayerData[i+1][i2].MatchNr += PlayerData[i][i3].MatchNr;
						
					}
				}
			}
		}
				
				// needs completion
		for (int i = 0;i < Methods.getObjectcountP(PlayerData[PlayerData.length-1]);i++){

			PlayerData[PlayerData.length-1][i].avgscore = PlayerData[PlayerData.length-1][i].avgscore / PlayerData[PlayerData.length-1][i].MatchNr;
		}
			return PlayerData [PlayerData.length-1];
	}

	public static void PrintLineupdata(Lineup Finallineup){
		System.out.println();
		System.out.println();
		System.out.println("The optimal Linup for this contest is composed of");
		System.out.println("Toplaner: " + Finallineup.Toplaner.name);
		System.out.println("Jungler : " + Finallineup.Jungler.name);
		System.out.println("Midlaner: " + Finallineup.Midlaner.name);
		System.out.println("Support : " + Finallineup.Support.name);
		System.out.println("ADC     : " + Finallineup.ADC.name);
		System.out.println("Flex    : " + Finallineup.Flex.name);
		System.out.println("Team    : " + Finallineup.Team.name);
		System.out.println("It costs " + Finallineup.Cost + " credits and is expected to reach "  + Finallineup.expectedpoints + " points.");
		
		}
}