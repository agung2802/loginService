package firstspring.belajarspringrestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import firstspring.belajarspringrestfulapi.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>  {
    
}
