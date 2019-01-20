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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Provider for greeting message.
 */
@ApplicationScoped
public class Provider {
    private final Permutation permutation = new Permutation();
    
    @Inject
    public Provider() {
        this.permutation.createData();
    }

    String getMessage() {
        if (this.permutation.getNumberOfElementsJson()<20) {
            return this.permutation.getValue();
        } else {
            return "To much values in JSON source";
        }
    }

    String getProgress() {
        return this.permutation.getProgress();
    }

    void setMessage() {
        this.permutation.setValue();
        this.permutation.createData();
    }

    void setMessage(String json) {
        this.permutation.setValue(json);
        this.permutation.createData();
    }
    
    int getNumberOfElements() {
        return permutation.getNumberOfElementsJson();
    }

    int getMaxElements() {
        return permutation.getMaxElements();
    }
    
}
