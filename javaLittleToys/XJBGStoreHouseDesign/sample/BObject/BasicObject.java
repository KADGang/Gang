//此包下的基类与派生类均使用JAXB的xml文件编码解码的相关注释用于生成保存为xml文件
package sample.BObject;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlSeeAlso({book.class,painting.class,videoCD.class})
public class BasicObject {
    @XmlTransient
    private final SimpleStringProperty identifier = new SimpleStringProperty("");
    @XmlTransient
    private final SimpleStringProperty title = new SimpleStringProperty("");
    @XmlTransient
    private final SimpleStringProperty author = new SimpleStringProperty("");
    @XmlTransient
    private final SimpleStringProperty grade = new SimpleStringProperty("");

    public BasicObject() {
        this("", "", "","");
    }

    public BasicObject(String identifier, String title, String author, String grade) {
        setIdentifier(identifier);
        setTitle(title);
        setAuthor(author);
        setGrade(grade);
    }
    @XmlElement
    public String getIdentifier() {
        return identifier.get();
    }

    public void setIdentifier(String fName) {
        identifier.set(fName);
    }
    @XmlElement
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String fName) {
        title.set(fName);
    }
    @XmlElement
    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String fName) {
        author.set(fName);
    }
    @XmlElement
    public String getGrade(){return grade.get();}

    public void setGrade(String fName){grade.set(fName);}
}
