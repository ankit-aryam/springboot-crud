package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDto) {
//        UserEntity user = mapToEntity(userDto);
//        UserEntity savedUser = userRepository.save(user);
//        logger.info("User Created: " +savedUser.getId());
//        return mapToResponseDTO(savedUser);

        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserByID(int id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        //logger.info("User Fetched" + user);
        return modelMapper.map(user, UserResponseDTO.class);
       // return mapToResponseDTO(user);
    }


    @Override
    public List<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        return userPage.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getFilteredUsers(String role, Boolean active, Pageable pageable) {
        Page<UserEntity> userPage;

        // Condition based filtering
        if (role != null && active != null) {
            userPage = userRepository.findByRoleAndActive(role, active, pageable);
        } else if (role != null) {
            userPage = userRepository.findByRole(role, pageable);
        } else if (active != null) {
            userPage = userRepository.findByActive(active, pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }

        return userPage.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserResponseDTO updateUser(int id, UserRequestDTO userDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAge(userDto.getAge());

        modelMapper.map(userDto, user);

        UserEntity updated = userRepository.save(user);
        //return mapToResponseDTO(updated);
        return modelMapper.map(updated, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(int id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    // 🔁 Mapping Helper Methods
//    private UserEntity mapToEntity(UserRequestDTO dto) {
//        return UserEntity.builder()
//                .name(dto.getName())
//                .email(dto.getEmail())
//                .age(dto.getAge())
//                .build();
//    }

//    private UserResponseDTO mapToResponseDTO(UserEntity entity) {
//        return UserResponseDTO.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .email(entity.getEmail())
//                .build();
//    }
}
