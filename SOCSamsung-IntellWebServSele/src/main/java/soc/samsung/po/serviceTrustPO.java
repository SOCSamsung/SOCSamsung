package soc.samsung.po;

public class serviceTrustPO {
	private String serviceName;
	private String serviceUrl;
	private int trustValue;
	
	public String getServiceUrl(){
		return serviceUrl;
	}
	
	public String getServiceName(){
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public int getServiceTrustValue(){
		return trustValue;
	}
	
	public void setServiceUrl(String url){
		serviceUrl = url;
	}
	
	public void setServiceTrustValue(int value){
		trustValue = value;
	}
	
}
