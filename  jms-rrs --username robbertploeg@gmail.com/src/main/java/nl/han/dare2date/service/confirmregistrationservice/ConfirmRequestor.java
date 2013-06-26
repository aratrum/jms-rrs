package nl.han.dare2date.service.confirmregistrationservice;

import nl.han.dare2date.applyregistrationservice.Registration;
import nl.han.dare2date.service.jms.util.ConfirmRequest;
import nl.han.dare2date.service.jms.util.Requestor;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import java.io.Serializable;

/**
 * User: Robbert
 * Date: 26-6-13
 * Time: 12:45
 */
public class ConfirmRequestor extends Requestor {
    Registration reg;

    public ConfirmRequestor(Connection connection, String requestQueueName, String replyQueueName, String invalidQueueName, Registration reg) throws JMSException, NamingException {
        super(connection, requestQueueName, replyQueueName, invalidQueueName);
        this.reg = reg;
    }

    @Override
    public ObjectMessage getObjectMessage() {
        ObjectMessage msg;
        try {
            msg = getSession().createObjectMessage();
            msg.setObject(new ConfirmRequest(reg));
            return msg;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Serializable getResponse() {
        return null;
    }

}
