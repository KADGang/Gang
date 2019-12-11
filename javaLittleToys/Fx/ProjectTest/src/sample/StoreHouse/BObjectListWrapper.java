package sample.StoreHouse;

import sample.BObject.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//添加使用JAXB进行对对象列表的xml编码及解码的相关注释
@XmlRootElement(name = "BasicObjects")
public class BObjectListWrapper {

    private List<BasicObject> BasicObjects;

    @XmlElement(name = "BasicObject")
    public List<BasicObject> getBasicObjects(){
        return BasicObjects;
    }

    public void setBasicObjects(List<BasicObject> basicObjects) {
        BasicObjects = basicObjects;
    }
}
