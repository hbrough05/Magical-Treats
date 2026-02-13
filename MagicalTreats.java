// This program is called Magical Treats and it delievers your favorite 
// Disneyland treats directly to your home!

import java.util.Scanner;

interface Payment {
	double calculateTotal();
}

interface Delivery {
	void scheduleDelivery(Scanner input);
}

class Customer {
	private String name, address;

	public Customer(String n, String a) {
		name = n;
		address = a;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}

class Order {
	protected String foodName;
	protected double foodPrice;
	protected int quantity;
	protected double totalCost;

	public Order(String n, double p, int q) {
		foodName = n;
		foodPrice = p;
		quantity = q;
	}
}

class DeliveryOrder extends Order implements Payment, Delivery {
	private int month, day, year;
	private int deliveryHour, deliveryMinute;

	public DeliveryOrder(String n, double p, int q) {
		super(n, p, q);
	}

	public double calculateTotal() {
		totalCost = foodPrice * quantity;
		return totalCost;
	}

	public void scheduleDelivery(Scanner input) {
		do {
			System.out.print("Enter delivery month (1-12): ");
    			month = input.nextInt();
   			input.nextLine(); 
    			if (month < 1 || month > 12) {
        		System.out.println("Invalid month. Please enter a number between 1 and 12.");
   	 		}
		} while (month < 1 || month > 12);

		do {
    			System.out.print("Enter delivery day: ");
    			day = input.nextInt();
    			input.nextLine(); 

    			boolean validDay = false;
    			if (month == 2 && day >= 1 && day <= 28) validDay = true;
    			else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) validDay = true;
    			else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) validDay = true;

    			if (!validDay) {
        			System.out.println("Invalid day for the selected month. Try again.");
    			} else break; // valid day, exit loop
		} while (true);

	
		System.out.print("Enter delivery year: ");
		year = input.nextInt();
	
		do {
			System.out.print("Enter delivery hour (9-17): ");
			deliveryHour = input.nextInt();
			input.nextLine();

			if (deliveryHour < 9 || deliveryHour > 17) {
				System.out.println("Delivery must be between 9 and 17.");
        		}
        	} while (deliveryHour < 9 || deliveryHour > 17);

		do {
			System.out.print("Enter delivery minute (0-59): ");
    			deliveryMinute = input.nextInt();
    			input.nextLine();
    			if (deliveryMinute < 0 || deliveryMinute > 59) {
        			System.out.println("Minute must be between 0 and 59.");
    			}
		} while (deliveryMinute < 0 || deliveryMinute > 59);
	}
	
	public String getDeliveryInfo() {
		return month + "/" + day + "/" + year + " at " + deliveryHour + ":" + deliveryMinute;
	}
}

class MagicalTreats {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);

		String foodNames[] = {"Mickey Ice Cream Bar", "Dole Whip", "Chocolate Chip Cookies (6 count)", "Churro",
		 "Beignets (3 count)", "Strawberry Fruit Bar", "Mickey Pretzel"};

		double foodPrices[] = {6.50, 8.00, 7.00, 5.50, 9.00, 5.00, 8.50};

		System.out.println("Welcome to Magical Treats!");
		System.out.println("Business Hours: 9:00 AM - 5:00 PM");

		System.out.print("Enter your name: ");
		String name = input.nextLine();

		System.out.print("Enter your delivery address: ");
		String address = input.nextLine(); 
		
		Customer customer = new Customer(name, address);

		System.out.println("\nMenu:");
		for(int i = 0; i < foodNames.length; i++) {
			System.out.println((i + 1) + ". " + foodNames[i] + " - $" + foodPrices[i] + "0");
		}

		int choice;
		do {
			System.out.println("Select a treat (1-7): ");
			choice = input.nextInt();
            		input.nextLine();

			switch (choice) {
                		case 1:
                    			System.out.println("You selected: " + foodNames[0]);
                    			break;
                		case 2:
                    			System.out.println("You selected: " + foodNames[1]);
                    			break;
                		case 3:
                    			System.out.println("You selected: " + foodNames[2]);
                    			break;
                		case 4:
                    			System.out.println("You selected: " + foodNames[3]);
                    			break;
                		case 5:
                    			System.out.println("You selected: " + foodNames[4]);
                    			break;
                		case 6:
                    			System.out.println("You selected: " + foodNames[5]);
                    			break;
                		case 7:
                    			System.out.println("You selected: " + foodNames[6]);
                    			break;
                		default:
                    			System.out.println("Invalid choice. Please select 1-7.");
                    			break;
            		}

        	} while (choice < 1 || choice > 7);
		
		int quantity;
		do {
			System.out.print("Enter the quantity you want: ");
			quantity = input.nextInt();
			input.nextLine();
		} while (quantity <= 0);

		String selectedFood = foodNames[choice - 1];
		double selectedPrice = foodPrices[choice - 1];	

		DeliveryOrder order = new DeliveryOrder(selectedFood, selectedPrice, quantity);
		order.scheduleDelivery(input);
	
		double total = order.calculateTotal();

		System.out.println("\n----- Order Summary -----");
		System.out.println("Name: " + customer.getName());
		System.out.println("Address: " + customer.getAddress());
                System.out.println("Food: " + selectedFood);
                System.out.println("Quantity: " + quantity);
                System.out.println("Total: $" + total + "0");
                System.out.println("Delivery Date & Time: " + order.getDeliveryInfo());

		System.out.println("\nYour order has been placed! Thank you for shopping with Magical Treats! Enjoy!");
	}
}
