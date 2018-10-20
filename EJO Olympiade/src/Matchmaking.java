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
	
	public static void setRounds(int totalRounds,int teamCount,ArrayList<Station> stationen,ArrayList<Round> Matchlist) {
		Round previousRound = new Round(0,teamCount,stationen);
		
		//create round and fill with correct stations
		for (int i = 1;i < totalRounds;i++) {
			Round newRound = new Round(previousRound);
			Matchlist.add(newRound);
			previousRound = newRound;
		}
	}
	
	public static void setFrame(int totalRounds,ArrayList<Station> stationen, WritableSheet excelSheet) throws RowsExceededException, WriteException {
		for (Station station : stationen) {
			Label label = new Label(station.Number+1, 0, station.Name );
	        excelSheet.addCell(label);
		}
		
		for (int i = 0; i < totalRounds;i++) {
			Label label = new Label(0, i, Integer.toString(i));
	        excelSheet.addCell(label);
		}
		
	}
	
	
	public static void main(String args[])  throws IOException, RowsExceededException, WriteException {
		int totalRounds = 0;
		int teamCount = 8;
		ArrayList<Station> stationen = new ArrayList<Station>();
		ArrayList<Round> Matchlist = new ArrayList<Round>();
		String writepath = "";
		
		
		//Reihenfolge der erstellung legt Reihenfolge der Stationen auf plan fest
		stationen.add(new Station(0,0,1,"sockeln0"));
		stationen.add(new Station(0,0,1,"sockeln1"));
		stationen.add(new Station(0,0,1,"sockeln2"));
		stationen.add(new Station(0,0,1,"sockeln3"));
		stationen.add(new Station(0,0,1,"sockeln4"));
		stationen.add(new Station(0,0,1,"sockeln5"));
		
		//set Round info to the right positions
		
		
		
		//Create an Excel file and sheet
		WritableWorkbook myFirstWbook = Workbook.createWorkbook(new File(writepath));
        WritableSheet excelSheet = myFirstWbook.createSheet("Plan", 0);
        
        //set the first column and first row
        setFrame(totalRounds,stationen,excelSheet);
        
        //write entries
        for (Round round : Matchlist) {
        	for (Station Station : round.stationenListe) {
        		writeCell(Station.Number+1,round.Number+1,Station.Team1,Station.Team2,excelSheet);
        	}
        }

        //finalize write and close notebook if nothing went wrong
        myFirstWbook.write();
        if (myFirstWbook != null) {
                myFirstWbook.close();
        }
	}
}
