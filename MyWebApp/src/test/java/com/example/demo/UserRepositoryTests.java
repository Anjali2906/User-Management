package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testNewAdd() {
        User user = new User();
        user.setLastname("Jain");
        user.setPassword("password4");
        user.setFirstName("Vaibhavee");
        user.setEmail("vivo@gmail.com");
        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

        @Test
    public void testUpdate(){
        Integer userId=1;
            Optional<User> optional=repo.findById(userId);
            User user= optional.get();
            user.setPassword("hello123");
            repo.save(user);
            User updatedUser =repo.findById(userId).get();
            Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello123");


    }
    @Test
    public void testGet(){
        Integer userId=2;
        Optional<User> optional=repo.findById(userId);
        User user=optional.get();

        Assertions.assertThat(optional).isPresent();
        System.out.println(optional.get());


    }
    @Test
    public void testDelete(){
        Integer userId=2;
        repo.deleteById(userId);
        Optional<User> optional=repo.findById(userId);
        Assertions.assertThat(optional).isNotPresent();

    }



    }

