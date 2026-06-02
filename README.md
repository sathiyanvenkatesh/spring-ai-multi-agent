# Spring AI Multi-Agent Workflow Demo

## Requirements
- Java 17+
- Maven 3.9+
- Install Ollma in local and run in the default post
  http://localhost:11434 
- insatll :ollama pull llama3.1
- verify :http://localhost:11434/api/tags

## Run

Linux/macOS:
```bash
mvn spring-boot:run
```

Windows PowerShell:
```powershell
mvn spring-boot:run
```

## Test health
```bash
curl http://localhost:8080/api/loan-workflow/health
```

## Test workflow
```bash
curl -X POST http://localhost:8080/api/loan-workflow/evaluate \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Raj Kumar",
    "age": 35,
    "monthlyIncome": 120000,
    "existingEmi": 25000,
    "requestedLoanAmount": 1500000,
    "creditScore": 760,
    "loanPurpose": "Home renovation"
  }'
```
## Response 
{
"finalDecision": "COMPLETED",
"recommendation": "Based on the agent outputs provided:\n\n* The IntakeAgent has confirmed the customer's details.\n* The ValidationAgent has validated all required fields.\n* The RiskAgent has classified the loan as HIGH RISK due to a high EMI ratio and low credit score, indicating potential difficulties in managing additional debt and making timely payments.\n* The PolicyAgent has determined that the customer is not eligible for a loan under this policy due to exceeding the allowed range for credit score and EMI ratio.\n\nFinal Decision: REJECT\nReason: High risk classification and non-compliance with policy limits.\nNext Step: Review alternative options or provide additional guidance on improving creditworthiness.",
"agentResults": [
{
"agentName": "IntakeAgent",
"status": "COMPLETED",
"output": "Here are the customer's loan details summarized in simple structured points:\n\n**Customer Information**\n\n* Name: Raj Kumar\n* Age: 35\n\n**Financial Details**\n\n* Monthly income: ₹1,20,000.00\n* Existing EMI (monthly payments): ₹50,000.00\n\n**Loan Request**\n\n* Loan amount requested: ₹15,00,000.00\n* Purpose of loan: Home renovation\n\n**Credit Information**\n\n* Credit score: 250 (Note: A credit score of 250 is considered low and may impact loan eligibility)"
},
{
"agentName": "ValidationAgent",
"status": "COMPLETED",
"output": "Validation successful. All required fields are available."
},
{
"agentName": "RiskAgent",
"status": "COMPLETED",
"output": "Based on the provided information, I would classify this loan as HIGH RISK.\n\nReason:\nThe EMI ratio is already high at 0.42, which indicates that a significant portion of the borrower's income is being allocated towards existing debt repayment. Adding another large loan amount of $1,500,000 will further strain their finances. With a credit score of only 250 (which is relatively low), I would be concerned about the borrower's ability to manage additional debt and make timely payments."
},
{
"agentName": "PolicyAgent",
"status": "COMPLETED",
"output": "The tool has checked the credit score and EMI ratio against the policy limits. Since both values are outside the allowed range, the user is not eligible for a loan under this policy."
}
]
}