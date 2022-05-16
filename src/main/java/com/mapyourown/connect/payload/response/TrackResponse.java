package com.mapyourown.connect.payload.response;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import com.mapyourown.connect.models.Module;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrackResponse {
    private Long id;
    private String thumbnail;
    private String topic;
    private int authorId;
    private String title;
    private String description;
    private int numberOfViews;
    private int length;
    private int modulesCount;
    private List<Module> modules;
    private Instant createdAt;
    private Instant updatedAt;
}
