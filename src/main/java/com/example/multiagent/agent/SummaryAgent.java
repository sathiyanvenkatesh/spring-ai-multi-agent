package com.example.multiagent.agent;

import com.example.multiagent.dto.AgentResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryAgent {

    private final ChatClient chatClient;

    public SummaryAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String summarize(List<AgentResult> results) {
        String context = results.stream()
                .map(r -> "Agent: " + r.agentName() + "\nOutput: " + r.output())
                .collect(Collectors.joining("\n\n"));

        return chatClient.prompt()
                .system("You are a Final Decision Agent for a bank loan workflow.")
                .user("""
                        Based on the agent outputs below, create final response.

                        %s

                        Return exactly:
                        Final Decision: APPROVE / REJECT / MANUAL_REVIEW
                        Reason:
                        Next Step:
                        """.formatted(context))
                .call()
                .content();
    }
}
