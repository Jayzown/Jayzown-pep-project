package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.AccountService;
import Model.Account;

import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    AccountService accountService;

    public SocialMediaController(){
        this.accountService = new AccountService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::userRegistrationHandler);
        app.post("/login", this::userLoginHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

  


    private void userRegistrationHandler(Context context) throws JsonProcessingException{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(context.body(),Account.class);
            Account addedUser = accountService.userRegistration(account);
            if(addedUser==null){
                context.status(400);
            }else{
                context.status(200);
                context.json(mapper.writeValueAsString(addedUser));
            }
    }

    private void userLoginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account userLoggedIn = accountService.userLogin(account);

        if(userLoggedIn==null){
            context.status(401);
        }else{
            context.status(200);
            context.json(mapper.writeValueAsString(userLoggedIn));
        }
    }

}