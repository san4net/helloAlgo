package com.reactive.service.biddingservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MainBiddingApplication extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
         vertx.deployVerticle(new BiddingServiceVerticle());
         vertx.deployVerticle(new BiddingServiceVerticle(), new DeploymentOptions().setConfig(new JsonObject().put("port",3001)));
         vertx.deployVerticle(new BiddingServiceVerticle(), new DeploymentOptions().setConfig(new JsonObject().put("port",3002)));

         vertx.deployVerticle(new BestOfferServiceVerticle(), new DeploymentOptions().setInstances(1));

         startFuture.complete();
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainBiddingApplication(), res->{
            if(res.succeeded()){
                System.out.printf("sucessfull\n");
            }else {
                System.out.println("failed"+ res.cause());
            }
        });
    }
}
