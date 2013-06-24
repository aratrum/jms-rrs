package nl.han.dare2date.service.jms.util;

import nl.han.dare2date.applyregistrationservice.Creditcard;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class ValidateCreditcardRequest implements Serializable {
    Creditcard card;

    public ValidateCreditcardRequest(Creditcard card) {
        this.card = card;
    }

    public Creditcard getCard() {
        return card;
    }
}
