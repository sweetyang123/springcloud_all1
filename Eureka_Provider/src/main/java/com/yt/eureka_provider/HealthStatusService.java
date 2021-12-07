package com.yt.eureka_provider;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class HealthStatusService implements HealthIndicator {
   private  Boolean status=true;

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if (status)return new Health.Builder().up().build();
        return new Health.Builder().down().build();
    }
}
