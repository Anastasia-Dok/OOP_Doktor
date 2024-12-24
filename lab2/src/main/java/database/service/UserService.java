package database.service;

import database.entity.UserEntity;
import database.repositories.UserRepository; //Интерфейс репозитория, который
// предоставляет методы для взаимодействия с базой данных
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    //загрузка данных пользователя на основе переданного имени пользователя.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));//шифруется пароль с помощью PasswordEncoder
        userRepository.save(user);
    }// сохраняет нового пользователя в базе данных
}
