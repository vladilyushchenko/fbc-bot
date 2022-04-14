package com.fbc.bot.service.mapper;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telegramId", source = "user.id")
    User toEntity(org.telegram.telegrambots.meta.api.objects.User user);

    User toEntity(UserDto userDto);
}