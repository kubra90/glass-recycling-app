package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class PrizeDetails {

    private int prize_id;
    @NotEmpty
    private String prize_name;
    @Min(value = 1, message = "Point Value must be at least 1")
    private int point_value;

    public PrizeDetails() {
    }

    public PrizeDetails(int prize_id, String prize_name, int point_value) {
        this.prize_id = prize_id;
        this.prize_name = prize_name;
        this.point_value = point_value;
    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public int getPoint_value() {
        return point_value;
    }

    public void setPoint_value(int point_value) {
        this.point_value = point_value;
    }

    @Override
    public String toString() {
        return "PrizeDetails{" +
                "prize_id=" + prize_id +
                ", prize_name='" + prize_name + '\'' +
                ", point_value=" + point_value +
                '}';
    }
}
