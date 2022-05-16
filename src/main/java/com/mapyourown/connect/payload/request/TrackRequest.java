package com.mapyourown.connect.payload.request;

import com.mapyourown.connect.models.Module;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class TrackRequest {

    private String topic;
    private String title;
    private Long categoryId;
    private String thumbnail;
    private int authorId;
    private String description;
    private int numberOfViews;
    private int length;
    private int modulesCount;
    private List<Module> modules;
    private Instant createdAt;
    private Instant updatedAt;

}
