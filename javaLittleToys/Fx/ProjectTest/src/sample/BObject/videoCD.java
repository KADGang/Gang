package sample.BObject;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class videoCD extends BasicObject {
    @XmlTransient
    public final SimpleStringProperty publisher = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty productionYear = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty duration = new SimpleStringProperty("");

    public videoCD(){this("","","","","","","");}

    public videoCD(String identifier, String title, String author, String grade, String publisher, String productionYear, String duration) {
        setIdentifier(identifier);
        setTitle(title);
        setAuthor(author);
        setGrade(grade);
        setPublisher(publisher);
        setProductionYear(productionYear);
        setDuration(duration);
    }

    @XmlElement
    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String fName) {
        publisher.set(fName);
    }

    @XmlElement
    public String getProductionYear() {
        return productionYear.get();
    }

    public void setProductionYear(String fName) {
        productionYear.set(fName);
    }

    @XmlElement
    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String fName) {
        duration.set(fName);
    }

}
