package bank.operations;

import bank.IOManager;
import bank.Session;

public class OperationCheckBalance implements Operation {

  public String getText() {
    return "Check Balance";
  }

  public void run(Session session, IOManager ioManager) {
    if (session != null) {
      ioManager.print("The balance for account {1} is {2} usd", new Object[] {
          session.getCustomer().getName(),
          session.getAccount().getId(),
          session.getAccount().getBalance()
      });
    }
  }

}
