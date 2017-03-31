package by.epam.movierating.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Даша on 31.03.2017.
 */

@XmlRootElement(name = "commands")
@XmlType(propOrder = { "nameList", "classList" })
public class Com {
    private List<String> nameList;
    private List<String> classList;
    private String command;
    private String name;

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    @XmlElementWrapper(name = "command")
    @XmlElement(name = "name")
    public List<String> getNameList() {
        return nameList;
    }
    @XmlElement(name = "class")
    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }


}
