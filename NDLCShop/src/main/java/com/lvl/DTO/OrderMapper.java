package com.lvl.DTO;

import java.util.stream.Collectors;
import com.lvl.Entity.Order;


public class OrderMapper {

    // Chuyển từ Order Entity sang OrderDTO
    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .address(order.getAddress())
                .amount(order.getAmount())
                .phone(order.getPhone())
                .status(order.getStatus())
                .orderDetails(order.getOrderDetails().stream()
                        .map(orderDetail -> OrderDetailDTO.builder()
                                .orderDetailId(orderDetail.getOrderDetailId())
                                .price(orderDetail.getPrice())
                                .quantity(orderDetail.getQuantity())
                                .productId(orderDetail.getProduct().getProductId())
                                .productName(orderDetail.getProduct().getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    // Chuyển từ OrderDTO sang Order Entity
    public static Order toOrderEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setAddress(orderDTO.getAddress());
        order.setAmount(orderDTO.getAmount());
        order.setPhone(orderDTO.getPhone());
        order.setStatus(orderDTO.getStatus());
        // Chuyển đổi danh sách OrderDetailDTO sang OrderDetail Entity (nếu cần)
        // Thiết lập các chi tiết đơn hàng trong order
        return order;
    }
}

