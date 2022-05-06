package com.te.employeeAssessment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.employeeAssessment.dto.EmployeeInfo;
import com.te.employeeAssessment.dto.EmployeeLeave;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu-mys-01");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		EmployeeInfo employeeInfo=new EmployeeInfo();
		employeeInfo.setEmail("ss@ga");
		employeeInfo.setEmpName("ss");
		employeeInfo.setEmpType("Manager");
		employeeInfo.setPassword("ss@@");
		
//		EmployeeInfo employeeInfo2 =new  EmployeeInfo();
//		employeeInfo2.setEmail("aa@ga");
//		employeeInfo2.setEmpName("aa");
//		employeeInfo2.setEmpType("employee");
//		employeeInfo2.setPassword("aa@@");
		
		EmployeeLeave employeeLeave=new EmployeeLeave();
		employeeLeave.setLeaveDate("12.11.11");
		employeeLeave.setLstatus("Pending");
		employeeLeave.setEmployeeInfo(employeeInfo);
		
//		EmployeeLeave employeeLeave2=new EmployeeLeave();
//		employeeLeave2.setLeaveDate("11.11.11");
//		employeeLeave2.setLstatus("pending");
//		employeeLeave2.setEmployeeInfo(employeeInfo);
		
		List<EmployeeLeave> employeeLeaves=new ArrayList<EmployeeLeave>();
		employeeLeaves.add(employeeLeave);
		//employeeLeaves.add(employeeLeave);
		
		employeeInfo.setEmployeeLeave(employeeLeaves);
		
		entityTransaction.begin();
		entityManager.persist(employeeInfo);
		//entityManager.persist(employeeInfo2);
		entityTransaction.commit();
				
    }
}
