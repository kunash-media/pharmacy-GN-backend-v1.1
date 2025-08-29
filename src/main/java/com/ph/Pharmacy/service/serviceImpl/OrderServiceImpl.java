package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.OrderItemDto;
import com.ph.Pharmacy.dto.request.OrderRequestDto;
import com.ph.Pharmacy.dto.response.OrderResponseDto;
import com.ph.Pharmacy.entity.OrderEntity;
import com.ph.Pharmacy.entity.OrderItemEntity;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.OrderItemRepository;
import com.ph.Pharmacy.repository.OrderRepository;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        OrderEntity orderEntity = new OrderEntity();
        mapOrderFields(orderRequestDto, orderEntity);

        OrderEntity savedEntity = orderRepository.save(orderEntity);

        if (orderRequestDto.getOrderItems() != null && !orderRequestDto.getOrderItems().isEmpty()) {
            List<OrderItemEntity> orderItems = orderRequestDto.getOrderItems().stream()
                    .map(itemDto -> createOrderItemEntity(itemDto, savedEntity))
                    .collect(Collectors.toList());

            orderItemRepository.saveAll(orderItems);
            savedEntity.setOrderItems(orderItems);
        }

        return mapToResponseDto(savedEntity);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return mapToResponseDto(orderEntity);
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        mapOrderFields(orderRequestDto, orderEntity);

        if (orderRequestDto.getOrderItems() != null) {
            if (orderEntity.getOrderItems() != null) {
                orderItemRepository.deleteAll(orderEntity.getOrderItems());
            }

            List<OrderItemEntity> newOrderItems = orderRequestDto.getOrderItems().stream()
                    .map(itemDto -> createOrderItemEntity(itemDto, orderEntity))
                    .collect(Collectors.toList());

            orderItemRepository.saveAll(newOrderItems);
            orderEntity.setOrderItems(newOrderItems);
        }

        OrderEntity updatedEntity = orderRepository.save(orderEntity);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        if (orderEntity.getOrderItems() != null) {
            orderItemRepository.deleteAll(orderEntity.getOrderItems());
        }

        orderRepository.deleteById(orderId);
    }

    private void mapOrderFields(OrderRequestDto requestDto, OrderEntity orderEntity) {
        orderEntity.setShippingAddress(requestDto.getShippingAddress());
        orderEntity.setShippingAddress2(requestDto.getShippingAddress2());
        orderEntity.setShippingCity(requestDto.getShippingCity());
        orderEntity.setShippingState(requestDto.getShippingState());
        orderEntity.setShippingPincode(requestDto.getShippingPincode());
        orderEntity.setShippingCountry(requestDto.getShippingCountry());
        orderEntity.setShippingFirstName(requestDto.getShippingFirstName());
        orderEntity.setShippingLastName(requestDto.getShippingLastName());
        orderEntity.setShippingEmail(requestDto.getShippingEmail());
        orderEntity.setShippingPhone(requestDto.getShippingPhone());

        orderEntity.setBillingCustomerName(requestDto.getBillingCustomerName());
        orderEntity.setBillingLastName(requestDto.getBillingLastName());
        orderEntity.setBillingAddress(requestDto.getBillingAddress());
        orderEntity.setBillingAddress2(requestDto.getBillingAddress2());
        orderEntity.setBillingCity(requestDto.getBillingCity());
        orderEntity.setBillingPincode(requestDto.getBillingPincode());
        orderEntity.setBillingState(requestDto.getBillingState());
        orderEntity.setBillingCountry(requestDto.getBillingCountry());
        orderEntity.setBillingEmail(requestDto.getBillingEmail());
        orderEntity.setBillingPhone(requestDto.getBillingPhone());

        orderEntity.setShippingIsBilling(requestDto.isShippingIsBilling());
        orderEntity.setCustomerFirstName(requestDto.getCustomerFirstName());
        orderEntity.setCustomerLastName(requestDto.getCustomerLastName());
        orderEntity.setCustomerPhone(requestDto.getCustomerPhone());
        orderEntity.setCustomerEmail(requestDto.getCustomerEmail());
        orderEntity.setPaymentMethod(requestDto.getPaymentMethod());
        orderEntity.setTotalAmount(requestDto.getTotalAmount());
        orderEntity.setOrderStatus(requestDto.getOrderStatus());
        orderEntity.setOrderDate(requestDto.getOrderDate());
        orderEntity.setDeliveryDate(requestDto.getDeliveryDate());
    }

    private OrderItemEntity createOrderItemEntity(OrderItemDto itemDto, OrderEntity orderEntity) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(orderEntity);

        if (itemDto.getProductId() != null) {
            ProductEntity product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDto.getProductId()));
            orderItemEntity.setProduct(product);
            orderItemEntity.setItemName(product.getName());
            orderItemEntity.setItemPrice(product.getPrice());
        } else {
            orderItemEntity.setItemName(itemDto.getItemName());
            orderItemEntity.setItemPrice(itemDto.getItemPrice());
        }

        orderItemEntity.setQuantity(itemDto.getQuantity());
        orderItemEntity.setSubtotal(itemDto.getSubtotal());

        return orderItemEntity;
    }

    private OrderResponseDto mapToResponseDto(OrderEntity orderEntity) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(orderEntity.getOrderId());
        responseDto.setShippingAddress(orderEntity.getShippingAddress());
        responseDto.setShippingAddress2(orderEntity.getShippingAddress2());
        responseDto.setShippingCity(orderEntity.getShippingCity());
        responseDto.setShippingState(orderEntity.getShippingState());
        responseDto.setShippingPincode(orderEntity.getShippingPincode());
        responseDto.setShippingCountry(orderEntity.getShippingCountry());
        responseDto.setShippingFirstName(orderEntity.getShippingFirstName());
        responseDto.setShippingLastName(orderEntity.getShippingLastName());
        responseDto.setShippingEmail(orderEntity.getShippingEmail());
        responseDto.setShippingPhone(orderEntity.getShippingPhone());

        responseDto.setBillingCustomerName(orderEntity.getBillingCustomerName());
        responseDto.setBillingLastName(orderEntity.getBillingLastName());
        responseDto.setBillingAddress(orderEntity.getBillingAddress());
        responseDto.setBillingAddress2(orderEntity.getBillingAddress2());
        responseDto.setBillingCity(orderEntity.getBillingCity());
        responseDto.setBillingPincode(orderEntity.getBillingPincode());
        responseDto.setBillingState(orderEntity.getBillingState());
        responseDto.setBillingCountry(orderEntity.getBillingCountry());
        responseDto.setBillingEmail(orderEntity.getBillingEmail());
        responseDto.setBillingPhone(orderEntity.getBillingPhone());

        responseDto.setShippingIsBilling(orderEntity.isShippingIsBilling());
        responseDto.setCustomerFirstName(orderEntity.getCustomerFirstName());
        responseDto.setCustomerLastName(orderEntity.getCustomerLastName());
        responseDto.setCustomerPhone(orderEntity.getCustomerPhone());
        responseDto.setCustomerEmail(orderEntity.getCustomerEmail());
        responseDto.setPaymentMethod(orderEntity.getPaymentMethod());
        responseDto.setTotalAmount(orderEntity.getTotalAmount());
        responseDto.setOrderStatus(orderEntity.getOrderStatus());
        responseDto.setOrderDate(orderEntity.getOrderDate());
        responseDto.setDeliveryDate(orderEntity.getDeliveryDate());

        if (orderEntity.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = orderEntity.getOrderItems().stream()
                    .map(item -> {
                        OrderItemDto itemDto = new OrderItemDto();
                        itemDto.setOrderItemId(item.getOrderItemId());
                        itemDto.setProductId(item.getProduct() != null ? item.getProduct().getId() : null);
                        itemDto.setQuantity(item.getQuantity());
                        itemDto.setItemPrice(item.getItemPrice());
                        itemDto.setSubtotal(item.getSubtotal());
                        itemDto.setItemName(item.getItemName());
                        return itemDto;
                    })
                    .collect(Collectors.toList());
            responseDto.setOrderItems(orderItemDtos);
        }

        return responseDto;
    }
}