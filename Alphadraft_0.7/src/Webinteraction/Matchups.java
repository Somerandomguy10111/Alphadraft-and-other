package Webinteraction;

import Dataprocessing.*;
import java.io.IOException;

public class Matchups {

	public static void main (String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
//		String sessionkey = Methods.getAlphadraftsessionkey();
		String sessionkey = "sessionid=ouvz5lnifyh62od982kv4l9fjjzqesnx; expires=Mon";
//		try {
		getMatchups(sessionkey,98992);
//		}
//		finally{
//			Methods.logout(0.005);
//		}
		
	}
	
	public static String[] getMatchups(String sessionkey,int contestid) throws IOException, NumberFormatException {
		
		String data = Methods.AlphadraftGetRequest("https://alphadraft.com/api/ui/contest_types/getcontestdetails/" + contestid, sessionkey);
		String[] Teams = new String[10];
		String[] Acronyms = new String[10];
		String[] Acronymor = new String[10];
		
		int Occ = Methods.getOccurences(data, "team_name");
		data = Methods.replaceString(data, "team_name");
		data = Methods.replaceString(data, "acronym");
		System.out.println(data);
		
		for (int i = 0;i < Occ;i++){
		String zws = data.substring(data.indexOf("team"+ i + "_name"),data.indexOf("team"+ i + "_name")+40);
		String zws2 = data.substring(data.indexOf("acr"+ i + "onym"),data.indexOf("acr"+ i + "onym")+40);
		
		int firstOcc = zws.indexOf("\"");
		int secondOcc = zws.indexOf("\"", firstOcc + 1);
		int thirdOcc = zws.indexOf("\"", secondOcc + 1);
		
		int firstOcc2 = zws2.indexOf("\"");
		int secondOcc2 = zws2.indexOf("\"", firstOcc2 + 1);
		int thirdOcc2 = zws2.indexOf("\"", secondOcc2 + 1);
		

		Teams[i] = zws.substring(secondOcc + 1, thirdOcc);
		Acronyms[i] = zws2.substring(secondOcc2 + 1, thirdOcc2);
		
		}
		
		
		
		for (int i = Occ;i < Occ*2;i++){
			String zws2 = data.substring(data.indexOf("acr"+ i + "onym"),data.indexOf("acr"+ i + "onym")+20);
			
			int firstOcc = zws2.indexOf("\"");
			int secondOcc = zws2.indexOf("\"", firstOcc + 1);
			int thirdOcc = zws2.indexOf("\"", secondOcc + 1);
			

			Acronymor[i-Occ] = zws2.substring(secondOcc + 1, thirdOcc);
		}
		
		for (int i = 0;i < Occ;i++){
			for (int i2 = 0;i2 < Occ;i2++){
				if (Acronymor[i].equals(Acronyms[i2])){
					String zws = Acronyms[i];
					String zws2 = Teams[i];
					Acronyms[i] = Acronyms[i2];
					Teams[i] = Teams[i2];
					Acronyms[i2] = zws;
					Teams[i2] = zws2;
				}
			}
		}

		for (int i = 0;i < Occ;i++){
		System.out.println(Teams[i]);
		System.out.println(i);
		}
		
		
		return Teams;
	}
}
