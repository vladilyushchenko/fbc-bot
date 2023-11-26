package com.fbc.bot.telegram.aspect;

import com.fbc.bot.telegram.service.BotSendMessageService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Aspect
@Component
@RequiredArgsConstructor
public class TelegramAuditAspect {

    private final BotSendMessageService botSendMessageService;
    private final TaskExecutor taskExecutor;

    @After("@annotation(com.fbc.bot.telegram.aspect.TelegramAudit)")
    void sendEvent(JoinPoint joinPoint) {
        for (var arg : joinPoint.getArgs()) {
            if (arg instanceof Update update) {
                taskExecutor.execute(() -> botSendMessageService.sendMessageToDefaultChat(update));
            }
        }
    }
}