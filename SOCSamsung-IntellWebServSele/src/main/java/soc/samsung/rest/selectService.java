package soc.samsung.rest;

import soc.samsung.context.UserContext;
import soc.samsung.discovery.SearchService;
import soc.samsung.discovery.ServicesData;



public class selectService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchService searchSrv = new SearchService();
		UserContext userContext = new UserContext();

		//		Fill in the userContext
		
		searchSrv.searchCloudServices(userContext);
		
		//		Assuming we get a list of services
		ServicesData servData = new ServicesData();
		servData.getServiceData(userContext);//(pass a service ID also);
	}
}
