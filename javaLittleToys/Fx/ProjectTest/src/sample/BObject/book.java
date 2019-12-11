package sample.BObject;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class book extends BasicObject {
    @XmlTransient
    public final SimpleStringProperty ISBN = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty publisher = new SimpleStringProperty("");
    @XmlTransient
    public final SimpleStringProperty pages = new SimpleStringProperty("");

    public book(){this("","","","","","","");}

    public book(String identifier, String title, String author, String grade, String publisher, String ISBN, String pages) {
        setIdentifier(identifier);
        setTitle(title);
        setAuthor(author);
        setGrade(grade);
        setPublisher(publisher);
        setISBN(ISBN);
        setPages(pages);
    }

    @XmlElement
    public String getISBN() {
        return ISBN.get();
    }

    public void setISBN(String fName) {
        ISBN.set(fName);
    }

    @XmlElement
    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String fName) {
        publisher.set(fName);
    }

    @XmlElement
    public String getPages() {
        return pages.get();
    }

    public void setPages(String fName) {
        pages.set(fName);
    }

}
