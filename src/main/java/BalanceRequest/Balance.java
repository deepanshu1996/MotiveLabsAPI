package BalanceRequest;

public class Balance {
    private static BalanceRequest.Amount Amount;

    public Balance(BalanceRequest.Amount amount) {
        Amount = amount;
    }

    public BalanceRequest.Amount getAmount() {
        return Amount;
    }

    public void setAmount(BalanceRequest.Amount amount) {
        Amount = amount;
    }
}
