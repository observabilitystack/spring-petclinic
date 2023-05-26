package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BugRepository {

	private boolean memory = false;

	private boolean cpu = false;

	private boolean locking = false;

	public boolean flipMemoryLeak() {
		memory = !memory;
		return memory;
	}

	public boolean flipCpuLeak() {
		cpu = !cpu;
		return cpu;
	}

	public boolean flipLocking() {
		locking = !locking;
		return locking;
	}

}
