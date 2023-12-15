import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    // initialize default user and loan
    User user = new User();
    Loan loan = new Loan();

    // initialize other variables
    boolean breakCondition = true;
    boolean hasInfo = false;
    boolean hasLoan = false;

    // initialize scanner
    Scanner input = new Scanner(System.in);

    // main header
    System.out.println("Welcome to Chase bank, how may we screw you over today?");

    // main app loop
    do
    {
      // options
      System.out.println("\n0: Leave.");
      System.out.println("1: Input Information.");
      System.out.println("2: Withdraw Money.");
      System.out.println("3: Take Out a Loan.");
      System.out.println("4: Deposit Money.");
      System.out.println("5: Print Your Information.");

      // main options switch
      switch (input.nextInt())
      {
        // leave
        case 0:
          System.out.println("\nGoodbye.");
          breakCondition = false;

          break;

        // withdraw money
        case 1:
          // initialize variables for User constructor
          String name, socialSecNum;
          double balance;
          int creditScore;

          // scanner method nextInt() doesn't read newline characters, so you must catch it before doing anything else
          // do this multiple times
          input.nextLine();

          // take in values for User constructor
          System.out.print("\nName (First Last): ");
          name = input.nextLine();

          System.out.print("Social Security Number (xxx-xx-xxxx): ");
          socialSecNum = input.nextLine();

          System.out.print("Balance (USD): ");
          balance = input.nextDouble();

          System.out.print("Credit Score (Integer): ");
          creditScore = input.nextInt();

          input.nextLine();

          // reset user object with new information
          user = new User(name, socialSecNum, balance, creditScore);

          // user now has information for other tasks
          hasInfo = true;

          break;

        case 2:
          // initialize withdrawl amount
          double withdrawlAmount;

          input.nextLine();

          // if user doesn't have info, they can't withdraw money
          if (!hasInfo)
          {
            System.out.println("\nYou have no information!");
            break;
          }

          // takes in amount to withdraw
          System.out.print("\nWithdrawl amount: ");
          withdrawlAmount = input.nextDouble();

          input.nextLine();

          // checks to make sure withdrawl amount fulfills requirements, rejecting it if it doesn't
          if ((withdrawlAmount % 10) != 0 || (user.getBalance() - withdrawlAmount) < 100 || withdrawlAmount > 400)
          {
            System.out.println("\nThat's an invalid withdrawl amount! (Must not be greater than $400; Must be a multiple of 10; Must not set your balance below $100;)");
          }
          else
          {
            // withdraws the amount
            user.withdraw(withdrawlAmount);
            System.out.println("\nSuccessfully withdrew $" + withdrawlAmount);
          }

          break;

        // loan
        case 3:
          // initialize variables for Loan constructor
          int loanAmount = 0;
          int numMonths = 0;
          double interest = 0;

          // initialize boolean to check whether it's safe to use Loan constructor
          boolean loanSuccess = false;

          // initialize number 0-4 depending on the range of user's credit score
          int creditScoreRange = (user.getCreditScore() < 600) ? 0 :
                                 (user.getCreditScore() <= 639) ? 1 :
                                 (user.getCreditScore() <= 699) ? 2 :
                                 (user.getCreditScore() <= 769) ? 3 : 4;

          input.nextLine();

          // if user doesn't have info, they can't take out a loan
          if (!hasInfo)
          {
            System.out.println("\nYou have no information!");
            break;
          }

          // check to make sure credit score isn't lower than 600 before beginning loan menu
          if (creditScoreRange != 0)
          {
            System.out.print("\nLoan amount (Max $");
          }
          else
          {
            System.out.println("\nYour credit score is too low! Try paying some bills once in a while...");
            break;
          }

          // max amount for loan changes depending on credit score 
          switch (creditScoreRange)
          {
            case 1:
              System.out.print("30000): ");
              break;

            case 2:
              System.out.print("40000): ");
              break;

            case 3:
              System.out.print("50000): ");
              break;

            case 4:
              System.out.print("50000): ");
              break;
          }

          // takes in value for loan amount
          loanAmount = input.nextInt();
          
          // main loan options switch (probably could have done this better given more time, but it works)
          switch (creditScoreRange)
          {
            // credit score 600 - 639
            case 1:
              if (loanAmount <= 10000)
              {
                // term length options
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");

                // term length options switch
                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    break;

                  case 2:
                    numMonths = 36;
                    break;

                  // any invalid term length
                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                // set interest specific to what's available for user's credit score
                interest = 12;

                // loan was successful (hopefully)
                loanSuccess = true;
              }
              // doing the same thing but with different amounts of money and credit scores for 500+ lines
              // definitely breaks DRY principle, but that wasn't in the rubric :)
              else if (loanAmount <= 20000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 10;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 12;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 12;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 30000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 12;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 14;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 14;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else
              {
                System.out.println("\nThat exceeds the maximum loan amount!");
              }

              break;

            // credit score 640 - 699
            case 2:
              if (loanAmount <= 10000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    break;

                  case 2:
                    numMonths = 36;
                    break;

                  case 3:
                    numMonths = 48;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                interest = 8;

                loanSuccess = true;
              }
              else if (loanAmount <= 20000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 8;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 10;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 10;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 10;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 30000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 10;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 10;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 12;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 12;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 40000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 12;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 12;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 14;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 14;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else
              {
                System.out.println("\nThat exceeds the maximum loan amount!");
              }

              break;

            // credit score 700 - 769
            case 3:
              if (loanAmount <= 10000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    break;

                  case 2:
                    numMonths = 36;
                    break;

                  case 3:
                    numMonths = 48;
                    break;

                  case 4:
                    numMonths = 60;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                interest = 6;

                loanSuccess = true;
              }
              else if (loanAmount <= 20000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 6;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 6;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 8;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 8;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 30000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 8;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 8;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 10;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 10;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 40000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 10;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 10;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 12;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 12;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 50000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 12;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 12;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 14;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 14;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else
              {
                System.out.println("\nThat exceeds the maximum loan amount!");
              }

              break;

            // credit score 770+
            case 4:
              if (loanAmount <= 30000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 5;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 6;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 6;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 6;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 40000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 8;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 9;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 8;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 9;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else if (loanAmount <= 50000)
              {
                System.out.println("\nHow long to pay it back?");
                
                System.out.println("\n1: 24 months");
                System.out.println("2: 36 months");
                System.out.println("3: 48 months");
                System.out.println("4: 60 months");

                switch (input.nextInt())
                {
                  case 1:
                    numMonths = 24;
                    interest = 9;
                    break;

                  case 2:
                    numMonths = 36;
                    interest = 9;
                    break;

                  case 3:
                    numMonths = 48;
                    interest = 10;
                    break;

                  case 4:
                    numMonths = 60;
                    interest = 10;
                    break;

                  default:
                    System.out.println("\nThat's an invalid option!");
                    break;
                }

                loanSuccess = true;
              }
              else
              {
                System.out.println("\nThat exceeds the maximum loan amount!");
              }

              break;
          }
          
          if (loanSuccess) 
          {
            // if the user already has an active loan, remove it before initializing another
            if (hasLoan) user.withdraw(loan.getLoanAmount());

            // take out a loan with the parameters specified by user
            loan = new Loan(loanAmount, numMonths, interest);
            user.deposit(loanAmount);

            // user now has a loan
            hasLoan = true;

            // prints info about the loan
            System.out.println("\nSuccessfully took out a loan of $" + loanAmount + " which you will pay back over " + numMonths + " months.");
            System.out.println("Interest rate is " + interest + "%. Monthly payment is " + "$" + loan.getMonthlyPayment());
          }

          break; 

        // deposit money
        case 4:
          // initialize deposit amount
          double depositAmount;

          input.nextLine();

          // if user doesn't have info, they can't deposit money
          if (!hasInfo)
          {
            System.out.println("\nYou have no information!");
            break;
          }

          // take in value for deposit amount
          System.out.print("\nDeposit amount: ");
          depositAmount = input.nextDouble();

          // deposit money
          user.deposit(depositAmount);
          System.out.println("\nSuccessfully deposited $" + depositAmount);

          break;

        // print information
        case 5:
          // naturally, if user doesn't have info, they can't print it out
          if (hasInfo)
          {
            System.out.println("\nUSER INFO:");
            System.out.println(user.toString());

            // if user has loan, print out loan info
            if (hasLoan)
            {
              System.out.println("\nLOAN INFO:");
              System.out.println(loan.toString());
            }
          }
          else
          {
            System.out.println("\nYou have no information!");
          }

          break;

        // any non-existent option
        default:
          System.out.println("\nThat's an invalid option!");
          break;
      }
    }
    while (breakCondition);

    // close scanner
    input.close();
  }
}
