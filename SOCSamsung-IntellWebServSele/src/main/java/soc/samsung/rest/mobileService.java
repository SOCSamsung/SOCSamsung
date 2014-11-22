package soc.samsung.rest;

import soc.samsung.context.UserContext;
import soc.samsung.discovery.ServicesData;
import soc.samsung.dto.*;
import soc.samsung.po.serviceTrustPO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/")
public class mobileService {

	private HashMap<String, List<Point>> verifyPoints;
	private HashMap<String, HashMap<StreetSegment, List<Integer>>> underEvaluation;
	private List<serviceTrustPO> serviceTrust;
	private UserContext context;
	
	public mobileService() {
		State state = State.getInstance();
		verifyPoints = state.verifyPoints;
		underEvaluation = state.underEvaluation;
		serviceTrust = state.serviceTrust;
		context = state.context;
	}
	

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public Behavior registerStreet(StreetRegistration street) {
        String streetName = street.getStreetName();
        Behavior behavior = new Behavior();
        if (verifyPoints.containsKey(streetName)) {
        	System.out.println("**** A user registered for " + streetName + " ******");
        	System.out.println("**** System has started service Verification ******");
        	behavior.setBehavior("evaluate");
        	behavior.setVerificationPoints(verifyPoints.get(streetName));
        } else {
        	System.out.println("**** No previous data found for " + streetName + ", Sampling road ******");
        	behavior.setBehavior("sample");
        	behavior.setVerificationPoints(null);
        }
        System.out.println("**** ******");
        return behavior;
    }

    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/recommendation")
    public Recommendation recommendation(StreetSegment segment) {
        System.out.println("**** Recommendation Requested ******");
        
    	/* TODO: Non-Random Recommendation logic */
//      Recommendation recommend =  new Recommendation();
//      recommend.generateRecommendation(serviceTrust);
        
        System.out.println(serviceTrust);
    	serviceTrustPO item = serviceTrust.get(0);

        System.out.println("**** Random Recommendation Provided: " + item.getServiceName() + "******");
      
    	
        ServicesData resultData = new ServicesData();
        resultData.getServiceData(item, segment, context);

        Recommendation recommend = new Recommendation();
        recommend.setRecommendedURI(resultData.getServiceUrl());
        recommend.setServiceName(item.getServiceName());
        return recommend;
    }

    @POST
    @Consumes("application/json")
    @Path("/evaluation_start")
    public Response evaluationStart(Evaluation evaluation) {
    	StreetSegment segment = evaluation.getSegment();
    	String streetName = evaluation.getStreetName();
    	System.out.println("**** Starting Evaluation for street segment starting at (" + Double.toString(segment.getPointA().getLatitude()) +
    			", " + Double.toString(segment.getPointA().getLongitude()) + ") *****" );
    	if (!underEvaluation.containsKey(streetName)) {
    		underEvaluation.put(streetName, new HashMap<StreetSegment, List<Integer>>());
    	}
		HashMap<StreetSegment, List<Integer>> map = underEvaluation.get(streetName);
		List<Integer> list = new ArrayList<Integer>();
	 
    	for (serviceTrustPO service : serviceTrust) {
            ServicesData resultData = new ServicesData();
            resultData.getServiceData(service, segment, context);
    		list.add(resultData.getDurationForSegment());
    	}
		map.put(segment, list);
		
    	return ok();
    }

    @POST
    @Consumes("application/json")
    @Path("/evaluate")
    public Response evaluate(Evaluation evaluation) {
    	StreetSegment segment = evaluation.getSegment();
    	String streetName = evaluation.getStreetName();
    	Integer duration = (int) (evaluation.getMilliseconds()/1000) / 60 ; // minutes
    	System.out.println("**** Received evaluation for street segment starting at (" + Double.toString(segment.getPointA().getLatitude()) +
    			", " + Double.toString(segment.getPointA().getLongitude()) + ") *****");
    	System.out.println("- The segment was measured at " + Integer.toString(duration) + " minutes");
    	
    	/* Evaluation Logic */
    	List<Integer> list = underEvaluation.get(streetName).get(segment);
    	
    	/* TODO: evaluation logic */
    	
        return ok();
    }

    @POST
    @Consumes("application/json")
    @Path("/streetsample")
    public Response submitPoint(StreetSample sample) {
      	String street = sample.getStreetName();
    	System.out.println("*** New Street Sample received for " + street + " *****");
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
