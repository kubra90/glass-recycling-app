package com.techelevator.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserDetails {

    private int account_id;
    private String username;
    @NotBlank(message = "Please enter your first and last name")
    private String full_name;
    @NotBlank(message = "Please enter a valid street address")
    private String street_address;
    @NotBlank(message = "Please enter a valid city name")
    private String city;
    @NotBlank(message = "Please enter your state abbreviation, Example: PA")
    @Size(min = 2)
    private String state_abbreviation;
    @NotBlank(message = "Please enter your 5 digit zipcode")
    @Size(min = 5, max = 10)
    private String zipcode;
    @Size(min = 10, max = 11, message = "Please enter a 10 digit phone number, Example: 7035558142")
    private String phone_number;
    @Email(message = "Please enter a valid email")
    private String email_address;
    private int total_pounds_recycled;
    private int credits_balance;
    private int credits_redeemed;

    public UserDetails() {}

    public UserDetails(int account_id, String username, String full_name,
                       String street_address, String city, String state_abbreviation,
                       String zipcode, String phone_number, String email_address,
                       int total_pounds_recycled, int credits_balance, int credits_redeemed) {
        this.account_id = account_id;
        this.username = username;
        this.full_name = full_name;
        this.street_address = street_address;
        this.city = city;
        this.state_abbreviation = state_abbreviation;
        this.zipcode = zipcode;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.total_pounds_recycled = total_pounds_recycled;
        this.credits_balance = credits_balance;
        this.credits_redeemed = credits_redeemed;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_abbreviation() {
        return state_abbreviation;
    }

    public void setState_abbreviation(String state_abbreviation) {
        this.state_abbreviation = state_abbreviation;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public int getTotal_pounds_recycled() {
        return total_pounds_recycled;
    }

    public void setTotal_pounds_recycled(int total_pounds_recycled) {
        this.total_pounds_recycled = total_pounds_recycled;
    }

    public int getCredits_balance() {
        return credits_balance;
    }

    public void setCredits_balance(int credits_balance) {
        this.credits_balance = credits_balance;
    }

    public int getCredits_redeemed() {
        return credits_redeemed;
    }

    public void setCredits_redeemed(int credits_redeemed) {
        this.credits_redeemed = credits_redeemed;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "account_id=" + account_id +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", state_abbreviation='" + state_abbreviation + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email_address='" + email_address + '\'' +
                ", total_pounds_recycled=" + total_pounds_recycled +
                ", credits_balance=" + credits_balance +
                ", credits_redeemed=" + credits_redeemed +
                '}';
    }
}
