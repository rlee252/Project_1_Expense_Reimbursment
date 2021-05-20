package com.revature.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.SessionUtility;
import java.sql.Timestamp;
public class Test {

	public static void main(String[] args) {
		Session session = SessionUtility.getSessionFactory().openSession();

		//how to view 
		
//		User user1 = session.get(User.class, 5); // when we use .get() our user object is in the persistent state
//		System.out.println(user1);
		
//		Transaction tx = session.beginTransaction();
//		Reimbursement reim = new Reimbursement(0,"FOOD","PENDING",null);
//		session.save(reim);
//		reim.setUser(user); // I can actually set my reim to this particular user
//		tx.commit();
		
//		Reimbursement reim2 = session.get(Reimbursement.class, 2);
//		System.out.println(reim2);
		
//		Transaction tx = session.beginTransaction();
//		UserRoles userRole1 = new UserRoles(0,"Finance Manager");
//		session.save(userRole1);
//		tx.commit();
//		
//		UserRoles ur1 = session.get(UserRoles.class,1);
//		System.out.println(ur1);
//		
//		Transaction tx2 = session.beginTransaction();
//		User user = new User(0,null,"iLoveBagels","password","John", "Jacobs", "johnjacobs@gmail.com");
//		session.save(user);
//		user.setUserRole(userRole1);
//		tx2.commit();	
//		
//		Transaction tx7 = session.beginTransaction();
//		User user3 = new User(0,null,"newUser","password","Billy", "Mays", "bm@gmail.com");
//		session.save(user3);
//		user3.setUserRole(ur1);
//		tx7.commit();	
//		
//		User user2 = session.get(User.class, 1);
//		System.out.println(user2);
//		
//		Transaction tx3 = session.beginTransaction();
//		ReimbursementType reimType = new ReimbursementType(0,"food");
//		session.save(reimType);
//		tx3.commit();
//		
//		ReimbursementType reimType2 = session.get(ReimbursementType.class, 1);
//		System.out.println(reimType2);
//		
//		Transaction tx4 = session.beginTransaction();
//		ReimbursementStatus reimStat = new ReimbursementStatus(0,"pending");
//		session.save(reimStat);
//		tx4.commit();
//		
//		ReimbursementStatus reimStat2 = session.get(ReimbursementStatus.class, 1);
//		System.out.println(reimStat2);
//		
//		Transaction tx5 = session.beginTransaction();
//		Reimbursement reim = new Reimbursement(0, reimStat2,reimType2,user,user3, 100, "something to say", null, null);
//		//reim.setReimbResolver(user3);
//		session.save(reim);
//		tx5.commit();
//		
//		User reim2 = session.get(User.class, 1);
//		System.out.println(reim2);
		
		
	}

}
