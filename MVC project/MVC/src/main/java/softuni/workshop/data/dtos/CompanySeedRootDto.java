package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedRootDto {
    @XmlElement(name = "company")
    List<CompanySeedDto> companySeedDtos;

    public CompanySeedRootDto() {
    }

    public List<CompanySeedDto> getCompanySeedDtos() {
        return companySeedDtos;
    }

    public void setCompanySeedDtos(List<CompanySeedDto> companySeedDtos) {
        this.companySeedDtos = companySeedDtos;
    }
}
