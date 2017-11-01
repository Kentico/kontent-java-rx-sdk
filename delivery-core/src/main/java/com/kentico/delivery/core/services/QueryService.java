/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.core.services;

import com.kentico.delivery.core.adapters.IHttpAdapter;
import com.kentico.delivery.core.adapters.IRxAdapter;
import com.kentico.delivery.core.config.DeliveryConfig;
import com.kentico.delivery.core.config.IDeliveryConfig;
import com.kentico.delivery.core.config.IDeliveryProperties;
import com.kentico.delivery.core.interfaces.item.common.IQueryConfig;
import com.kentico.delivery.core.interfaces.item.common.IQueryParameter;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;

public final class QueryService implements IQueryService{

    private IDeliveryConfig config;
    private IRxAdapter rxAdapter;
    private IHttpAdapter httpAdapter;

    public QueryService(IDeliveryConfig config, IRxAdapter rxAdapter, IHttpAdapter httpAdapter){
        this.config = config;
        this.rxAdapter = rxAdapter;
        this.httpAdapter = httpAdapter;
    }

    private String getDeliveryUrl(){
        return config.getDeliveryApiUrl();
    }

    private String getBaseUrl(){
        return this.getDeliveryUrl() + '/' + this.config.getProjectId();
    }

    private String addParametersToUrl( String url, List<IQueryParameter> parameters){
        if (parameters == null){
            return url;
        }

        StringBuilder urlBuilder = new StringBuilder(url);
        for(int i = 0; i < parameters.size(); i++) {
            IQueryParameter parameter = parameters.get(i);
            if (i == 0) {
                // first item
                urlBuilder.append("?").append(parameter.getParam()).append("=").append(parameter.getParamValue());
            } else {
                // after first items
                urlBuilder.append("&").append(parameter.getParam()).append("=").append(parameter.getParamValue());
            }
        }
        url = urlBuilder.toString();

        return url;
    }

    @Override
    public String getUrl( String action, List<IQueryParameter> parameters){
        return addParametersToUrl(getBaseUrl() + action, parameters);
    }

    @Override
    public Observable<JSONObject> getObservable(String url, IQueryConfig queryConfig, IDeliveryProperties deliveryProperties) {
        return this.rxAdapter.get(url, queryConfig, deliveryProperties);
    }

    @Override
    public JSONObject getJson(String url, IQueryConfig queryConfig, IDeliveryProperties deliveryProperties) {
        return this.httpAdapter.get(url, queryConfig, deliveryProperties);
    }
}
