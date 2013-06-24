package nl.han.dare2date.service.jms.util;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Serhat
 * Date: 24-6-13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class ValidateCreditcardResponse implements Serializable {
    private boolean valid;

    public ValidateCreditcardResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
