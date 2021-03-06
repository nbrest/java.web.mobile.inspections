package com.nicobrest.kamehouse.commons.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nicobrest.kamehouse.commons.dao.KameHouseUserAuthenticationDao;
import com.nicobrest.kamehouse.commons.model.KameHouseUser;
import com.nicobrest.kamehouse.commons.model.dto.KameHouseUserDto;
import com.nicobrest.kamehouse.commons.testutils.KameHouseUserTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the KameHouseUserAuthenticationService class.
 *
 * @author nbrest
 */
public class KameHouseUserAuthenticationServiceTest
    extends AbstractCrudServiceTest<KameHouseUser, KameHouseUserDto> {

  private KameHouseUser kameHouseUser;

  @InjectMocks
  private KameHouseUserAuthenticationService kameHouseUserService;

  @Mock
  private KameHouseUserAuthenticationDao kameHouseUserDaoMock;

  /**
   * Resets mock objects and initializes test repository.
   */
  @Before
  public void beforeTest() {
    testUtils = new KameHouseUserTestUtils();
    testUtils.initTestData();
    kameHouseUser = testUtils.getSingleTestData();

    MockitoAnnotations.initMocks(this);
    Mockito.reset(kameHouseUserDaoMock);
  }

  /**
   * Tests calling the service to get a single KameHouseUser in the repository
   * by username.
   */
  @Test
  public void loadUserByUsernameTest() {
    when(kameHouseUserDaoMock.loadUserByUsername(kameHouseUser.getUsername()))
        .thenReturn(kameHouseUser);

    KameHouseUser returnedUser =
        kameHouseUserService.loadUserByUsername(kameHouseUser.getUsername());

    testUtils.assertEqualsAllAttributes(kameHouseUser, returnedUser);
    verify(kameHouseUserDaoMock, times(1)).loadUserByUsername(kameHouseUser.getUsername());
  }
}
