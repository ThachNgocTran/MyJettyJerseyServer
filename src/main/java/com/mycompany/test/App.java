package com.mycompany.test;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/*
Reference:
https://gist.github.com/seralf/f9c268dc7046ec436e29
https://github.com/erankeren/jetty-jersey
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            URI baseUri = UriBuilder
                    .fromUri("http://localhost/")
                    .port(8080)
                    .build();

            ResourceConfig resConf = new MyResourceConfig();
            //resConf.register(UtilHandler.class);
            resConf.register(MyObjectMapper.class);

            Server jetty = JettyHttpContainerFactory.createServer(baseUri, resConf);

            jetty.start();
            jetty.join();
        }
        catch (Throwable th){
            System.out.println(ExceptionUtils.getStackTrace(th));
        }
    }
}
