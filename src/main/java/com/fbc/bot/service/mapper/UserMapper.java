package com.fbc.bot.service.mapper;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    UserDto toDto(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "cockSize", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telegramId", source = "user.id")
    User toEntity(org.telegram.telegrambots.meta.api.objects.User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "cockSize", ignore = true)
    @Mapping(target = "telegramId", ignore = true)
    User toEntity(UserDto userDto);
}