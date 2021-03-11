package com.finalproject.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.finalproject.exception.UserException;
import com.finalproject.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String userName, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userName, e);
		}
	}

	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID= :userID");
			q.setInteger("userID", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public User register(User u) throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			User user = new User(u.getUserName(), u.getPassword());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUserName(), e);
		}
	}
}