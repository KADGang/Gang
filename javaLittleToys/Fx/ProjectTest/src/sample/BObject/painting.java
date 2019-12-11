package sample.BObject;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class painting extends BasicObject {
    @XmlTransient
    public final SimpleStringProperty nationality = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty pslength = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty pswidth = new SimpleStringProperty("");

    public painting(){this("","","","","","","");}

    public painting(String identifier, String title, String author, String grade, String nationality, String pslength, String pswidth) {
        setIdentifier(identifier);
        setTitle(title);
        setAuthor(author);
        setGrade(grade);
        setNationality(nationality);
        setPslength(pslength);
        setPswidth(pswidth);
    }

    @XmlElement
    public String getNationality() {
        return nationality.get();
    }

    public void setNationality(String fName) {
        nationality.set(fName);
    }

    @XmlElement
    public String getPslength() {
        return pslength.get();
    }

    public void setPslength(String fName) {
        pslength.set(fName);
    }

    @XmlElement
    public String getPswidth() {
        return pswidth.get();
    }

    public void setPswidth(String fName) {
        pswidth.set(fName);
    }
}
