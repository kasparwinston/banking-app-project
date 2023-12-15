public class User
{
  // initialize instance variables
  private String name, socialSecNum;
  private double balance;
  private int creditScore;

  public User()
  {
    // do nothing
  }

  public User(String name, String socialSecNum, double balance, int creditScore)
  {
    this.name = name;
    this.socialSecNum = socialSecNum;
    this.balance = balance;
    this.creditScore = creditScore;
  }

  public double getBalance()
  {
    return balance;
  }

  public int getCreditScore()
  {
    return creditScore;
  }

  public void withdraw(double withdrawlAmount)
  {
    balance -= withdrawlAmount;
  }

  public void deposit(double depositAmount)
  {
    balance += depositAmount;
  }

  public String toString()
  {
    return "Last Name: " + name.substring(name.indexOf(" ") + 1) +
         "\nFirst Name: " + name.substring(0, name.indexOf(" ") + 1) +
         "\nSocial Security Number: xxx-xx-" + socialSecNum.substring(7) +
         "\nCredit Score: " + creditScore +
         "\nBalance: $" + balance;
  }
}
