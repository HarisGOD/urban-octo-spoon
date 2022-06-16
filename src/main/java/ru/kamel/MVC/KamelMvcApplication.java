package ru.kamel.MVC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class KamelMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(KamelMvcApplication.class, args);
	}

	@Value("${error.message}")
	private String errormessage;
	private String message= "ab";
	
	class man{
		public static int COUNT=0;
		private int age = 0;
		private String name = "";
		private int id;
		public int getAge() {return age;}
		public String getName() {return name;}
		public int getId() {return id;}
		public int getId(int a) {return id-a;}
		
		public void setAge(int age) {this.age = age;}
		public void setName(String name) {this.name = name;}
		public void setId(int id) {this.id = id;}
		
		public man(int age,String name, int id) {
			this.setAge(age);
			this.setName(name);
			this.setId(id);
			
		}
		public static String showInfo(int id) {
			return (mans.get(id).getName()+", age "+mans.get(id).getAge());
				
			}
		public static List<man> mane(){
		return mans;
	}
		
	}
	private static List<man> mans;{
		mans = new ArrayList<>();
		mans.add(new man(16,"abba babasov", man.COUNT++));
		mans.add(new man(16,"memel kamol", man.COUNT++));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//—“–¿Õ»÷€
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}
	@RequestMapping(value = {"/sosyns" }, method = RequestMethod.GET)
		public String sosyns(Model model) {
				
			model.addAttribute("msg",errormessage);
			return "sosyns" ;
				
			}
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcome(Model model) {

		model.addAttribute("message", message);

		return "welcome-page";
	}
	@RequestMapping(value = { "/start" }, method = RequestMethod.GET)
	public String start(@RequestParam(value = "name", required = false) String name,
						@RequestParam(value = "surname", required = false) String surname ,
						@RequestParam(value = "id", required = false) Integer id,Model model) {
		try {
			model.addAttribute("list", man.showInfo(id));	}
			catch(Exception e){
			model.addAttribute("list", "no any id");
			}
		return "start";
	}
	@RequestMapping(value = { "/list"}, method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("manlist", mans );
		
		return "list";
	}
	@PostMapping("/count")
	public String count(@RequestParam("first") String a, 
						@RequestParam("second") String b, Model model) { 
		int aa;
		int bb;
		try {
			aa = Integer.parseInt(a);
		}catch(Exception e) {
			model.addAttribute("sum", "invalid input");
			return "count";
		}
		try {
			bb = Integer.parseInt(b);
		}catch(Exception e) {
			model.addAttribute("sum", "invalid input");
			return "count";
		}
		model.addAttribute("sum", (aa+bb));
		
		return "count";
		
	}
	@PostMapping("/register")
	public String register(@RequestParam("first") String a, 
							@RequestParam("second") String b, Model model) { 
		int aa;
		try {
			aa = Integer.parseInt(a);
		}catch(Exception e) {
			model.addAttribute("ermsg", "last added age is invalid");
			return "list";
		}
		mans.add(new man(aa,b,man.COUNT++ ));
		model.addAttribute("manlist", mans );
		
		
		return "list";
		
	}
	
	
}
