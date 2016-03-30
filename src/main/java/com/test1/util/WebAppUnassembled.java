package com.test1.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.test1.config.WebConfig;

public class WebAppUnassembled {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8090);
    WebAppContext context = new WebAppContext();
    context.setContextPath("/test1");
    String path = System.getProperty("user.dir");
    String baseName = path.substring(path.lastIndexOf("\\")+1);
    context.setResourceBase("../"+baseName+"/src/main/webapp");
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(WebConfig.class);
    DispatcherServlet ds = new DispatcherServlet(ctx);
    context.addServlet(new ServletHolder(ds), "/");
    
    server.setHandler(context);
    
    server.start();
    server.join();
  }

}

