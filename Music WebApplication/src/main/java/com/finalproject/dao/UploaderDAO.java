package com.finalproject.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.finalproject.exception.UploaderException;
import com.finalproject.pojo.Uploader;
import com.finalproject.pojo.User;

public class UploaderDAO extends DAO {

	public UploaderDAO() {
	}

	public Uploader get(String userName, String password) throws UploaderException {
		try {
			begin();
			Query q = getSession().createQuery("from Uploader where userName = :userName and password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			Uploader uploader = (Uploader) q.uniqueResult();
			commit();
			return uploader;
		} catch (HibernateException e) {
			rollback();
			throw new UploaderException("Could not get user " + userName, e);
		}
	}

	public Uploader get(int uploaderID) throws UploaderException {
		try {
			begin();
			Query q = getSession().createQuery("from Uploader where uploaderID= :uploaderID");
			q.setInteger("uploaderID", uploaderID);
			Uploader uploader = (Uploader) q.uniqueResult();
			commit();
			return uploader;
		} catch (HibernateException e) {
			rollback();
			throw new UploaderException("Could not get uploader " + uploaderID, e);
		}
	}

	public Uploader register(User u) throws UploaderException {
		try {
			begin();
			System.out.println("inside DAO");

			Uploader uploader = new Uploader(u.getUserName(), u.getPassword());

			uploader.setFirstName(u.getFirstName());
			uploader.setLastName(u.getLastName());
			uploader.setEmail(u.getEmail());
			getSession().save(uploader);
			commit();
			return uploader;

		} catch (HibernateException e) {
			rollback();
			throw new UploaderException("Exception while creating uploader: " + e.getMessage());
		}
	}

	public void delete(Uploader uploader) throws UploaderException {
		try {
			begin();
			getSession().delete(uploader);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UploaderException("Could not delete uploader " + uploader.getUserName(), e);
		}
	}
}