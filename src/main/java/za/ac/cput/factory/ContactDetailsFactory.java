package za.ac.cput.factory;

import za.ac.cput.domain.ContactDetails;
import za.ac.cput.util.Helper;

public class ContactDetailsFactory {

    public static ContactDetails createContactDetails(String cellNumber, String email,
                                                      String homePhone,
                                                      String emergencyContact) {

        // Validate required fields
        Helper.requireNotEmptyOrNull(cellNumber, "Cell Number");
        Helper.requireNotEmptyOrNull(email, "Email");

        // Validate phone number (South African)
        if (!Helper.isValidSouthAfricanPhoneNumber(cellNumber)) {
            throw new IllegalArgumentException("Invalid cell phone number");
        }

        // Validate email
        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Validate optional phone numbers
        if (homePhone != null && !homePhone.isEmpty() && !Helper.isValidSouthAfricanPhoneNumber(homePhone)) {
            throw new IllegalArgumentException("Invalid home phone number");
        }


        return new ContactDetails.Builder()
                .setCellNumber(cellNumber)
                .setEmail(email)
                .setHomePhone(homePhone)
                .setEmergencyContact(emergencyContact)
                .build();
    }


}
