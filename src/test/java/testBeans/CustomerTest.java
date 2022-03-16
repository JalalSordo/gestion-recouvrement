/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package testBeans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
/**
 *
 * @author FO
 */
public class CustomerTest {

     public CustomerTest() {
}

@Test
public void testCreateCustomer(){
  Customer customer = new Customer();

  String firstName = "Philip";
  String lastName = "Riecks";
  String email = "philip@riecks.de";
  LocalDate dayOfBirth = LocalDate.of(1995, 9, 13);

  String resAttendu = ("Philip", "Riecks", "philip@riecks.de", LocalDate.of(1995, 9, 13);
  String resCustomer = customer.createCustomer(description, customer, amount, dueDate);
  assertEquals("this customer does not exist", resCustomer, resAttendu);    
}
}