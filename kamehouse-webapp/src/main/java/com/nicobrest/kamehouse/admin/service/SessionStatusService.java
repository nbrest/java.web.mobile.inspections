package com.nicobrest.kamehouse.admin.service;
 
import com.nicobrest.kamehouse.admin.model.ApplicationUser;
import com.nicobrest.kamehouse.admin.model.SessionStatus;

import com.nicobrest.kamehouse.commons.utils.PropertiesUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Service layer to get the session status.
 * 
 * @author nbrest
 *
 */
@Service
public class SessionStatusService {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private ApplicationUserService applicationUserService;
  
  public void setApplicationUserService(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }
  
  public ApplicationUserService getApplicationUserService() {
    return applicationUserService;
  }
  
  /**
   * Returns the current session's status.
   */
  public SessionStatus get(HttpSession session) {
    logger.trace("getting the user's session status");
    Authentication authentication = getAuthentication();
    String username = authentication.getName();
    SessionStatus sessionStatus = new SessionStatus();
    sessionStatus.setUsername(StringEscapeUtils.escapeHtml(username));
    sessionStatus.setServer(PropertiesUtils.getHostname());
    try {
      ApplicationUser applicationUser = applicationUserService.loadUserByUsername(username);
      sessionStatus.setFirstName(applicationUser.getFirstName());
      sessionStatus.setLastName(applicationUser.getLastName()); 
    } catch (UsernameNotFoundException e) {
      logger.warn(e.getMessage());
    }
    if (session != null) {
      sessionStatus.setSessionId(session.getId());
    }
    logger.trace("get session response {}", sessionStatus);
    return sessionStatus;
  }
  
  /**
   * Gets the Authentication object from the spring security context.
   */
  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}