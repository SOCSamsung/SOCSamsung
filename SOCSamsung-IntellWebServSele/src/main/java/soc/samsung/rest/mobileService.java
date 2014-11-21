package soc.samsung.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import soc.samsung.dto.*;

@Path("/")
public class mobileService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public Behavior registerStreet(StreetRegistration street) {
        Behavior behavior = new Behavior();
        behavior.setBehavior("sample");
        return behavior;
    }

    /* JSON Showcase classes */
    @GET
    @Produces("application/json")
    @Path("/sampleregistration")
    public StreetRegistration sampleRegistration() {
        StreetRegistration dummy_registration = new StreetRegistration();
        dummy_registration.setStreetName("Murlagan Ave");
        return dummy_registration;
    }

    @GET
    @Produces("application/json")
    @Path("/samplesegment")
    public StreetSegment sampleSegment() {
        StreetSegment dummy_segment = new StreetSegment();
        Point a = new Point();
        a.setLatitude(75.8);
        a.setLongitude(27.8);
        dummy_segment.setPointA(a);
        Point b = new Point();
        a.setLatitude(35.8);
        a.setLongitude(87.8);
        dummy_segment.setPointB(b);
        return dummy_segment;
    }

    @GET
    @Produces("application/json")
    @Path("/sampleevaluation")
    public Evaluation sampleEvaluation() {
        Evaluation eval = new Evaluation();

        StreetSegment dummy_segment = new StreetSegment();
        Point a = new Point();
        a.setLatitude(75.8);
        a.setLongitude(27.8);
        dummy_segment.setPointA(a);
        Point b = new Point();
        a.setLatitude(35.8);
        a.setLongitude(87.8);
        dummy_segment.setPointB(b);
        eval.setSegment(dummy_segment);
        eval.setMilliseconds(12312436232L);
        return eval;
    }

    @GET
    @Produces("application/json")
    @Path("/samplesample")
    public StreetSample sampleSample() {
        StreetSample sample = new StreetSample();
        sample.setStreetName("Super High Street");

        Point a = new Point();
        a.setLatitude(75.8);
        a.setLongitude(27.8);
        sample.setSample(a);
        return sample;
    }
    /* End of JSON Showcase classes */
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/recommendation")
    public Recommendation recommendation(Point segment) {
        Recommendation recommend = new Recommendation();
        recommend.setRecommendedURI("http://recommended");
        return recommend;
    }

    @POST
    @Consumes("application/json")
    @Path("/evaluation_start")
    public Response evaluationStart(Point segment) {
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
