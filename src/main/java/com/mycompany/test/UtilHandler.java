package com.mycompany.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created on 22/Jul/2017.
 */
@Path("/util")
public class UtilHandler {

    @GET
    @Path("/clock/date/next/{day}/{month}/{year}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNextDate(@PathParam("day") String day,
                                @PathParam("month") String month,
                                @PathParam("year") String year){

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
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(nextDate, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/uuid/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUUID(){

        Uuid uuid = new Uuid(UUID.randomUUID().toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return Response.ok(gson.toJson(uuid), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/say/hello")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(final Name someName){

        SentenceConstructor senCon = new SentenceConstructor();

        Optional<String> res = senCon.sayHello(String.format("%s %s", someName.getFirstName(), someName.getLastName()));
        if (!res.isPresent()){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok(res.get(), MediaType.TEXT_PLAIN).build();
    }
}
