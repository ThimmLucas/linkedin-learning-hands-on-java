package bank.entities;

import java.sql.SQLException;

import bank.DataSource;
import bank.exceptions.ValueException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.setId(id);
    this.setType(type);
    this.setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Account deposit(double ammount) throws ValueException, SQLException {
    if (ammount < 0) {
      throw new ValueException("Only positive values");
    } else {
      this.setBalance(this.balance + ammount);
    }
    this.save();
    return this;
  }

  public Account withdraw(double ammount) throws ValueException, SQLException {
    if (ammount < 0 || ammount > this.balance) {
      throw new ValueException("Insufficient funds");
    } else {
      this.setBalance(this.balance - ammount);
    }
    this.save();
    return this;
  }

  private void save() throws SQLException {
    DataSource.UpdateAccount(this.getId(), this.getBalance());
  }
}
