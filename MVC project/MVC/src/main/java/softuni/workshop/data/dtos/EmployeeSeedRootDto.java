package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSeedRootDto {

    @XmlElement(name = "employee")
    List<EmployeeSeedDto> employeeSeedDtoList;

    public EmployeeSeedRootDto() {
    }

    public List<EmployeeSeedDto> getEmployeeSeedDtoList() {
        return employeeSeedDtoList;
    }

    public void setEmployeeSeedDtoList(List<EmployeeSeedDto> employeeSeedDtoList) {
        this.employeeSeedDtoList = employeeSeedDtoList;
    }
}
