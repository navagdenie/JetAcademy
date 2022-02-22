package machine;

import java.util.Locale;
import java.util.Scanner;

public class CoffeeMachine {

    enum States {
        CHOOSING_AN_ACTION, CHOOSING_A_VARIANT_OF_COFFEE, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUPS
    }

    enum Acts {
        BUY, FILL, TAKE, REMAINING
    }

    private static int money = 550;
    private static int water = 400;
    private static int milk = 540;
    private static int coffee = 120;
    private static int cups = 9;
    private static States state = States.CHOOSING_AN_ACTION;


    private static void process(String inputStr) {

        switch (state) {
            case CHOOSING_AN_ACTION:

                switch (Acts.valueOf(inputStr.toUpperCase(Locale.ROOT))) {
                    case BUY:
                        state = States.CHOOSING_A_VARIANT_OF_COFFEE;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                        break;
                    case FILL:
                        state = States.FILL_WATER;
                        System.out.println("Write how many ml of water you want to add:");
                        break;
                    case TAKE:
                        state = States.CHOOSING_AN_ACTION;
                        System.out.println("I gave you $" + money);
                        money = 0;
                        break;
                    case REMAINING:
                        state = States.CHOOSING_AN_ACTION;
                        System.out.println("The coffee machine has:");
                        System.out.println(water + " ml of water");
                        System.out.println(milk + " ml of milk");
                        System.out.println(coffee + " g of coffee beans");
                        System.out.println(cups + " disposable cups");
                        System.out.println("$" + money + " of money");
                        break;
                }
                break;

            case CHOOSING_A_VARIANT_OF_COFFEE:
                state = States.CHOOSING_AN_ACTION;
                switch (inputStr) {
                    case "1": //espresso
                        if (water < 250) {
                            System.out.println("Sorry, not enough water!");
                        } else if (coffee < 16) {
                            System.out.println("Sorry, not enough coffee");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups");
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            money += 4;
                            water -= 250;
                            coffee -= 16;
                            cups--;
                        }
                        break;
                    case "2": //latte
                        if (water < 350) {
                            System.out.println("Sorry, not enough water!");
                        } else if (milk < 75) {
                            System.out.println("Sorry, not enough milk!");
                        } else if (coffee < 20) {
                            System.out.println("Sorry, not enough coffee!");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups!");
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            money += 7;
                            water -= 350;
                            milk -= 75;
                            coffee -= 20;
                            cups--;
                        }
                        break;
                    case "3": //cappuccino
                        if (water < 200) {
                            System.out.println("Sorry, not enough water!");
                        } else if (milk < 100) {
                            System.out.println("Sorry, not enough milk!");
                        } else if (coffee < 12) {
                            System.out.println("Sorry, not enough coffee!");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups!");
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            money += 6;
                            water -= 200;
                            milk -= 100;
                            coffee -= 12;
                            cups--;
                        }
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("error!");
                        state = States.CHOOSING_AN_ACTION;
                        break;
                }
                break;

            case FILL_WATER:
                water += Integer.parseInt(inputStr);
                state = States.FILL_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                break;

            case FILL_MILK:
                milk += Integer.parseInt(inputStr);
                state = States.FILL_COFFEE;
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;

            case FILL_COFFEE:
                coffee += Integer.parseInt(inputStr);
                state = States.FILL_CUPS;
                System.out.println("Write how many disposable cups of coffee you want to add:");
                break;

            case FILL_CUPS:
                cups += Integer.parseInt(inputStr);
                state = States.CHOOSING_AN_ACTION;
                break;
        }

        if (state == States.CHOOSING_AN_ACTION) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Write action (buy, fill, take, remaining, exit): ");

        while (true) {

            String act = scanner.next();
            if (act.equals("exit")) {
                break;
            } else {
                CoffeeMachine.process(act);
            }
        }
    }

}


