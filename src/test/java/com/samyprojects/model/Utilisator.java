package com.samyprojects.model;

// import org.springframework.data.annotation.Id;
//* Id with JPA should come from jakarta persistence and not from springframework.data.annotation */
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity  // ✅ Correct
@Table(name = "utilisators") // ✅This correctly maps the entity to the table //* But here for simplicity we can name this table users instead */
public class Utilisator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //* ✅ Make sure column name is correct // but this is DB side
    // @Column(name = "username") 
    @JsonProperty("username")  // ✅ Forces JSON key to be "username" DURING DATA TRANSFER OBJECT WHEN SENT TO ANGULAR FOR EXAMPLE
    private String username;
    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id"), 
                inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id"))        
    private List<Role> roles = new ArrayList<>();
    

    
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }




    public Integer getId() {
        return id;
    }




    public void setId(Integer id) {
        this.id = id;
    }


    
    public Utilisator(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisator(){}




    public String getName() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    } 
}
