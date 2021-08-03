package com.ahmad.carrental.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;

    @NonNull
    private String model;

    @NonNull
    private String license;

    @NonNull
    private Boolean available;

//    private String owner;

    @JsonIgnoreProperties("car")
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "customer_car",
            joinColumns =
                    { @JoinColumn(name = "customer_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "car_id", referencedColumnName = "id") })
    private Customer customer;

}
