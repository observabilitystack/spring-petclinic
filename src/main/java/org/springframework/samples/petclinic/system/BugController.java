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
	public Boolean flipMemoryLeak() {
		return bugs.flipMemoryLeak();
	}

	@ResponseBody
	@GetMapping("/bugs/cpu")
	public Boolean flipCpuLeak() {
		return bugs.flipCpuLeak();
	}

	@ResponseBody
	@GetMapping("/bugs/locking")
	public Boolean flipLocking() {
		return bugs.flipLocking();
	}

}
