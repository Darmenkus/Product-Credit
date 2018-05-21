package model;

import java.util.Date;

public class CreditRequest {
    private Integer id;
    private Integer clientId;
    private Date requestDate;
    private String requestNumber;
    private Double amount;
    private Period period;
    private String rate;
    private Double monthlyPayment;
    private Double amountInUSD;
    private Double totalPayout;
    private Double overpayment;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Double getAmountInUSD() {
        return amountInUSD;
    }

    public void setAmountInUSD(Double amountInUSD) {
        this.amountInUSD = amountInUSD;
    }

    public Double getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(Double totalPayout) {
        this.totalPayout = totalPayout;
    }

    public Double getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(Double overpayment) {
        this.overpayment = overpayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
