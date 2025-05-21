package com.company.users;

import com.company.component.ApiResponse;
import com.company.users.dto.UserResponse;
import com.company.users.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ApiResponse<UserResponse> get(UUID userId) {
        Optional<UsersEntity> optional = userRepository.findByIdAndStatusAndVisibilityTrue(userId, Status.SUCCEED);
        if (optional.isPresent()) {
            UsersEntity usersEntity = optional.get();
            UserResponse userResponse = new UserResponse(usersEntity.getFullName(), usersEntity.getEmail(), usersEntity.getCreatedAt(), usersEntity.getLocationId());
            return new ApiResponse<>(200, userResponse);
        }
        else {
            return new ApiResponse<>(400, "Something went error please try again.");
        }
    }

    public ApiResponse<UserResponse> update(UUID userId, UserUpdateDto userUpdateDto) {
        Optional<UsersEntity> optional = userRepository.findByIdAndStatusAndVisibilityTrue(userId, Status.SUCCEED);
        if (optional.isPresent()) {
            UsersEntity usersEntity = optional.get();
            usersEntity.setFullName(userUpdateDto.getFullName());
            return new ApiResponse<>(200, toResponse(userRepository.save(usersEntity)));
        }
        else {
            return new ApiResponse<>(404, "Something went error please try again.");
        }
    }

    private UserResponse toResponse(UsersEntity entity) {
        return new UserResponse(entity.getFullName(), entity.getEmail(), entity.getCreatedAt(), entity.getLocationId());
    }
}
