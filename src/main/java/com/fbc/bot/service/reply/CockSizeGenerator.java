package com.fbc.bot.service.reply;

import com.fbc.bot.model.User;
import org.springframework.stereotype.Component;

import static com.fbc.bot.model.UserStatus.CLUB_MEMBER;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Component
public class CockSizeGenerator {

    public String generateAnswer(User user) {
        StringBuilder answer = new StringBuilder();
        if (CLUB_MEMBER.equals(user.getUserStatus())) {
            return getMemberAnswer(user, answer, user.getCockSize().getSize());
        }
        return getUnknownMemberAnswer(user, user.getCockSize().getSize());
    }

    private String getUnknownMemberAnswer(User user, Long cockSize) {
        String name = isNull(user.getUserName())
                ? user.getFirstName()
                : "@" + user.getUserName();
//        answer.append(name)
//                .append(", у тебя сегодня ")
//                .append(cockSize)
//                .append("см.");
        return format("%s, у тебя сегодня %dсм.", name, cockSize);
    }

    private String getMemberAnswer(User user, StringBuilder answer, Long cockSize) {
//        String answers = null;
//        answer.append("О боже! Это же ")
//                .append(user.getNickname())
//                .append("!");
        if (cockSize > 40) {
            return format("О боже! Это же %s! Наконец норм хуй - %dсм.", user.getNickname(), cockSize);
//            answer.append(" Наконец норм хуй -  ")
//                    .append(cockSize)
//                    .append("см.");
        }
        return format("О боже! Это же %s! Сегодня взял перерыв, член только %dсм.", user.getNickname(), cockSize);
//        if (cockSize <= 40) {
//            answer.append(" Сегодня взял перерыв, член только ")
//                    .append(cockSize)
//                    .append("см.");
//        }
//        return answers;
    }
}