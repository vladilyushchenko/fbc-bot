package com.fbc.bot.bereal.service;

import com.fbc.bot.bereal.model.BerealTextTemplate;
import com.fbc.bot.bereal.repository.BerealTextTemplateRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BerealTextTemplateService {

    private final BerealTextTemplateRepository repository;

    public List<BerealTextTemplate> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public BerealTextTemplate save(BerealTextTemplate textTemplate) {
        return repository.save(textTemplate);
    }
}
