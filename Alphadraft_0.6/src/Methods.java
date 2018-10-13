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
	
	public static String replaceString(String data,String toReplace){
		 int i = 0;
	        while (data.contains(toReplace)){
    		i++;
    		data = data.replaceFirst(toReplace , toReplace.substring(0, (toReplace.length()/2)) + i + toReplace.substring((toReplace.length()/2), toReplace.length()));
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
	
	public static Player[] mergeLists(Player[] list1, Player[] list2){

		Player[] output = new Player[list1.length];
		
		
		
		
		return output;
	}
	
	public static void print(String toprint){
		System.out.println(toprint);
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
}

