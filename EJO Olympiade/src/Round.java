import java.util.ArrayList;



public class Round {

	ArrayList<Station> stationenListe = new ArrayList<Station>();
	int Number;

	public void main(String args[]) {
		
	}
	
	Round(int num,int teamCount,ArrayList<Station> stationenListe){
		this.Number = num;
		for (Station station : stationenListe) {
			station.Number = stationenListe.indexOf(station)+1;
			if (2*station.Number <= teamCount) {
				station.Team1 = 2*station.Number-1;
				station.Team2 = 2*station.Number;
			}
		}
	}
	
	Round(Round previousRound){
		for (Station station : stationenListe) {
			
		}
	}
}
