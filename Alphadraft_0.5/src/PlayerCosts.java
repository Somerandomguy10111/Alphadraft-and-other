import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.OutputStreamWriter;


public class PlayerCosts {

	   static class Player {
		   	String name;
	    	String teamname;
			String role;
			int id;
	    	double cost ;
	    	double expectedpoints;
	    }
	
	public static void main (String[] args) throws IOException, NumberFormatException {
		PlayerCosts.getAlphadraftData();
	}
	
	public static Player[] getAlphadraftData () throws IOException, NumberFormatException {
		//  TODO Auto-generated method stub

		// declare variables
		    StringBuffer jsonString = new StringBuffer();
		    String sessionkey = "init";
		    Player[] Playerarray = new Player[61];
	        String Playername;
	        int Playerid;
	        int k = 0;
	        String cost;
		    
		    try {
		    
		    	String zws;
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
		    // send a get request to contest site with sessionkey from Post request
		    URL scrapeurl = new URL("https://alphadraft.com/api/ui/player/getplayersforbatchcall_mvp/");
		    HttpURLConnection connection2 = (HttpURLConnection) scrapeurl.openConnection();
	        connection2.setDoInput(true);
	        connection2.setDoOutput(true);
	        connection2.setRequestMethod("POST");
	        connection2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
	        connection2.setRequestProperty("cookie","__cfduid=d305c5172431e651eb75169885ba615631451920253; barc-SI=616c9ae633e59d67e418506c7b6ea7fdb"
	        		+ "1b08dc1ac9b93ca4af0d968c95b354d6425eff1d7c92c4ba6a4ad07d77989da79071b051502c6c59f60a4559db992bcdb180e0876b143e"
	        		+ "a5cd8f6d6259f3250;_dc_gtm_UA-57311355-1=1; _gat=1; _ga=GA1.2.1884699382.1451920256;"
	        		+ sessionkey);
	        OutputStreamWriter writer = new OutputStreamWriter(connection2.getOutputStream(), "UTF-8");
	        writer.write("{\"contest_group\":1054}");
	        writer.close();
	        
	        System.out.println("scraping player costs and ids");
	        
	        String line;
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
	        while ((line = br.readLine()) != null) {
                jsonString.append(line);
        }
        
	        System.out.println(jsonString.toString());
	        br.close();
	        
	        String data;
	        data = jsonString.toString();
	        
	        // replace keywords to navigate data
	        int i = 0;
	        while (data.contains("player_name")){
	        	System.out.println(i);
        		i++;
        		data = data.replaceFirst("player_name", "player" + i + "_name");
        	}
	        
	        int i2 = 0;
	        while (data.contains("player_handle")){
	        	System.out.println(i2);
        		i2++;
        		data = data.replaceFirst("player_handle", "player" + i2 + "_handle");
        	}
	        
	        int i3 = 0;
	        while (data.contains("cost")){
	        	System.out.println(i3);
        		i3++;
        		data = data.replaceFirst("cost", "c" + i3+ "ost" );
        	}
	        
	        int i5 = 0;
	        while (data.contains("fppg")){
	        	System.out.println(i5);
        		i5++;
        		data = data.replaceFirst("fppg", "fp" + i5 + "pg" );
        	}
	        
	        
	        // create Player objects with name
	        for (int i4= 1 ;i4 <= 60;i4++){
	        	if (i4 < 10 ){
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5)); 
	        	System.out.println(Playerid);
	        	Playername = data.substring(data.indexOf("player" + i4 +"_name")+16,data.indexOf("player" + i4 +"_handle")-4);
        		
	        	}
	        	else{
	        	Playername = data.substring(data.indexOf("player" + i4 +"_name")+17,data.indexOf("player" + i4 +"_handle")-4);
	        	Playerid = Integer.parseInt(data.substring(data.indexOf("fp" + i4 +"pg")-9,data.indexOf("fp" + i4 +"pg")-5));
	        	}
            			k++;
            			Playerarray[k] = new Player();
            			Playerarray[k].name = Playername;
            			Playerarray[k].id = Playerid;
            		
        	}
	        
	        // find Player costs
	        for (int i7= 61;i7 <= 120;i7++){
	        	if (i7 < 100 ){
	        	cost = data.substring(data.indexOf("c"+ i7 + "ost")+9,data.indexOf("c"+ i7 + "ost")+13);
	        	}
	        	else{
	        	cost = data.substring(data.indexOf("c"+ i7 + "ost")+10,data.indexOf("c"+ i7 + "ost")+14);
	        	}
            	Playerarray[i7-60].cost = Integer.parseInt(cost);
            		
        	}
	        
	        
	        for (int i6= 1;i6 <= 60;i6++){
	        	System.out.println(i6);
	        	System.out.println(Playerarray[i6].name);
	        	System.out.println(Playerarray[i6].cost);
	        	System.out.println(Playerarray[i6].id);
	        }
	        
	        // logout user 
	        // thread sleep to simulate normal session duration 0.1 = between 0 and 6 minutes
	        double rand = (double) Math.random()*0.05;
	        try {
	            Thread.sleep((long) (rand*24*60*1000));
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        
	        URL url2 = new URL("https://alphadraft.com/api/ui/auth/logoutuser/");
			URLConnection con2 = url2.openConnection();
	        con2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
	        con2.connect();
	        System.out.println("Logout succesful!");
			return Playerarray;
	        
	}
}