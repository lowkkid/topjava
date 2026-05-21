package ru.javawebinar.topjava.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServletApplicationContext implements ServletContextListener  {

    private ConfigurableApplicationContext appCtx;

    @Override
    public void contextInitialized(ServletContextEvent event){
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        event.getServletContext().setAttribute("springContext", appCtx);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){
        appCtx.close();
    }

    public <T> T getBean(Class<T> clazz){
        return appCtx.getBean(clazz);
    }
}