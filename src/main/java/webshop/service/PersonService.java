package webshop.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import webshop.data.PersonRepository;
import webshop.data.RoleRepository;
import webshop.model.Person;
import webshop.model.Role;

@Service
@SessionScope
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Person findByEmail(String email) {
	return personRepository.findByEmail(email).orElseThrow();
    }

    public void register(String email, String password, String confirmPassword) {
	if (personRepository.findByEmail(email).isPresent()) {
	    throw new IllegalArgumentException("Error, email already in use");
	}
	if (!password.equals(confirmPassword)) {
	    throw new IllegalArgumentException("Error, passwords do not match");
	}

	Person person = new Person(email);
	person.setPassword(passwordEncoder.encode(password));
	person.setRoles(Set.of(roleRepository.findById(2).orElseThrow()));

	personRepository.save(person);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Person person = personRepository.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
	return new User(person.getEmail(), person.getPassword(), getAuthorities(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
	return roles.stream().map(Role::getRole)
		.map((SimpleGrantedAuthority::new))
		.collect(Collectors.toSet());
    }

}
