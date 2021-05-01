package com.enigma.tokonyadiaboot.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletDto {

    private String id;
    private String phoneNumber;
    private BigDecimal balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDto walletDto = (WalletDto) o;
        return Objects.equals(id, walletDto.id) && Objects.equals(phoneNumber, walletDto.phoneNumber) && Objects.equals(balance, walletDto.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, balance);
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
