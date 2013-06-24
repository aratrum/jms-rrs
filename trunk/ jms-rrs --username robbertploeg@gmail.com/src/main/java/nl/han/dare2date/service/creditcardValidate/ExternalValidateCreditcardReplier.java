package nl.han.dare2date.service.creditcardValidate;

import nl.han.dare2date.applyregistrationservice.Creditcard;
import nl.han.dare2date.service.jms.util.Replier;
import nl.han.dare2date.service.jms.util.ValidateCreditcardRequest;
import nl.han.dare2date.service.jms.util.ValidateCreditcardResponse;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class ExternalValidateCreditcardReplier extends Replier{
    private Creditcard card;

    protected ExternalValidateCreditcardReplier(Connection connection, String requestQueueName, String invalidQueueName)
        throws JMSException, NamingException {
        super(connection, requestQueueName, invalidQueueName);

    }

    @Override
    public ObjectMessage getReplyMessage() {
        ObjectMessage msg = null;
        try{
            msg = getSession().createObjectMessage();
            boolean isValid = false;
            if(card.getCvc() == 1234 && card.getNumber() == 4321){
                isValid = true;
            }
            ValidateCreditcardResponse response = new ValidateCreditcardResponse(isValid);
            msg.setObject(response);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void handleMessage(Serializable contents) {
        ValidateCreditcardRequest request = (ValidateCreditcardRequest) contents;
        this.card = request.getCard();
    }
}
