package classes;

public class Statistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double balance = 10;
		double chance = 0.52;
		
		
		double[] resultsV1 = new double[1010];
		double[] resultsV2 = new double[1010];
		double[] resultsV3 = new double[1010];
		double avgsv1 = 0;
		double avgsv2 = 0;
		double avgsv3 = 0;
		
		for (int i = 1 ;i <= 1000;i++){
			resultsV1[i] = Ver1(balance,chance);
			resultsV2[i] = Ver2(balance,chance);
			resultsV3[i] = Ver3(balance,chance);
			
			avgsv1 += resultsV1[i];
			avgsv2 += resultsV2[i];
			avgsv3 += resultsV3[i];
		}
		
		avgsv1 = avgsv1 / 1000;
		avgsv2 = avgsv2 / 1000;
		avgsv3 = avgsv3 / 1000;
		
		System.out.println();
		System.out.println("Version 1: " + avgsv1);
		System.out.println("Version 2: " + avgsv2);
		System.out.println("Version 2: " + avgsv3);
	}

	
	public static double Ver1(double balance, double chance){
		
		double bet = 2;
		
		for (int i = 1;i <= 1000; i++){
			if (Math.random() < chance){
				balance += bet;
			}
			else {
				balance -= bet;
			}
			if (balance == 0) {
				break;
			}
		}
		
//		System.out.println(balance);
		return balance;
	}
	
	
	public static double Ver2 (double balance, double chance){
		
		for (int i = 1;i <= 1000; i++){
			double bet = balance/5;
			if (Math.random() < chance){
				balance += bet;
			}
			else {
				balance -= bet;
			}
		}
		
//		System.out.println(balance);
		return balance;
	
	}
	public static double Ver3(double balance, double chance){
		
		
		for (int i = 1;i <= 1000; i++){
			double bet = 5/balance;
			if (Math.random() < chance){
				balance += bet;
			}
			else {
				balance -= bet;
			}
			if (balance <= 0) {
				break;
			}
		}
		
//		System.out.println(balance);
		return balance;
	
	}
}
