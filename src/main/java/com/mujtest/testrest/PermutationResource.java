/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mujtest.testrest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get JSON:
 * curl -X GET http://localhost:8080/
 *
 * Get progress in permutation count
 * curl -X GET http://localhost:8080/progress
 *
 * Put default JSON to application
 * curl -X GET http://localhost:8080/put
 *
 * Put JSON to the application
 * curl -X PUT -H "Content-Type: application/json" -d "[1,2,3,4]" http://localhost:8080/put
 *
 * The message is returned as a JSON object.
 */
@Path("/")
@RequestScoped
public class PermutationResource {
    private final Provider provider;

    @Inject
    public PermutationResource(Provider config) {
        this.provider = config;
    }

    @SuppressWarnings("checkstyle:designforextension")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDefaultMessage() {
        return createResponse();
    }

    @SuppressWarnings("checkstyle:designforextension")
    @Path("/progress")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProgress() {
        return Json.createObjectBuilder().add("progress", provider.getProgress()).build();
    }

    @SuppressWarnings("checkstyle:designforextension")
    @PUT
    public Response setPermutation() {
        provider.setMessage();
        return Response.status(200).build();
    }

    @SuppressWarnings("checkstyle:designforextension")
    @Path("/put")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePermutation(InputStream incomingData) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
                String line = null;
                while ((line = in.readLine()) != null) { stringBuilder.append(line); }
        } catch (Exception e) {
                System.out.println("error: " + e);
        }
        provider.setMessage(stringBuilder.toString());
        if (provider.getNumberOfElements()>provider.getMaxElements()) { return Response.status(400).build(); }
        return Response.status(200).build();
    }

    private JsonObject createResponse() {
        String message = provider.getMessage();
        String messageType = "result";
        if ((message.length()>=22) && ("To much values in JSON".equals(message.substring(0, 10)))) { messageType = "error"; }
        
        return Json.createObjectBuilder().add(messageType, message).build();
    }
}
