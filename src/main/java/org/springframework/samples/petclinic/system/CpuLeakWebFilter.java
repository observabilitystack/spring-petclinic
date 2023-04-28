package org.springframework.samples.petclinic.system;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CpuLeakWebFilter extends GenericFilterBean {

	private final BugRepository bugs;

	private final AtomicInteger threadNo = new AtomicInteger(0);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (bugs.isCpu()) {
			new Thread("cpu-reaper-" + threadNo.incrementAndGet()) {
				public void run() {
					for (int i = 0; i < 1_000_000; i++) {
						Math.tan(Math.atan(Math.tan(Math.atan(
								Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))))))));
					}
				};
			}.start();
		}

		chain.doFilter(request, response);
	}

}
