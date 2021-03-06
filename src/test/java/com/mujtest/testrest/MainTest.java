/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
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

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    private static Server server;

    @BeforeAll
    public static void startTheServer() throws Exception {
        server = Main.startServer();
    }

    @Test
    void testHelloWorld() {

        Client client = ClientBuilder.newClient();
        JsonObject jsonObject = client
                .target(getConnectionString("/"))
                .request()
                .get(JsonObject.class);
        Assertions.assertEquals("[]", jsonObject.getString("result"),
                "data without sending any json");

        jsonObject = client
                .target(getConnectionString("/progress"))
                .request()
                .get(JsonObject.class);
        Assertions.assertEquals("0%", jsonObject.getString("progress"),
                "progress without data");

        Response r = client
                .target(getConnectionString("/"))
                .request()
                .put(Entity.entity("[1,2,3]", MediaType.APPLICATION_JSON));
        Assertions.assertEquals(200, r.getStatus(), "GET metrics status code");

        r = client
                .target(getConnectionString("/put"))
                .request()
                .put(Entity.entity("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]", MediaType.APPLICATION_JSON));
        Assertions.assertEquals(400, r.getStatus(), "GET metrics status code for to much input in JSON");

    }

    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }

    private String getConnectionString(String path) {
        return "http://localhost:" + server.port() + path;
    }

}
