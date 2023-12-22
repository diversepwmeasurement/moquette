/*
 * Copyright (c) 2012-2023 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package io.moquette.broker.subscriptions;

import io.netty.handler.codec.mqtt.MqttQoS;

import java.util.Objects;

/**
 * Shared subscription data class.
 * */
class SharedSubscription implements Comparable<SharedSubscription> {
    private final ShareName shareName;
    private final Topic topicFilter;
    private final String clientId;
    private final MqttQoS requestedQoS;

    public SharedSubscription(ShareName shareName, Topic topicFilter, String clientId, MqttQoS requestedQoS) {
        Objects.requireNonNull(requestedQoS, "qos parameter can't be null");
        this.shareName = shareName;
        this.topicFilter = topicFilter;
        this.clientId = clientId;
        this.requestedQoS = requestedQoS;
    }

    public String clientId() {
        return clientId;
    }

    public Topic topicFilter() {
        return topicFilter;
    }

    public MqttQoS requestedQoS() {
        return requestedQoS;
    }

    public ShareName getShareName() {
        return shareName;
    }

    @Override
    public int compareTo(SharedSubscription o) {
        return this.clientId.compareTo(o.clientId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedSubscription that = (SharedSubscription) o;
        return Objects.equals(shareName, that.shareName) &&
            Objects.equals(topicFilter, that.topicFilter) &&
            Objects.equals(clientId, that.clientId) &&
            Objects.equals(requestedQoS, that.requestedQoS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shareName, topicFilter, clientId, requestedQoS);
    }
}