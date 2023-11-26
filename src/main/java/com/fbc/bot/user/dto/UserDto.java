package com.fbc.bot.user.dto;

import com.fbc.bot.common.dto.BaseDto;
import com.fbc.bot.user.model.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Value;

import static com.fbc.bot.util.ValidationConstants.NOT_NULL_FIELD;

@Value
@Setter
public class UserDto implements BaseDto {

    Long id;
    @NotNull(message = NOT_NULL_FIELD)
    String firstName;
    @NotNull(message = NOT_NULL_FIELD)
    String lastName;
    @NotNull(message = NOT_NULL_FIELD)
    String userName;
    @NotNull(message = NOT_NULL_FIELD)
    UserStatus userStatus;
}