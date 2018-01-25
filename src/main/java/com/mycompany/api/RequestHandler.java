package com.mycompany.api;

import com.mycompany.api.sample.MyUuid;
import com.mycompany.api.sample.Name;
import com.mycompany.api.sample.SentenceConstructor;
import com.mycompany.util.JsonConverter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Path("/api")
public class RequestHandler {

    private static final String CLASS_NAME = RequestHandler.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(CLASS_NAME);

    @GET
    @Path("/clock/date/next/{day}/{month}/{year}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNextDate(@PathParam("day") String day,
                                @PathParam("month") String month,
                                @PathParam("year") String year){

        LOGGER.info("API getNextDate() is being called.");

        String nextDate;

        try{

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = df.parse(String.format("%s/%s/%s", day, month, year));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, 1);

            nextDate = df.format(cal.getTime());
        }
        catch (Throwable th){
            // Only serious error!
            LOGGER.error(String.format("Error occurred when calling api getNextDate(): [%s]",
                    ExceptionUtils.getStackTrace(th)));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok(nextDate, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/uuid/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUUID(){

        LOGGER.info("API getUUID() is being called.");

        /*
        https://stackoverflow.com/questions/7212635/is-java-util-uuid-thread-safe
        It's thread-safe!
         */
        MyUuid myUuid = new MyUuid(UUID.randomUUID().toString());

        return Response.ok(JsonConverter.toJson(myUuid), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/say/hello")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(final Name someName){  // Note: The JSON String is cast into this Class!

        LOGGER.info("API sayHello() is being called.");

        SentenceConstructor senCon = new SentenceConstructor();

        Optional<String> res = senCon.sayHello(String.format("%s %s",
                someName.getFirstName(), someName.getLastName()));
        if (!res.isPresent()){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok(res.get(), MediaType.TEXT_PLAIN).build();
    }
}
