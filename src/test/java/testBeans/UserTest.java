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
public class UserTest {

User user = new User();

     public UserTest() {}

@Test
public void testCreateUser(){

String firtName = "Jalal";
String lastName = "Sordo";
String username = "admin";
String password = "admin";
String email = "js@live.com";

String userAttendu = ("Jalal", "Sordo", "admin", "admin", "js@live.com");
String resUser = user.createUser(firtName, lastName, username, password, email));
assertEquals(userAttendu, resUser);
fail("This user does not exist !");
}
  
}