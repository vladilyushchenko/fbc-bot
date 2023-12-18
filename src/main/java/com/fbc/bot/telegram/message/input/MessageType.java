package com.fbc.bot.telegram.message.input;

import java.util.Set;

public enum MessageType {
    START,
    SHARE_COCK_SIZE,
    JOIN_PARTY,
    INLINE_GLOBAL,
    ADD_MUSIC,
    UNKNOWN,
    TURN_BEREAL_ON,
    TURN_BEREAL_OFF;

    public static Set<MessageType> getInlineMessageTypes() {
        return Set.of(INLINE_GLOBAL);
    }
}