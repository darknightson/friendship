package dev.wetox.wetoxrestful;

import dev.wetox.wetoxrestful.user.User;
import dev.wetox.wetoxrestful.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(
                User.builder().nickname("hong-gil-dong").password("1111").build()
        );
        userRepository.save(
                User.builder().nickname("gang-gam-chan").password("1111").build()
        );
        userRepository.save(
                User.builder().nickname("lee-sun-shin").password("1111").build()
        );
        userRepository.save(
                User.builder().nickname("kakao").password("1111").build()
        );
        userRepository.save(
                User.builder().nickname("naver").password("1111").build()
        );
    }
}
