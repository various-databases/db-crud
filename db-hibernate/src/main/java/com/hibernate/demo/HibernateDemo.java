package com.hibernate.demo;

import org.hibernate.Session;

import com.hibernate.common.HibernateUtil;
import com.hibernate.pojo.User;

public class HibernateDemo {

	public static void main(String[] args) {
		HibernateDemo demo = new HibernateDemo();
		User user1 = new User("Tom", "Heln", 54);
		User user2 = new User("Han", "Jimbo", 25);
		User user3 = new User("Ale", "Thoms", 56);
		demo.insert(user1);
		demo.insert(user2);
		demo.insert(user3);
		demo.select(2);
		demo.delete(2);
		demo.update(1, 124);
	}

	public void select(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			User user = (User) session.load(User.class, id);
			System.out.println(user.getFirst() + " " + user.getLast() + "  " + user.getAge());
		} finally {
			session.close();
		}
	}

	public int insert(User user) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			int result = (Integer) session.save(user);
			session.getTransaction().commit();
			return result;
		} finally {
			session.close();
		}
	}

	public void delete(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			User user = new User();
			user.setId(id);
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	//先load 再update
	//这里仅修改age作为示例
	public void update(int id, int age) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			User user = (User) session.load(User.class, id);
			user.setAge(age);
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

}
