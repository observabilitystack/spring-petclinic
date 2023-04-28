package org.springframework.samples.petclinic.system;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RandomLockingWebFilter extends GenericFilterBean {

	private final BugRepository bugs;

	private final Random random = new Random();

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (bugs.isLocking()) {
			Lock l = null;

			if (random.nextDouble() > 0.5d) {
				l = lock.writeLock();
			}
			else {
				l = lock.readLock();
			}

			try {
				l.lock();
				chain.doFilter(request, response);
			}
			finally {
				l.unlock();
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
