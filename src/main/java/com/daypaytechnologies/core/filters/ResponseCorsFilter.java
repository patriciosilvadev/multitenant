package com.daypaytechnologies.core.filters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Filter that returns a response with headers that allows for Cross-Origin
 * Requests (CORs) to be performed against the platform API.
 */
public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(final ContainerRequest request, final ContainerResponse response) {

        final ResponseBuilder resp = Response.fromResponse(response.getResponse());

        resp.header("Access-Control-Allow-Origin", "*")
        // .header("Access-Control-Expose-Headers", "Fineract-Platform-TenantId")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        final String reqHead = request.getHeaderValue("Access-Control-Request-Headers");

        if (null != reqHead && !reqHead.equals(null)) {
            resp.header("Access-Control-Allow-Headers", reqHead);
        }

        response.setResponse(resp.build());

        return response;
    }
}