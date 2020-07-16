package com.example.service;

import com.example.domain.store.Store;
import com.example.domain.user.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public boolean save(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser == null){
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }
}
