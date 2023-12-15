public class Loan
{
  // initialize instance variables
  private int loanAmount;
  private int numMonths;
  private double interest;
  private double monthlyPayment;

  public Loan()
  {
    // do nothing
  }

  public Loan(int loanAmount, int numMonths, double interest)
  {
    this.loanAmount = loanAmount;
    this.numMonths = numMonths;
    this.interest = interest;

    // calculate monthly payment
    double totalInterest = (loanAmount * (interest / 100)) * (numMonths / 12);
    monthlyPayment = Double.parseDouble(String.format("%.2f", (loanAmount + totalInterest) / numMonths));
  }

  public int getLoanAmount()
  {
    return loanAmount;
  }

  public double getMonthlyPayment()
  {
    return monthlyPayment;
  }

  public String toString()
  {
    return "Loan Amount: $" + loanAmount +
         "\nInterest Rate: %" + interest +
         "\nMonthly Payment: $" + monthlyPayment;
  }
}
