package bank;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import bank.entities.Account;
import bank.entities.Customer;

public class Session {
  private Customer customer = null;
  private Account account = null;

  public Session(
      Customer customer,
      Account account) {
    this.setCustomer(customer);
    this.setAccount(account);
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Account getAccount() {
    return this.account;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public static Session CreateSession(String username, String password) throws LoginException, SQLException {
    Customer customer = Authenticator.login(username, password);
    Account account = DataSource.GetAccount(customer.getAccountId());
    customer.setAuthenticated(true);
    return new Session(customer, account);
  }

  public void close() {
    customer.setAuthenticated(false);
    this.setCustomer(null);
    this.setAccount(null);
  }
}