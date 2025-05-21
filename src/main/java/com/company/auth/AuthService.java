package com.company.auth;

import com.company.auth.dto.*;
import com.company.component.ApiResponse;
import com.company.component.Components;
import com.company.component.smpt.SmptService;
import com.company.component.smpt.Sms;
import com.company.users.Status;
import com.company.users.UserRepository;
import com.company.users.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final SmptService smptService;

    public ApiResponse<String> register(AuthRegister authRegister) {
        Optional<UsersEntity> optionalUsers = userRepository.findByEmailAndVisibilityTrue(authRegister.getEmail());
        if (optionalUsers.isPresent()) {
            return new ApiResponse<>(400, Components.USER_ALREADY_EXIST_BY_EMAIL);
        }
        else {
            try {
                int i = (int) (Math.random() * 900000) + 100000;
                smptService.sendSms(buildSms(authRegister.getEmail(), "Verification your email" , Components.SMS_1 + authRegister.getFullName() + Components.SMS_2 + i));
                UsersEntity usersEntity = UsersEntity.builder()
                        .email(authRegister.getEmail())
                        .status(Status.REGISTER)
                        .verifCode(i)
                        .fullName(authRegister.getFullName())
                        .build();
                userRepository.save(usersEntity);
                return new ApiResponse<>(200, "Verification code sent to your email successfully. To verify your email, please check the website below:\n http://localhost:8080/api/v1/auth/verify");
            } catch (Exception e) {
                return new ApiResponse<>(404, "Error sending verification code");
            }
        }
    }
    public ApiResponse<AuthVerificationResponse> verification(AuthVerification authVerification) {
        Optional<UsersEntity> optionalUser = userRepository.findByEmailAndVerifCodeAndStatusAndVisibilityTrue(authVerification.getEmail(), authVerification.getCode(), Status.REGISTER);
        if (optionalUser.isPresent()) {
            optionalUser.get().setStatus(Status.VERIFICATION);
            userRepository.save(optionalUser.get());
            return new ApiResponse<>(200, Components.VERIFICATION_SUCCESS, new AuthVerificationResponse(optionalUser.get().getId(), optionalUser.get().getFullName(), optionalUser.get().getEmail()));
        }
        else {
            return new ApiResponse<>(400, "Email or verification code is incorrect.");
        }
    }

    public ApiResponse<AuthVerificationResponse> login(AuthLogin authLogin) {
        Optional<UsersEntity> optional = userRepository.findByEmailAndStatusAndVisibilityTrue(authLogin.getEmail(), Status.VERIFICATION);
        if (optional.isPresent()) {
            try {
                int i = (int) (Math.random() * 900000) + 100000;
                Sms oneTimePassword = buildSms(authLogin.getEmail(), "One time password", "Your one time password is: " + i);
                smptService.sendSms(oneTimePassword);
                optional.get().setVerifCode(i);
                optional.get().setStatus(Status.LOGIN);
                userRepository.save(optional.get());
                return new ApiResponse<>(200, "One time password sent to your email.", new AuthVerificationResponse(optional.get().getId(), optional.get().getFullName(), optional.get().getEmail()));
            } catch (Exception e) {
                return new ApiResponse<>(404, "Error occurred while sending one time password.");
            }
        }
        else {
            return new ApiResponse<>(400, "There is no account registered by this email.");
        }
    }

    public ApiResponse<AuthVerificationResponse> confirmLogin(AuthConfirmLogin authConfirmLogin) {
        Optional<UsersEntity> optional = userRepository.findByEmailAndVerifCodeAndStatusAndVisibilityTrue(authConfirmLogin.getEmail(), authConfirmLogin.getCode(), Status.LOGIN);
        if (optional.isPresent()) {
            optional.get().setVerifCode(777777);
            return new ApiResponse<>(200, "You successfully login to your account.", new AuthVerificationResponse(optional.get().getId(), optional.get().getFullName(), optional.get().getEmail()));
        }
        else {
            return new ApiResponse<>(400, "Email or one time password is incorrect.");
        }
    }

    private Sms buildSms(String email, String subject, String text) {
        return Sms.builder()
                .text(text)
                .subject(subject)
                .to(email)
                .build();
    }
}
