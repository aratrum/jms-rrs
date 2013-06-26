package nl.han.dare2date.service.jms.util;

import nl.han.dare2date.applyregistrationservice.Registration;

import java.io.Serializable;

/**
 * User: Robbert
 * Date: 26-6-13
 * Time: 13:05
 */
public class ConfirmRequest implements Serializable {
    Registration reg;

    public ConfirmRequest(Registration reg) {
        this.reg = reg;
    }

    public Registration getRegistration() {
        return reg;
    }

}
