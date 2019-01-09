package br.com.backend.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import br.com.backend.rest.services.EntryPoint;

public class App {
	public static int port = 8080;

	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}

		System.out.println("\nStating Server on port [" + port + "]\n");

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		Server jettyServer = new Server(port);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", EntryPoint.class.getCanonicalName());

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			// jettyServer.destroy();
		}
	}
}