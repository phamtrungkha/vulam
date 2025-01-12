package com.lvl.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lvl.Entity.Order;
import com.lvl.Entity.User;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

	List<Order> findByUserOrderByOrderIdDesc(User user);

	List<Order> findAllByOrderByOrderIdDesc();

	List<Order> findByStatus(int status);

	List<Order> findByUserAndStatus(User user, int status);
		
	Optional<Order> findByUser_UserId(Long userId);
	
	Optional<Order> findByUserUserIdAndStatus(Long userId, Integer status);
	
	Order findByUser_UserIdAndStatus(Long userId, Integer status);
	
	List<Order> findByUser_UserIdAndStatusIn(Long userId, List<Integer> statuses);

}