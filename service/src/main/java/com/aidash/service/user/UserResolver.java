package com.aidash.service.user;

import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.aidash.repository.entity.User;
import com.aidash.schemas.model.CreateUserInputTO;
import com.aidash.schemas.model.UpdateUserInputTO;
import com.aidash.schemas.model.UserTO;
import com.aidash.schemas.model.UsersPageTO;
import com.aidash.schemas.model.PageInfoTO;

import java.util.UUID;

@Controller
public class UserResolver {
    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public User createUser(@Argument("input") CreateUserInputTO input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setCompanyName(input.getCompanyName());
        user.setPhoneNumber(input.getPhoneNumber());

        return userService.createUser(user);
    }

    @QueryMapping
    public User user(@Argument("id") UUID id) {
        return userService.getUser(id);
    }

    @QueryMapping
    public UsersPageTO users(@Argument("limit") Integer limit, @Argument("offset") Integer offset) {
        Page<UserTO> page = userService.getUsers(limit, offset).map(user -> {
            UserTO userTO = new UserTO();
            userTO.setId(user.getId().toString());
            userTO.setEmail(user.getEmail());
            userTO.setFirstName(user.getFirstName());
            userTO.setLastName(user.getLastName());
            userTO.setCompanyName(user.getCompanyName());
            userTO.setPhoneNumber(user.getPhoneNumber());
            return userTO;
        });

        UsersPageTO result = new UsersPageTO();
        result.setItems(page.getContent());

        PageInfoTO pageInfo = new PageInfoTO();
        pageInfo.setHasNextPage(page.hasNext());
        pageInfo.setHasPreviousPage(page.hasPrevious());
        result.setPageInfo(pageInfo);

        return result;
    }

    @MutationMapping
    public User updateUser(@Argument("id") UUID id, @Argument("input") UpdateUserInputTO input) {
        return userService.updateUser(id, input);
    }

    @MutationMapping
    public boolean deleteUser(@Argument("id") UUID id) {
        return userService.deleteUser(id);
    }
}