package my.homework.user.controller;

import my.homework.user.persist.User;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecifications {

    public static Specification<User> usernameContaining(String username) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<User> emailContaining(String email) {
        return (root, query, cb) -> cb.like(root.get("email"), "%" + email + "%");
    }
}
