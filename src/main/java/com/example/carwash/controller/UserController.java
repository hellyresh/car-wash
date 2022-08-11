package com.example.carwash.controller;

import com.example.carwash.dto.operator.OperatorDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.dto.user.UserUpdateDto;
import com.example.carwash.service.OrderService;
import com.example.carwash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrderService orderService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}/grant")
    @ResponseStatus(HttpStatus.OK)
    public OperatorDto grantOperatorToUser(@PathVariable Long id) {
        return userService.grantOperatorToUser(id);
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getUserOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }
}
