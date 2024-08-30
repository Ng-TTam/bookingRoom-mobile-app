package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.RoleRequest;
import com.example.bookingroom.dto.response.PermissionResponse;
import com.example.bookingroom.dto.response.RoleResponse;
import com.example.bookingroom.entity.Permission;
import com.example.bookingroom.entity.Role;
import com.example.bookingroom.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Set;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    private RoleRequest roleRequest;
    private RoleResponse roleResponse;
    private Role role;

    @MockBean
    RoleRepository roleRepository;

    @BeforeEach
    void setUpDate() {
        roleRequest = RoleRequest.builder()
                .name("ADMIN")
                .description("Admin role")
                .permissions(Set.of("APPROVE_ROLE",
                        "APPROVE_PERMISSION",
                        "APPROVE_USER"))
                .build();

        roleResponse = RoleResponse.builder()
                .name("ADMIN")
                .description("Admin role")
                .permissions(Set.of(
                        PermissionResponse.builder().name("APPROVE_PERMISSION").description("Approve CURD permission").build(),
                        PermissionResponse.builder().name("APPROVE_USER").description("Approve data user").build(),
                        PermissionResponse.builder().name("APPROVE_ROLE").description("Approve CURD role").build()))
                .build();

        role = Role.builder()
                .name("ADMIN")
                .description("Admin role")
                .permissions(Set.of(
                        Permission.builder().name("APPROVE_PERMISSION").description("Approve CURD permission").build(),
                        Permission.builder().name("APPROVE_USER").description("Approve data user").build(),
                        Permission.builder().name("APPROVE_ROLE").description("Approve CURD role").build()))
                .build();
    }

    @Test
    void createRole_validRequest_success() {
        //GIVEN
        Mockito.when(roleRepository.save(ArgumentMatchers.any())).thenReturn(role);
        //WHEN
        var response = roleService.create(roleRequest);
        //THEN
        Assertions.assertThat(response.getPermissions().isEmpty());
    }
}
