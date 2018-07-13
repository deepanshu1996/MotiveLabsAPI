package BalanceRequest;

public class Amount {
    String Amount,Currency;

    public Amount(String amount, String currency) {
        Amount = amount;
        Currency = currency;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
