package com.kenticocloud.delivery.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.Date;

import com.kenticocloud.delivery.models.exceptions.KenticoCloudException;
import com.kenticocloud.delivery.utils.DateHelper;

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
            throw new KenticoCloudException("Could not parse Date for field '" + codename + "'", e);
        }
    }

    @Override
    public Date getValue(){
        return this.value;
    }
}