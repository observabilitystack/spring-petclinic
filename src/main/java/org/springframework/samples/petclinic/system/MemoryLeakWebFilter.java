package org.springframework.samples.petclinic.system;

import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemoryLeakWebFilter extends GenericFilterBean {

	private final BugRepository bugs;

	private final Random random = new Random();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (bugs.isMemory()) {
			getServletContext().setAttribute("bug" + random.nextInt(), new byte[256]);
		}

		chain.doFilter(request, response);
	}

}
