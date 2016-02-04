package com.brian.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.app.dao.DepartmentRepository;
import com.brian.app.dao.EmployeeRepository;
import com.brian.app.model.Department;
import com.brian.app.model.Employee;

@Service
public class TestService {
@Autowired
DepartmentRepository departmentRepository;

@Autowired
EmployeeRepository employeeRepository;
@Transactional
public void save()
{
	Department department=new Department();
	department.setName("brian");
	departmentRepository.save(department);
	Employee employee=new Employee("samuel",department);
	 employeeRepository.save(employee);
	
	
}

@Transactional
public List<Department>  list()
{
return departmentRepository.getdep();
	
}
}
