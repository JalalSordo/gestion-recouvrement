/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package testBeans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
 
/**
 *
 * @author FO
 */
public class InvoiceTest {

     public InvoiceTest() {
 }

@Test
public void testCreateInvoice(){

      Invoice invoice = new Invoice();

      String description = "Bon de commande N10255";
      Customer customer = customerPhilip;
      Double amount = 1000.0;
      LocalDate dueDate = LocalDate.of(2022, 2, 13);

      String resAttendu = ("Bon de commande N10255", customerPhilip, 1000.0, LocalDate.of(2022, 2, 13);
      String resInvoice = user.createUser(description, customer, amount, dueDate);

      assertEquals("this invoice does not exist !", resInvoice, resAttendu);
       }
}
