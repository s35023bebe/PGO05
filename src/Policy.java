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

    public double calculateFinalPremium() {
        double finalPremium = basePremium + ADMINISTRATIVE_FEE;

        finalPremium += (riskLevel * 120.0);

        if (vehicleValue > 60000) {
            finalPremium += 150.0;
        }
        if (hasAlarm) {
            finalPremium *= 0.95;
        }
        if (claimFreeClient) {
            finalPremium *= 0.90;
        }

        if (finalPremium < basePremium) {
            finalPremium = basePremium;
        }
        return finalPremium;
    }

    public double calculateRenewalPremium() {
        double currentPremium = calculateFinalPremium();
        double renewalPremium = currentPremium;

        if (riskLevel == 4) {
            renewalPremium *= 1.10;
        } else if (riskLevel >= 5) {
            renewalPremium *= 1.20;
        }

        if (vehicleValue > 60000) {
            renewalPremium += 150.0;
        }

        if (claimFreeClient) {
            renewalPremium *= 0.92;
        }
        if (hasAlarm) {
            renewalPremium *= 0.95;
        }

        double minThreshold = currentPremium * 0.90;
        double maxThreshold = currentPremium * 1.25;

        if (renewalPremium < minThreshold) {
            renewalPremium = minThreshold;
        } else if (renewalPremium > maxThreshold) {
            renewalPremium = maxThreshold;
        }

        return Math.round(renewalPremium * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Policy policy = (Policy) obj;
        return policyNumber.equals(policy.policyNumber);
    }

    @Override
    public String toString() {
        return "Policy[" + policyNumber + "] Client: " + clientName + ", Final Premium: " + String.format("%.2f", calculateFinalPremium());
    }
}