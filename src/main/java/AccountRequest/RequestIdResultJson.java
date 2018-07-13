package AccountRequest;

public class RequestIdResultJson {
    private AccountRequest.Data Data;

    public RequestIdResultJson(Data data) {
        Data = data;
    }

    public Data getData() {
        return Data;
    }

    public void setData(Data data) {
        Data = data;
    }
}
