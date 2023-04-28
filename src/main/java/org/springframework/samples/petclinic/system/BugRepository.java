package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BugRepository {

    private boolean memory = false;
    private boolean cpu = false;
    private boolean locking = false;

    public void flipMemoryLeak() {
        memory = !memory;
    }

    public void flipCpuLeak() {
        cpu = !cpu;
    }

    public void flipLocking() {
        locking = !locking;
    }

}
