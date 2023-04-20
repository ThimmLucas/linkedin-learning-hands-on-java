package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.entities.Account;
import bank.entities.Customer;

public class DataSource {
  public static Connection connect() throws SQLException {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    connection = DriverManager.getConnection(db_file);
    return connection;
  }

  public static Customer GetCustomer(String username) throws SQLException {
    String sql = "SELECT * FROM customers where username=?";
    Connection conn = connect();
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, username);
    ResultSet res = statement.executeQuery();
    Customer customer = new Customer(
        res.getInt("id"),
        res.getString("name"),
        res.getString("username"),
        res.getString("password"),
        res.getInt("ACCOUNT_ID"));
    conn.close();
    return customer;
  }

  public static Account GetAccount(int id) throws SQLException {
    String sql = "SELECT * FROM accounts where id=?";
    Connection conn = connect();
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setInt(1, id);
    ResultSet res = statement.executeQuery();
    Account account = new Account(
        res.getInt("ID"),
        res.getString("TYPE"),
        res.getDouble("BALANCE"));
    conn.close();
    return account;
  }

  public static void UpdateAccount(int id, double balance) throws SQLException {
    String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
    Connection conn = connect();
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setDouble(1, balance);
    statement.setInt(2, id);
    statement.executeUpdate();
    conn.close();
  }
}
