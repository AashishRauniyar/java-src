package com.ecom.controller.dao;

import com.ecom.model.AddressModel;
import com.ecom.model.UserModel;

public class UserProfileDetails {
    private UserModel user;
    private AddressModel address;

    public UserProfileDetails(UserModel user, AddressModel address) {
        this.user = user;
        this.address = address;
    }

    public UserModel getUser() {
        return user;
    }

    public AddressModel getAddress() {
        return address;
    }
}
