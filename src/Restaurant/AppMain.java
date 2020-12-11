package Restaurant;

import java.util.Scanner;

public class AppMain {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Restaurant r = new Restaurant();
		System.out.println("==============   " + r.getRestaurantName() + "   ================");
		System.out.println("  ============     " + r.getRestaurantAddress() + "    ==============");
		System.out.println();
		r.appMain();
		
	}
}
