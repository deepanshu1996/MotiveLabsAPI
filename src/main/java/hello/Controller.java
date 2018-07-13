package hello;

import ListAccounts.Account;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/execute")
    public RedirectView execute() throws  IOException {
        AccountsApis accountsApis = new AccountsApis();
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
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authorize", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Account authorize(@RequestParam(name = "code")String code) throws IOException {
        AccountsApis accountsApis = new AccountsApis();
        System.out.println(code);
        String access_token = accountsApis.exchangeCodeForAccessToken(code);
        System.out.println(access_token);
        ArrayList<Account> accounts = accountsApis.listAccounts(access_token);
        System.out.println(accounts.get(0).getAccountId());
        Account account = accounts.get(0);
        return account;
        // Amount amount = accountsApis.getBalance(access_token, account.getAccountId());
    }
//    System.out.println(amount.getAmount());
//        System.out.println(amount.getCurrency());
//        return amount;
//    }

    //@PostMapping

}
