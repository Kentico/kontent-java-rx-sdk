/*
 * Copyright 2019 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.kentico.kontent_delivery_core.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kentico.kontent_delivery_core.utils.DateHelper;
import com.github.kentico.kontent_delivery_core.models.exceptions.KontentException;

import java.text.ParseException;
import java.util.Date;

public class DateTimeElement extends ContentElement<Date> {
    private Date value;

    public DateTimeElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        try {
            this.value = DateHelper.parseIso8601(value.textValue());
        } catch (ParseException e) {
            throw new KontentException("Could not parse Date for field '" + codename + "'", e);
        }
    }

    @Override
    public Date getValue(){
        return this.value;
    }
}