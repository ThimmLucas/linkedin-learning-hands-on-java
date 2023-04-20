package bank.operations;

import bank.IOManager;
import bank.Session;

public interface Operation {
  public void run(Session session, IOManager ioManager) throws Exception;

  public String getText();
}
