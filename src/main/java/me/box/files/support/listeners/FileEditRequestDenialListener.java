package me.box.files.support.listeners;

import org.springframework.stereotype.Component;

@Component("fileEditRequestDenialListener")
public class FileEditRequestDenialListener implements FileRequestListener {
  @Override
  public void handle(Long id, String username) {

  }
}
