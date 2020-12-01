package com.cinema.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.dao.*;
import com.cinema.model.*;

import com.cinema.dao.AuthoritiesDao;
import com.cinema.dao.UserDetailsDao;
import com.cinema.model.authorities;
import com.cinema.model.users;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Controller1 {

	@Autowired
	private SalonDao SalonDao;

	@Autowired
	private SeansDao SeansDao;
	
	@Autowired
	private FilmDao FilmDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private AuthoritiesDao authoritiesDao;

	////////////////////LISTELEME////////////////////////////////777
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage(ModelAndView model) {
		String message = "sinema";
		model.addObject("message", message);
		
		model.addObject("liste1", SalonDao.salonlarListesi());
		model.addObject("liste2", SeansDao.seanslarListesi());
		model.addObject("liste3", FilmDao.filmlerListesi());

		model.setViewName("liste");
		return model;
	}
////////////////////////SALONLAR///////////////////////////////////77
	@GetMapping(value = "/salonlarSil")
	public ModelAndView DeletesalonAdi(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		SalonDao.Deletesalonlar(id);
		return new ModelAndView("redirect:/");
	}
	
	@ModelAttribute
	public salonlar salon() {
		return new salonlar();
	}
	
	@GetMapping("/ekle")
	public String insertStd() {
		return "ekle";
	}
	@PostMapping(value = "/ekle")
	public String success(@ModelAttribute salonlar salonlar) {
		SalonDao.salonEkle1(salonlar);
		return ("redirect:/");
	
	}
	
	@GetMapping (value = "/guncelle/{id}")
	public String editContact (@PathVariable ("id") int salonid, Model model) 
	{
		model.addAttribute("salonlar", SalonDao.getsalonlar(salonid) );
		return "guncelle";
	}
	@PostMapping(value = "/guncelle")
	public String Updatesalonlar(@ModelAttribute salonlar salon) {
		SalonDao.Updatesalonlar(salon);		
		return ("redirect:/");
	}
	
///////////////////////SEANSLAR/////////////////////////////7	
	@GetMapping(value = "/seanslarSil")
	public ModelAndView Deleteseanslar(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		SeansDao.Deleteseanslar(id);
		return new ModelAndView("redirect:/");
	}

	@ModelAttribute
	public seanslar seans() {
		return new seanslar();
	}
	
	@GetMapping("/SeansEkle")
	public String SeansEkle() {
		return "SeansEkle";
	}
	@PostMapping(value = "/SeansEkle2")
	public String success(@ModelAttribute seanslar seanslar) {
		SeansDao.SeansEkle(seanslar);
		return ("redirect:/");
	
	}
	@GetMapping (value = "/guncelle1/{id}")
	public String editContact1 (@PathVariable ("id") int seansid, Model model) 
	{
		model.addAttribute("seanslar", SeansDao.getSeanslar(seansid) );
		return "guncelle1";
	}
	@PostMapping(value = "/guncelle1")
	public String UpdateSeans(@ModelAttribute seanslar seanslar) {
		SeansDao.UpdateSeans(seanslar);		
		return ("redirect:/");
	}
	
	//////////////////////BIRLESTIRME///////////////////////////////////////7
	
	 @GetMapping("/birlestirme")
	 public String insertStd2(Model model) {
		 model.addAttribute("filmler", new filmler());
		model.addAttribute("salonlarListesi", SalonDao.salonlarListesi());
		model.addAttribute("seanslarListesi", SeansDao.seanslarListesi());
		
		return "birlestirme";
	 }
	
	 
	 @PostMapping(value = "/birlestirme")
		public String success1(@ModelAttribute("filmler") filmler film) {
			FilmDao.AddFilm(film);
			return ("redirect:/");
		}
	 //////////////////////3.TABLO ICIN///////////////////////////77
	 @GetMapping(value = "/filmlerSil")
		public ModelAndView filmlerSil(HttpServletRequest request) {
		 	String h ="";
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				FilmDao.filmlerSil(id);
			}catch (Exception e) {
				h=e.getMessage();
				System.out.println("hata: "+ h);
			}
			
			return new ModelAndView("redirect:/");
		}
	 
	 ////////////////////RAPORLA//////////////////////////////////////////////////
		@RequestMapping(value = "/raporla", method = RequestMethod.GET)
		@ResponseBody
		public void rapor(HttpServletResponse response) throws JRException, IOException, SQLException {
			FilmDao.rapor(response);
			
		
		}
		@RequestMapping(value = "/fraporla", method = RequestMethod.POST)
		@ResponseBody
		public void filtreliRapor(HttpServletResponse response, @RequestParam int id) throws JRException, IOException, SQLException {
			FilmDao.filtreliRapor(response, id);
		}
	///////////////////LOGIN/////////////////////////////////
		@RequestMapping(value = { "/login"}, method = RequestMethod.GET)
		public ModelAndView login() {
			ModelAndView model = new ModelAndView();
			model.setViewName("login");
			return model;
		}

		
		@RequestMapping(value = "/login	", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error",required = false) String error,
		@RequestParam(value = "logout",	required = false) String logout) {
			
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid Credentials provided.");
			}

			if (logout != null) {
				model.addObject("message", "Logged out from JournalDEV successfully.");
			}

			model.setViewName("login");
			return model;
		}

		//////////////////////////////////////////////////////////////////////////////
		
		@RequestMapping(value={"/","/accessDenied"})
		public ModelAndView accessDeniedPage() {
			ModelAndView model= new ModelAndView();
			model.addObject("accessDenied");
			return model;
		}
		
		@RequestMapping(value={"/","/register"})
		public ModelAndView register() {
			ModelAndView model= new ModelAndView();
			model.addObject("register");
			return model;
		}
		
		@RequestMapping(value="/register",method=RequestMethod.POST )
		public ModelAndView rgstr(@RequestParam String username,@RequestParam String password, @RequestParam String authority) {
			  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			    
			ModelAndView model= new ModelAndView();
			
			authorities authorities=new authorities();
			users user=new users();
			
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setEnabled(true);
			
			userDetailsDao.create(user);
			
			authorities.setAuthority(authority);
			authorities.setUsername(username);
			authoritiesDao.create(authorities);
			model.addObject("register");
			 return model;
		}
		////////////////////////////////////////////////////////////////////////////////
		
//		@RequestMapping(value = { "/giris"}, method = RequestMethod.GET)
//		public ModelAndView giris() {
//			ModelAndView model = new ModelAndView();
//			model.setViewName("giris");
//			return model;
//		}
}
