package soc.samsung.rest;

import soc.samsung.dto.*;
import soc.samsung.po.serviceTrustPO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Path("/")
public class mobileService {

	private HashMap<String, List<Point>> verifyPoints;
	private List<serviceTrustPO> serviceTrust;
	private Random randomGenerator;
	
	public mobileService() {
		verifyPoints = new HashMap<>();
		serviceTrust = new ArrayList<serviceTrustPO>();
		
		/* Hardcoded services */
		serviceTrustPO bingService = new serviceTrustPO();
		bingService.serviceUrl = "http://dev.virtualearth.net/REST/V1/Routes/Driving";
		bingService.trustValue = 0;
		serviceTrustPO mapquestService = new serviceTrustPO();
		mapquestService.serviceUrl = "http://open.mapquestapi.com/directions/v2/route";
		mapquestService.trustValue = 0;
		serviceTrustPO googleService = new serviceTrustPO();
		googleService.serviceUrl = "http://dummyurl";
		googleService.trustValue = 0;
		serviceTrust.add(bingService);
		serviceTrust.add(mapquestService);
		serviceTrust.add(googleService);
	}
	

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public Behavior registerStreet(StreetRegistration street) {
        String streetName = street.getStreetName();
        Behavior behavior = new Behavior();
        if (verifyPoints.containsKey(streetName)) {
        	behavior.setBehavior("evaluate");
        	behavior.setVerificationPoints(verifyPoints.get(streetName));
        } else {
        	behavior.setBehavior("sample");
        	behavior.setVerificationPoints(null);
        }
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
    	/* TODO: Non-Random Recommendation logic */
    	int index = randomGenerator.nextInt(serviceTrust.size());
        serviceTrustPO item = serviceTrust.get(index);
        
        /* TODO:  call services and get map uris*/
        Recommendation recommend = new Recommendation();
        recommend.setRecommendedURI("dummy");
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
    	String street = sample.getStreetName();
    	Point samplePoint = sample.getSample();
    	if (verifyPoints.containsKey(street)) {
    		verifyPoints.get(street).add(samplePoint);
    	} else {
    		ArrayList<Point> newList = new ArrayList<Point>();
    		newList.add(samplePoint);
    		verifyPoints.put(street, newList);
    	}
        return ok();
    }

    protected Response ok(Object obj) {
        return Response.status(Status.OK).entity(obj).build();
    }

    protected Response ok() {
        return Response.status(Status.OK).build();
    }
}
