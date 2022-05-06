package com.te.employeeAssessment.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_info")
public class EmployeeInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "e_Id")
	private int empId;
	@Column(name = "e_name")
	private  String empName;
	@Column(name = "e_type")
	private String empType;
	@Column(name = "e_email")
	private String email;
	@Column(name = "e_password")
	private String password;
	@OneToMany(mappedBy = "employeeInfo",cascade = CascadeType.ALL)
	private List<EmployeeLeave> employeeLeave;
	@Override
	public String toString() {
		return "EmployeeInfo [empId=" + empId + ", empName=" + empName + ", empType=" + empType + ", email=" + email
				+ ", password=" + password + ", employeeLeave=" + employeeLeave + "]";
	}
	
}
