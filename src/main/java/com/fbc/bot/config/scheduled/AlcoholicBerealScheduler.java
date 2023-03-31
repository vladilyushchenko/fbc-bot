package com.fbc.bot.config.scheduled;

import com.fbc.bot.bereal.service.AlcoholBerealGenerator;
import com.fbc.bot.config.properties.TelegramProperties;
import com.fbc.bot.telegram.client.TelegramApiClient;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
@Setter
public class AlcoholicBerealScheduler {

    private final TelegramApiClient telegramApiClient;
    private final AlcoholBerealGenerator alcoholBerealGenerator;
    private final TelegramProperties telegramProperties;
    private boolean enabled = false;

    @SneakyThrows
    @Scheduled(fixedRate = 10000)
    public void syncUsers() {
        if (enabled) {
            String message = alcoholBerealGenerator.generateMessage();
            telegramApiClient.sendMessage(telegramProperties.getBot().getBotToken(),
                    Long.valueOf(telegramProperties.getBot().getMainChatId()), message);
        }
    }
}