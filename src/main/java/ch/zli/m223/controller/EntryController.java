package ch.zli.m223.controller;

import ch.zli.m223.dto.EntryDto;
import ch.zli.m223.model.Employee;
import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getEntry(@PathParam("id") Long id) {
        Entry entry = entryService.findById(id);
        if (entry == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entry).build();
    }

    @POST
    public Response createEntry(EntryDto entryDto) {
        try {
            Entry entry = toEntry(entryDto);
            Entry created = entryService.createEntry(entry);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntry(@PathParam("id") Long id) {
        try {
            Entry entry = entryService.findById(id);
            if (entry == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            entryService.deleteEntry(entry);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        
    }

    @PUT
    @Path("/{id}")
    public Response updateEntry(@PathParam("id") Long id, EntryDto entryDto) {
        try {
            Entry entry = toEntry(entryDto);
            entry.setId(id);
            Entry updatedEntry = entryService.updateEntry(entry);
            return Response.ok(updatedEntry).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
       
    }

    private Entry toEntry(EntryDto entryDto) {
        if (entryDto == null) {
            throw new IllegalArgumentException("Entry data is required");
        }
        if (entryDto.getCheckIn() == null) {
            throw new IllegalArgumentException("checkIn is required");
        }
        if (entryDto.getEmployeeId() == null) {
            throw new IllegalArgumentException("employeeId is required");
        }

        Employee employee = new Employee();
        employee.setId(entryDto.getEmployeeId());

        Entry entry = new Entry();
        entry.setCheckIn(entryDto.getCheckIn());
        entry.setCheckOut(entryDto.getCheckOut());
        entry.setEmployee(employee);
        return entry;
    }

}

