package practice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Table(name = "student")
@Entity
public class Student implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private int id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "studentid")
    private String studentId;
    @Column(name = "dob")
    @NotNull
    private String DOB;
    @NotNull
    @Column(name = "address")
    private String address;

}
