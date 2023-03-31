package com.fbc.bot.bereal.repository;

import com.fbc.bot.bereal.model.BerealTextTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BerealTextTemplateRepository extends CrudRepository<BerealTextTemplate, Long> {
}
