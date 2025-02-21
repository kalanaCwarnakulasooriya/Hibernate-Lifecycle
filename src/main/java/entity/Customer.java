package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    Auto - hibernate
//    IDENTITY - auto genarate on database (auto increment)
//    without - @GeneratedValue you can add custom value
    private String id;
    private String name;
}
