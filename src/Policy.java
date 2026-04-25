public class Policy {
    private String policyNumber;
    private String clientName;
    private double basePremium;
    private int riskLevel;
    private double vehicleValue;
    private boolean hasAlarm;
    private boolean claimFreeClient;

    private static int createdPolicyCount = 0;
    private static final double ADMINISTRATIVE_FEE = 50.0;


    public Policy(String policyNumber, String clientName, double basePremium, int riskLevel, double vehicleValue, boolean hasAlarm, boolean claimFreeClient) {
        this.policyNumber = policyNumber;
        this.clientName = clientName;
        this.basePremium = basePremium;
        this.riskLevel = riskLevel;
        this.vehicleValue = vehicleValue;
        this.hasAlarm = hasAlarm;
        this.claimFreeClient = claimFreeClient;
        createdPolicyCount++;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public static int getCreatedPolicyCount() {
        return createdPolicyCount;
    }

    public String getRiskSummary() {
        return "Policy: " + policyNumber + " | Client: " + clientName + " | Risk Level: " + riskLevel;
    }
}