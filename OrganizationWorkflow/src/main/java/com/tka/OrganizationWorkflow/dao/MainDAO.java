package com.tka.OrganizationWorkflow.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.OrganizationWorkflow.entity.Country;
import com.tka.OrganizationWorkflow.entity.Employee;

@Repository
public class MainDAO {
	
	@Autowired
	SessionFactory factory;

	public String addCountry(Country c) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		session.save(c);		
		tx.commit();
		msg="Country Addedd Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public String updateCountry(Country c, int id) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		country.setCname(c.getCname());
		session.merge(country);		
		
		tx.commit();
		msg="Coutry Updated Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public String deleteCountry(int id) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		
		session.remove(country);		
		tx.commit();
		msg="Coutry Deleted Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public List<Country> getAllCountry() {
		Session session=null;
		Transaction tx=null;
		List<Country> list=null;
		String hqlQuery="from Country";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Country> query=session.createQuery(hqlQuery,Country.class);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}

	public Country getParticularCountryById(int id) {
		Session session=null;
		Transaction tx=null;
		
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		tx.commit();
		
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return country;
	}

	public String addEmployee(Employee emp) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		emp.setCreatedDate(new Date());
		emp.setUpdatedDate(new Date());
		session.save(emp);		
		tx.commit();
		msg="Employee Added Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public Employee login(Employee emp) {
		Session session=null;
		Transaction tx=null;
		Employee employee=null;
		String hqlQuery="from Employee where emailid=:emailid and mobileno=:mobileno";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			
			Query<Employee> query= session.createQuery(hqlQuery,Employee.class);
			query.setParameter("emailid", emp.getEmailid());
			query.setParameter("mobileno", emp.getMobileno());
			employee= query.uniqueResult();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return employee;
	}

	public List<Employee> salaryRange(double startSal, double endSal) {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuery="from Employee where salary between :startSal and :endSal";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query=session.createQuery(hqlQuery,Employee.class);
			query.setParameter("startSal", startSal);
			query.setParameter("endSal", endSal);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}

	public List<Employee> getAllEmployee() {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuery="from Employee";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query=session.createQuery(hqlQuery,Employee.class);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}
	
	public Employee getParticularEmpByID(int id) {
		Session session=null;
		Transaction tx=null;
		
		Employee employee=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		employee=session.get(Employee.class, id);
		tx.commit();
		
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return employee;
	}

	public String updateEmployee(Employee emp) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {		
		 session= factory.openSession();
		 tx=session.beginTransaction();
		 Employee employee= session.get(Employee.class, emp.getId());
		 
		 employee.setName(emp.getName());
		  employee.setMobileno(emp.getMobileno());
		  employee.setCountry(emp.getCountry());
		  employee.setCreatedBy(emp.getCreatedBy());
		  employee.setCreatedDate(emp.getCreatedDate());
		  employee.setDepartment(emp.getDepartment());
		  employee.setStatus(emp.getStatus());
		  employee.setUpdatedBy(emp.getUpdatedBy());
		  employee.setUpdatedDate(emp.getUpdatedDate());
		  
		    session.merge(employee);	
		 
		 
		 tx.commit();
		 msg="Record is updated";
		 
		}catch (Exception e1) {
			if(tx!=null) {
				tx.rollback();
			}
						
			e1.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return msg;
	}

	public String deleteEmployee(int id) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {		
		 session= factory.openSession();
		 tx=session.beginTransaction();
		 Employee employee= session.get(Employee.class, id);
		
		 session.remove(employee);
		 tx.commit();
		 
		 msg="Record is Deleted";
		}catch (Exception e1) {
			if(tx!=null) {
				tx.rollback();
			}
						
			e1.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return msg;
	}
	
	public List<Employee> getAllEmpByStatus(String status) {
		Session session=null;
		Transaction tx=null;
		String strQuery="from Employee where status=:mystatus";
		List<Employee> list=null;
		
		try {		
			 session= factory.openSession();
			 tx=session.beginTransaction();
			 
			 Query<Employee> query= session.createQuery(strQuery,Employee.class);
			 query.setParameter("mystatus", status);
			 
			  list=query.list();
			 
			 tx.commit();
		
		}catch (Exception e1) {
			if(tx!=null) {
				tx.rollback();
			}
						
			e1.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return list;
		
		
	}

		
	}
