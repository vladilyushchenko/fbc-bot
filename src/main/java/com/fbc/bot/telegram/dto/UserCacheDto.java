package com.fbc.bot.telegram.dto;

import com.fbc.bot.common.dto.BaseDto;
import com.fbc.bot.telegram.message.input.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCacheDto<T> implements BaseDto {

    private Long userId;
    private T data;
    private MessageType currentMessageType;
}