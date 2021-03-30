package ch06.inner;

interface AccountChecker {
    boolean isEligible();
    boolean isBalanceEnough(Integer amount);
}


public class Account {

    class InternalAudit implements AccountChecker {

    }

    class ExternalAudit implements AccountChecker {

    }

    private Integer balance;
    private Integer accountNo;
    private AccountChecker checker;

    public Account(Integer accountNo, Integer balance) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.checker = new InternalAudit();
    }

    public void transfer(Account other, Integer amount) {
        ExternalAudit otherChecker = other.new ExternalAudit();

        if (    checker.isEligible() &&
                checker.isBalanceEnough(amount) &&
                otherChecker.isEligible() ) {
            this.balance -= amount;
            other.balance += amount;
        } else {
            throw new IllegalArgumentException("One of the accounts is not eligible!");
        }

    }
}
