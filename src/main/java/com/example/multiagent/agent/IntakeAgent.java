package com.example.multiagent.agent;

import com.example.multiagent.dto.AgentResult;
import com.example.multiagent.dto.LoanRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class IntakeAgent {

    private final ChatClient chatClient;

    public IntakeAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public AgentResult process(LoanRequest request) {
        String response = chatClient.prompt()
                .system("You are an Intake Agent. Summarize customer loan details in simple structured points.")
                .user("""
                        Customer loan request:
                        Name: %s
                        Age: %s
                        Monthly income: %s
                        Existing EMI: %s
                        Requested loan amount: %s
                        Credit score: %s
                        Loan purpose: %s
                        """.formatted(
                        request.customerName(), request.age(), request.monthlyIncome(),
                        request.existingEmi(), request.requestedLoanAmount(),
                        request.creditScore(), request.loanPurpose()))
                .call()
                .content();

        return new AgentResult("IntakeAgent", "COMPLETED", response);
    }
}
