package com.number26.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

/**
 * Information about transaction
 */
@JsonPropertyOrder({"parent_id", "amount", "type"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("parent_id")
    private Long parentId;
    private double amount;
    private String type;

    @JsonCreator
    public Transaction(@JsonProperty("parent_id") Long parentId,
                       @JsonProperty(value = "amount", required = true) double amount,
                       @JsonProperty(value = "type", required = true) String type) {
        this.parentId = parentId;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(double amount, String type) {
        this(null, amount, type);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, amount, type);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "parentId=" + parentId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
