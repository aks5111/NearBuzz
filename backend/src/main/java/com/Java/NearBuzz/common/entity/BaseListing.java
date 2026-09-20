package com.Java.NearBuzz.common.entity;

import com.Java.NearBuzz.common.converter.StringListJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Shared shape for every "happening somewhere" module (travel, fitness,
 * calisthenics, party place, friend meetups, sports activity, event
 * booking) — see PROJECT_STRUCTURE_GUIDE.md section 3. Each module's
 * entity extends this and adds its own two or three specific columns.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 255)
    private String location;

    @Column(name = "schedule_text", length = 255)
    private String scheduleText;

    @Column(name = "price_label", length = 50)
    private String priceLabel;

    @Column(length = 100)
    private String tag;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Convert(converter = StringListJsonConverter.class)
    @Column(name = "image_urls", nullable = false, columnDefinition = "TEXT")
    private List<String> imageUrls = List.of();

    /** Replaces the image list and keeps imageUrl (the cover shown wherever
     * only one photo fits — map pins, product grids) as its first entry. */
    public void setImages(List<String> urls) {
        this.imageUrls = urls == null ? List.of() : urls;
        this.imageUrl = this.imageUrls.isEmpty() ? null : this.imageUrls.get(0);
    }

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ListingStatus status = ListingStatus.PUBLISHED;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (status == null) {
            status = ListingStatus.PUBLISHED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
