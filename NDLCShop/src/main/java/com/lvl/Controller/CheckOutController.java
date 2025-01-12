package com.lvl.Controller;

import com.lvl.Entity.Order;
import com.lvl.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/orders")
    public Optional<Order> getOrder(@RequestParam("userId") Long userId) {
        return checkoutService.getOrderForUser(userId);
    }

    @PostMapping("/updateOrder")
    public String updateOrder(@RequestParam("orderId") Long orderId, 
                              @RequestParam("address") String address, 
                              @RequestParam("phone") String phone) {
        checkoutService.updateOrder(orderId, address, phone);
        return "Order updated successfully!";
    }
}
