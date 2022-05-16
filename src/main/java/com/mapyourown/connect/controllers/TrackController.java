package com.mapyourown.connect.controllers;

import com.mapyourown.connect.exception.ResourceNotFoundException;
import com.mapyourown.connect.models.Module;
import com.mapyourown.connect.models.Track;
import com.mapyourown.connect.payload.request.TrackRequest;
import com.mapyourown.connect.payload.response.TrackResponse;
import com.mapyourown.connect.repository.ModuleRepository;
import com.mapyourown.connect.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @PostMapping("/track")
    public ResponseEntity<Track> create(@Valid @RequestBody TrackRequest trackRequest) {
        Track track = new Track(trackRequest);
        Track savedTrack = trackRepository.save(track);
        for (int i=0; i<trackRequest.getModules().size(); i++){
            Module module = new Module();
            module.setContent(trackRequest.getModules().get(i).getContent());
            module.setLength(trackRequest.getModules().get(i).getLength());
            module.setTitle(trackRequest.getModules().get(i).getTitle());
            module.setTopic(trackRequest.getModules().get(i).getTopic());
            module.setVideoUrl(trackRequest.getModules().get(i).getVideoUrl());
            module.setTrack(track);
            moduleRepository.save(module);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTrack.getId()).toUri();

        return ResponseEntity.created(location).body(savedTrack);

    }
    @GetMapping("/tracks")
    public List<TrackResponse> getTrack(){
        List<Track> trackList = trackRepository.findAll();
        List<TrackResponse> trackResponseList = new ArrayList<>();

        trackList.forEach(l -> {
            TrackResponse  response = new TrackResponse();
            response.setId(l.getId());
            response.setAuthorId(l.getAuthorId());
            response.setThumbnail(l.getThumbnail());
            response.setTitle(l.getTitle());
            response.setDescription(l.getDescription());
            response.setCreatedAt(l.getCreatedAt());
            response.setUpdatedAt(l.getUpdatedAt());
            response.setNumberOfViews(l.getNumberOfViews());
            List<Module> modules = new ArrayList<>();

            for (int i=0; i<l.getModules().size(); i++) {
                Module module = new Module();
                module.setId(l.getModules().get(i).getId());
                module.setContent(l.getModules().get(i).getContent());
                module.setLength(l.getModules().get(i).getLength());
                module.setTitle(l.getModules().get(i).getTitle());
                module.setTopic(l.getModules().get(i).getTopic());
                module.setVideoUrl(l.getModules().get(i).getVideoUrl());
                modules.add(module);
            }
            response.setModules(modules);
            trackResponseList.add(response);
        });
        return trackResponseList;
    }
    @GetMapping("/track/{id}")
    public ResponseEntity<Track> getTrackByID(@PathVariable(value="id") Long trackId) throws ResourceNotFoundException {
        Optional<Track> track = trackRepository.findById(trackId);
        return new ResponseEntity<Track>(track.get(), HttpStatus.OK);
    }
    @GetMapping("/track/{id}/modules")
    public ResponseEntity<List<Module>> getModulesByTrackId(@PathVariable(value="id") Long trackId) throws  ResourceNotFoundException {
        Optional<Track> track = trackRepository.findById(trackId);
        List<Module> module = track.get().getModules();
        return new ResponseEntity<List<Module>>(module, HttpStatus.OK);

    }
    @PatchMapping("/track/{id}/numberOfViews")
    public ResponseEntity<Track> UpdateNumberOfViews(@PathVariable(value="id") Long trackId) throws  ResourceNotFoundException {
        Optional<Track> trackById = trackRepository.findById(trackId);
        Track track = trackById.get();
        int numberOfViews = trackById.get().getNumberOfViews();
        numberOfViews = numberOfViews + 1;
        track.setNumberOfViews(numberOfViews);
        Track savedTracked = trackRepository.save(track);
        return new ResponseEntity<Track>(savedTracked, HttpStatus.OK);
    }
}
