package com.lvl.DTO;

import lombok.Data;

@Data
public class LoginResponse {
	String role;
	String message;
	boolean success;
}
