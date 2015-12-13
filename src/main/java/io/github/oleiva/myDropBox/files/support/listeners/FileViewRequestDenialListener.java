package io.github.oleiva.myDropBox.files.support.listeners;

import org.springframework.stereotype.Component;

@Component("fileViewRequestDenialListener")
public class FileViewRequestDenialListener implements FileRequestListener {
  @Override
  public void handle(Long id, String username) {

  }
}
