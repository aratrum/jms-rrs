package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.Registration;
import nl.han.dare2date.service.jms.util.JMSUtil;
import nl.han.dare2date.service.jms.util.Queues;

import javax.jms.Connection;
import javax.jms.Destination;

/**
 * @author mdkr
 *         <p/>
 *         Is used as a JMS publisher
 */
public class ConfirmRegistrationService {
    public void confirm(Registration reg) {
        Connection conn = JMSUtil.getConnection();
        Destination confirmationQueue = JMSUtil.getDestinationForQueue(Queues.CONFIRM_QUEUE);

    }
}
