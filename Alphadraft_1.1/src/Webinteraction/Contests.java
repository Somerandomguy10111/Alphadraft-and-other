
package Webinteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Dataprocessing.Methods;

public class Contests {

	public static void main(String[] args)throws IOException, NumberFormatException  {
		// TODO Auto-generated method stub
		String sessionkey = "sessionid=ouvz5lnifyh62od982kv4l9fjjzqesnx; expires=Mon";
		getContests(sessionkey);
	}

	
	public static String[] getContests(String sessionkey) throws IOException, NumberFormatException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Contest to search after");
        
        String contest = br.readLine();
        
		String[] Contestdata = new String [3];
		
		String scrapeurl = "https://alphadraft.com/api/ui/contest/getlobbycontests/";
		String payload = "{\"gameType\":\"1\"}";
		
		String data = Methods.AlphadraftPostRequest(scrapeurl, sessionkey, payload);
		
		System.out.println("Data: " + data);
		System.out.println(contest);
		
		data = data.substring(data.indexOf(contest), data.indexOf("contest_group_id",data.indexOf(contest))+50);

		
		Contestdata[0] = data.substring(data.indexOf("contest_group_id")+19,data.indexOf("buyins")-3);
		Contestdata[1] = data.substring(data.indexOf("id")+5,data.indexOf("guaranteed")-3);   
		Contestdata[2] = contest;
		
		System.out.println(Contestdata[0]);
		System.out.println(Contestdata[1]);
		System.out.println(Contestdata[2]);
		
		return Contestdata;
	}

}