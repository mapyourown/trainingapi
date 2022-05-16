package com.mapyourown.connect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapyourown.connect.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "topic")
    @NotNull
    private String topic;

    @Column(name = "length")
    @NotNull
    private int length;

    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "videoUrl")
    @NotNull
    private String videoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Track track;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    public Track getTrack() {
        return track;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}


