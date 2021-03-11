package com.finalproject.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.TransactionException;

import com.finalproject.exception.TrackException;
import com.finalproject.pojo.Track;
import com.finalproject.pojo.Uploader;

public class TrackDAO extends DAO{

	public TrackDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Track upload(Track track, Uploader uploader) {
		try {
			begin();
			Track t = new Track(track.getTitle(), track.getArtist(), track.getURL(), uploader);
			//t.setUploader(track.getUploader());
			getSession().save(t);
			commit();
			return t;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new TransactionException("Exception while uploading" + e);
		}
	}
	
	public List<Track> getUploadedTracks(String email) throws Exception{
		try {
			begin();
			Query query = getSession().createQuery("from Track where uploader = :uploader_Email");
			query.setString("uploader_Email", email);
			List<Track> list = query.list();
			commit();
			return list;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new TrackException("Could not list the categories", e);
		}
	}
	
	public List<Track> getTracks() throws Exception{
		try {
			begin();
			Query query = getSession().createQuery("from Track");
			List list = query.list();
			commit();
			return list;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			e.printStackTrace();
			throw new TrackException("Could not list the songs", e);
		}
	}
	
	public List<Track> getTracks(String title) throws Exception{
		try {
			begin();
			Query query = getSession().createQuery("from Track where title like :title");
			query.setString("title", "%" + title + "%");
			List list = query.list();
			commit();
			return list;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			e.printStackTrace();
			throw new TrackException("Could not list the songs", e);
		}
	}
}
