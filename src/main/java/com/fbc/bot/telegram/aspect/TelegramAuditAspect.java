package com.fbc.bot.telegram.aspect;

import com.fbc.bot.telegram.service.BotSendMessageService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Aspect
@Component
@RequiredArgsConstructor
public class TelegramAuditAspect {

    private final BotSendMessageService botSendMessageService;

    @AfterReturning("@annotation(com.fbc.bot.config.annotation.TelegramAudit)")
    void sendEvent(JoinPoint joinPoint) {
        for (var arg : joinPoint.getArgs()) {
            if (arg instanceof Update) {
                var update = (Update) arg;
                botSendMessageService.sendMessageToDefaultChat(update);
            }
        }
    }
}