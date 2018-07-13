package ListAccounts;

import java.util.ArrayList;

public class Data {
    ArrayList<Account> Account;

    public Data(ArrayList<Account> accountArrayList) {
        this.Account = accountArrayList;
    }

    public ArrayList<Account> getAccount() {
        return Account;
    }

    public void setAccountArrayList(ArrayList<Account> accountArrayList) {
        this.Account = accountArrayList;
    }
}
