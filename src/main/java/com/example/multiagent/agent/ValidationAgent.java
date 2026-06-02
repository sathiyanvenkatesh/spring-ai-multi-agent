package com.example.multiagent.agent;

import com.example.multiagent.dto.AgentResult;
import com.example.multiagent.dto.LoanRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidationAgent {

    public AgentResult validate(LoanRequest request) {
        StringBuilder errors = new StringBuilder();

        if (request.customerName() == null || request.customerName().isBlank()) {
            errors.append("Customer name is missing. ");
        }
        if (request.age() == null || request.age() < 18) {
            errors.append("Customer age is invalid. ");
        }
        if (request.monthlyIncome() == null || request.monthlyIncome() <= 0) {
            errors.append("Monthly income is invalid. ");
        }
        if (request.existingEmi() == null || request.existingEmi() < 0) {
            errors.append("Existing EMI is invalid. ");
        }
        if (request.requestedLoanAmount() == null || request.requestedLoanAmount() <= 0) {
            errors.append("Requested loan amount is invalid. ");
        }
        if (request.creditScore() == null || request.creditScore() <= 0) {
            errors.append("Credit score is invalid. ");
        }

        String output = errors.isEmpty()
                ? "Validation successful. All required fields are available."
                : errors.toString();

        return new AgentResult("ValidationAgent", "COMPLETED", output);
    }
}
