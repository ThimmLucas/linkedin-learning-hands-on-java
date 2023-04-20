package bank;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import bank.entities.Customer;

public class Authenticator {
  public static Customer login(String username, String password) throws LoginException, SQLException {
    Customer customer = DataSource.GetCustomer(username);
    if (customer == null || !customer.getPassword().equals(password)) {
      throw new LoginException("Username or password not valid");
    }
    customer.setAuthenticated(true);
    return customer;
  }

  public static void logout(Customer customer) {
    customer.setAuthenticated(false);
  }
}
