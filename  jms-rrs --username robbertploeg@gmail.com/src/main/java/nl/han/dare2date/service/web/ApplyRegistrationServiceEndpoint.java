package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.*;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;


@Endpoint
@SuppressWarnings( { "unchecked", "deprecation", "unused" })
public class ApplyRegistrationServiceEndpoint {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public ApplyRegistrationServiceEndpoint(Marshaller marshaller,
			Unmarshaller unmarshaller) {
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}


	@PayloadRoot(localPart = "ApplyRegistrationRequest", namespace = "http://www.han.nl/schemas/messages")
	public ApplyRegistrationResponse applyRegistration(ApplyRegistrationRequest req) {

        // Get the generated Java Object from the SOAP Request
        Registration registration = req.getRegistration();
        User user = registration.getUser();
        Creditcard cc = user.getCard();

        // Send the Registration to the ValidateCreditcardService (into a Queue)
        ValidateCreditcardService creditcardService = new ValidateCreditcardService();
        System.out.println("before cc");
        boolean validationResult = creditcardService.validate(cc);
        System.out.println("afther cc");

        // Send the Registration to the ConfirmRegistrationService (into a Topic)
        // if the Creditcard is valid
        if(validationResult){
            ConfirmRegistrationService confirmRegistrationService = new ConfirmRegistrationService();
            confirmRegistrationService.confirm(registration);
        }

        // Return the Registration into a Response to SOAP
        registration.setSuccesFul(validationResult);
        ApplyRegistrationResponse response =
                new ObjectFactory().createApplyRegistrationResponse();
        response.setRegistration(registration);

		return response;
	}
}
