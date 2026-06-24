package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Admin extends User {
    private String employeeId;
    private String department;
    private String accessLevel;
    private LocalDateTime hireDate;

    @ElementCollection
    private List<String> permissions;

    protected Admin() {}

    private Admin(Builder builder) {
        super(builder);
        this.employeeId = builder.employeeId;
        this.department = builder.department;
        this.accessLevel = builder.accessLevel;
        this.hireDate = builder.hireDate;
        this.permissions = builder.permissions;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getAccessLevel() { return accessLevel; }
    public LocalDateTime getHireDate() { return hireDate; }
    public List<String> getPermissions() { return permissions; }

    @Override
    public String toString() {
        return "Admin{" +
                "employeeId='" + employeeId + '\'' +
                ", department='" + department + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                '}';
    }

    public static class Builder extends User.Builder {
        private String employeeId;
        private String department;
        private String accessLevel;
        private LocalDateTime hireDate = LocalDateTime.now();
        private List<String> permissions;

        public Builder setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setAccessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public Builder setHireDate(LocalDateTime hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder setPermissions(List<String> permissions) {
            this.permissions = permissions;
            return this;
        }

        @Override
        public Builder copy(User user) {
            super.copy(user);
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                this.employeeId = admin.employeeId;
                this.department = admin.department;
                this.accessLevel = admin.accessLevel;
                this.hireDate = admin.hireDate;
                this.permissions = admin.permissions;
            }
            return this;
        }

        @Override
        public Admin build() {
            return new Admin(this);
        }
    }
}