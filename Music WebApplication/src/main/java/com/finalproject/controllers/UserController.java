package com.finalproject.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.finalproject.dao.DAO;
import com.finalproject.dao.TrackDAO;
import com.finalproject.dao.UploaderDAO;
import com.finalproject.dao.UserDAO;
import com.finalproject.exception.UploaderException;
import com.finalproject.exception.UserException;
import com.finalproject.pojo.Track;
import com.finalproject.pojo.Uploader;
import com.finalproject.pojo.User;
import com.finalproject.validators.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("uploaderDao")
	UploaderDAO uploaderDao;
	
	@Autowired
	@Qualifier("trackDao")
	TrackDAO trackDAO;
	
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String loginUser (HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) return "user-home";
		else if (session.getAttribute("uploader") != null){
			Uploader uploader = (Uploader) session.getAttribute("uploader");
			List<Track> tracksList = trackDAO.getUploadedTracks(uploader.getEmail());
			System.out.println("Size" + tracksList.size());
			session.setAttribute("tracksList", tracksList);
			return "uploader-home";
		}
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println("username: " + userName + "password: " + password);
		
		try {
			User user = userDAO.get(userName, password);
			Uploader uploader = uploaderDao.get(userName, password);
			if (user == null && uploader == null) {
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			if (uploader == null) {
				session.setAttribute("user", user);
				List<Track> tracksList = trackDAO.getTracks();
				session.setAttribute("tracksList", tracksList);
				return "user-home";
			} 
			
			else {
				session.setAttribute("uploader", uploader);
				List<Track> tracksList = trackDAO.getUploadedTracks(uploader.getEmail());
				System.out.println("Size" + tracksList.size());
				session.setAttribute("tracksList", tracksList);
				return "uploader-home";
			}				
			
		} catch (UserException e) {
			// TODO: handle exception
			session.setAttribute("errorMessage", "Login Error. Please try again")	;
			return "error";
		} catch (UploaderException e) {
			session.setAttribute("errorMessage", "Login Error. Please try again");
			return "error";
		}
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String loginUser(){
		return "home";
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register", "user", new User());
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		userValidator.validate(user, result);
		String userType = request.getParameter("userType");
		
		if (userType.equals("User")){			
			User u;	
			
			if (result.hasErrors()) {
				return new ModelAndView("register", "user", user);
			}
			
			try {
				System.out.print("registerNewUser");

				u = userDAO.register(user);
				
				request.getSession().setAttribute("user", u);

			} catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "Looks like you are already in our database. Please try logging in with your username and password");
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			List<Track> tracksList = trackDAO.getTracks();
			session.setAttribute("tracksList", tracksList);
			return new ModelAndView("user-home", "user", u);			
		}
		
		else {
			Uploader u;
			
			if (result.hasErrors()) {
				return new ModelAndView("register", "uploader", user);
			}

			try {

				System.out.print("registerNewUploader");

				u = uploaderDao.register(user);
				
				request.getSession().setAttribute("uploader", u);

			} catch (UploaderException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "Looks like you are already in our database. Please try logging in with your username and password");
			}
			
			return new ModelAndView("uploader-home", "uploader", u);
		}		
	}
	
	@RequestMapping (value = "/user/logout", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO.close();
		
		HttpSession session = request.getSession();
		session.invalidate();
		//response.sendRedirect("/WEB-INF/views/home.jsp");
		
		return "home";
	}
	
	@RequestMapping(value = "/user/trackslist", method = RequestMethod.POST)
	public String userTracksList(HttpServletRequest request) throws Exception{
		/*System.out.println("hahahahahahahahahahaha");
		String json = request.getParameter("myJSONFile");
		System.out.println(json);*/
		
		/*String data = getJSONData();
		try {
			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> map = new HashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(data, new TypeReference<Map<String, String>>(){});
			
			System.out.println(map.get("track"));
			
			JSONObject obj2 = (JSONObject) jsonArray.get(1);
			System.out.println(obj2.get("1"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/	
		
		
		return "user-home";
	}
	
	@RequestMapping(value = "/user/searchTracks", method = RequestMethod.POST)
	public String searchTracks(HttpServletRequest request) throws Exception{
		String searchField = request.getParameter("searchField");
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		List<Track> tracksList = trackDAO.getTracks(searchField);
		session.setAttribute("tracksList", tracksList);
		
		return "user-home";
		
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String userHome(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();

		List<Track> tracksList = trackDAO.getTracks();
		session.setAttribute("tracksList", tracksList);
		return "user-home";
	}
	
	public String getJSONData() throws Exception{
		String url = "http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=disco&api_key=82d41887eded3508556137889b65f14e&format=json";
		URL obj = new URL(url);
		HttpURLConnection connection= (HttpURLConnection) obj.openConnection();
		
		connection.setRequestMethod("GET");
		
		int responseCode = connection.getResponseCode();
		System.out.println("responsecode: " + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine())!= null) {
						response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
