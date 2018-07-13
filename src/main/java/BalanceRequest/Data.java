package BalanceRequest;

import java.util.ArrayList;

public class Data {
    ArrayList<Balance> Balance;

    public Data(ArrayList<BalanceRequest.Balance> balance) {
        Balance = balance;
    }

    public ArrayList<BalanceRequest.Balance> getBalance() {
        return Balance;
    }

    public void setBalance(ArrayList<BalanceRequest.Balance> balance) {
        Balance = balance;
    }
}
