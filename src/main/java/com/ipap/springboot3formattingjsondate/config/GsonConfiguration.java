package com.ipap.springboot3formattingjsondate.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipap.springboot3formattingjsondate.adapter.GsonLocalDateTimeTypeAdapter;
import com.ipap.springboot3formattingjsondate.adapter.GsonLocalDateTypeAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class GsonConfiguration {
    @Bean
    public Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new GsonLocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeTypeAdapter())
                .create();
    }
}
