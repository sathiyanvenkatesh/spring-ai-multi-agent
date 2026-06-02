package com.example.multiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class BankingPolicyTool {

    @Tool(description = "Check bank loan eligibility policy using credit score and EMI ratio")
    public String checkLoanPolicy(Integer creditScore, Double emiRatio) {
        if (creditScore == null || emiRatio == null) {
            return "Policy check failed: credit score or EMI ratio is missing.";
        }
        if (creditScore >= 750 && emiRatio <= 0.40) {
            return "Eligible. Strong credit score and acceptable EMI ratio.";
        }
        if (creditScore >= 650 && emiRatio <= 0.50) {
            return "Conditionally eligible. Manual review recommended.";
        }
        return "Not eligible. Credit score or EMI ratio is outside policy limit.";
    }
}
