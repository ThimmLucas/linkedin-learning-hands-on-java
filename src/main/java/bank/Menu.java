package bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.security.auth.login.LoginException;

import bank.exceptions.ValueException;
import bank.operations.Operation;
import bank.operations.OperationCheckBalance;
import bank.operations.OperationDeposit;
import bank.operations.OperationLogout;
import bank.operations.OperationWithdraw;

public class Menu {
  private IOManager ioManager;
  private Session session;
  List<String> options = new ArrayList<>();
  static private HashMap<String, Operation> operations = new HashMap<>();

  static public void RegisterOperation(Operation op) {
    operations.put(op.getText(), op);
  }

  public Menu() {
    this.ioManager = new IOManager();
    this.session = null;
  }

  public String[] requestLogin() {
    String username = this.ioManager.request("Username:");
    String passwrod = this.ioManager.request("Password:");
    return new String[] { username, passwrod };
  }

  public void initSession() {
    try {
      this.ioManager.print("Please login");
      String[] credentials = this.requestLogin();
      this.session = Session.CreateSession(credentials[0], credentials[1]);
    } catch (LoginException e) {
      this.ioManager.print("Invalid username or password");
    } catch (SQLException e) {
      this.ioManager.print("Invalid username or password");
    }

  }

  public void showAccountInfo() {
    this.ioManager.print("Hello, {0}! you are at account{1}", new Object[] {
        this.session.getCustomer().getName(),
        this.session.getAccount().getId()
    });
  }

  public void close() {
    this.ioManager.close();
  }

  public void printOperations() {
    ListIterator<String> optionsIterator = this.options.listIterator();
    while (optionsIterator.hasNext()) {
      this.ioManager.print("{0}. {1}", new Object[] { optionsIterator.nextIndex(), optionsIterator.next() });
    }
  }

  public Operation requestOperations() {
    this.printOperations();
    int operation = this.ioManager.requestInt("Operation:");
    return operations.get(this.options.get(operation));
  }

  public void init() {
    // Todo create an factory for methods
    Menu.RegisterOperation(new OperationLogout());
    Menu.RegisterOperation(new OperationDeposit());
    Menu.RegisterOperation(new OperationWithdraw());
    Menu.RegisterOperation(new OperationCheckBalance());
    this.options = new ArrayList<>(Menu.operations.keySet());

    while (true) {
      if (this.session == null
          || (this.session.getCustomer() == null)
          || !this.session.getCustomer().isAuthenticated()) {
        this.initSession();
      } else {
        this.ioManager.print("__________________________________");
        this.showAccountInfo();
        Operation op = this.requestOperations();
        try {
          op.run(session, ioManager);
          this.ioManager.print("Opperation completed");
        } catch (ValueException e) {
          this.ioManager.print("Failed to process opperation:");
          this.ioManager.print(e.getMessage());
        } catch (Exception e) {
          this.ioManager.print("Failed to connect to database");
          this.ioManager.print(e.getMessage());
        }
        this.ioManager.print("__________________________________");
      }
    }
  }

  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.ioManager.print("Welcome to Bank");
    menu.init();
    menu.close();
  }
}