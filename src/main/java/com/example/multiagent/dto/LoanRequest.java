package com.example.multiagent.dto;

public record LoanRequest(
        String customerName,
        Integer age,
        Double monthlyIncome,
        Double existingEmi,
        Double requestedLoanAmount,
        Integer creditScore,
        String loanPurpose
) {}
