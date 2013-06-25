package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.Creditcard;
import nl.han.dare2date.service.jms.util.*;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * 
 * @author mdkr
 *
 * Is used as a JMS client using request-reply
 */
public class ValidateCreditcardService {
	public boolean validate(Creditcard cc) {

        Connection conn = JMSUtil.getConnection();

        System.out.println(conn.toString());
        String requestQueueName = Queues.REQUEST_QUEUE;
        String replyQueueName = Queues.REPLY_QUEUE;
        String invalidQueueName = Queues.INVALID_QUEUE;

        Creditcard card = new Creditcard();
        card.setCvc(cc.getCvc());
        card.setNumber(cc.getNumber());
        card.setValidThrough(cc.getValidThrough());

        try{
            System.out.println(card.getNumber());
            System.out.println(card.getCvc());
            System.out.println(card.getValidThrough());
            System.out.println(requestQueueName);
            System.out.println(replyQueueName);
            System.out.println(invalidQueueName);
            ValidateCreditcardRequestor requestor = new ValidateCreditcardRequestor(conn, requestQueueName, replyQueueName, invalidQueueName, card);
            System.out.println(requestor);

            requestor.send();
            requestor.receiveSync();
            ValidateCreditcardResponse response = requestor.getResponse();
            conn.close();
            return response.isValid();
        } catch(JMSException e){
            e.printStackTrace();
        } catch(NamingException e){
            e.printStackTrace();
        }
		return false;
	}
}