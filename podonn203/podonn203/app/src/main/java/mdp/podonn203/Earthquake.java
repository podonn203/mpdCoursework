//
// Name                 Patrick O'Donnell
// Student ID           S1714595
// Programme of Study   BSc Computing
//

package mdp.podonn203;

public class Earthquake {

    // Variables
    private String title;
    private String description;
    private String pubDate;
    private String category;
    private String Depth;
    private String location;
    private String LatLong;
    private double Magnitude;

    //Set default Values

    public Earthquake() {title = "Earthquake Alerts UK";}

    //Set Strings

    public String toString ()
    {
        String instanceInformation;
        instanceInformation = "Location: " + getLocation();
        instanceInformation += "\n" +"Date/Time Published: " + getPubDate();
        instanceInformation += "\n" +"Category: " + getCategory();
        instanceInformation += "\n" +"\n" +"Magnitude: "+ getMagnitude();
        instanceInformation += "\n" +"Depth: " + getDepth();
        instanceInformation += "\n" +"Latitude,Longitude: "  + getLatLong();

        return instanceInformation;

    }

    //get and set title
    public String getTitle() {return title;}

    public void setTitle(String pTitle) {this.title = pTitle;}

    //get and set description
    public String getDescription() {return description;}

    public void setDescription(String pDescription) {this.description = pDescription;}

    //get and set published date
    public String getPubDate() {return pubDate;}

    public void setPubDate(String pPubDate) {this.pubDate = pPubDate;}

    //get and set category
    public String getCategory() {return category;}

    public void setCategory(String pCategory) {this.category = pCategory;}

    //get and set depth
    public String getDepth(){return Depth;}

    public void setDepth(String pDepth) {this.Depth = pDepth;}

    //get and set location
    public String getLocation() {return location;}

    public void setLocation(String pLocation) {this.location = pLocation;}

    //get and set lat/long
    public String getLatLong() {return LatLong;}

    public void setLatLong(String pLatLong) {this.LatLong = pLatLong;}

    //get and set magnitude
    public double getMagnitude() {return Magnitude;}

    public void setMagnitude(double pMagnitude) {this.Magnitude = pMagnitude;}
}


