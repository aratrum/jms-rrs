package nl.han.dare2date.service.creditcardValidate;

import nl.han.dare2date.service.jms.util.JMSUtil;
import nl.han.dare2date.service.jms.util.Queues;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class ExternalValidateCreditcardService {
    public static void main(String args[]){
        new ExternalValidateCreditcardService().initialize();
    }

    private void initialize() {
        try{
            Connection conn = JMSUtil.getConnection();
            new ExternalValidateCreditcardReplier(conn, Queues.REQUEST_QUEUE, Queues.INVALID_QUEUE);
        } catch (JMSException e){
            e.printStackTrace();
        } catch (NamingException e){
            e.printStackTrace();
        }
    }
}
