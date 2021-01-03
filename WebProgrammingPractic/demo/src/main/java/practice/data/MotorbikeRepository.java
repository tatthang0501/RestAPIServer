package practice.data;

import org.springframework.data.repository.CrudRepository;

import practice.Motorbike;

public interface MotorbikeRepository extends CrudRepository<Motorbike,Integer>{
    
}
