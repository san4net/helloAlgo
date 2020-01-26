package com.reactive.service.processor;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@ProxyGen // Generate the proxy and handle
@VertxGen //@VertxGen // Generate clients in non-java languages
public interface Service {

    // The service methods
    void process(JsonObject document, Handler<AsyncResult<JsonObject>> resultHandler);

}
