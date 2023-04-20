package bank.operations;

import bank.IOManager;
import bank.Session;

public class OperationLogout implements Operation {

  public String getText() {
    return "Logout";
  }

  public void run(Session session, IOManager ioManager) {
    if (session != null) {
      session.close();
      session = null;
    }
  }

}
