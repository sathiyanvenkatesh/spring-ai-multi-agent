package com.example.multiagent.agent;

import com.example.multiagent.dto.AgentResult;
import com.example.multiagent.dto.LoanRequest;
import com.example.multiagent.tools.BankingPolicyTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PolicyAgent {

    private final ChatClient chatClient;
    private final BankingPolicyTool bankingPolicyTool;

    public PolicyAgent(ChatClient.Builder builder, BankingPolicyTool bankingPolicyTool) {
        this.chatClient = builder.build();
        this.bankingPolicyTool = bankingPolicyTool;
    }

    public AgentResult checkPolicy(LoanRequest request) {
        double emiRatio = request.existingEmi() / request.monthlyIncome();

        String response = chatClient.prompt()
                .system("You are a Banking Policy Agent. Use the available policy tool and explain result simply.")
                .user("""
                        Check policy eligibility.
                        Credit score: %d
                        EMI ratio: %.2f
                        """.formatted(request.creditScore(), emiRatio))
                .tools(bankingPolicyTool)
                .call()
                .content();

        return new AgentResult("PolicyAgent", "COMPLETED", response);
    }
}
