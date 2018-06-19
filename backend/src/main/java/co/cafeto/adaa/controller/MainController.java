package co.cafeto.adaa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.cafeto.adaa.business.ProcessModifier;
import co.cafeto.bp3.model.impl.Bp3process;

@RestController
public class MainController {
	
	@ResponseBody
	@PostMapping("/reduce")
	public Bp3process reduce(@RequestBody Bp3process process) throws Exception {
		ProcessModifier modifier = new ProcessModifier(process);
		return modifier.eraseNodesByTypeConfig();		
	}
}
