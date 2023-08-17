package Service;
import DAO.MessageDAO;
import java.util.List;
import Model.Message;
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
        if((message.getMessage_text()!="")&&(message.getMessage_text().length()<=255)){
            System.out.println("Here?");
            return messageDAO.createMessage(message);
        }return null;
    }

    public List<Message> getAllMessages (){
        return messageDAO.getAllMessages();
    }
}
