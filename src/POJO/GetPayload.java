package POJO;

//import FilePayload.GetCources;

public class GetPayload {
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private GetCources courses;
	private String linkedIn;
	
	public void setinstructor(String instructor) {
		this.instructor=instructor;
	}
	public void seturl(String url) {
		this.url=url;
	}
	public void setservices(String services) {
		this.services=services;
	}
	public void setexpertise(String expertise) {
		this.expertise=expertise;
	}
	public void setcourses(POJO.GetCources courses) {
		this.courses=courses;
	}
	public void setlinkedIn(String linkedIn) {
		this.linkedIn=linkedIn;
	}
	public String getinstructor() {
		return instructor;
	}
	public String geturl() {
		return url;
	}
	public String getservices() {
		return services;
	}
	public String getexpertise() {
		return expertise;
	}
	public GetCources getcourses() {
		return courses;
	}
	public String getlinkedIn() {
		return linkedIn;
	}
	
}
