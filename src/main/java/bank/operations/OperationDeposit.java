package bank.operations;

import java.sql.SQLException;

import bank.IOManager;
import bank.Session;
import bank.exceptions.ValueException;

public class OperationDeposit implements Operation {

  public String getText() {
    return "Deposit";
  }

  public void run(Session session, IOManager ioManager) throws ValueException, SQLException {
    if (session != null) {
      double value = ioManager.requestDouble("Ammount: ");
      session.getAccount().deposit(value);
    }
  }

}
