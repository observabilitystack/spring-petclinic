package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BugController {

	private final BugRepository bugs;

	@GetMapping("/bugs/memory")
	@ResponseBody
	public String flipMemoryLeak() {
		bugs.flipMemoryLeak();

		return "OK";
	}

	@ResponseBody
	@GetMapping("/bugs/cpu")
	public String flipCpuLeak() {
		bugs.flipCpuLeak();

		return "OK";
	}

	@ResponseBody
	@GetMapping("/bugs/locking")
	public String flipLocking() {
		bugs.flipLocking();

		return "OK";
	}

}
