package com.lvl.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
	String email;
	String password;
	String name;
}
