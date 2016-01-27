/**
 * Copyright 2012 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corundumstudio.socketio;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.handler.codec.http.HttpHeaders;

public class HandshakeData implements Serializable {

    private static final long serialVersionUID = 1196350300161819978L;

    private final HttpHeaders headers;
    private final InetSocketAddress address;
    private final Date time = new Date();
    private final String url;
    private final Map<String, List<String>> urlParams;
    private final boolean xdomain;

    public HandshakeData(HttpHeaders headers, Map<String, List<String>> urlParams, InetSocketAddress address, String url, boolean xdomain) {
        super();
        this.headers = headers;
        this.urlParams = urlParams;
        this.address = address;
        this.url = url;
        this.xdomain = xdomain;
    }

    /**
     * Client network address
     *
     * @return
     */
    public InetSocketAddress getAddress() {
        return address;
    }

    /**
     * Http headers sent during first client request
     *
     * @return
     */
    public HttpHeaders getHttpHeaders() {
        return headers;
    }

    /**
     * Use {@link #getHttpHeaders()}
     */
    @Deprecated
    public Map<String, List<String>> getHeaders() {
        Map<String, List<String>> result = new HashMap<String, List<String>>(headers.names().size());
        for (String name : headers.names()) {
            List<String> values = headers.getAll(name);
            result.put(name, values);
        }
        return result;
    }

    /**
     * Use {@link #getHttpHeaders().get()}
     */
    @Deprecated
    public String getSingleHeader(String name) {
        return headers.get(name);
    }

    /**
     * Client connection date
     *
     * @return
     */
    public Date getTime() {
        return time;
    }

    /**
     * Url used by client during first request
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    public boolean isXdomain() {
        return xdomain;
    }

    /**
     * Url params stored in url used by client during first request
     *
     * @return
     */
    public Map<String, List<String>> getUrlParams() {
        return urlParams;
    }

    public String getSingleUrlParam(String name) {
        List<String> values = urlParams.get(name);
        if (values != null && values.size() == 1) {
            return values.iterator().next();
        }
        return null;
    }

}
