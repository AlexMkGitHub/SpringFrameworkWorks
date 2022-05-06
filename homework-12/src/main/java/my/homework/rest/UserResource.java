package my.homework.rest;

import my.homework.user.controller.NotFoundException;
import my.homework.user.controller.UserController;
import my.homework.user.dto.UserDto;
import my.homework.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

// http://localhost:8080/swagger-ui/index.html
@RequestMapping("/rest/v1/user")
@RestController
public class UserResource {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    private Page<UserDto> findAll(@RequestParam Optional<String> usernameFilter,
                                  @RequestParam Optional<String> emailFilter,
                                  @RequestParam Optional<Integer> page,
                                  @RequestParam Optional<Integer> size,
                                  @RequestParam Optional<String> sortField,
                                  Authentication auth) {
        if (auth != null) {
            logger.info("Current user {}", auth.getName());
        } else {
            logger.info("Current user is anonymous!");
        }
        String usernameFilterValue = usernameFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        String emailFilterValue = emailFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        return userService.findUsersByFilter(
                usernameFilterValue,
                emailFilterValue,
                pageValue,
                sizeValue,
                sortFieldValue);
    }

    @GetMapping("/{id}/id")
    public UserDto findById(@PathVariable long id) {
        return userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Created user shouldn't have id");
        }
        return userService.save(user);
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PutMapping
    public UserDto update(@RequestBody UserDto user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("Created user should have id");
        }
        return userService.save(user);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String sqlException(SQLException ex) {
        return ex.getMessage();
    }
}
