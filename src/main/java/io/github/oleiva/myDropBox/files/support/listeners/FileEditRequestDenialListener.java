package io.github.oleiva.myDropBox.files.support.listeners;

import org.springframework.stereotype.Component;

@Component("fileEditRequestDenialListener")
public class FileEditRequestDenialListener implements FileRequestListener {
  @Override
  public void handle(Long id, String username) {

  }
}
