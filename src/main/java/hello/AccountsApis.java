package pennybank.tset;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;

class AccountsApis {

    private final String REDIRECT_URI = "http%3A%2F%2Flocalhost%3A8080";
    private final String CLIENT_ID = "tTKoOUTwq5WOUQBhoheayGbwtkR7uEHA";
    private final String CLIENT_SECRET = "HSzRmYCVmjuOv1VKGaDuoQOuBmX7j53t";
    private final String BASE_URL = "https://api.pennybank.motivelabs.io/cma9-account-api/open-banking/v1.1";
    private final String CONSENT_URL = "https://api.pennybank.motivelabs.io/consent/v1.0/cma9-account";

    String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormEncodingBuilder().add("grant_type", "client_credentials")
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET).build();
        Request request = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(BASE_URL+"/oauth2/token")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        Gson gson = new Gson();
        AccessTokenResultJson resultJson = gson.fromJson(result, AccessTokenResultJson.class);
        return resultJson.getAccess_token();
    }
    String postAccountRequest(String access_token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String bodyString;
        bodyString = "{\n" +
                "  \"Data\": {\n" +
                "    \"Permissions\": [\n" +
                "      \"ReadAccountsDetail\",\n" +
                "      \"ReadBalances\",\n" +
                "      \"ReadTransactionsCredits\",\n" +
                "      \"ReadTransactionsDebits\",\n" +
                "      \"ReadTransactionsDetail\",\n" +
                "      \"ReadBeneficiariesBasic\",\n" +
                "      \"ReadBeneficiariesDetail\",\n" +
                "      \"ReadDirectDebits\",\n" +
                "      \"ReadStandingOrdersDetail\",\n" +
                "      \"ReadProducts\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"Risk\": {}\n" +
                "}";
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,bodyString);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer "+ access_token)
                .addHeader("x-fapi-financial-id", "TBD")
                .addHeader("Content-Type", "application/json")
                .url(BASE_URL+"/account-requests")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        Gson gson = new Gson();
        RequestIdResultJson resultJson = gson.fromJson(result, RequestIdResultJson.class);
        return resultJson.getData().getAccountRequestId();
    }
    String redirectUserToConsentAuth(String request_id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CONSENT_URL+"/authorize?" +
                        "client_id="+CLIENT_ID +
                        "&response_type=code%20id_token" +
                        "&scope=openid%20accounts" +
                        "&redirect_uri="+REDIRECT_URI +
                        "&state=ABC" +
                        "&request="+request_id)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.request().url().toString();
    }
    String exchangeCodeForAccessToken(String code) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormEncodingBuilder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                //.add("redirect_uri",REDIRECT_URI)
                .add("grant_type","authorization_code")
                .add("code",code)
                .build();
        //"http://localhost:8080/authorize"
        Request request = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(BASE_URL+"/oauth2/token")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        Gson gson = new Gson();
        AccessTokenResultJson resultJson = gson.fromJson(result, AccessTokenResultJson.class);
        return resultJson.getAccess_token();
    }
    String listAccounts(String access_token) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer "+ access_token)
                .addHeader("x-fapi-financial-id", "TBD")
                .url(BASE_URL+"/accounts")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
