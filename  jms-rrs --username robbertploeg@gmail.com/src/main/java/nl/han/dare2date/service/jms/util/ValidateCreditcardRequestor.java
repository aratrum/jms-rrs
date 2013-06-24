package nl.han.dare2date.service.jms.util;

import nl.han.dare2date.applyregistrationservice.Creditcard;

import javax.jms.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class ValidateCreditcardRequestor {
    private ValidateCreditcardResponse response;

    public ValidateCreditcardRequestor(Connection conn, String requestQueueName, String replyQueueName, String invalidQueueName, Creditcard card) {
    }

    public void send() {
    }

    public void receiveSync() {
    }

    public ValidateCreditcardResponse getResponse() {
        return response;
    }
}
