package com.fbc.bot.telegram.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fbc.bot.telegram.model.MessageType.*;

@Getter
@RequiredArgsConstructor
public enum ResourceMessageKey {
    ASK_START("ask.start", START),
    ASK_JOIN_PARTY("ask.joinParty", JOIN_PARTY),
    ASK_SHARE_COCK_SIZE("ask.shareCockSize", SHARE_COCK_SIZE),
    ASK_GROUP_SHARE_COCK_SIZE("ask.groupShareCockSize", SHARE_COCK_SIZE);

    private final String value;
    private final MessageType type;
}