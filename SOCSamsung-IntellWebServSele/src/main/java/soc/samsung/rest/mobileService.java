package soc.samsung.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/heartbeat")
public class mobileService {

    @GET
    @Produces("application/json")
    public Response heartBeat() {
        System.out.println("in");
        return ok("asd");
    }

    protected Response ok(Object obj) {
        return Response.status(Status.OK).entity(obj).build();
    }
}
