package com.lvl.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long orderId;
    private String address;
    private Double amount;
    private String phone;
    private Integer status;
    private List<OrderDetailDTO> orderDetails;
}
