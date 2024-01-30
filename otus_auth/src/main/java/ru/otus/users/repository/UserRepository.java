package ru.otus.users.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.users.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String userName, String password);

}
