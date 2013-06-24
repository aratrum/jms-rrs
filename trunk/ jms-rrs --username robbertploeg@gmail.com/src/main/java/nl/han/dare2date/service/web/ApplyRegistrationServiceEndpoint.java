package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.*;

import nl.han.dare2date.service.jms.util.JMSUtil;
import nl.han.dare2date.service.jms.util.Queues;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import javax.jms.Connection;

@Endpoint
public class ApplyRegistrationServiceEndpoint {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public ApplyRegistrationServiceEndpoint(Marshaller marshaller,
			Unmarshaller unmarshaller) {
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	@PayloadRoot(localPart = "ApplyRegistrationRequest", namespace = "http://www.han.nl/schemas/messages")
	public ApplyRegistrationResponse applyRegistration(ApplyRegistrationRequest req) {

        // Get the generated Java Object from the SOAP Request
        Registration registration = req.getRegistration();
        User user = registration.getUser();
        Creditcard cc = user.getCard();

        // Send the Registration to the ValidateCreditcardService (into a Queue)
        ValidateCreditcardService creditcardService = new ValidateCreditcardService();
        boolean validationResult = creditcardService.validate(cc);

        // Send the Registration to the ConfirmRegistrationService (into a Topic)
        // if the Creditcard is valid
        if(validationResult){
            ConfirmRegistrationService confirmRegistrationService = new ConfirmRegistrationService();
            confirmRegistrationService.confirm(registration);
        }

        // Return the Registration into a Response to SOAPUI
        registration.setSuccesFul(validationResult);
        ApplyRegistrationResponse response =
                new ObjectFactory().createApplyRegistrationResponse();
        response.setRegistration(registration);

		return response;
	}
}
