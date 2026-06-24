package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Student extends User {
    private String studentNumber;
    private String universityEmail;
    private boolean isVerified;
    private double sellerRating;
    private double buyerRating;
    private int totalSales;
    private int totalPurchases;
    private String preferredLanguage;
    private LocalDateTime joinDate;
    private String registeredAddress;
    private boolean isDeliveryEligible;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    // Relationships based on methods in UML
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "requester", cascade = CascadeType.ALL)
    private List<RefundRequest> refundRequests;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment> payments;

    protected Student() {}

    private Student(Builder builder) {
        super(builder);
        this.studentNumber = builder.studentNumber;
        this.universityEmail = builder.universityEmail;
        this.isVerified = builder.isVerified;
        this.sellerRating = builder.sellerRating;
        this.buyerRating = builder.buyerRating;
        this.totalSales = builder.totalSales;
        this.totalPurchases = builder.totalPurchases;
        this.preferredLanguage = builder.preferredLanguage;
        this.joinDate = builder.joinDate;
        this.registeredAddress = builder.registeredAddress;
        this.isDeliveryEligible = builder.isDeliveryEligible;
        this.accommodationType = builder.accommodationType;
        this.products = builder.products;
        this.orders = builder.orders;
        this.reviews = builder.reviews;
        this.notifications = builder.notifications;
        this.sentMessages = builder.sentMessages;
        this.receivedMessages = builder.receivedMessages;
        this.documents = builder.documents;
        this.cart = builder.cart;
        this.wishlists = builder.wishlists;
        this.refundRequests = builder.refundRequests;
        this.payments = builder.payments;
    }

    // Getters
    public String getStudentNumber() { return studentNumber; }
    public String getUniversityEmail() { return universityEmail; }
    public boolean isVerified() { return isVerified; }
    public double getSellerRating() { return sellerRating; }
    public double getBuyerRating() { return buyerRating; }
    public int getTotalSales() { return totalSales; }
    public int getTotalPurchases() { return totalPurchases; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public LocalDateTime getJoinDate() { return joinDate; }
    public String getRegisteredAddress() { return registeredAddress; }
    public boolean isDeliveryEligible() { return isDeliveryEligible; }
    public AccommodationType getAccommodationType() { return accommodationType; }
    public List<Product> getProducts() { return products; }
    public List<Order> getOrders() { return orders; }
    public List<Review> getReviews() { return reviews; }
    public List<Notification> getNotifications() { return notifications; }
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getReceivedMessages() { return receivedMessages; }
    public List<Document> getDocuments() { return documents; }
    public Cart getCart() { return cart; }
    public List<Wishlist> getWishlists() { return wishlists; }
    public List<RefundRequest> getRefundRequests() { return refundRequests; }
    public List<Payment> getPayments() { return payments; }

    // Business Methods from UML
    public Product addProduct(Product product) {
        if (this.products == null) {
            this.products = new java.util.ArrayList<>();
        }
        this.products.add(product);
        product.setSeller(this);
        return product;
    }

    public Product editProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(product.getProductId())) {
                products.set(i, product);
                return product;
            }
        }
        return null;
    }

    public boolean removeProduct(Product product) {
        return this.products.remove(product);
    }

    public void markAsSold(Product product) {
        product.setStatus(ProductStatus.SOLD);
        this.totalSales++;
    }

    public Message respondToBuyerQuery(Message message) {
        message.setSender(this);
        return message;
    }

    public boolean confirmPickup(Order order) {
        if (order.getDeliveryMethod() == DeliveryMethod.PICKUP) {
            order.updateStatus(OrderStatus.READY_FOR_PICKUP);
            return true;
        }
        return false;
    }

    public boolean confirmDelivery(Order order) {
        if (order.getDeliveryMethod() == DeliveryMethod.DELIVERY) {
            order.updateStatus(OrderStatus.DELIVERED);
            return true;
        }
        return false;
    }

    public List<Product> searchProducts(String keyword) {
        return new java.util.ArrayList<>();
    }

    public Order buyProduct(Product product) {
        Order order = new Order.Builder()
                .setBuyer(this)
                .setSeller(product.getSeller())
                .setProducts(java.util.Arrays.asList(product))
                .build();
        return order;
    }

    public void addToCart(Product product) {
        if (this.cart == null) {
            this.cart = new Cart.Builder()
                    .setStudent(this)
                    .build();
        }
        CartItem item = new CartItem.Builder()
                .setProduct(product)
                .setQuantity(1)
                .setPriceAtAdd(product.getPrice())
                .build();
        this.cart.addItem(item);
    }

    public Order checkout() {
        if (this.cart != null && !this.cart.isEmpty()) {
            Order order = new Order.Builder()
                    .setBuyer(this)
                    .setProducts(this.cart.getItems().stream()
                            .map(CartItem::getProduct)
                            .collect(java.util.stream.Collectors.toList()))
                    .build();
            this.cart.clearCart();
            return order;
        }
        return null;
    }

    public Payment selectPaymentMethod(PaymentMethod method) {
        Payment payment = new Payment.Builder()
                .setStudent(this)
                .setPaymentMethod(method)
                .build();
        return payment;
    }

    public Delivery selectDeliveryMethod(DeliveryMethod method) {
        Delivery delivery = new Delivery.Builder()
                .setStudent(this)
                .setDeliveryMethod(method)
                .build();
        return delivery;
    }

    public void uploadVerificationDocuments(List<Document> documents) {
        for (Document doc : documents) {
            doc.setStudent(this);
            doc.setVerificationStatus(VerificationStatus.PENDING);
            if (this.documents == null) {
                this.documents = new java.util.ArrayList<>();
            }
            this.documents.add(doc);
        }
    }

    public boolean confirmReceipt(Order order) {
        if (order.getStatus() == OrderStatus.DELIVERED) {
            order.updateStatus(OrderStatus.RECEIVED_VERIFIED);
            return true;
        }
        return false;
    }

    public OrderStatus trackOrder(Order order) {
        return order.getStatus();
    }

    public RefundRequest requestRefund(Order order, String reason) {
        RefundRequest refund = new RefundRequest.Builder()
                .setOrder(order)
                .setRequester(this)
                .setReason(reason)
                .setAmount(order.getTotalAmount())
                .build();
        return refund;
    }

    public Message sendMessage(Student receiver, String content) {
        Message message = new Message.Builder()
                .setSender(this)
                .setReceiver(receiver)
                .setContent(content)
                .build();
        return message;
    }

    public Review leaveReview(Product product, int rating, String comment) {
        Review review = new Review.Builder()
                .setReviewer(this)
                .setReviewee(product.getSeller())
                .setProduct(product)
                .setRating(rating)
                .setComment(comment)
                .build();
        return review;
    }

    public List<ChatRoom> getChatRooms() {
        return new java.util.ArrayList<>();
    }

    public void addToWishlist(Product product) {
        if (this.wishlists == null) {
            this.wishlists = new java.util.ArrayList<>();
        }
        Wishlist wishlist = this.wishlists.stream()
                .filter(Wishlist::isDefault)
                .findFirst()
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist.Builder()
                            .setStudent(this)
                            .setName("Default Wishlist")
                            .setIsDefault(true)
                            .build();
                    this.wishlists.add(newWishlist);
                    return newWishlist;
                });

        WishlistItem item = new WishlistItem.Builder()
                .setProduct(product)
                .build();
        wishlist.addItem(item);
    }

    public List<Order> getOrderHistory() {
        return this.orders != null ? this.orders : new java.util.ArrayList<>();
    }

    public void updateRating() {
        if (this.reviews != null && !this.reviews.isEmpty()) {
            double avgSellerRating = this.reviews.stream()
                    .filter(r -> r.getReviewee() != null &&
                            r.getReviewee().equals(this))
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            this.sellerRating = avgSellerRating;
        }
    }

    public void reportUser(User user, String reason) {
        // Implementation would create a report
    }

    public String getPhoneNumber() {
        // Implementation would retrieve from contact details
        return null;
    }

    public String getEmail() {
        return this.universityEmail != null ? this.universityEmail : super.getEmail();
    }

    public DeliveryEligibility checkDeliveryEligibility() {
        if (this.isDeliveryEligible) {
            return DeliveryEligibility.CPUT_RESIDENCE;
        }
        return DeliveryEligibility.NOT_ELIGIBLE;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", universityEmail='" + universityEmail + '\'' +
                ", isVerified=" + isVerified +
                ", sellerRating=" + sellerRating +
                ", buyerRating=" + buyerRating +
                ", totalSales=" + totalSales +
                ", totalPurchases=" + totalPurchases +
                ", joinDate=" + joinDate +
                ", accommodationType=" + accommodationType +
                '}';
    }

    public static class Builder extends User.Builder {
        private String studentNumber;
        private String universityEmail;
        private boolean isVerified = false;
        private double sellerRating = 0.0;
        private double buyerRating = 0.0;
        private int totalSales = 0;
        private int totalPurchases = 0;
        private String preferredLanguage;
        private LocalDateTime joinDate = LocalDateTime.now();
        private String registeredAddress;
        private boolean isDeliveryEligible = false;
        private AccommodationType accommodationType;
        private List<Product> products;
        private List<Order> orders;
        private List<Review> reviews;
        private List<Notification> notifications;
        private List<Message> sentMessages;
        private List<Message> receivedMessages;
        private List<Document> documents;
        private Cart cart;
        private List<Wishlist> wishlists;
        private List<RefundRequest> refundRequests;
        private List<Payment> payments;

        public Builder setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder setUniversityEmail(String universityEmail) {
            this.universityEmail = universityEmail;
            return this;
        }

        public Builder setIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public Builder setSellerRating(double sellerRating) {
            this.sellerRating = sellerRating;
            return this;
        }

        public Builder setBuyerRating(double buyerRating) {
            this.buyerRating = buyerRating;
            return this;
        }

        public Builder setTotalSales(int totalSales) {
            this.totalSales = totalSales;
            return this;
        }

        public Builder setTotalPurchases(int totalPurchases) {
            this.totalPurchases = totalPurchases;
            return this;
        }

        public Builder setPreferredLanguage(String preferredLanguage) {
            this.preferredLanguage = preferredLanguage;
            return this;
        }

        public Builder setJoinDate(LocalDateTime joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public Builder setRegisteredAddress(String registeredAddress) {
            this.registeredAddress = registeredAddress;
            return this;
        }

        public Builder setIsDeliveryEligible(boolean isDeliveryEligible) {
            this.isDeliveryEligible = isDeliveryEligible;
            return this;
        }

        public Builder setAccommodationType(AccommodationType accommodationType) {
            this.accommodationType = accommodationType;
            return this;
        }

        public Builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder setOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setNotifications(List<Notification> notifications) {
            this.notifications = notifications;
            return this;
        }

        public Builder setSentMessages(List<Message> sentMessages) {
            this.sentMessages = sentMessages;
            return this;
        }

        public Builder setReceivedMessages(List<Message> receivedMessages) {
            this.receivedMessages = receivedMessages;
            return this;
        }

        public Builder setDocuments(List<Document> documents) {
            this.documents = documents;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Builder setWishlists(List<Wishlist> wishlists) {
            this.wishlists = wishlists;
            return this;
        }

        public Builder setRefundRequests(List<RefundRequest> refundRequests) {
            this.refundRequests = refundRequests;
            return this;
        }

        public Builder setPayments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            if (user instanceof Student) {
                Student student = (Student) user;
                this.studentNumber = student.studentNumber;
                this.universityEmail = student.universityEmail;
                this.isVerified = student.isVerified;
                this.sellerRating = student.sellerRating;
                this.buyerRating = student.buyerRating;
                this.totalSales = student.totalSales;
                this.totalPurchases = student.totalPurchases;
                this.preferredLanguage = student.preferredLanguage;
                this.joinDate = student.joinDate;
                this.registeredAddress = student.registeredAddress;
                this.isDeliveryEligible = student.isDeliveryEligible;
                this.accommodationType = student.accommodationType;
                this.products = student.products;
                this.orders = student.orders;
                this.reviews = student.reviews;
                this.notifications = student.notifications;
                this.sentMessages = student.sentMessages;
                this.receivedMessages = student.receivedMessages;
                this.documents = student.documents;
                this.cart = student.cart;
                this.wishlists = student.wishlists;
                this.refundRequests = student.refundRequests;
                this.payments = student.payments;
            }
            return this;
        }

        @Override
        public Student build() {
            return new Student(this);
        }
    }
}