package com.coul.common.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@WebServlet(description="Log4jInitServletServlet",urlPatterns = {},loadOnStartup=0,initParams={@WebInitParam(name="path",value="classpath:conf/front/log4j.properties")})
public class Log4jInitServlet extends HttpServlet {
    
    private static final long serialVersionUID = -7257509080871760365L;

    @Override
	public void init() throws ServletException {
        
//        ResourcePatternResolver resoler = new PathMatchingResourcePatternResolver();
//        
//        String root = this.getServletContext().getRealPath("/")+"WEB-INF/";
//        String key = "catalina.base";  
//        if (System.getProperty(key) == null) {//不在tomcat中,需定义  
//            System.setProperty(key, root);  
//        }                  
//        String propPath = getInitParameter("path");
//        if (StringUtils.isBlank(propPath)) {
//            propPath = "classpath:log4j.properties";
//        }
//        try {
//            Resource[] resources = resoler.getResources(propPath);
//            InputStream is = resources[0].getInputStream();
//            Properties props = new Properties();
//            props.load(is);
//            PropertyConfigurator.configure(props);            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
    }
}
