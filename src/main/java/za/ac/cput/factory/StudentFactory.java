package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class StudentFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Student createStudent(String firstName, String lastName,
                                        String email, String password,
                                        String studentNumber, String universityEmail,
                                        ContactDetails contactDetails) {

        // Validate required fields
        Helper.requireNotEmptyOrNull(firstName, "First Name");
        Helper.requireNotEmptyOrNull(lastName, "Last Name");
        Helper.requireNotEmptyOrNull(email, "Email");
        Helper.requireNotEmptyOrNull(password, "Password");
        Helper.requireNotEmptyOrNull(studentNumber, "Student Number");

        // Validate email formats
        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!Helper.isValidUniversityEmail(universityEmail)) {
            throw new IllegalArgumentException("Invalid university email. Must end with @cput.ac.za");
        }



        // Validate contact details if provided
        if (contactDetails != null) {
            if (!contactDetails.validateEmail() || !contactDetails.validatePhone()) {
                throw new IllegalArgumentException("Invalid contact details");
            }
        }

        // Generate ID
        String id = idGenerator.generateNextId("STUDENT");
        Long numericId = idGenerator.generateNumericId("STUDENT");

        return new Student.Builder()
                .setUserId(numericId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setStudentNumber(studentNumber)
                .setUniversityEmail(universityEmail)
                .setIsVerified(false)
                .setSellerRating(0.0)
                .setBuyerRating(0.0)
                .setTotalSales(0)
                .setTotalPurchases(0)
                .setIsDeliveryEligible(false)
                .setCreatedAt(LocalDateTime.now())
                .setIsActive(true)
                .build();
    }

    /**
     * Creates a Student with all details
     */
    public static Student createStudentWithDetails(String firstName, String lastName,
                                                   String email, String password,
                                                   String studentNumber, String universityEmail,
                                                   ContactDetails contactDetails,
                                                   List<Address> addresses,
                                                   AccommodationType accommodationType,
                                                   String registeredAddress) {

        Student student = createStudent(firstName, lastName, email, password,
                studentNumber, universityEmail, contactDetails);

        return new Student.Builder()
                .copy(student)
                .setAddresses(addresses)
                .setAccommodationType(accommodationType)
                .setRegisteredAddress(registeredAddress)
                .build();
    }
}
