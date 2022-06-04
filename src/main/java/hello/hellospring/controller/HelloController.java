package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("hello") // get 메소드, url은 /hello
	public String hello(Model model){
		model.addAttribute("data", "spring");
		return "hello"; // 템플릿에서 hello.html을 찾아서 연결
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model){
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody //응답으로 return 값을 그대로 보여주겠단 의미
	public String helloMvc(@RequestParam("name") String name){
		return "hello" + name;
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name){
		Hello hello = new Hello(); // responeBody로 객체가 오면 json으로 넘김
		hello.setName(name);
		return hello;
	}
	
	static class Hello{
		private String name;
		
		
		public String getName(){
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
}
