package ch.zli.m223.controller;

import ch.zli.m223.dto.TimeSummaryDto;
import ch.zli.m223.service.EntryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsController {

    @Inject
    EntryService entryService;

    @GET
    public List<TimeSummaryDto> getTimeSummaries() {
        return entryService.GetTimeSummaries();
    }
}