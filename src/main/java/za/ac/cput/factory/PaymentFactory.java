package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class PaymentFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Payment createPayment(Long orderId, double amount,
                                        PaymentMethod method) {

        // Validate required fields
        Helper.requireNonNull(orderId, "Order ID");
        Helper.requirePositive(amount, "Amount");
        Helper.requireNonNull(method, "Payment Method");

        // Generate ID and transaction ID
        Long paymentId = idGenerator.generateNumericId("PAYMENT");
        String transactionId = "TXN-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);

        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setOrderId(orderId)
                .setTransactionId(transactionId)
                .setAmount(amount)
                .setMethod(method)
                .setStatus(PaymentStatus.PENDING)
                .setPaymentDate(LocalDateTime.now())
                .build();
    }

    public static Payment createCompletedPayment(Long orderId, double amount,
                                                 PaymentMethod method,
                                                 String receiptUrl) {

        Payment payment = createPayment(orderId, amount, method);

        return new Payment.Builder()
                .setPaymentId(payment.getPaymentId())
                .setOrderId(orderId)
                .setTransactionId(payment.getTransactionId())
                .setAmount(amount)
                .setMethod(method)
                .setStatus(PaymentStatus.COMPLETED)
                .setPaymentDate(LocalDateTime.now())
                .setReceiptUrl(receiptUrl)
                .build();
    }
}
