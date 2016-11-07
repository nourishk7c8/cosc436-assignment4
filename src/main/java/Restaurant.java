/**
 * @author Nourish Khan (nkhan2@students.towson.edu)
 */

import java.util.Scanner;

public class Restaurant {
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Menu menu;
        int choice;

        menu = createMenu();

        do {
            displayMenu();
            choice = getInt();
            System.out.println();
            executeMenuAction(menu, choice);
        } while (choice != 0);

        sc.close();
    }//end main

    private static void displayMenu() {
        System.out.println("1 - Display all menu items");
        System.out.println("2 - Display all appetizers");
        System.out.println("3 - Display all main dishes");
        System.out.println("4 - Display all desserts");
        System.out.println("5 - Display all hearty healthy items");
        System.out.println("6 - Display all main dishes under a specified price");
        System.out.println("7 - Add menu item");
        System.out.println("8 - Remove menu item");
        System.out.println("0 - Exit");
    }//end method displayMenu

    private static Menu createMenu() {
        Menu menu = new Menu();
        menu.add(new MenuItem("Rawr", MenuItemType.APPETIZER, Menu.NOT_HEART_HEALTHY, 3.45));
        menu.add(new MenuItem("Meep", MenuItemType.APPETIZER, Menu.HEART_HEALTHY, 5.65));
        menu.add(new MenuItem("LAWLZ", MenuItemType.MAIN_DISH, Menu.NOT_HEART_HEALTHY, 34.45));
        menu.add(new MenuItem("Durr", MenuItemType.MAIN_DISH, Menu.HEART_HEALTHY, 14.45));
        menu.add(new MenuItem("What", MenuItemType.MAIN_DISH, Menu.HEART_HEALTHY, 21.00));
        menu.add(new MenuItem("Derp", MenuItemType.MAIN_DISH, Menu.NOT_HEART_HEALTHY, 17.45));
        menu.add(new MenuItem("Gelato", MenuItemType.DESSERT, Menu.HEART_HEALTHY, 8.30));
        menu.add(new MenuItem("Why", MenuItemType.DESSERT, Menu.NOT_HEART_HEALTHY, 14.45));

        return menu;
    }//end method createMenu

    private static void executeMenuAction(Menu menu, int choice) {
        MenuIterator itr;
        switch (choice) {
            case 0:
                System.out.println("Thank you for using this menu system.\n"
                        + "Goodbye!");
                break;
            case 1:
                itr = menu.getAllItemsIterator();
                display("ALL MENU ITEMS:", itr);
                break;
            case 2:
                itr = menu.getItemIterator(MenuItemType.APPETIZER.getCode());
                display("ALL APPETIZERS:", itr);
                break;
            case 3:
                itr = menu.getItemIterator(MenuItemType.MAIN_DISH.getCode());
                display("ALL MAIN DISHES:", itr);
                break;
            case 4:
                itr = menu.getItemIterator(MenuItemType.DESSERT.getCode());
                display("ALL DESSERTS:", itr);
                break;
            case 5:
                itr = menu.getHeartHealthyIterator();
                display("ALL HEART HEALTHY ITEMS:", itr);
                break;
            case 6:
                System.out.println("Enter maximum price value:");
                String maxPrice = "" + getDouble();
                itr = menu.getPriceIterator(maxPrice);
                display(("ALL ITEMS UNDER $" + maxPrice), itr);
                break;
            case 7:
                addMenuItem(menu, sc);
                break;
            case 8:
                itr = menu.getAllItemsIterator();
                removeMenuItem(itr, menu);
                break;
            default:
                System.out.println("Invalid Option!");

        }//end switch
    }//end executeMenuAction

    private static void display(String header, MenuIterator itr) {
        MenuItem item;
        System.out.println(header);
        while (itr.hasNext()) {
            item = itr.next();
            System.out.println(item.getItemName() + "\n$" + item.getPrice() + "\n");
        }//end while
    }//end display

    private static void addMenuItem(Menu menu, Scanner sc) {
        System.out.println("Enter the name of the menu item:");
        String itemName = sc.nextLine();
        System.out.println("Enter the category of the menu item:");
        MenuItemType type = getCategoryInput();
        System.out.println("Heart Healthy? ");
        boolean heartHealthy = getYesOrNo();
        System.out.println("Enter the price of the menu item:");
        double price = getDouble();
        menu.add(new MenuItem(itemName, type, heartHealthy, price));
    }

    private static void removeMenuItem(MenuIterator itr, Menu menu) {
        MenuItem item = null;
        System.out.println("ALL MENU ITEMS:");
        System.out.println("Press <d> to delete or <Enter> to move to the next item:\n");
        char delete = '.';
        String s;
        while (itr.hasNext() && (delete != 'd' && delete != 'D')) {
            item = itr.next();
            System.out.println(item.getItemName());
            s = sc.nextLine();
            delete = s.length() > 0 ? s.charAt(0) : '-';
        }//end while
        if (delete == 'd' || delete == 'D') {
            menu.delete(item);
            System.out.println("Item has been deleted.");
        } else
            System.out.println("You have chosen to delete no item.");
    }//end method removeMenuItem

    private static int getInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Must be an integer!");
        }
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }//end method getInt

    private static double getDouble() {
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.println("Must be an double!");
        }
        double d = sc.nextDouble();
        sc.nextLine();
        return d;
    }//end method getDouble


    private static MenuItemType getCategoryInput() {
        int cat;
        do {
            System.out.println("Categories:");
            for(MenuItemType type : MenuItemType.values()){
                System.out.println(type.getCode() + " - " + type.getName());
            }
            cat = getInt();
        } while (!validCategory(cat));
        return MenuItemType.getType(cat);
    }//end method getCategoryInput

    private static boolean validCategory(int n) {
        for(MenuItemType item : MenuItemType.values()){
            if(n == item.getCode()) return true;
        }
        return false;
    }//end method validCategory

    private static boolean getYesOrNo() {
        char ans;
        do {
            System.out.println("Enter y/n");
            ans = sc.next().charAt(0);
        } while (ans != 'y' && ans != 'Y' && ans != 'n' && ans != 'N');
        sc.nextLine();
        return (ans == 'y' || ans == 'Y');
    }//end method getYesOrNo

}//end class
