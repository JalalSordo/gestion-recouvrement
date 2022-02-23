/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Account implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)  
private String username;

@NotEmpty
@Size(min=8, max=20)  
private String password;

@Column(nullable = false)
@NotEmpty
private String firstName;

@Column(nullable = false)
@NotEmpty
private String lastName;

@Column(nullable = false)
@NotEmpty
@Email
private String email;

}
