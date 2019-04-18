package models;

public class ProfileImage {
    private String url;
    
    ProfileImage() {
        setUrl("emoticons/emoticon-1.png");
    }
    
    ProfileImage(String imageUrl) {
    	this.setUrl(imageUrl);
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
