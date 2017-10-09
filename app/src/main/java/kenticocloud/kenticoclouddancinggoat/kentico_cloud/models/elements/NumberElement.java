package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NumberElement extends ContentElement<Double> {
    private Double _value;

    public NumberElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        _value = value.doubleValue();
    }

    @Override
    public Double getValue(){
        return this._value;
    }
}