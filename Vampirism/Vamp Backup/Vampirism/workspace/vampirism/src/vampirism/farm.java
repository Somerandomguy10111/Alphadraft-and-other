package vampirism;

public class farm extends building {
	int globalLevel;
	
	public farm(int level) {
		globalLevel = level;
		switch (level) {
		case 1: 	cost = 210;
					income = 3;
					break;
		case 2:   	cost = 400;
					income = 7;
					//biodome lvl 2 or ancient tree required
					break;
		}
			
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "build farm of type " + globalLevel;
	}

}
