package com.fbc.bot.bereal.service;

import com.fbc.bot.bereal.model.BerealTextTemplate;
import com.fbc.bot.bereal.repository.BerealTextTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BerealTextTemplateService {

    private final BerealTextTemplateRepository repository;

    public List<BerealTextTemplate> getAll() {
        List<BerealTextTemplate> textTemplates = new ArrayList<>();
        repository.findAll().forEach(textTemplates::add);
        return textTemplates;
    }

    public BerealTextTemplate save(BerealTextTemplate textTemplate) {
        return repository.save(textTemplate);
    }
}
