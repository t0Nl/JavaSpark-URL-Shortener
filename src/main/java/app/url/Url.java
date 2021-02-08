package app.url;

public class Url {
	private String longUrl;
	private String userId;
	private int redirectType;
	private long numberOfVisits;
	
	public Url(String longUrl, String userId, int redType) {
		this.longUrl = longUrl;
		this.userId = userId;
		this.redirectType = redType;
		this.numberOfVisits = 0;
	}

	public String getLngUrl() {
		return this.longUrl;
	}
	
	public String getUrlUserId() {
		return this.userId;
	}
	
	public int getRedirecttype() {
		return this.redirectType;
	}
	
	public long getVisitNum() {
		return this.numberOfVisits;
	}
	
	public void increaseVisitNum() {
		this.numberOfVisits++;
	}

}
