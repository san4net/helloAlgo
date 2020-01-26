package com.reactive.service.biddingservice;

import io.reactivex.Single;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.RxHelper;
import io.vertx.reactivex.core.http.HttpServerRequest;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BestOfferServiceVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(BestOfferServiceVerticle.class);

    private WebClient webClient;


    private List<JsonObject> targets;

    private final String host="host";
    private final String port="port";
    private final String path="path";
    public static final String ClientRequestId ="Client-Request-Id";

    private  final JsonArray DEFAULT_TARGETS = new JsonArray()
            .add(new JsonObject()
                    .put(host, "localhost")
                    .put(port, 3000)
                    .put(path, "/offer"))
            .add(new JsonObject()
                    .put(host, "localhost")
                    .put(port, 3001)
                    .put(path, "/offer"))
            .add(new JsonObject()
                    .put(host, "localhost")
                    .put(port, 3002)
                    .put(path, "/offer"));


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        webClient = WebClient.create(vertx);

        targets = config().getJsonArray("targets", DEFAULT_TARGETS)
                .stream()
                .map(JsonObject.class::cast).collect(Collectors.toList());


        vertx.createHttpServer()
                .requestHandler(this::findBestOffer)
                .rxListen(9090)
                .subscribe((server, error) -> {
                        if(error!=null){
                            startFuture.fail(error);
                        }else{
                          startFuture.complete();
                        }
                });

    }

    private final AtomicLong requestIds = new AtomicLong(1);

    private static final JsonObject EMPTY_RESPONSE = new JsonObject()
            .put("empty", true)
            .put("bid", Integer.MAX_VALUE);

    private void findBestOffer(HttpServerRequest request) {
        logger.info("**request received***");
        long requestId = requestIds.getAndIncrement();

        List<Single<JsonObject>> responses = targets.stream().map(t -> {
            return webClient.get(t.getInteger(port), t.getString(host), t.getString(path))
                    .putHeader(ClientRequestId,
                            String.valueOf(requestId))
                    .as(BodyCodec.jsonObject())
                    .rxSend()
                    .retry(1)
                    .timeout(1000, TimeUnit.MILLISECONDS,
                            RxHelper.scheduler(vertx))
                    .map(HttpResponse::body)
                    .map(body -> {
                        logger.info("#{} best offer received {}", requestId, body.encodePrettily());

                        return body;
                    })
                    .onErrorReturnItem(EMPTY_RESPONSE);

        }).collect(Collectors.toList());


    Single.merge(responses)
            .reduce((acc, next)->{
                if(acc.containsKey("bid") && isHigher(acc, next)){
                    return acc;
                }else {
                    return next;
                }

            }).flatMapSingle(best->{
                if(!best.containsKey("empty")){
                    return Single.just(best);
                }
                else {
                    return Single.error(new Exception("No offer " +
                            "could be found for requestId=" + requestId));
                }
                }).subscribe( best->{
                 logger.info("#{} best offer: {}", requestId,
                    best.encodePrettily());
                  request.response()
                    .putHeader("Content-Type",
                            "application/json")
                    .end(best.encode());
            },
            error->{
                logger.error("#{} ends in error", requestId, error);
                request.response()
                        .setStatusCode(502)
                        .end();
            });

    }

    private boolean isHigher(JsonObject acc, JsonObject next) {
        return acc.getInteger("bid") > next.getInteger("bid");
    }


}
