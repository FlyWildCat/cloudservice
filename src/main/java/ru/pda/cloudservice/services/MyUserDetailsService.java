package ru.pda.cloudservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pda.cloudservice.entitys.AuthUser;
import ru.pda.cloudservice.repositorys.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AuthUser myUser= userRepository.findByUsername(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Неизвестный пользователь: " + userName);
        }
        UserDetails user = User.builder()
                .username(myUser.getUsername())
                .password("{noop}" + myUser.getPassword())
                .roles(myUser.getRole().toUpperCase())
                .build();
        return user;
    }
}
