package com.reactive.service.biddingservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class BiddingServiceVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory
            .getLogger(BiddingServiceVerticle.class);

    /**
     * 1. accept /offer
     * 2. with certain about of error
     *
     * @param startFuture
     * @throws Exception
     */
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Random random = new Random();
        String myId = UUID
                .randomUUID().toString();
        Router router = Router.router(vertx);

        router.get("/offer")
                .handler(routingContext -> {
                    String clientRequestId = routingContext.request().getHeader(BestOfferServiceVerticle.ClientRequestId);

                    logger.info("bidding service at port {} got offer request and clientRequestid {}", config().getInteger("port"), clientRequestId);

                    int myBid = 10 + random.nextInt();

                    JsonObject payload = new JsonObject()
                            .put("id", myId)
                            .put("bid", myBid);

                    if (clientRequestId != null) {
                        payload.put("clientRequestId", clientRequestId);
                    }

                    if(random.nextInt(1000)%2==0){
                        routingContext.response().setStatusCode(500).end();
                        logger.error("{} injects an error (client-id={}",
                                myId, myBid, clientRequestId);
                    }else {
                        routingContext.response().putHeader("Content-Type", "application/json")
                                .end(payload.encode());
                        logger.info("{} offers {} (client-id={}", myId, myBid, clientRequestId);

                    }



                    // adding random delay and
                    /*vertx.setPeriodic(random.nextInt(1000),
                            id -> {
                                if (id % 2 == 0) {
                                    routingContext.response().setStatusCode(500).end();

                                } else {
                                    routingContext.response().putHeader("Content-Type", "application/json")
                                            .end(payload.encode());
                                }
                            });*/


                });

        //now start server
        int portNumber = config().getInteger("port", 3000);

        vertx.createHttpServer().requestHandler(router::accept)
                .listen(portNumber, ar -> {
                    if (ar.succeeded()) {
                        logger.info("started server on port {}", portNumber);
                        startFuture.complete();
                    } else {
                        logger.error("Bidding service failed to start",
                                ar.cause());
                        startFuture.fail(ar.cause());
                    }
                });


//        super.start(startFuture);
    }
}
