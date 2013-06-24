package nl.han.dare2date.service.jms.util;

import nl.han.dare2date.applyregistrationservice.Creditcard;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class ValidateCreditcardRequestor extends Requestor {
    private Creditcard card;
    private ValidateCreditcardResponse response;
    private ObjectMessage msg;

    public ValidateCreditcardRequestor(Connection connection, String requestQueueName, String replyQueueName, String invalidQueueName, Creditcard card) throws JMSException, NamingException {
        super(connection, requestQueueName, replyQueueName, invalidQueueName);
        this.card = card;
    }

    /*public void send() throws JMSException {
        super.send();
    }*/

    @Override
    public ObjectMessage getObjectMessage() {
        ObjectMessage msg;
        try{
            msg = getSession().createObjectMessage();
            msg.setObject(new ValidateCreditcardRequest(card));
            return msg;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void receiveSync() throws JMSException {
        super.receiveSync();
    }*/

    public ValidateCreditcardResponse getResponse() {
        ObjectMessage replyMessage = getReplyMessage();
        try{
            return (ValidateCreditcardResponse) replyMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }
}