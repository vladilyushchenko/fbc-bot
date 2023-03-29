package com.fbc.bot.telegram.dto;

import com.fbc.bot.common.dto.BaseDto;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCacheDto<T> implements BaseDto {

    private Long userId;
    private UserCacheStatus cacheStatus;
    private T data;
    private MessageType currentMessageType;
}