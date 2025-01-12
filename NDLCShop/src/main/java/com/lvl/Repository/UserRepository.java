package com.lvl.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lvl.Entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByStatusTrue();


	User findByToken(String token);
	
    User findByEmail(String email);
    
    boolean existsByEmail(String email);
	

}