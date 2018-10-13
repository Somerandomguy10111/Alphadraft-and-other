package classes;

public class Player {
		public Team team;
		public String name;
		public String role;
		public int id;
    	public double cost = Math.random()*10000 ;
    	public double expectedpoints = 0;
		public double winscoreavg;
		public double loosescoreavg;
		public double avgscore;
		public int[] winkills = new int [200];
		public int[] windeaths = new int [200];
		public int[] winassists= new int [200];
		public double[] wincreeps= new double [200];
		public double[] winscore = new double [200];
		public int[] loosekills = new int [200];
		public int[] loosedeaths = new int [200];
		public int[] looseassists = new int [200];
		public double[] loosecreeps= new double [200];
		public double[] loosescore = new double [200];
		public int wonmatches = 0;
		public int lostmatches = 0;
		public int MatchNr = 0;
}
