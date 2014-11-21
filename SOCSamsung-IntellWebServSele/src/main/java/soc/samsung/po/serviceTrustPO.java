package soc.samsung.po;

public class serviceTrustPO {
	private String serviceUrl;
	private int trustValue;
	
	public String getServiceUrl(){
		return serviceUrl;
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
