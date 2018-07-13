package ListAccounts;

public class ListAccountsJsonResult {
    Data Data;

    public ListAccountsJsonResult(Data data) {
        this.Data = data;
    }

    public Data getData() {
        return Data;
    }

    public void setData(Data data) {
        this.Data = data;
    }
}
