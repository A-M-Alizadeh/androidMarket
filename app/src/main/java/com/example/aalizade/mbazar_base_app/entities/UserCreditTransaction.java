package com.example.aalizade.mbazar_base_app.entities;

/**
 * Created by aalizade on 3/7/2018.
 */

public class UserCreditTransaction {
    String type;
    String creditDate;
    //colorful
    Boolean isPositive;
    String creditTransactionMoney;

    String remainingMoney;
    String returnDate;

    public UserCreditTransaction(String type, String creditDate, Boolean isPositive, String creditTransactionMoney, String remainingMoney, String returnDate) {
        this.type = type;
        this.creditDate = creditDate;
        this.isPositive = isPositive;
        this.creditTransactionMoney = creditTransactionMoney;
        this.remainingMoney = remainingMoney;
        this.returnDate = returnDate;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getCreditTransactionMoney() {
        return creditTransactionMoney;
    }

    public void setCreditTransactionMoney(String creditTransactionMoney) {
        this.creditTransactionMoney = creditTransactionMoney;
    }

    public String getRemainingMoney() {
        return remainingMoney;
    }

    public void setRemainingMoney(String remainingMoney) {
        this.remainingMoney = remainingMoney;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "UserCreditTransaction{" +
                "type='" + type + '\'' +
                ", creditDate='" + creditDate + '\'' +
                ", isPositive=" + isPositive +
                ", creditTransactionMoney='" + creditTransactionMoney + '\'' +
                ", remainingMoney='" + remainingMoney + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
