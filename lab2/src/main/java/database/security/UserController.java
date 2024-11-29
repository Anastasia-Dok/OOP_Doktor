package database.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
//Контроллер обрабатывает HTTP запросы и взаимодействует с сервисом
@Controller
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//Метод для отображения страницы логина (HTTP GET):
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
//Метод для отображения домашней страницы (HTTP GET):
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model,
                              HttpServletRequest request) {
        try {
            // Создаем объект аутентификации
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Устанавливаем аутентификацию в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/home";
        } catch (AuthenticationException e) {
            // Если аутентификация не удалась, показываем ошибку
            model.addAttribute("error", "Неверное имя пользователя или пароль");
            return "login"; // Отображаем страницу логина с ошибкой
        }
    }


//Метод для регистрации пользователя (HTTP POST):
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        //Сначала проверяет, существует ли уже пользователь с таким именем, используя репозиторий.
        //Если такой пользователь есть, возвращается сообщение об ошибке с кодом 400 (Bad Request).
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }
//Если форма регистрации валидна, создается новый объект UserEntity,
//устанавливаются свойства (имя, зашифрованный пароль, роль и время создания
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setCreationTime(LocalDateTime.now());

        userRepository.save(newUser);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}
