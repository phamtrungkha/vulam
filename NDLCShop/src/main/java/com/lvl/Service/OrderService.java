package com.lvl.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lvl.Entity.Order;
import com.lvl.Entity.OrderDetail;
import com.lvl.Entity.Product;
import com.lvl.Entity.User;
import com.lvl.Repository.OrderDetailRepository;
import com.lvl.Repository.OrderRepository;
import com.lvl.Repository.ProductRepository;
import com.lvl.Repository.UserRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    // Thêm sản phẩm vào giỏ hàng
    public boolean AddToOrder(String userId, String productId) {
        User user = userRepository.findById(Long.parseLong(userId)).orElse(null);
        Product product = productRepository.findById(Long.parseLong(productId)).orElse(null);

        if (user != null && product != null) {
            List<Order> orders = GetOrderByUserId(user);

            // Tìm giỏ hàng hiện tại (status = 1)
            Order currentOrder = orders.stream()
                .filter(order -> order.getStatus() == 1)
                .findFirst()
                .orElse(null);

            if (currentOrder != null) {
                // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
                OrderDetail existingOrderDetail = currentOrder.getOrderDetails().stream()
                    .filter(detail -> detail.getProduct().getProductId().equals(product.getProductId()))
                    .findFirst()
                    .orElse(null);

                if (existingOrderDetail != null) {
                    // Sản phẩm đã tồn tại -> Tăng số lượng và cập nhật giá
                    existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + 1);
                    existingOrderDetail.setPrice(existingOrderDetail.getPrice() +
                        (product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100)));
                    orderDetailRepository.save(existingOrderDetail);

                    // Cập nhật tổng tiền của giỏ hàng
                    currentOrder.setAmount(currentOrder.getAmount() +
                        (product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100)));
                    orderRepository.save(currentOrder);
                } else {
                    // Sản phẩm chưa tồn tại -> Tạo mới
                    OrderDetail newOrderDetail = new OrderDetail();
                    double priceAfterDiscount = product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100);
                    newOrderDetail.setPrice(priceAfterDiscount);
                    newOrderDetail.setQuantity(1);
                    newOrderDetail.setOrder(currentOrder);
                    newOrderDetail.setProduct(product);
                    orderDetailRepository.save(newOrderDetail);

                    // Cập nhật tổng tiền của giỏ hàng
                    currentOrder.setAmount(currentOrder.getAmount() + priceAfterDiscount);
                    orderRepository.save(currentOrder);
                }
                return true;
            } else {
                // Chưa có giỏ hàng -> Tạo mới giỏ hàng và thêm sản phẩm
                double priceAfterDiscount = product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100);
                Order newOrder = new Order();
                LocalDateTime currentDateTime = LocalDateTime.now();
                newOrder.setAmount(priceAfterDiscount);
                newOrder.setOrderDate(currentDateTime);
                newOrder.setPhone(user.getPhone());
                newOrder.setStatus(1);
                newOrder.setUser(user);
                orderRepository.save(newOrder);

                OrderDetail newOrderDetail = new OrderDetail();
                newOrderDetail.setPrice(priceAfterDiscount);
                newOrderDetail.setQuantity(1);
                newOrderDetail.setOrder(newOrder);
                newOrderDetail.setProduct(product);
                orderDetailRepository.save(newOrderDetail);
                return true;
            }
        }
        return false;
    }

    // Lấy danh sách đơn hàng của người dùng theo trạng thái
    public List<Order> getOrdersByUserIdAndStatus(Long userId) {
        return orderRepository.findByUser_UserIdAndStatusIn(userId, List.of(1, 2, 3, 4));
    }

    @Transactional
    public List<Order> GetOrderByUserId(User user) {
        // Lấy đơn hàng của người dùng có trạng thái '1'
        return orderRepository.findByUserAndStatus(user, 1);
    }
}
