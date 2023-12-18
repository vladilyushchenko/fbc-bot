package com.fbc.bot.bereal.model;

import lombok.Data;

import jakarta.persistence.*;

import static com.fbc.bot.common.model.base.BaseEntity.ALLOCATION_SIZE;

@Data
@Entity
@Table(name = "bereal_text_templates")
public class BerealTextTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bereal_seq_generator")
    @SequenceGenerator(name = "bereal_seq_generator",
            sequenceName = "SEQ_BEREAL_TEXT_TEMPLATES",
            allocationSize = ALLOCATION_SIZE)
    private Long id;
    private String text;
}
