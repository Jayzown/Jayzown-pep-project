package Service;
import DAO.MessageDAO;
import java.util.List;
import Model.Message;

import java.sql.SQLException;
import java.util.*;

public class MessageService {
    MessageDAO messageDAO;

    /*------------------------------------------------------------------------------------------------------------------ */
    /*Constructors for MessageService. One in which no arguments are taken and the other where an MessageDAO object is */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    /*------------------------------------------------------------------------------------------------------------------- */
    
    public Message createMessage(Message message){
        if((message.getMessage_text()!="")&&(message.getMessage_text().length()<255)){
            System.out.println("Here?");
            return messageDAO.createMessage(message);
        }return null;
    }

    public List<Message> getAllMessages (){
        return messageDAO.getAllMessages();
    }

    public Message getMessageByMessageId (int id){
        return messageDAO.getMessageByMessageId(id);
    }

    public Message deleteMessageById (int id){
        Message retMsg = null;
        if(getMessageByMessageId(id)!=null){
            retMsg = getMessageByMessageId(id);
            messageDAO.deleteMessageById(id);
        }return retMsg;
    }
    
    public Message updateMessageById (int id, Message message){
        if((message.getMessage_text()!="")&&(message.getMessage_text().length()<255)&&(getMessageByMessageId(id)!=null)){
            messageDAO.updateMessageById(id, message);
            return getMessageByMessageId(id);
        }return null;
    }

    public List<Message> getAllMessagesByUser(int id){
        // System.out.println(messageDAO.getAllMessagesByUser(id));
        return messageDAO.getAllMessagesByUser(id);
    }
}
