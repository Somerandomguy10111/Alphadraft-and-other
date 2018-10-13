package classes;

public class Statistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] resultsV1 = new int[1010];
		int[] resultsV2 = new int[1010];
		double avgsv1 = 0;
		double avgsv2 = 0;
		
		for (int i = 1 ;i <= 1000;i++){
			resultsV1[i] = Ver1();
			resultsV2[i] = Ver2();
			avgsv1 += resultsV1[i];
			avgsv2 += resultsV2[i];
		}
		avgsv1 = avgsv1 / 1000;
		avgsv2 = avgsv2 / 1000;
		
		System.out.println();
		System.out.println("Version 1: " + avgsv1);
		System.out.println("Version 2: " + avgsv2);
	}

	
	public static int Ver1(){
		int balance = 10;
		double bet = balance/5;
		double chance = 0.5;
		
		for (int i = 1;i <= 100; i++){
			if (Math.random() < chance){
				balance += bet;
			}
			else {
				balance -= bet;
			}
		}
		
		System.out.println(balance);
		return balance;
	}
	
	
	public static int Ver2(){
		int balance = 10;
		
		double chance = 0.5;
		
		for (int i = 1;i <= 100; i++){
			double bet = balance/5;
			if (Math.random() < chance){
				balance += bet;
			}
			else {
				balance -= bet;
			}
		}
		
		System.out.println(balance);
		return balance;
	
	}
	
}
