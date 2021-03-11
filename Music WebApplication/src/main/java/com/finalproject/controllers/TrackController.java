package com.finalproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.util.ValidationEventCollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finalproject.dao.TrackDAO;
import com.finalproject.pojo.Track;
import com.finalproject.pojo.Uploader;
import com.finalproject.validators.TrackValidator;

@Controller
@RequestMapping(value = "/track/*")
public class TrackController {
	
	@Autowired
	@Qualifier("trackValidator")
	TrackValidator trackValidator;
	
	@Autowired
	@Qualifier("trackDao")
	TrackDAO trackDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(trackValidator);
	}
	
	@RequestMapping(value = "/track/uploadNewTrack", method = RequestMethod.GET)
	public String uploadNewTrack (Track track){
		return "upload-new-track";
	}
	
	@RequestMapping(value = "/track/uploadNewTrack", method = RequestMethod.POST)
	public ModelAndView uploadNewTrack (HttpServletRequest request, @ModelAttribute("track") Track track, BindingResult result) throws Exception{
		trackValidator.validate(track, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("upload-new-track", "track", track);
		}
		
		try {
			Uploader uploader = (Uploader) request.getSession().getAttribute("uploader");
			Track t = trackDAO.upload(track, uploader);
			String uploaded = (String) request.getAttribute("uploaded");
			request.setAttribute("uploaded", "Uploaded successfully");
			return new ModelAndView("upload-new-track", "uploadedTrack", t);
		} catch (Exception e) {
			// TODO: handle exception
			return new ModelAndView("upload-new-track", "error", "Looks like this song is already uploaded. Login as a user to view the song.");
		} 
	}
}
