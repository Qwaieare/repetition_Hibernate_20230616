package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer city_id;
    @Column(name = "city_name")
    private String city_name;

    public City(int i, String city_name) {
        this.city_name = city_name;
    }

}
