package io.github.oleiva.myDropBox.support;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;

@Aspect
public class LoggingAspect {

  private static final Logger logger = Logger.getLogger(LoggingAspect.class);

  @Before("execution(* io.github.oleiva.myDropBox.files.FileController.view(..))")
  public void logViewRequest(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = ((Authentication) arguments[2]).getName();
    logger.error(String.format("[user %s] requested to view [file %d]", username, id));
  }

  @Before("execution(* io.github.oleiva.myDropBox.files.FileController.edit(..))")
  public void logEditRequest(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = ((Authentication) arguments[2]).getName();
    logger.error(String.format("[user %s] requested editing of [file %d]", username, id));
  }

  @Before("execution(* io.github.oleiva.myDropBox.files.FileController.remove(..))")
  public void logRemoveRequest(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = ((Authentication) arguments[2]).getName();
    logger.error(String.format("[user %s] requested removal of [file %d]", username, id));
  }

  @Before("execution(* io.github.oleiva.myDropBox.files.support.listeners.FileViewRequestDenialListener.handle(..))")
  public void logViewRequestDenial(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = (String) arguments[1];
    logger.error(String.format("[user %s] was refused to view [file %d]", username, id));
  }

  @Before("execution(* io.github.oleiva.myDropBox.files.support.listeners.FileEditRequestDenialListener.handle(..))")
  public void logEditRequestDenial(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = (String) arguments[1];
    logger.error(String.format("[user %s] was refused to edit [file %d]", username, id));
  }

  @Before("execution(* io.github.oleiva.myDropBox.files.support.listeners.FileRemoveRequestDenialListener.handle(..))")
  public void logRemoveRequestDenial(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();
    Long id = (Long) arguments[0];
    String username = (String) arguments[1];
    logger.error(String.format("[user %s] was refused to remove [file %d]", username, id));
  }
}
