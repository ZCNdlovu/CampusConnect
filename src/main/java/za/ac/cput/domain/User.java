package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    protected Long userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String profilePicture;
    protected boolean isActive;
    protected LocalDateTime createdAt;
    protected LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    protected UserRole role;

    @Enumerated(EnumType.STRING)
    protected VerificationStatus verificationStatus;

    protected User() {}

    protected User(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.profilePicture = builder.profilePicture;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.lastLogin = builder.lastLogin;
        this.role = builder.role;
        this.verificationStatus = builder.verificationStatus;
    }

    // Getters
    public Long getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getProfilePicture() { return profilePicture; }
    public boolean isActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public UserRole getRole() { return role; }
    public VerificationStatus getVerificationStatus() { return verificationStatus; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public abstract static class Builder {
        protected Long userId;
        protected String firstName;
        protected String lastName;
        protected String email;
        protected String password;
        protected String profilePicture;
        protected boolean isActive = true;
        protected LocalDateTime createdAt = LocalDateTime.now();
        protected LocalDateTime lastLogin;
        protected UserRole role;
        protected VerificationStatus verificationStatus = VerificationStatus.PENDING;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }

        public Builder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setLastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder setVerificationStatus(VerificationStatus verificationStatus) {
            this.verificationStatus = verificationStatus;
            return this;
        }

        public Builder copy(User user) {
            this.userId = user.userId;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.password = user.password;
            this.profilePicture = user.profilePicture;
            this.isActive = user.isActive;
            this.createdAt = user.createdAt;
            this.lastLogin = user.lastLogin;
            this.role = user.role;
            this.verificationStatus = user.verificationStatus;
            return this;
        }

        public abstract User build();
    }
}