package com.example;

import java.time.LocalDate;

public interface expirable_interface {

    boolean isExpired();

    LocalDate getExpirationDate();
}