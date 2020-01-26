package com.reactive.service.processor.impl;

import com.reactive.service.processor.Service;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public class ServiceImpl implements Service {

    @Override
    public void process(JsonObject document, Handler<AsyncResult<JsonObject>> resultHandler) {
        
    }
}
