package pl.bie.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

@AllArgsConstructor
@Entity
@Table(name = "restaurantsincome")
public class RecordEntity {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "voivodeshipName")
    private String voivodeshipName;
    @Column(name = "category")
    private String category;
    @Column(name = "year")
    private int year;
    @Column(name = "value")
    private float value;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RecordEntity that = (RecordEntity) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
