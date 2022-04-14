package com.maint.manager.persistence.repositories;

import com.github.javafaker.Faker;

import java.time.LocalDate;

import static java.util.Locale.ENGLISH;

public class BaseRepo {

    protected static final Faker FAKER = new Faker(ENGLISH);
    protected static final LocalDate CURRENT_DATA = LocalDate.now();
}
