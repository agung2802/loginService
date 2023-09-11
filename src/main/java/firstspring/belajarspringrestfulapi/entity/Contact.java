/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firstspring.belajarspringrestfulapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Agung
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    
    @Id
    private String id;
    
    @Column(name = "first_name")
    private String firstName;
    
    private String lastName;
    
    private String phone;
    
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    
    @OneToMany(mappedBy = "contact")
    private List<Address> address;
}
