package com.mycompany.server;

import com.mycompany.api.sample.SentenceConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final String CLASS_NAME = App.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(CLASS_NAME);

    static {
        // Force initialization early for the cache to be created.
        SentenceConstructor.init();
    }

    public static void main( String[] args )
    {
        try{
            URI baseUri = UriBuilder
                    .fromUri("http://localhost/")
                    .port(8080)
                    .build();

            ResourceConfig resConf = new MyResourceConfig();
            //resConf.register(RequestHandler.class);
            resConf.register(MyObjectMapper.class);

            /*
            The APIs may return some results that will be accessed by the web browser's javascript,
                which is disabled by default IF both of them are from different domains (even example.com:8080
                and example.com:8081).
            Solution: The API Server must explicitly allow access for different domains by denoting the CORS in
                the response header.
             */
            resConf.register(CORSResponseFilter.class);

            Server jetty = JettyHttpContainerFactory.createServer(baseUri, resConf);

            jetty.start();
            jetty.join();
        }
        catch (Throwable th){
            LOGGER.error(String.format("Server crashes! [%s]",
                    ExceptionUtils.getStackTrace(th)));
        }
    }
}
