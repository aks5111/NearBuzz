package com.Java.NearBuzz.geo.repository;

import com.Java.NearBuzz.geo.entity.GeocodeCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeocodeCacheRepository extends JpaRepository<GeocodeCache, Long> {
    Optional<GeocodeCache> findByAddressHash(String addressHash);
}
