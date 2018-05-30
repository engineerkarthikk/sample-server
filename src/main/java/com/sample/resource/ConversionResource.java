package com.sample.resource;

import com.google.common.collect.ImmutableMap;
import com.sample.bean.ConversionResponse;
import com.sample.service.ConversionService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/convert")
public class ConversionResource {

    public final ConversionService conversionService;

    public ConversionResource(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GET
    @Path("{value}")
    public Response getNumberToWords(@PathParam("value") final int value){
        try {
            final ConversionResponse response = conversionService.getNumberToWords(value);
            return Response.ok().entity(response).build();
        }catch (final NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ImmutableMap.of("errors", e.getMessage())).build();
        } catch (final Exception e) {
            log.error("Error - {}", e.getMessage(), e);
            return Response.serverError()
                    .entity(ImmutableMap.of("errors", e.getMessage())).build();
        }
    }
}
