package edu.bondarmih.memorygame.service;

import edu.bondarmih.memorygame.dto.UserDTO;
import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.Role;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.repository.GameResultRepository;
import edu.bondarmih.memorygame.repository.RoleRepository;
import edu.bondarmih.memorygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 05.08.16.
 */

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GameResultRepository gameResultRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, GameResultRepository gameResultRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.gameResultRepository = gameResultRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByName(String name) {
        User user = userRepository.findByName(name);
        return user;

    }

    public UserDTO findByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO(user);
        userDTO.setBestResult(getUserBestResult(user));
        userDTO.setGameCount(getUserGameCount(user));
        return userDTO;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> result = userRepository.findAll()
                .stream()
                .map(this::getUserDTO)
                .collect(Collectors.toList());
        return result;
    }

    private UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user);

        Integer bestResult = getUserBestResult(user);
        userDTO.setBestResult(bestResult);

        Integer gameCount = getUserGameCount(user);
        userDTO.setGameCount(gameCount);

        return userDTO;
    }

    public Integer getUserBestResult(User user) {
        GameResult result = gameResultRepository.findTop1ByUserOrderByResultAsc(user);
        if (result == null) {
            return 0;
        }
        return result.getResult();
    }

    public Integer getUserGameCount(User user) {
        Integer result = gameResultRepository.getUserGameCount(user);
        if (result == null) {
            return 0;
        }
        return result;
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
