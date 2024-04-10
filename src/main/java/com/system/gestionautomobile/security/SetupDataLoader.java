package com.system.gestionautomobile.security;


import com.system.gestionautomobile.entity.User;
import com.system.gestionautomobile.entity.UserRole;
import com.system.gestionautomobile.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

    Optional<User> existingsUser1 = userRepository.findByEmail("tanafaat.rca.16@gmail.com");
    Optional<User> existingsUser2 = userRepository.findByEmail("ilias.rouchdi21@gmail.com");

    if (!existingsUser1.isPresent()){
    User user1 = User.builder()
            .email("tanafaat.rca.16@gmail.com")
            .lastname("Abdessamad")
            .firstname("Tanafaat")
            .username("tanafaat")
            .role(UserRole.ROLE_ADMIN)
            .password(passwordEncoder.encode("tanafaat"))
            .build();
            userRepository.save(user1);

    }
        if(!existingsUser2.isPresent()){
        User user2 = User.builder()
                .email("ilias.rouchdi21@gmail.com")
                .lastname("ilias")
                .firstname("Rochdi")
                .username("rochdi")
                .role(UserRole.ROLE_ADMIN)
                .password(passwordEncoder.encode("rochdi"))
                .build();
        userRepository.save(user2);
        }
        alreadySetup =true;
}

}
