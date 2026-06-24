package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

public class AddressFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Address createAddress(Long studentId, String addressType,
                                        String streetNumber, String streetName,
                                        String suburb, String city,
                                        String province, String country,
                                        String postalCode, boolean isDefault) {

        // Validate required fields
        Helper.requireNonNull(studentId, "Student ID");
        Helper.requireNotEmptyOrNull(addressType, "Address Type");
        Helper.requireNotEmptyOrNull(streetNumber, "Street Number");
        Helper.requireNotEmptyOrNull(streetName, "Street Name");
        Helper.requireNotEmptyOrNull(suburb, "Suburb");
        Helper.requireNotEmptyOrNull(city, "City");
        Helper.requireNotEmptyOrNull(province, "Province");
        Helper.requireNotEmptyOrNull(country, "Country");
        Helper.requireNotEmptyOrNull(postalCode, "Postal Code");

        // Validate postal code format (South African: 4 digits)
        if (!postalCode.matches("^[0-9]{4}$")) {
            throw new IllegalArgumentException("Postal code must be 4 digits");
        }

        // Generate ID
        Long addressId = idGenerator.generateNumericId("ADDRESS");

        return new Address.Builder()
                .setAddressId(addressId)
                .setStudentId(studentId)
                .setAddressType(addressType)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setCountry(country)
                .setPostalCode(postalCode)
                .setIsDefault(isDefault)
                .build();
    }
}
