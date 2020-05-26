package softuni.workshop.data.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseClass {
    private Integer id;

    public BaseClass() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false,unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
