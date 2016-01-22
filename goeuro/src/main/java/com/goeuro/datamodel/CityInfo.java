package com.goeuro.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"_id", "name", "type", "latitude", "longitude"})
public class CityInfo {

    @JsonProperty("_id")
    private long id;
    private String name;
    private String type;
    private GeoPosition geoPosition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonSetter("geo_position")
    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public BigDecimal getLatitude() {
        return geoPosition.getLatitude();
    }

    public BigDecimal getLongitude() {
        return geoPosition.getLongitude();
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                '}';
    }
}
