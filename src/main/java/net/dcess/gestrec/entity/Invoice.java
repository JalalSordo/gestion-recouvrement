/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.entity;



import java.time.LocalDate;
import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nexxion Sarl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotEmpty
    private String customer;
    
    @Column(nullable = false)
    @NotEmpty
    private double amount;
    
    @Column(nullable = false)
    @NotEmpty
    private LocalDate dueDate;
    
    @Column(nullable = false)
    @NotEmpty
    private String description;
    
    @Column(nullable = false)
    @NotEmpty
    private LocalDate creationDate;
}
