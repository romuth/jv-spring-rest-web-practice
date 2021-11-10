package mate.academy.spring.service.dto.mapping.impl.response;

import java.util.ArrayList;
import mate.academy.spring.model.Order;
import mate.academy.spring.model.dto.response.OrderResponseDto;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper implements DtoResponseMapper<OrderResponseDto, Order> {

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate().toString());
        dto.setTicketIds(new ArrayList<>());
        order.getTickets()
                .forEach(t -> dto.getTicketIds()
                        .add(t.getId()));
        return dto;
    }
}