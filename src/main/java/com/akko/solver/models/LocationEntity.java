package com.akko.solver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LocationEntity {
    @Id
    @GeneratedValue
    private long id;
    private Double longitude;
    private Double latitude;

    public LocationEntity() {
    }
    public LocationEntity(Long id,Double longitude, Double latitude) {
        this.id=id;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
