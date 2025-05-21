package com.company.component;

import com.company.auth.dto.AuthResponse;
import com.company.product.dto.ProductResponse;

public interface Components {

    String DELETED = "Successfully deleted";
    String CREATED_AT = "createdAt";
    String ALREADY_EXISTS = "This item Already exists!!";
    String NOT_BLANK = "Not blank!!!";
    String NOT_NULL = "Not null!!!";
    String SMS_1 = "Hi, ";
    String SMS_2 = " Your verification code is: ";
    String SMS_SUCCESSFULL = "Verification code sent to your email successfully. To verify your email, please check the website below:\n http://localhost:8080/api/v1/auth/verification";
    String VERIFICATION_SUCCESS = "Congratulations you successfully registered please login in the website below:\n http://localhost:8080/api/v1/auth/login";
    String LOCATION_ALREADY_EXISTS = "You have already selected your location to update please go to the this link http://localhost:8080/api/v1/location/update";
    String LOCATION_IS_NULL = "You have no location yet. To create location please check the link below. http://localhost:8080/api/v1/location/create";
    String NOT_FOUND = "No item found by this id.";
    String ERROR = "Error ocured while operation. Please try again.";
    String USER_ALREADY_EXIST_BY_EMAIL = "User Already registered by this email.";
}
