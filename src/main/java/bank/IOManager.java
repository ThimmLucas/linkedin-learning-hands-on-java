package bank;

import java.text.MessageFormat;
import java.util.Scanner;

public class IOManager {
  private Scanner scanner;

  public IOManager() {
    this.scanner = new Scanner(System.in);
  }

  public void print(String message) {
    System.out.println(message);
  }

  public void print(String message, Object[] params) {
    System.out.println(MessageFormat.format(message, params));
  }

  public String read() {
    return this.scanner.next();
  }

  public String request(String message, Object[] params) {
    this.print(message, params);
    return this.scanner.next();
  }

  public int requestInt(String message, Object[] params) {
    this.print(message, params);
    return this.scanner.nextInt();
  }

  public double requestDouble(String message, Object[] params) {
    this.print(message, params);
    return this.scanner.nextDouble();
  }

  public String request(String message) {
    return this.request(message, new Object[] {});
  }

  public int requestInt(String message) {
    return this.requestInt(message, new Object[] {});
  }

  public double requestDouble(String message) {
    return this.requestDouble(message, new Object[] {});
  }

  public void close() {
    this.scanner.close();
  }
}
