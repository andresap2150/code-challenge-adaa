package co.cafeto.adaa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.cafeto.bp3.model.Impl.Bp3process;

@RestController
public class MainController {
	
	@ResponseBody
	@PostMapping("/reduce")
	public Bp3process reduce(@RequestBody Bp3process process) {
		return process;		
	}
}
