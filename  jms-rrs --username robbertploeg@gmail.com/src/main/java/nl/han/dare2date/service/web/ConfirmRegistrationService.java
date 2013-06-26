package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.Registration;
import nl.han.dare2date.service.confirmregistrationservice.ConfirmRequestor;
import nl.han.dare2date.service.jms.util.JMSUtil;
import nl.han.dare2date.service.jms.util.Queues;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * @author mdkr
 *         <p/>
 *         Is used as a JMS publisher
 */
public class ConfirmRegistrationService {
    public void confirm(Registration reg) {
        Connection conn = JMSUtil.getConnection();
        System.out.println(conn.toString());

        Registration registration = new Registration();
        registration.setSuccesFul(registration.isSuccesFul());
        registration.setNumber(registration.getNumber());
        registration.setDate(registration.getDate());

        String confirmQueue = Queues.CONFIRM_QUEUE;
        String replyQueue = Queues.REPLY_QUEUE;
        String invalidQueueName = Queues.INVALID_QUEUE;

        try{
            System.out.println(replyQueue);
            System.out.println(confirmQueue);
            System.out.println(invalidQueueName);
            ConfirmRequestor requestor = new ConfirmRequestor(conn, replyQueue, confirmQueue, invalidQueueName, registration);
            System.out.println(requestor);

            requestor.send();
            requestor.receiveSync();

            conn.close();
        } catch(JMSException e){
            e.printStackTrace();
        } catch(NamingException e){
            e.printStackTrace();
        }

    }
}
