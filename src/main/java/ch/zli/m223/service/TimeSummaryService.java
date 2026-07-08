package ch.zli.m223.service;

import ch.zli.m223.dto.TimeSummaryDto;
import ch.zli.m223.model.Entry;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class TimeSummaryService {

    public List<TimeSummaryDto> CalculateSummaryPerDay(List<Entry> entries) {
        Map<LocalDate, Duration> summaryByDate = new TreeMap<>();

        if (entries == null) {
            return new ArrayList<>();
        }

        for (Entry entry : entries) {
            if (entry == null || entry.getCheckIn() == null || entry.getCheckOut() == null) {
                continue;
            }

            LocalDate date = entry.getCheckIn().toLocalDate();
            Duration duration = Duration.between(entry.getCheckIn(), entry.getCheckOut());
            summaryByDate.merge(date, duration, Duration::plus);
        }

        List<TimeSummaryDto> summaries = new ArrayList<>();
        for (Map.Entry<LocalDate, Duration> summary : summaryByDate.entrySet()) {
            summaries.add(new TimeSummaryDto(summary.getKey(), summary.getValue()));
        }

        return summaries;
    }
}