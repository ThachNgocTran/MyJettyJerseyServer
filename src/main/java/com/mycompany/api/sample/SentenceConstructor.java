package com.mycompany.api.sample;

import jersey.repackaged.com.google.common.cache.CacheBuilder;
import jersey.repackaged.com.google.common.cache.CacheLoader;
import jersey.repackaged.com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.util.Args;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class SentenceConstructor {

    private static final String CLASS_NAME = SentenceConstructor.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(CLASS_NAME);

    /*
    Caching Feature:
     */
    private static final LoadingCache<String, Optional<String>> helloCache;

    static {
        helloCache = CacheBuilder.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build(
                        new CacheLoader<String, Optional<String>>() {
                            @Override
                            public Optional<String> load(String fullName) throws Exception {

                                LOGGER.info(String.format("Hello sentence for [%s] not available in cache. Call constructHelloSentence()",
                                        fullName));

                                return constructHelloSentence(fullName);
                            }
                        }
                );
    }

    public static void init(){
        // When this static function gets called, it forces the static block of this class to be called first.
        // Thus, the caching starts!
    }

    public SentenceConstructor(){

    }

    public Optional<String> sayHello(String fullName){

        Args.notEmpty(fullName, "fullName");
        Optional<String> res = Optional.empty();

        try{
            res = helloCache.get(fullName);
            if (!res.isPresent()){
                helloCache.invalidate(fullName);    // call "constructHelloSentence" next time, instead of relying on cache.
            }
        }
        catch (Throwable th){
            LOGGER.error(ExceptionUtils.getStackTrace(th));
        }

        return res;
    }

    /*
    Supposed that this function takes much time to finish and exception may arise.
     */
    private static Optional<String> constructHelloSentence(String fullName){

        Optional<String> res = Optional.empty();

        try{
            res = Optional.of(String.format("Hello [%s]", fullName));
        }
        catch (Throwable th){
            LOGGER.error(ExceptionUtils.getStackTrace(th));
            res = Optional.empty();
        }

        return res;
    }
}
