package nl.han.dare2date.service.jms.util;

import nl.han.dare2date.service.confirmregistrationservice.Notification1;
import nl.han.dare2date.service.confirmregistrationservice.Notification2;
import nl.han.dare2date.service.confirmregistrationservice.Notification3;

import javax.jms.JMSException;
import javax.naming.NamingException;

@SuppressWarnings("unused")
public class ConfirmNotifiers {
    public static void main(String args[]) {
        DurableObserver observer1 = new Notification1();
        DurableObserver observer2 = new Notification2();
        DurableObserver observer3 = new Notification3();
        try {
            ObserverGateway gateway1 = new ObserverGateway(observer1, Queues.CONFIRM_QUEUE);
            ObserverGateway gateway2 = new ObserverGateway(observer2, Queues.CONFIRM_QUEUE);
            ObserverGateway gateway3 = new ObserverGateway(observer3, Queues.CONFIRM_QUEUE);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        {

        }
    }
}
