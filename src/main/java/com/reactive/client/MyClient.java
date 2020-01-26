package com.reactive.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;


import io.vertx.reactivex.core.http.HttpClientRequest;
//import javax.ws.rs.container.AsyncResponse;

import rx.Single;

public class WebClient extends AbstractVerticle {

    final String host="https://api.github.com";
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        client();
//        HttpClient client = vertx.createHttpClient();
//        var requestStream = Rx.Observable.just('https://api.github.com/users');
//        https://github.com/dream11
//        HttpClientRequest req = client.request(HttpMethod.GET,  host, "/users");

//        Single.just(req).flatMap(res->{
//            client.
//        })

       /* req.toFlowable().

                // Status code check and -> Flowable<Buffer>
                        flatMap(resp -> {
                    if (resp.statusCode() != 200) {
                        throw new RuntimeException("Wrong status code " + resp.statusCode());
                    }
                    System.out.println("what is ");
                    return resp.toFlowable();
                }).
                subscribe(data -> System.out.println("Server content " + data.toString("UTF-8")));

        // End request
        req.end()z*/
        super.start(startFuture);
    }

    private void client() {
        HttpClientOptions options = new HttpClientOptions();
//        options.setSsl(true);
        options.setTrustAll(true);
        options.setVerifyHost(false);


        io.vertx.reactivex.core.http.HttpClient  client = vertx.createHttpClient(options);

        client.getNow(
                host,
                "/users",
                resp -> {
                    System.err.println("Got response");
                    resp.bodyHandler(body -> {
                        System.err.println("Got body");

//                        asyncResponse.resume(Response.ok(body.toString()).build());
                    });
                });
        System.err.println("Created client");
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new WebClient());
    }
}
