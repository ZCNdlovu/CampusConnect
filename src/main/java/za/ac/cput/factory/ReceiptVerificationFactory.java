package za.ac.cput.factory;

import za.ac.cput.domain.Document;
import za.ac.cput.domain.ReceiptVerification;
import za.ac.cput.domain.VerificationStatus;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class ReceiptVerificationFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static ReceiptVerification createReceiptVerification(Long orderId, Long studentId,
                                                                Document productImage,
                                                                Document studentCardImage,
                                                                String enteredStudentNumber,
                                                                String actualStudentNumber) {

        // Validate required fields
        Helper.requireNonNull(orderId, "Order ID");
        Helper.requireNonNull(studentId, "Student ID");
        Helper.requireNonNull(productImage, "Product Image");
        Helper.requireNonNull(studentCardImage, "Student Card Image");
        Helper.requireNotEmptyOrNull(enteredStudentNumber, "Entered Student Number");
        Helper.requireNotEmptyOrNull(actualStudentNumber, "Actual Student Number");

        // Generate ID
        Long verificationId = idGenerator.generateNumericId("VERIFICATION");

        return new ReceiptVerification.Builder()
                .setVerificationId(verificationId)
                .setOrderId(orderId)
                .setStudentId(studentId)
                .setProductImage(productImage)
                .setStudentCardImage(studentCardImage)
                .setEnteredStudentNumber(enteredStudentNumber)
                .setActualStudentNumber(actualStudentNumber)
                .setStudentNumberMatch(false)
                .setProductImageValid(false)
                .setStudentCardValid(false)
                .setStatus(VerificationStatus.PENDING)
                .setVerificationDate(LocalDateTime.now())
                .build();
    }


}
