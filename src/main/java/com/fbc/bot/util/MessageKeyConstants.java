package com.fbc.bot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageKeyConstants {

    public static class Response {

        public static final String ANSWER_UNKNOWN = "answer.unknown";
        public static final String ANSWER_START = "answer.start";
        public static final String ANSWER_JOIN_PARTY = "answer.joinParty";
        public static final String ANSWER_BEREAL_NOT_ADMIN = "answer.bereal.notAdmin";
        public static final String ANSWER_BEREAL_TURNED_ON = "answer.bereal.turnedOn";
        public static final String ANSWER_BEREAL_TURNED_OFF = "answer.bereal.turnedOff";
        public static final String ANSWER_ADD_MUSIC_FAILURE = "answer.addMusic.failure";
        public static final String ANSWER_ADD_MUSIC_SUCCESS = "answer.addMusic.success";
        public static final String ANSWER_ADD_MUSIC_ALREADY_EXISTS = "answer.addMusic.alreadyExists";
        public static final String ANSWER_ADD_MUSIC_WRITE_TITLE = "answer.addMusic.writeTitle";
        public static final String INLINE_QUERY_TITLE_SHARE_COCK_SIZE = "inlineQuery.title.shareCockSize";
        public static final String INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE = "inlineQuery.description.shareCockSize";
        public static final String INLINE_QUERY_TITLE_FIND_FRIEND = "inlineQuery.title.findFriend";
        public static final String INLINE_QUERY_DESCRIPTION_FIND_FRIEND = "inlineQuery.description.findFriend";
        public static final String INLINE_QUERY_REPLY_MEMBER_GOOD_SIZE = "inlineQuery.reply.clubMember.goodCockSize";
        public static final String INLINE_QUERY_REPLY_MEMBER_BAD_SIZE = "inlineQuery.reply.clubMember.badCockSize";
        public static final String INLINE_QUERY_REPLY_DEFAULT_SIZE = "inlineQuery.reply.cockSize";
        public static final String INLINE_QUERY_REPLY_FIND_FRIEND = "inlineQuery.reply.findFriend";
    }

    public static class IncomeCommand {
        public static final String ASK_START = "ask.start";
        public static final String ASK_JOIN_PARTY = "ask.joinParty";
        public static final String ASK_SHARE_COCK_SIZE = "ask.shareCockSize";
        public static final String ASK_GROUP_SHARE_COCK_SIZE = "ask.groupShareCockSize";
        public static final String ASK_ALEX_RUBCHINSKIY = "ask.imAlexRubchinskiy";
        public static final String ASK_ADD_MUSIC = "ask.addMusic";
        public static final String ASK_TURN_BEREAL_ON = "ask.turnBerealOn";
        public static final String ASK_TURN_BEREAL_OFF = "ask.turnBerealOff";
        public static final String INLINE_QUERY_ASK_SHARE_COCK_SIZE = "inlineQuery.ask.shareCockSize";
    }
}