package vampirism;

public class farm extends bioDome{
	int globalLevel;
	public farm () {
		int buildChoice = (int) Math.ceil((Math.random()*2));
		//globalLevel = level needs to be behind method else Timerame prints out random inputs
		switch (buildChoice) {
		case 1: 	if (bioDome1count == 1){
					cost = 210;
					income = 3;
		}

		case 2:   	if (bioDome2count == 1){
					cost = 400;
					income = 7;
				
		}
		
				}
		globalLevel = buildChoice;
			}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "build farm of type " + globalLevel;
	}

}
