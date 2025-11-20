package guru.springframework;

public class Money implements Expression {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    protected String currency() {
        return this.currency;
    }

    @Override
    public Expression times(int multiplier){
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public Money reduce(Bank bank, String to){
        //int rate = (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
        return new Money(this.amount / bank.rate(this.currency, to), to);
    }

    @Override
    public boolean equals(Object object){
        Money money = (Money) object;
        return this.amount == money.amount
                && this.currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }
}
