package com.fbc.bot.user.mapper;

import com.fbc.bot.user.dto.UserDto;
import com.fbc.bot.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telegramId", source = "user.id")
    User toEntity(org.telegram.telegrambots.meta.api.objects.User user);

    User toEntity(UserDto userDto);
}