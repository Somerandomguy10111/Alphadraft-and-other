package vampirism;

public class tent extends building{
	int level;
	int wallcost = 0;
	int wallLevel = 0;
	
	public tent() {
		level = 1;
		cost = 0;
		income = 1;
	}
	
	public void upgrade(){
			level++;
			cost = (level-1)*50 + wallCost();
			income = 2^(level-1);
	}
	
	public int wallCost(){
		int cost = 0;
		switch (level){
		case 2: cost = 4;
				break;
		case 3: cost = 8 + 16 + 32;
				break;
		case 6: cost = 64 + 128;
				break;
		default: cost = 0;
		}
		return cost;
		
	}
	
	@Override
	public String toString() {
		
		return "upgrade your tent to level " + level;
	}
}
