package application.builder;

import javax.persistence.Column;

public class CustomerSearchCriteria {

    private CustomerSearchCriteria(Builder builder) {
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.name = builder.name;
    }
    private String name;
    private String address;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private String name;
        private String address;
        private String phoneNumber;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public CustomerSearchCriteria build(){
            return new CustomerSearchCriteria(this);
        }
    }
}
