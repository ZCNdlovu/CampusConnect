package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class AdminFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Admin createAdmin(String firstName, String lastName,
                                    String email, String password,
                                    String employeeId, String department,
                                    String accessLevel, List<String> permissions) {

        // Validate required fields
        Helper.requireNotEmptyOrNull(firstName, "First Name");
        Helper.requireNotEmptyOrNull(lastName, "Last Name");
        Helper.requireNotEmptyOrNull(email, "Email");
        Helper.requireNotEmptyOrNull(password, "Password");
        Helper.requireNotEmptyOrNull(employeeId, "Employee ID");
        Helper.requireNotEmptyOrNull(department, "Department");

        // Validate email
        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Generate ID
        Long numericId = idGenerator.generateNumericId("ADMIN");

        return new Admin.Builder()
                .setUserId(numericId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setEmployeeId(employeeId)
                .setDepartment(department)
                .setAccessLevel(accessLevel)
                .setHireDate(LocalDateTime.now())
                .setPermissions(permissions)
                .setCreatedAt(LocalDateTime.now())
                .setIsActive(true)
                .build();
    }
}
