package bank.operations;

import java.sql.SQLException;

import bank.IOManager;
import bank.Session;
import bank.exceptions.ValueException;

public class OperationWithdraw implements Operation {

  public String getText() {
    return "Withdraw";
  }

  public void run(Session session, IOManager ioManager) throws ValueException, SQLException {
    if (session != null) {
      double value = ioManager.requestDouble("Ammount: ");
      session.getAccount().withdraw(value);
    }
  }

}
