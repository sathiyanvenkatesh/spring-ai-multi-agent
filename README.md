# Spring AI Multi-Agent Workflow Demo

## Requirements
- Java 17+
- Maven 3.9+
- OpenAI API key

## Run

Linux/macOS:
```bash
export OPENAI_API_KEY="your-api-key"
mvn spring-boot:run
```

Windows PowerShell:
```powershell
$env:OPENAI_API_KEY="your-api-key"
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
