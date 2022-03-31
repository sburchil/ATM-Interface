package ATM;

public enum Payment {
    CREDIT (0.0, 0.0, 0.0), 
    DEBIT (0.0, 0.0, 0.0), 
    CASH (0.0, 0.0, 0.0), 
    CHECK (0.0, 0.0, 0.0);

    double amtDeposited, amtWithdrawn, balanceTotal;

    Payment(double amtDeposited, double amtWithdrawn, double amtTotal){
        this.amtDeposited = amtDeposited;
        this.amtWithdrawn = amtWithdrawn;
        this.balanceTotal = amtTotal;
    }



}
