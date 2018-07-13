package ListAccounts;

public class Account {
    String AccountId,Currency,Nickname;

    public Account(String accountId, String currency, String nickname) {
        AccountId = accountId;
        Currency = currency;
        Nickname = nickname;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }
}
