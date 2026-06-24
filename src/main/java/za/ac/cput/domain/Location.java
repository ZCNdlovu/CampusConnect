package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    private Long locationId;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private double latitude;
    private double longitude;
    private String buildingName;
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private CollectionPoint collectionPoint;

    protected Location() {}

    private Location(Builder builder) {
        this.locationId = builder.locationId;
        this.name = builder.name;
        this.address = builder.address;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.buildingName = builder.buildingName;
        this.roomNumber = builder.roomNumber;
        this.collectionPoint = builder.collectionPoint;
    }

    // Getters
    public Long getLocationId() { return locationId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getBuildingName() { return buildingName; }
    public String getRoomNumber() { return roomNumber; }
    public CollectionPoint getCollectionPoint() { return collectionPoint; }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", collectionPoint=" + collectionPoint +
                '}';
    }

    public static class Builder {
        private Long locationId;
        private String name;
        private String address;
        private String city;
        private String province;
        private String postalCode;
        private double latitude;
        private double longitude;
        private String buildingName;
        private String roomNumber;
        private CollectionPoint collectionPoint;

        public Builder setLocationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setBuildingName(String buildingName) {
            this.buildingName = buildingName;
            return this;
        }

        public Builder setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setCollectionPoint(CollectionPoint collectionPoint) {
            this.collectionPoint = collectionPoint;
            return this;
        }

        public Builder copy(Location location) {
            this.locationId = location.locationId;
            this.name = location.name;
            this.address = location.address;
            this.city = location.city;
            this.province = location.province;
            this.postalCode = location.postalCode;
            this.latitude = location.latitude;
            this.longitude = location.longitude;
            this.buildingName = location.buildingName;
            this.roomNumber = location.roomNumber;
            this.collectionPoint = location.collectionPoint;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}