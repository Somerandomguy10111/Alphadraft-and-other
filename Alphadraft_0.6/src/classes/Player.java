package classes;

public class Player {
		public String name;
    	public String teamname;
		public String role;
		public int id;
    	public double cost ;
    	public double expectedpoints;
		public double winscoreavg;
		public double loosescoreavg;
		public double avgscore;
		public int[] winkills = new int [200];
		public int[] windeaths = new int [200];
		public int[] winassists= new int [200];
		public int[] winscore = new int [200];
		public int[] loosekills = new int [200];
		public int[] loosedeaths = new int [200];
		public int[] looseassists = new int [200];
		public int[] loosescore = new int [200];
		public int wonmatches = 0;
		public int lostmatches = 0;
		public int MatchNr = 0;
}
