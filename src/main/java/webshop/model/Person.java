package webshop.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany
    private Set<Role> roles;

    public Person(String email) {
	this.email = email;
    }

    public Person() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String name) {
	this.email = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

}
