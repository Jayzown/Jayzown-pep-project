package Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;

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
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::userRegistrationHandler);
        app.post("/login", this::userLoginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByMessageIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);

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

    private void createMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(),Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage==null){
            context.status(400);
        }else{
            context.status(200);
            context.json(mapper.writeValueAsString(createdMessage));
        }
    }

    private void getAllMessagesHandler(Context context){
        context.json(messageService.getAllMessages());
    }

    private void getMessageByMessageIdHandler(Context context) throws JsonProcessingException{

        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageByMessageId(message_id);

        if(message!=null){
            context.json(message);
        }else{
            context.status(200).json("");
        }
    }

    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException{
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message delMessage = messageService.deleteMessageById(message_id);
        if(delMessage!=null){
            context.status(200).json(delMessage);
        }else{
            context.status(200).json("");
        }
    }

    private void updateMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageById(message_id, message);
    
        if(updatedMessage!=null){
            context.status(200).json(updatedMessage);
        }
        else{
            context.status(400).json("");
        }
    }

    private void getAllMessagesByAccountIdHandler(Context context) throws JsonProcessingException{
        int account_id = Integer.parseInt(context.pathParam("account_id"));        
        
        if(messageService.getMessageByMessageId(account_id)!=null){
            context.status(200).json(messageService.getAllMessagesByUser(account_id));
        }else{
            context.status(200).json("");
        }
  
    }

}