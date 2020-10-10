package com.lit.lms.model;
import java.math.BigDecimal;

/**
 *
 * @author Life
 */
public class PaystackdataM
{

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int amount;
    private String email;

}