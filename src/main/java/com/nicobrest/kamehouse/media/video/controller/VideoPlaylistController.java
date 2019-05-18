package com.nicobrest.kamehouse.media.video.controller;

import com.nicobrest.kamehouse.media.video.model.Playlist;
import com.nicobrest.kamehouse.media.video.service.VideoPlaylistService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/media/video")
public class VideoPlaylistController {

  private static final Logger logger = LoggerFactory.getLogger(VideoPlaylistController.class);

  @Autowired
  private VideoPlaylistService videoPlaylistService;

  /**
   * Get all video playlists.
   */
  @RequestMapping(value = "/playlists", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<Playlist>> getAllVideoPlaylists() {

    logger.trace("In controller /api/v1/media/video/playlists (GET)");
    List<Playlist> videoPlaylists = videoPlaylistService.getAllVideoPlaylists();
    return new ResponseEntity<List<Playlist>>(videoPlaylists, HttpStatus.OK);
  }
}