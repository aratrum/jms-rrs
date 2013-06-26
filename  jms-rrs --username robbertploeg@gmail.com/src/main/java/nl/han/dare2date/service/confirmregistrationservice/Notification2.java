package nl.han.dare2date.service.confirmregistrationservice;

import nl.han.dare2date.service.jms.util.DurableObserver;

import java.util.Observable;

/**
 * User: Robbert
 * Date: 26-6-13
 * Time: 13:13
 */
public class Notification2 extends DurableObserver {
    @Override
    public String getSubscriberName() {
        return "<< Notification 2 >>";
    }

    public void update(Observable o, Object arg) {
        System.out.println(getClass().getName() + " received a notification! - " + arg);
    }
}
