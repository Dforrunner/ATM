import java.util.InputMismatchException;
import java.util.Scanner;

/*

This program is currently a simple ATM program. It doesn't necessarily function as a typical ATM because it's more
like a program that keeps track of your wallet, but I'm calling it an ATM anyways because I might work on it more to make
it behave more like a ATM.
 */

public class ATM {
    private boolean userAuthenticated;

    public static void main(String[] args){
        ATM ATM = new ATM(); //create ATM object
        User[] user = new User[5]; //Creating an object array of size 5

        //Creating 5 users because we will need user accounts in order to validate user info and also provide user info
        user[0] = new User("Bob", 1234, 254.38);
        user[1] = new User("Ali", 0000, 654.50);
        user[2] = new User("Ana", 1111, 6544.44);
        user[3] = new User("Bee", 2222, 6874.45);
        user[4] = new User("Liz", 3333, 12540.55);

        int userIndex = 0; //user index initialized
        ATM.setUserAuthenticated(false);
        //Initialize scanner to take user input
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean tryAgain = false;

        //The if statement will check if the user is authenticated if not it will first display login page
        // If they login successfully then they will gain access to their account info
        if(!ATM.isUserAuthenticated()) {
            do {
                try{
                    System.out.println("\nWorld Bank ATM" +
                            "\nPlease choose one of the following options by entering its number:" +
                            "\n 1 : Login" +
                            "\n 2 : Exit");
                    System.out.print(" >>> ");
                    choice = sc.nextInt(); //get user input
                    sc.nextLine(); //consume \n. It's necessary because otherwise the scanner will skip the next string input request

                    //Validate user input so to make sure they just enter 1 or 2
                    while(choice != 1 && choice != 2){
                        System.out.println("Wrong Input. Try Again");
                        System.out.print(" >>> ");
                        choice = sc.nextInt();
                    }
                }catch (InputMismatchException e){ //catch exception if user enters something other than a int

                    //keep asking the user to for input until they enter an acceptable input which is 1 or 2 in this case
                    System.out.println("Wrong Input. Try Again");
                    sc.next();
                    do{
                        System.out.print(" >>> ");
                        choice = sc.nextInt();
                    }while (choice != 1 && choice != 2);
                }

                if (choice == 1) {
                    System.out.println("\nPlease enter the info below to gain access:");
                    //Initializing user input variables
                    String usernameInput = "";
                    int pinInput = 0;

                    // The code block below checks for exceptions
                    // If no exceptions are thrown the loop exits meaning the inputs are acceptable types
                    // If they are not then it will keep looping
                    do {
                        try {
                            //request and get username and pin from the user
                            System.out.print(" Username: ");
                            usernameInput = sc.nextLine();
                            System.out.print(" Pin: ");
                            pinInput = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong Input. Try Again");
                            sc.next();
                            tryAgain = true;
                        }
                    }while (tryAgain == true);

                    //Loop through the array of users and check to see if the username and pin match with any of the users
                    // If they match then authenticate user
                    for (int i = 0; i < user.length; i++) {
                        if (user[i].getUserName().equals(usernameInput) && user[i].getUserPin() == pinInput) {
                            ATM.setUserAuthenticated(true);
                            userIndex = i;
                            break;
                        }
                    }

                    if (!ATM.isUserAuthenticated()) {
                        System.out.println("\nWrong Username or Pin." +
                                "\nPlease choose one of the following options:" +
                                "\n 1 : Try Again" +
                                "\n 2 : Exit ");
                        System.out.print(" >>> ");
                        choice = sc.nextInt();
                        //TODO: validate user input and handle exceptions
                        if(choice == 2){
                            System.exit(0);
                        } else {
                            tryAgain = true;
                        }
                    }

                } else if (choice == 2) { //if the user chooses option 2 exit the program
                    System.out.print("Goodbye!");
                    System.exit(0);
                }
            }while(tryAgain);
        }


        if(ATM.isUserAuthenticated()){

            do {
                //TODO: validate user input and handle exceptions
                System.out.println("\nWelcome " + user[userIndex].getUserName() + "!" +  //<------------Greet User
                        "\n\nYour current balance is: $" + user[userIndex].getAccoutnBalance() + //<----Show them their current balance
                        "\n\nPlease choose one of the following options by entering its number:" +
                        "\n 1 : Deposit Money" +
                        "\n 2 : Withdraw Money" +
                        "\n 3 : Exit");
                System.out.print(" >>> ");
                choice = sc.nextInt(); //get user input

                //Validate user input
                while (choice != 1 && choice != 2 && choice != 3){
                    System.out.println("Incorrect input. Please enter 1, 2 or 3." );
                    choice = sc.nextInt();
                }

                // Store user balance before it is deposited or withdrawn from.
                // The main use of this variable is to show the visual calculation
                double balanceBeforeDepositOrWithdraw = user[userIndex].getAccoutnBalance();
                if (choice == 1) {

                    System.out.print("Enter deposit amount: $");
                    double depositAmount = sc.nextDouble(); //Ask user to enter a deposit amount
                    //add the amount entered by the user onto the users current balance
                    user[userIndex].setAccoutnBalance(user[userIndex].getAccoutnBalance() + depositAmount);
                    //return users current balance
                    System.out.println("\n Balance before deposit: $" + balanceBeforeDepositOrWithdraw +
                                       "\n Deposited amount: $" + depositAmount +
                                       "\n ------------------------------" +
                                       "\n Total Current Balance: $" + String.format("%.2f", user[userIndex].getAccoutnBalance()));

                } else if (choice == 2) {
                    boolean enterNewAmount = false;
                    do {
                        System.out.print("Enter withdraw amount: $");
                        double withdrawAmount = sc.nextDouble();

                        if(withdrawAmount < balanceBeforeDepositOrWithdraw) {
                            user[userIndex].setAccoutnBalance(user[userIndex].getAccoutnBalance() - withdrawAmount);
                            System.out.println("\n Balance before deposit: $" + balanceBeforeDepositOrWithdraw +
                                    "\n Withdraw amount: -( $" + withdrawAmount + " )" +
                                    "\n ------------------------------" +
                                    "\n Total Current Balance: $" + String.format("%.2f", user[userIndex].getAccoutnBalance()));

                        }else{
                            System.out.println("\n You don't have enough money in your account." +
                                               "\n You currently have: $" + balanceBeforeDepositOrWithdraw +
                                               "\n Would you like to enter a new amount?" +
                                               "\n 1 : Yes" +
                                               "\n 2 : No (Exit) ");
                            System.out.print(" >>> ");
                            sc.nextLine();
                            choice = sc.nextInt();
                            if(choice == 1){
                                enterNewAmount = true;
                            }else{
                                System.out.println("Goodbye!");
                                System.exit(0);
                            }
                        }

                    }while(enterNewAmount == true);

                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                }

            }while(choice != 3);
        }

    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }
    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }
}

