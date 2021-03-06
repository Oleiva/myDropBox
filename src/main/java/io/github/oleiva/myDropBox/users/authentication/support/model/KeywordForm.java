package io.github.oleiva.myDropBox.users.authentication.support.model;

import io.github.oleiva.myDropBox.entity.UsersEntity;

/**
 * Created by Ivasoft on 9/28/14.
 */
public class KeywordForm {
  private String question;
  private String answer;

  public KeywordForm() {
  }

  public KeywordForm(UsersEntity usersEntity) {
    this.question = usersEntity.getSecretQuestion();
    this.answer = "";
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
