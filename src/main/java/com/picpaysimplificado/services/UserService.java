package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUsertype() == UserType.MERCHANT) {
            throw new Exception("User type 'Merchant' is not authorized to make a transaction");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("User has no balance for this transaction");
        }
    }
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
