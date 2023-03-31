package com.fbc.bot.telegram.aspect;

import com.fbc.bot.message.service.LocaleMessageProvider;
import com.fbc.bot.telegram.service.BotSendMessageService;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.user.model.UserStatus.ADMIN;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_BEREAL_NOT_ADMIN;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminCheckAuditAspect {

    private final UserService userService;
    private final BotSendMessageService botSendMessageService;
    private final LocaleMessageProvider messageService;

    @SneakyThrows
    @Around("@annotation(com.fbc.bot.config.annotation.AdminCheckAudit)")
    public Object sendEvent(ProceedingJoinPoint joinPoint) {
        for (var arg : joinPoint.getArgs()) {
            if (arg instanceof Update) {
                var update = (Update) arg;

                User user = userService.getUserByTelegramId(update.getMessage().getFrom().getId());

                if (!user.getUserStatus().equals(ADMIN)) {
                    update.getMessage().getChat().getId();
                    botSendMessageService.sendMessage(update.getMessage().getChatId(),
                            messageService.getLocalMessage(ANSWER_BEREAL_NOT_ADMIN));
                    log.info("Message sent");
                    return null;
                }
            }
        }
        return joinPoint.proceed();
    }
}
