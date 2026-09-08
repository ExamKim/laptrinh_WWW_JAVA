package vn.edu.iuh.cartapi.api;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("vn.edu.iuh.cartapi.api");
    }
}