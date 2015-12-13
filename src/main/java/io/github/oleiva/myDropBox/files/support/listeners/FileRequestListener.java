package io.github.oleiva.myDropBox.files.support.listeners;

public interface FileRequestListener {
  void handle(Long id, String username);
}
