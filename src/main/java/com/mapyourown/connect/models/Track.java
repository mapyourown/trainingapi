package com.mapyourown.connect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapyourown.connect.models.audit.UserDateAudit;
import com.mapyourown.connect.models.User;
import com.mapyourown.connect.payload.request.TrackRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "track")
@NoArgsConstructor
public class Track extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "thumbnail")
    @NotNull
    private String thumbnail;


    @Column(name = "topic")
    @NotNull
    private String topic;

    @Column(name = "author_id")
    @NotNull
    private int authorId;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "number_of_views")
    @NotNull
    private int numberOfViews;


    @Column(name = "length")
    @NotNull
    private int length;

    @Column(name = "modules_count")
    @NotNull
    private int modulesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules = new ArrayList<>();

    public Track(TrackRequest request) {

      this.thumbnail = request.getThumbnail();
      this.topic = request.getTopic();
      this.authorId = request.getAuthorId();
      this.title = request.getTitle();
      this.description = request.getDescription();
      this.numberOfViews = request.getNumberOfViews();
      this.length = request.getLength();
      this.modulesCount = request.getModulesCount();
      this.setCreatedAt(request.getCreatedAt());
      this.setUpdatedAt(request.getUpdatedAt());
    }
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;

        for(Module m : modules) {
            m.setTrack(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setModulesCount(int modulesCount) {
        this.modulesCount = modulesCount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTopic() {
        return topic;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public int getLength() {
        return length;
    }

    public int getModulesCount() {
        return modulesCount;
    }

    public Category getCategory() {
        return category;
    }
}












