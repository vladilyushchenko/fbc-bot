package com.fbc.bot.util;

import com.fbc.bot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;

@Component
@RequiredArgsConstructor
@Slf4j
public class MembersImporter {

    private final UserService userService;

    @PostConstruct
    @SneakyThrows
    public void importMembers() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(ResourceUtils.getFile("classpath:members.csv")))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                var user = new org.telegram.telegrambots.meta.api.objects.User();
                user.setUserName(values[0]);
                user.setFirstName(values[1]);
                user.setId(Long.valueOf(values[2]));

                if (!userService.existsByTelegramId(user.getId())) {
                    userService.createUser(user);
                    log.info("Saved new user " + user);
                }
            }
        }
    }
}