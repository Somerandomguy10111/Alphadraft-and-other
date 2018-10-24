import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;

public class Matchmaking {

	public static void writeCell(int x,int y,int Team1Number,int Team2Number,WritableSheet excelSheet) throws RowsExceededException, WriteException {
        Label label = new Label(x, y,Integer.toString(Team1Number) + "||" +  Integer.toString(Team2Number) );
        excelSheet.addCell(label);
	}
	
	public static ArrayList<Round> setRounds(int totalRounds,int teamCount,ArrayList<Station> stationen,ArrayList<Round> Matchlist) {
		Round previousRound = new Round(0,teamCount,stationen);
		Matchlist.add(previousRound);
//		previousRound.printRoundstats();
		
		//create round and fill with correct stations
		for (int i = 1;i < totalRounds;i++) {
			Matchlist.add(new Round(previousRound));
			previousRound = Matchlist.get(i);
//			Matchlist.get(i).printRoundstats();
			
			System.out.println("Begin output loop");
			for (int i2 = 0;i2 <= i;i2++) {
					System.out.println("Matchlist " + i2 +  " content");
					Matchlist.get(i2).printRoundstats();
			}
		}
		
		for (int i = 1;i < totalRounds;i++) {
			System.out.println("Matchlist " + i +  " content");
			Matchlist.get(i).printRoundstats();
		}
		
		return Matchlist;
	}
	
	public static void setFrame(int totalRounds,ArrayList<Station> stationen, WritableSheet excelSheet) throws RowsExceededException, WriteException {
		for (Station station : stationen) {
			Label label = new Label(station.Number, 0, station.Name );
	        excelSheet.addCell(label);
		}
		
		for (int i = 1; i <= totalRounds;i++) {
			Label label = new Label(0, i, Integer.toString(i));
	        excelSheet.addCell(label);
		}
		
	}
	
	public static void main(String args[])  throws IOException, RowsExceededException, WriteException {
		int totalRounds = 20;
		int teamCount = 12;
		ArrayList<Station> stationen = new ArrayList<Station>();
		ArrayList<Round> Matchlist = new ArrayList<Round>();
		String name = "Plan";
		String writepath = "C:\\Users\\LiquidSpot\\Desktop" + name +".csv";
		
		
		//Reihenfolge der erstellung legt Reihenfolge der Stationen auf plan fest
		stationen.add(new Station(0,0,0,"sockeln0"));
		stationen.add(new Station(0,0,0,"sockeln1"));
		stationen.add(new Station(0,0,0,"sockeln2"));
		stationen.add(new Station(0,0,0,"sockeln3"));
		stationen.add(new Station(0,0,0,"sockeln4"));
		stationen.add(new Station(0,0,0,"sockeln5"));
		
		//set Round info to the right positions
		Matchlist = setRounds(totalRounds,teamCount,stationen,Matchlist);
		System.out.println(Matchlist.size());
		
		//Create an Excel file and sheet
		WritableWorkbook myFirstWbook = Workbook.createWorkbook(new File(writepath));
        WritableSheet excelSheet = myFirstWbook.createSheet("Plan", 0);
        
        //set the first column and first row
        setFrame(totalRounds,stationen,excelSheet);
        
        //write entries
        for (int i = 0;i < totalRounds;i++) {
//        	round.printRoundstats();
        	Round round = Matchlist.get(i);
        	for (Station Station : round.stationenListe) {
//        		System.out.println("Writedata");
//        		System.out.println("Station Number: " + Station.Number);
//        		System.out.println("Station Team 1: " + Station.Team1);
//        		System.out.println("Station Team 2: " + Station.Team2);
        		
        		writeCell(Station.Number,round.Number+1,Station.Team1,Station.Team2,excelSheet);
        	}
        }

        //finalize write and close notebook if nothing went wrong
        myFirstWbook.write();
        if (myFirstWbook != null) {
                myFirstWbook.close();
        }
	}
}
