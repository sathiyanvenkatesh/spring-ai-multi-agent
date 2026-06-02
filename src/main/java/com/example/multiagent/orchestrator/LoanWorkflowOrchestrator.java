package com.example.multiagent.orchestrator;

import com.example.multiagent.agent.*;
import com.example.multiagent.dto.AgentResult;
import com.example.multiagent.dto.LoanDecisionResponse;
import com.example.multiagent.dto.LoanRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanWorkflowOrchestrator {

    private final IntakeAgent intakeAgent;
    private final ValidationAgent validationAgent;
    private final RiskAgent riskAgent;
    private final PolicyAgent policyAgent;
    private final SummaryAgent summaryAgent;

    public LoanWorkflowOrchestrator(
            IntakeAgent intakeAgent,
            ValidationAgent validationAgent,
            RiskAgent riskAgent,
            PolicyAgent policyAgent,
            SummaryAgent summaryAgent) {
        this.intakeAgent = intakeAgent;
        this.validationAgent = validationAgent;
        this.riskAgent = riskAgent;
        this.policyAgent = policyAgent;
        this.summaryAgent = summaryAgent;
    }

    public LoanDecisionResponse executeWorkflow(LoanRequest request) {
        List<AgentResult> results = new ArrayList<>();

        results.add(intakeAgent.process(request));

        AgentResult validationResult = validationAgent.validate(request);
        results.add(validationResult);

        String validationText = validationResult.output().toLowerCase();
        if (validationText.contains("missing") || validationText.contains("invalid")) {
            return new LoanDecisionResponse(
                    "MANUAL_REVIEW",
                    "Validation failed. Correct the missing or invalid customer details.",
                    results
            );
        }

        results.add(riskAgent.analyzeRisk(request));
        results.add(policyAgent.checkPolicy(request));

        String finalRecommendation = summaryAgent.summarize(results);

        return new LoanDecisionResponse("COMPLETED", finalRecommendation, results);
    }
}
