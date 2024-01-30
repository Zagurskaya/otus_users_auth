package ru.otus.users.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.users.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
