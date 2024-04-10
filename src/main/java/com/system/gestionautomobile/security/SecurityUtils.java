package com.system.gestionautomobile.security;

import com.system.gestionautomobile.repository.UserRepository;
import com.system.gestionautomobile.security.BlacklistToken.BlacklistRepository;
import com.system.gestionautomobile.security.BlacklistToken.BlacklistToken;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SecurityUtils {


    private UserRepository userRepository;
    private BlacklistRepository blacklistRepository;


    public void addToBlacklist(String jwtToken) {
        BlacklistToken blacklistToken = new BlacklistToken();
        blacklistToken.setToken(jwtToken);
        blacklistRepository.save(blacklistToken);
    }

}
