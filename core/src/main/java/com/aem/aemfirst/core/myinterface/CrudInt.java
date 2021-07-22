package com.aem.aemfirst.core.myinterface;


import java.util.List;

import com.aem.aemfirst.core.bean.StudentDetails;


public interface CrudInt {

	public boolean addStudent(String firstName,String lastName,String email);
	public boolean updateStudentDetails(String firstName,String lastName,String email);
	public boolean deleteStudentDetailsByEmail(String firstName,String lastName,String email);
	public List<StudentDetails> getAllStudentRecords();
	public StudentDetails getStudentDetailsByEmail(String email);

}
