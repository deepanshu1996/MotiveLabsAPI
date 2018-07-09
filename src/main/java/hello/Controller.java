package hello;

import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    AccountsApis accountsApis = new AccountsApis();
    @RequestMapping("/execute")
    public RedirectView execute() throws  IOException {
        String access_token = accountsApis.getAccessToken();
        System.out.println(access_token);
        String request_id = accountsApis.postAccountRequest(access_token);
        System.out.println(request_id);
        final String url = accountsApis.redirectUserToConsentAuth(request_id);
        System.out.println(url);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
    @RequestMapping(value = "/authorize")
    public Response authorize(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state, @RequestParam(name = "id_token")String id_token) throws IOException {
        System.out.println(code);
        String access_token = accountsApis.exchangeCodeForAccessToken(code);
        System.out.println(access_token);
        Response response = accountsApis.listAccounts(access_token);
        System.out.println(response.body().string());
        return response;

    }


}
