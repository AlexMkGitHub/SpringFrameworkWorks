package my.homework.user_service;

import my.homework.user_controller.UserSpecifications;
import my.homework.user_dto.UserDto;
import my.homework.user_preset.User;
import my.homework.user_preset.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

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
        return userToDto(userRepository.save(
                        new User(
                                user.getId(),
                                user.getUsername(),
                                user.getEmail(),
                                encoder.encode(user.getPassword())
                        )));
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    private static UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), null);
    }
}
