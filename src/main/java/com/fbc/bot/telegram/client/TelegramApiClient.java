package com.fbc.bot.telegram.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "telegram-api-client", url = "https://api.telegram.org")
public interface TelegramApiClient {

    @GetMapping("/bot{botToken}/sendMessage")
    Object sendMessage(@PathVariable String botToken,
                       @RequestParam(name = "chat_id") Long chatId,
                       @RequestParam(name = "text") String text);
}