package io.github.oleiva.myDropBox.files.support.listeners;

import org.springframework.stereotype.Component;

@Component("fileRemoveRequestDenialListener")
public class FileRemoveRequestDenialListener implements FileRequestListener {
  @Override
  public void handle(Long id, String username) {

  }
}
