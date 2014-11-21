package soc.samsung.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import soc.samsung.dto.StreetRegistration;
import soc.samsung.dto.StreetSegment;
import soc.samsung.dto.Evaluation;
import soc.samsung.dto.StreetSample;

@Path("/")
public class mobileService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public Response registerStreet(StreetRegistration street) {
        System.out.println("in");
        return ok(); // Returns Behavior
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/recommendation")
    public Response recommendation(StreetSegment segment) {
        return ok(null); // Returns recommendation
    }

    @POST
    @Consumes("application/json")
    @Path("/evaluation_start")
    public Response evaluationStart(StreetSegment segment) {
        return ok();
    }

    @POST
    @Consumes("application/json")
    @Path("/evaluate")
    public Response evaluate(Evaluation evaluation) {
        return ok();
    }

    @POST
    @Consumes("application/json")
    @Path("/streetsample")
    public Response submitPoint(StreetSample sample) {
        return ok();
    }

    protected Response ok(Object obj) {
        return Response.status(Status.OK).entity(obj).build();
    }

    protected Response ok() {
        return Response.status(Status.OK).build();
    }
}
