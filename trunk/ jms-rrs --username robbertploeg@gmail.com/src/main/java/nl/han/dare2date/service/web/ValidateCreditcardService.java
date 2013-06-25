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

        System.out.println("inside cc 1");
        Connection conn = JMSUtil.getConnection();

        System.out.println(conn.toString());
        System.out.println("inside cc 2");
        String requestQueueName = Queues.REQUEST_QUEUE;
        String replyQueueName = Queues.REPLY_QUEUE;
        String invalidQueueName = Queues.INVALID_QUEUE;

        System.out.println("inside cc 3");
        Creditcard card = new Creditcard();
        card.setCvc(cc.getCvc());
        card.setNumber(cc.getNumber());
        card.setValidThrough(cc.getValidThrough());

        System.out.println("inside cc 4");
        try{
            System.out.println("inside cc 5");
            System.out.println(card.getNumber());
            System.out.println(card.getCvc());
            System.out.println(card.getValidThrough());
            System.out.println(requestQueueName);
            System.out.println(replyQueueName);
            System.out.println(invalidQueueName);
            ValidateCreditcardRequestor requestor = new ValidateCreditcardRequestor(conn, requestQueueName, replyQueueName, invalidQueueName, card);
            System.out.println(requestor);

            System.out.println("inside cc 5.5");
            requestor.send();
            System.out.println("inside cc 6");
            requestor.receiveSync();
            ValidateCreditcardResponse response = requestor.getResponse();
            System.out.println("inside cc 7");
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