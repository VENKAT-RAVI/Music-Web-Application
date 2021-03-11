package com.finalproject.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.finalproject.dao.UploaderDAO;
import com.finalproject.pojo.Track;
import com.finalproject.pojo.Uploader;
import com.finalproject.pojo.User;


public class TrackValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Track.class);
	}

	public void validate(Object obj, Errors errors) {
		Track track = (Track) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artist", "error.invalid.track", "Artist Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.track", "Title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "URL", "error.invalid.track", "URL Required");
	}
}
