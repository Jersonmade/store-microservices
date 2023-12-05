package com.jersonmade.orderservice.service;

import com.jersonmade.orderservice.dto.InventoryResponse;
import com.jersonmade.orderservice.dto.OrderItemsDto;
import com.jersonmade.orderservice.dto.OrderRequest;
import com.jersonmade.orderservice.model.Order;
import com.jersonmade.orderservice.model.OrderItems;
import com.jersonmade.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderItemsList(orderItems);

        List<String> scuCodes = order.getOrderItemsList().stream()
                .map(OrderItems::getScuCode)
                .toList();

        InventoryResponse[] inventoryResponseArr = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("scuCode", scuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result = Arrays.stream(inventoryResponseArr)
                .allMatch(InventoryResponse::isInStock);

        if (result) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is in stock");
        }

    }

    private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setScuCode(orderItemsDto.getScuCode());
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        return orderItems;
    }

}
