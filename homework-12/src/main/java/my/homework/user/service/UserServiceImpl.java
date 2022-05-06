package my.homework.user.service;

import my.homework.user.controller.UserController;
import my.homework.user.controller.UserSpecifications;
import my.homework.user.dto.UserDto;
import my.homework.user.persist.User;
import my.homework.user.persist.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Page<UserDto> findUsersByFilter(String usernameFilter, String emailFilter,
                                           Integer page, Integer size, String sortField) {
        Specification<User> spec = Specification.where(null);
        if (usernameFilter != null) {
            spec = spec.and(UserSpecifications.usernameContaining(usernameFilter));
        }
        if (emailFilter != null) {
            spec = spec.and(UserSpecifications.emailContaining(emailFilter));
        }
        return userRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                .map(UserServiceImpl::userToDto);
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return userRepository.findById(id).map(UserServiceImpl::userToDto);
    }

    @Override
    public UserDto save(UserDto user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Current user {}:", auth.getName());
        return userToDto(userRepository.save(
                new User(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        encoder.encode(user.getPassword()),
                        user.getRoles()
                )));
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    private static UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRoles()
        );
    }
}
