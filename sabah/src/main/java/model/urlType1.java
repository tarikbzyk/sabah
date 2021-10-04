package model;

public class urlType1 {


    public String contentUrl;
    public String elementName;


    public urlType1(String contentUrl,String elementName) {
        this.contentUrl = contentUrl;
        this.elementName=elementName;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
}
