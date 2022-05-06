package com.te.employeeAssessment;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeeAssessment.dto.EmployeeInfo;
import com.te.employeeAssessment.dto.EmployeeLeave;


public class App2 {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu-mys-01");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Scanner scanner=new Scanner(System.in);
		EmployeeInfo employeeInfo=new EmployeeInfo();	
		EmployeeLeave employeeLeave=new EmployeeLeave();
		App2 app2 =new App2();
		boolean s = true;
		String str;
		String str1;
		int id;
		String password;
		int option;
		do {
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Display employeeinfo");
			System.out.println("4. Exit");
			System.out.println("Enetr the option :");
			option= scanner.nextInt();
			
			switch(option) {
			case 1:{
				scanner.nextLine();
				System.out.println("Enter name :");
				employeeInfo.setEmpName(scanner.nextLine());
				
				System.out.println("Enter employee Type :");
				employeeInfo.setEmpType(scanner.nextLine());
				System.out.println("Enter email :");
				employeeInfo.setEmail(scanner.nextLine());
				System.out.println("create Password");
				employeeInfo.setPassword(scanner.nextLine());
				entityTransaction.begin();
				entityManager.persist(employeeInfo);
				entityTransaction.commit();
				break;
			}
			case 2:{
				System.out.println("Enter id");
				id=scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter password");
				password=scanner.nextLine();
				
				str="from EmployeeInfo where empId=:id";
				
				Query query=entityManager.createQuery(str);
				query.setParameter("id", id);
				
				
				
				List<Object> employee = query.getResultList();
				if(employee.size()>0) {
					EmployeeInfo emp =(EmployeeInfo)employee.get(0);
					
					System.out.println(emp);
				
					if(emp.getPassword().equals(password)) {
						if(emp.getEmpType().equals("Manager")) {
							boolean s1=true;
							do {
								System.out.println("1. Show all leave request");
								System.out.println("2. Approve/Reject leave");
								System.out.println("3. Exit");
								System.out.println("Enter the option");
								
								option = scanner.nextInt();
								switch(option) {
								
								case 1: {
								 str = "from EmployeeLeave";
									Query query2 = entityManager.createQuery(str);
									List<EmployeeLeave> resultList = query2.getResultList();
									for (Object object : resultList) {
										EmployeeLeave empleave = (EmployeeLeave) object;
										System.out.println(empleave);
									}
									break;
									
								}
								case 2: {
									str = "from EmployeeLeave";
									Query query2 = entityManager.createQuery(str);
									List<EmployeeLeave> resultList = query2.getResultList();
									scanner.nextLine();
									for (Object object : resultList) {
										EmployeeLeave empleave = (EmployeeLeave) object;
										if(empleave.getLstatus().equals("Pending")) {
											System.out.println(empleave);											
											System.out.println("Enter the status ");
											empleave.setLstatus(scanner.nextLine());
											System.out.println(empleave);
										}
									}
									break;
								}
								case 3:{
									s1=false;
									break;
								}
								}
							}while(s1);
							
						}
						if(emp.getEmpType().equals("Employee")) {
							boolean s2=true;
							do {
								System.out.println("1. show all leave status");
								System.out.println("2. apply leave");
								System.out.println("Enter option");
								option=scanner.nextInt();
								switch(option) {
								case 1:{
									str1="from EmployeeLeave where employeeInfo.empId=:id ";
									Query query1=entityManager.createQuery(str1);
									query1.setParameter("id", id);
									
									
									
									List<EmployeeLeave> employee1 = query1.getResultList();
									for (Object object : employee1) {
										EmployeeLeave leave=(EmployeeLeave) object;
										System.out.println(leave);
									}
									break;
								}
								case 2:{
									scanner.nextLine();
									System.out.println("Enter the date of leave");
									employeeLeave.setLeaveDate(scanner.nextLine());
									employeeLeave.setLstatus("Pending");
									employeeLeave.setEmployeeInfo(emp);
									entityTransaction.begin();
									entityManager.persist(employeeLeave);
									entityTransaction.commit();
									System.out.println("Leave applied");
									break;
								}
								
								case 3:{
									s2=false;
									break;
								}
								}
							}while(s2);
						}
					
					}
					else {
						System.err.println("Incorrect Password and id");
					}
				}
				break;
			}
			case 3:{
				String str3 = "from EmployeeInfo";
				Query query3 = entityManager.createQuery(str3);
				List<EmployeeInfo> resultList = query3.getResultList();
				for (Object object : resultList) {
					EmployeeInfo movieCast = (EmployeeInfo) object;
					System.out.println(movieCast);
				}
				break;
			}
			case 4:{
				
				s=false;
				break;
			}
			}
			
		}while(s);
	}
}
