package com.lvl.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvl.Entity.AppRole;
import com.lvl.Entity.User;
import com.lvl.Entity.UserRole;
import com.lvl.Repository.AppRoleRepository;
import com.lvl.Repository.UserRepository;
import com.lvl.Repository.UserRoleRepository;

@Service
public class UserService {
	@Autowired
	private AppRoleRepository approleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}
	public List<UserRole> findbyUser(User user) {
		return userRoleRepository.findByUser(user);
	}
	public Optional<User> findUserByUserId(Long user){
		return userRepository.findById(user);
	}
	
	public AppRole findbyId() {
		return approleRepository.findById(1);

	}
	public void save(UserRole userrole) {
		userRoleRepository.save(userrole);
	}
	public User loginUser(String email, String password) {
	    User user = userRepository.findByEmail(email);
	    if (user != null && user.getPassword().equals(password)) {
	        return user;
	    }
	    return null;
	}
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
	}
	
	public User createUser(User user) {
        // Mã hóa mật khẩu trước khi lưu
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        // Kiểm tra xem user có tồn tại không
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setGender(user.getGender());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setPassword(user.getPassword());
//            if(user.getPassword() != null && !user.getPassword().isEmpty()){
//                String encodedPassword = passwordEncoder.encode(user.getPassword());
//                updatedUser.setPassword(encodedPassword);
//            }
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + user.getUserId());
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}