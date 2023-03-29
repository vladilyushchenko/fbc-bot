package com.fbc.bot.telegram.handler.input;

import com.fbc.bot.util.MessageKeyConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IncomeCommand {
    ASK_START(MessageKeyConstants.IncomeCommand.ASK_START, MessageType.START),
    ASK_JOIN_PARTY(MessageKeyConstants.IncomeCommand.ASK_JOIN_PARTY, MessageType.JOIN_PARTY),
    ASK_SHARE_COCK_SIZE(MessageKeyConstants.IncomeCommand.ASK_SHARE_COCK_SIZE, MessageType.SHARE_COCK_SIZE),
    ASK_GROUP_SHARE_COCK_SIZE(MessageKeyConstants.IncomeCommand.ASK_GROUP_SHARE_COCK_SIZE, MessageType.SHARE_COCK_SIZE),
    ASK_ALEX_RUBCHINSKIY(MessageKeyConstants.IncomeCommand.ASK_ALEX_RUBCHINSKIY, MessageType.UNKNOWN),
    INLINE_QUERY_ASK_SHARE_COCK_SIZE(MessageKeyConstants.IncomeCommand.INLINE_QUERY_ASK_SHARE_COCK_SIZE, MessageType.INLINE_SHARE_COCK_SIZE),
    ASK_ADD_MUSIC(MessageKeyConstants.IncomeCommand.ASK_ADD_MUSIC, MessageType.ADD_MUSIC);

    private final String key;
    private final MessageType type;

}