import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/*

This program is currently a simple ATM program. It doesn't necessarily function as a typical ATM, but
the idea is close to it.
 */
public class main {
    public static void main(String[] args){
        ATM ATM = new ATM(); //create ATM object
        User[] user = new User[5];

        //Creating 5 users because we will need user accounts in order to validate user info and also provide user info
        user[0] = new User("Bob", 1234, 254.38);
        user[1] = new User("Ali", 0000, 654.50);
        user[2] = new User("Ana", 1111, 6544.44);
        user[3] = new User("Bee", 2222, 6874.45);
        user[4] = new User("Liz", 3333, 12540.55);


        //Initialize scanner to take user input
        Scanner sc = new Scanner(System.in);
        int choice;
        //TODO: implement user input validation

        //The if statement will check if the user is authenticated if not it will first display login page
        // If they login successfully then they will gain access to their account info
        if (!ATM.isUserAuthenticated()) {

            System.out.println("\nWorld Bank ATM" +
                    "\nPlease choose one of the following options by entering its number:" +
                    "\n 1 : Login" +
                    "\n 2 : Exit" );
            System.out.print(" >>>");
            choice = sc.nextInt(); //get user input
            sc.nextLine(); //consume \n. It's necessary because otherwise the scanner will skip the next string input request

            if ( choice == 1){
                System.out.println("\nPlease enter the info below to gain access:" );
                //request and get username and pin from the user
                System.out.print(" Username: ");
                String usernameInput = sc.nextLine();
                System.out.print(" Pin: ");
                int pinInput = sc.nextInt();

                //TODO: validate user input
                //TODO: if validated make userAuthenticated = true else tell user to try again or exit

            } else if (choice == 2){ //if the user chooses option 2 exit the program
                System.out.print("Goodbye!");
                System.exit(0);
            }

        }

        if(ATM.isUserAuthenticated()){

            do {
                System.out.println("\n Welcome " + user[1].getUserName() + "!" +
                        "\n Please choose one of the following options by entering its number:" +
                        "\n 1 : Deposit Money" +
                        "\n 2 : Withdraw Money" +
                        "\n 3 : Exit");
                choice = sc.nextInt();

                while (choice != 1 && choice != 2 && choice != 3){
                    System.out.println("Incorrect input. Please enter 1, 2 or 3." );
                    choice = sc.nextInt();
                }

                //TODO: implement the proper methods and statements
                if (choice == 1) {

                } else if (choice == 2) {

                } else {
                    System.exit(0);
                }

            }while(choice != 3);
        }

    }

}

