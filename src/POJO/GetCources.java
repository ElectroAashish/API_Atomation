package POJO;

import java.util.List;

public class GetCources {
	private List<GetwebAutomation> webAutomation;
	private List<GetApi> api;
	private List<GetMobile> mobile;
	
	public List<GetwebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<GetwebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<GetApi> getApi() {
		return api;
	}
	public void setApi(List<GetApi> api) {
		this.api = api;
	}
	public List<GetMobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<GetMobile> mobile) {
		this.mobile = mobile;
	}
}
