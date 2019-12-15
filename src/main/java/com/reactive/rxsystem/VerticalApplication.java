package com.reactive.rxsystem;

import io.vertx.core.Vertx;

public class VerticalApplication {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle( new HttpVertical());
    }
}
