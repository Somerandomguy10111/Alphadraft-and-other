package classes;

public class Team {
	
		public double Elo = 1000;
		public int cost = (int) Math.random()*3000;
		public int Expectedpoints = (int )(Math.random() * 20);
		public String name = "team";
		public double PofW;
		public double PofL;
}