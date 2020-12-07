package webshop.data;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
