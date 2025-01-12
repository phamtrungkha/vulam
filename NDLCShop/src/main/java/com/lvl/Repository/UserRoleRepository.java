package com.lvl.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvl.Entity.User;
import com.lvl.Entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//	@Query("SELECT ur.roleId FROM UserRole ur WHERE ur.userId = (SELECT u.userId FROM User u WHERE u.email = :email)")
//    Long findRoleIdByEmail(@Param("email") String email);
	List<UserRole> findByUser(User user);
	
//	void deleteAll(List<UserRole> userRoles);


	@Modifying
	@Transactional
	@Query("delete from UserRole ur WHERE ur.user.id = :userId")
	void deleteByUserId(@Param("userId") Long userId);

}
