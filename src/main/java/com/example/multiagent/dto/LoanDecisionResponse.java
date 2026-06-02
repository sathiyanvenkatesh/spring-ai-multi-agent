package com.example.multiagent.dto;

import java.util.List;

public record LoanDecisionResponse(
        String finalDecision,
        String recommendation,
        List<AgentResult> agentResults
) {}
