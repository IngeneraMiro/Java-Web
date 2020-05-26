package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedDto {
    @XmlAttribute(name = "name")
    private String name;

    public CompanySeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}