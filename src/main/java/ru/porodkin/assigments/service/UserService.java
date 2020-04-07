package ru.porodkin.assigments.service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import ru.porodkin.assigments.domain.Role;
//import ru.porodkin.assigments.domain.User;
//import ru.porodkin.assigments.repository.UserRepository;
//
//import java.util.Collections;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username);
//    }
//
//    public boolean addUser(User user){
//        User byUsername = userRepository.findByUsername(user.getUsername());
//
//        if (byUsername != null) {
//            return false;
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepository.save(user);
//
//        return true;
//    }
//
//}
