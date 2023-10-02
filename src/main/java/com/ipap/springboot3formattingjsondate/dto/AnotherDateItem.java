package com.ipap.springboot3formattingjsondate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AnotherDateItem {


    private final Date date;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime localDateTime;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private final LocalDate localDate;

    public AnotherDateItem(Date date, LocalDateTime localDateTime, LocalDate localDate) {
        this.date = date;
        this.localDateTime = localDateTime;
        this.localDate = localDate;
    }

    public Date getDate() {
        return date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "AnotherDateItem{" +
                "date=" + date +
                ", localDateTime=" + localDateTime +
                ", localDate=" + localDate +
                '}';
    }
}
