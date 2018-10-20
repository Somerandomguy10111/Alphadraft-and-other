import java.util.ArrayList;



public class Round {

	ArrayList<Station> stationenListe = new ArrayList<Station>();
	int Number;

	public void main(String args[]) {
		
	}
	
	public void printRoundstats() {
		System.out.println("Round Number: " + this.Number);

		for (Station station : stationenListe) {
			System.out.println("Station Nummer: " + station.Number);
			System.out.println("Team 1 Nummer: " + station.Team1);
			System.out.println("Team 2 Nummer: " + station.Team2);
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	Round(int num,int teamCount,ArrayList<Station> stationenListe){
		this.Number = num;
		for (Station station : stationenListe) {
			station.Number = stationenListe.indexOf(station)+1;
			if (2*station.Number <= teamCount) {
				station.Team1 = 2*station.Number-1;
				station.Team2 = 2*station.Number;
			}
			
			else if (2*station.Number-1 == teamCount) {
				station.Team1 = 2*station.Number-1;
				station.Team2 = 0;
			}
			
			else {
				station.Team1 = 0;
				station.Team2 = 0;
			}
		}
		this.stationenListe = stationenListe;
	}
	
	Round(Round previousRound){
		
		this.Number = previousRound.Number+1;
		this.stationenListe = rotateStations(previousRound.stationenListe);
//		printRoundstats();
		
	}
	
	public Station getNextStation(ArrayList<Station> stationenListe,Station station) {
		int index = stationenListe.indexOf(station);
		if (index+1 < stationenListe.size()) {
				return stationenListe.get(index+1);
		}
		return stationenListe.get(0);
	}
	
	public Station getPreviousStation(ArrayList<Station> stationenListe,Station station) {
		int index = stationenListe.indexOf(station);
		if (index-1 >= 0) {
				return stationenListe.get(index-1);
		}
		return stationenListe.get(stationenListe.size()-1);
	}
	
	public ArrayList<Station> rotateStations(ArrayList<Station> stationenListe) {
		int lastOccupiedStation = 0;
		int firstOccupiedStation = 0;
		int teamCount = 0;
		ArrayList<Station> newStationList = new ArrayList<Station>();
		for (Station station : stationenListe) {
			newStationList.add(station);
		}

//		System.out.println(stationenListe.size());
		
		for (int i = 0;i < stationenListe.size();i++) {
			if (stationenListe.get(i).Team1 > teamCount) {
				teamCount = stationenListe.get(i).Team1;
			}
			
			if (stationenListe.get(i).Team2 > teamCount) {
				teamCount = stationenListe.get(i).Team2;
			}
			
		}
		
		for (int i = 0;i < stationenListe.size();i++) {
			if (getNextStation(stationenListe,stationenListe.get(i)).Team2 == 0 && stationenListe.get(i).Team2 != 0){
				lastOccupiedStation = i;
			}
		}

		
		for (int i = 0;i < stationenListe.size();i++) {
			if (getPreviousStation(stationenListe,stationenListe.get(i)).Team2 == 0 && stationenListe.get(i).Team2 != 0){
				firstOccupiedStation = i;
			}
		}
		
		System.out.println("Number of first Occupied Station: " + firstOccupiedStation);
		System.out.println("Number of last Occupied Station: " + lastOccupiedStation);
		
		int counter = 0;
		for (int i = lastOccupiedStation+1;true;i--) {
			counter++;
			if (i == 0) {
				i = newStationList.size();
			}
			
			if (counter == newStationList.size()+1) {
				break;
			}

			
			System.out.println("Iteratior variable rotate Stations: " + Integer.toString(i));
			
			Station newStation = new Station();
			if (i < newStationList.size()) {
				newStation =  new Station(newStationList.get(i-1).Team1,newStationList.get(i-1).Team2,i+1,newStationList.get(i).Name);
				newStationList.set(i, newStation);
			}
			
			else {
				newStation =  new Station(newStationList.get(newStationList.size()-1).Team1,newStationList.get(newStationList.size()-1).Team2,1,newStationList.get(0).Name);
				newStationList.set(0, newStation);
			}
			
		}
		System.out.println("teamCount: " + teamCount);
		Station emptyStation = newStationList.get(firstOccupiedStation);
		if (newStationList.size()*2 != teamCount) {
			if (teamCount % 2 == 0) {
				emptyStation.Team1 = 0;
				emptyStation.Team2 = 0;
			}
			else {
				emptyStation.Team1 = teamCount;
				emptyStation.Team2 = 0;
			}
		}
		
		newStationList.set(firstOccupiedStation, emptyStation);
		
		return newStationList;
	}
}
