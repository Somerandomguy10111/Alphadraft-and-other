import java.io.IOException;

import classes.*;

public class Compiler {

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub

		String sessionkey = Methods.getAlphadraftsessionkey();
		Player[] Playerarray = AlphadraftData.getAlphadraftData(sessionkey);
		PTAssignment.getPTAssignment(Playerarray,sessionkey);
		Methods.logout(0.005);
	}

}
