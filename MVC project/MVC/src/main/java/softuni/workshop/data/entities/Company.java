package softuni.workshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "companies")
public class Company extends BaseClass {
    private String name;
    private List<Project> projectList;

    public Company() {
    }

    public Company(String name, List<Project> list) {
        this.name = name;
        this.projectList = list;
    }

    @OneToMany(mappedBy = "company")
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
