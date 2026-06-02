package com.example.multiagent.controller;

import com.example.multiagent.dto.LoanDecisionResponse;
import com.example.multiagent.dto.LoanRequest;
import com.example.multiagent.orchestrator.LoanWorkflowOrchestrator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan-workflow")
public class LoanWorkflowController {

    private final LoanWorkflowOrchestrator orchestrator;

    public LoanWorkflowController(LoanWorkflowOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @GetMapping("/health")
    public String health() {
        return "Multi-agent workflow API is running";
    }

    @PostMapping("/evaluate")
    public LoanDecisionResponse evaluateLoan(@RequestBody LoanRequest request) {
        return orchestrator.executeWorkflow(request);
    }
}
