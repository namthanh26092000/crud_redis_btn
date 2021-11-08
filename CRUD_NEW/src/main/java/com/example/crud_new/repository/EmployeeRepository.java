package com.example.crud_new.repository;

import com.example.crud_new.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;//crud hash



   private ListOperations listOperations;
    private SetOperations setOperations;
    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.listOperations= redisTemplate.opsForList();
        this.setOperations = redisTemplate.opsForSet();
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;

    }
//    public  void saveEmployee(Employee employee){
//        listOperations.set("EMPLOYEE",employee.getId(),employee);
//    }
    public void saveEmployee(Employee employee){

        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }
//    public List<Employee> findAll(){
//        return  listOperations.index("EMPLOYEE");
//    }
    public List<Employee> findAll(){

        return hashOperations.values("EMPLOYEE");
    }
    public Employee findById(Integer id){

        return (Employee) hashOperations.get("EMPLOYEE", id);
    }
//    public  Employee findById(Integer id){
//        return (Employee) listOperations.index("EMPLOYEE",id);
//    }

    public void update(Employee employee){
        saveEmployee(employee);
    }

    public void delete(Integer id){
        hashOperations.delete("EMPLOYEE", id);
    }
}
