package ch.zli.m223.dto;

import java.time.Duration;
import java.time.LocalDate;

public class TimeSummaryDto {

    private LocalDate date;
    private Duration duration;

    public TimeSummaryDto() {
    }

    public TimeSummaryDto(LocalDate date, Duration duration) {
        this.date = date;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}