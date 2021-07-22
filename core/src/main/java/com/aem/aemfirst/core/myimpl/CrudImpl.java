package com.aem.aemfirst.core.myimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.aemfirst.core.bean.StudentDetails;
import com.aem.aemfirst.core.dbutils.CrudDbUtil;
import com.aem.aemfirst.core.myinterface.CrudInt;


@Component(service=CrudInt.class, immediate=true)
public class CrudImpl implements CrudInt {
	
	protected static final Logger log = LoggerFactory.getLogger(CrudImpl.class);
	
	@Reference
	CrudDbUtil db;
	@Reference
	CrudDbUtil objss;
	
	Connection con = null;
	PreparedStatement ps = null;
	boolean flag = false;
	@Override
	public boolean addStudent(String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		String sql = "insert into student(firstName, lastName, email) values(?,?,?)";
		boolean flag=false;
		log.info("enterd into the implimentation classs************");
		try {
			con=objss.getDatabaseConnection("mysource");
			ps=con.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			int i=ps.executeUpdate();
			if (i==1) {
				flag=true;
				log.info("recorded sucessfully*******");
			} else {
				flag=false;
				log.info("OOOOOps..............");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (con!=null) {
				try {
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
		return false;
	}
	@Override
	public boolean updateStudentDetails(String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		
		String mysql=("update student set firstName=?,lastName=? where email=?");	
		boolean flag=false;
		try {
			con=objss.getDatabaseConnection("mysource");
			ps=con.prepareStatement(mysql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			int i=ps.executeUpdate();
			if (i==1) {
				flag=true;
			} else {
				flag=false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (con!=null) {
				try {
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
		return flag;
	}
	@Override
	public boolean deleteStudentDetailsByEmail(String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		
		String sql=("delete from student where email=?");
		boolean flag=false;
		try {
			con=objss.getDatabaseConnection("mysource");
			ps=con.prepareStatement(sql);
			ps.setString(1, email);
			int i=ps.executeUpdate();
			if (i==1) {
				flag=true;
			} else {
				flag=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (con!=null) {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
			}
		}
		
		return flag;
	}
	@Override
	public List<StudentDetails> getAllStudentRecords() {
		// TODO Auto-generated method stub
		
		String sql = ("select * from student");
		boolean flag = false;
		ResultSet rs = null;
		List<StudentDetails> list = new ArrayList<StudentDetails>();
		try {
			con=objss.getDatabaseConnection("mysource");
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//list= new ArrayList<Student>();
			
			while (rs.next()) {
				StudentDetails std = new StudentDetails();
				std.setFirstName(rs.getString(1));
				std.setLastName(rs.getString(2));
				std.setEmail(rs.getString(3));
				list.add(std);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (con!=null) {
				try {
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
		return list;
	}
	

	@Override
	public StudentDetails getStudentDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		String sql=("select * from student where email=?");
		StudentDetails std=new StudentDetails();
		try {
			con=objss.getDatabaseConnection("mysource");
			ps=con.prepareStatement(sql);
			ps.setString(1, email);
			rs=ps.executeQuery();
			if (rs.next()) {
				std.setFirstName(rs.getString(1));
				std.setLastName(rs.getString(2));
				std.setEmail(rs.getString(3));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return std;


	}
	
}
