package com.lvl.DTO;

import lombok.Data;

@Data
public class RegisterResponse {
	boolean success;
    private String message; // Thêm thuộc tính message
}
