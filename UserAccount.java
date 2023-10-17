package uqu;

public class UserAccount {
    private String password;
    private String[][] accounts = {{" ", "1234"}};

    public UserAccount(String password) {
        this.password = password;
    }

    public boolean checkPassword() {
        return password.equals(accounts[0][1]);
    }

    public static class IncorrectPasswordException extends Exception {
        public IncorrectPasswordException() {
            super("The password you entered is incorrect.");
        }
    }

    public static boolean login(UserAccount account) throws IncorrectPasswordException {
        if (account.checkPassword()) {
            return true;
        } else {
            throw new IncorrectPasswordException();
        }
    }
}