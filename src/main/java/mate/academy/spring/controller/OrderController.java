package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.Order;
import mate.academy.spring.model.dto.response.OrderResponseDto;
import mate.academy.spring.service.OrderService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppCartService;
    private final DtoResponseMapper<OrderResponseDto, Order> mapper;

    @Autowired
    public OrderController(UserService userService, OrderService orderService,
                           ShoppingCartService shoppCartService,
                           DtoResponseMapper<OrderResponseDto, Order> mapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppCartService = shoppCartService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<OrderResponseDto> getHistoryByUser(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.get(userId))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(@RequestParam Long userId) {
        return mapper.toDto(orderService.completeOrder(
                shoppCartService.getByUser(userService.get(userId))));
    }
}