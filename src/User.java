public class User {
    private String userName;
    private int userPin;
    private double accoutnBalance;

    public User() {
    }
    public User(String userName, int userPin, double accoutnBalance) {
        this.userName = userName;
        this.userPin = userPin;
        this.accoutnBalance = accoutnBalance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPin() {
        return userPin;
    }

    public void setUserPin(int userPin) {
        this.userPin = userPin;
    }

    public double getAccoutnBalance() {
        return accoutnBalance;
    }

    public void setAccoutnBalance(double accoutnBalance) {
        this.accoutnBalance = accoutnBalance;
    }
}
