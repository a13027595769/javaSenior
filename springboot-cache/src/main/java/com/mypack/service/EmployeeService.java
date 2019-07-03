package com.mypack.service;

import com.mypack.domain.Employee;
import com.mypack.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp",cacheManager = "employeeCacheManager")
//定义公共的cacheNames
@Service
public class EmployeeService {
    //    key = "#root.methodName+'['+#id+']'"
    @Autowired
    EmployeeMapper employeeMapper;

    //Cacheable是方法执行之前就会调用里面的东西,没有的话就创建一个key，有的话就不用创建了，直接从缓存中拿
    @Cacheable(value = {"emp"}/*,keyGenerator = "myGenerrator",condition = "#a0>1"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /*和@Cacheable不一样，这个直接就会调用里面的方法，执行完之后，再按照key的生成策略，把数据放到缓存中去*/
    @CachePut(value = {"emp"}, key = "#result.id")
    public Employee update(Employee employee) {
        System.out.println("updateEmp" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @CacheEvict(value = "emp", key = "#id"/*beforeInvocation = true*/)
    public void delete(Integer id) {
        System.out.println("deptEmp:" + id);
        //employeeMapper.deleteEmp(id);
    }

    /**
     * 复合注解
     * 在方法执行之前，@Cacheable会去缓存中查找是否有#lastName的注解，没有就创建了一个key
     * 方法执行之后，里面就有了数据，@CachePut就会把，#result.id,#result.email,也创建相应的key
     * 所以就能根据#lastName,#result.id,#result.email，都能找到相应的对象
     * 但是第二次使用lastName缓存中就没有了。因为put一定会执行，相当于覆盖吧
     */

     @Caching( cacheable = @Cacheable(cacheNames = {"emp"},key = "#lastName"),
     put = {
     @CachePut(value = "emp",key = "#result.id"),
     @CachePut(value = "emp",key = "#result.email")
     },evict = @CacheEvict("#result.email")
     )
     public Employee getEmpByLastName(String lastName){
     Employee empByLastName = employeeMapper.getEmpByLastName(lastName);
     return empByLastName;
     }


}
