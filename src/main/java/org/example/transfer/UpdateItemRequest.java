package org.example.transfer;

public class UpdateItemRequest {

    private int phoneNumber;

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UpdateItemRequest{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}
