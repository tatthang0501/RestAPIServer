package practice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;



@Data
@Table(name = "motorbike")
@Entity
public class Motorbike implements Serializable{
    @Id
    private int id;
    @NotNull
    @Column(name="licenseplate")
    private String licensePlate;
    @NotNull
    @Column(name="price")
    private Float price;

    @OneToOne
    @JoinColumn(name="studentid")
    private Student student;
}
