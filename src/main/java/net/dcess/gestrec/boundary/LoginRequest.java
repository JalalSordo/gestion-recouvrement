/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.boundary;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author aaytouna
 */
@Data
public class LoginRequest implements Serializable {
    @Column(nullable = false)
    @NotEmpty
    private String username;
    
    @Column(nullable = false)
    @NotEmpty
    private String password;
}
