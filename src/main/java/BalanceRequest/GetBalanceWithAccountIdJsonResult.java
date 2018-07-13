package BalanceRequest;

public class GetBalanceWithAccountIdJsonResult {
    Data Data;

    public GetBalanceWithAccountIdJsonResult(BalanceRequest.Data data) {
        Data = data;
    }

    public BalanceRequest.Data getData() {
        return Data;
    }

    public void setData(BalanceRequest.Data data) {
        Data = data;
    }
}
