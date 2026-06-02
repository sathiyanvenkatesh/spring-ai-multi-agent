package com.example.multiagent.agent;

import com.example.multiagent.dto.AgentResult;
import com.example.multiagent.dto.LoanRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RiskAgent {

    private final ChatClient chatClient;

    public RiskAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public AgentResult analyzeRisk(LoanRequest request) {
        double emiRatio = request.existingEmi() / request.monthlyIncome();

        String response = chatClient.prompt()
                .system("You are a banking Risk Analysis Agent. Classify risk as LOW, MEDIUM, or HIGH.")
                .user("""
                        Analyze this loan risk:
                        Monthly income: %.2f
                        Existing EMI: %.2f
                        EMI ratio: %.2f
                        Requested loan amount: %.2f
                        Credit score: %d

                        Return risk category and short reason.
                        """.formatted(
                        request.monthlyIncome(), request.existingEmi(), emiRatio,
                        request.requestedLoanAmount(), request.creditScore()))
                .call()
                .content();

        return new AgentResult("RiskAgent", "COMPLETED", response);
    }
}
