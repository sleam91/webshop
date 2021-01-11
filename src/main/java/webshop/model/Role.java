package webshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<Person> persons;

    public Role(String role) {
	this.role = role;
    }

    public Role() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }
    
    @JsonIgnore
    public List<Person> getPersons() {
	return persons;
    }

    public void setPersons(List<Person> persons) {
	this.persons = persons;
    }

}
