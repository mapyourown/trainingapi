package com.mapyourown.connect.repository;

import com.mapyourown.connect.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
