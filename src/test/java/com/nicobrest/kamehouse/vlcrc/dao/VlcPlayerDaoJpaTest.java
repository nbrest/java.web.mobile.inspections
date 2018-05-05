package com.nicobrest.kamehouse.vlcrc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.nicobrest.kamehouse.exception.KameHouseBadRequestException;
import com.nicobrest.kamehouse.exception.KameHouseNotFoundException;
import com.nicobrest.kamehouse.exception.KameHouseServerErrorException;
import com.nicobrest.kamehouse.vlcrc.model.VlcPlayer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Unit tests for the VlcPlayerDaoJpa class.
 *
 * @author nbrest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class VlcPlayerDaoJpaTest {
   
  @Autowired
  private VlcPlayerDao vlcPlayerDaoJpa;

  @Autowired
  private EntityManagerFactory entityManagerFactory;
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Clear data from the repository before each test.
   */
  @Before
  public void setUp() {
    
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Query query = em.createNativeQuery("DELETE FROM VLC_PLAYER");
    query.executeUpdate();
    em.getTransaction().commit();
    em.close();
  }

  /**
   * Test for creating a VlcPlayer in the repository.
   */
  @Test
  public void createVlcPlayerTest() {

    VlcPlayer vlcPlayerCreated = new VlcPlayer();
    vlcPlayerCreated.setHostname("playerCapsuleCorp");
    vlcPlayerCreated.setPort(8080);
    vlcPlayerCreated.setUsername("goku");
    vlcPlayerCreated.setPassword("vegeta");

    try {
      assertEquals(0, vlcPlayerDaoJpa.getAllVlcPlayers().size());
      vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);
      assertEquals(1, vlcPlayerDaoJpa.getAllVlcPlayers().size());
      vlcPlayerDaoJpa
          .deleteVlcPlayer(vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp").getId());
    } catch (KameHouseBadRequestException | KameHouseNotFoundException e) {
      e.printStackTrace();
      fail("Caught unexpected exception.");
    }
  }

  /**
   * Test for creating a VlcPlayer in the repository Exception flows.
   */
  @Test
  public void createVlcPlayerConflictExceptionTest() {

    thrown.expect(KameHouseServerErrorException.class);
    thrown.expectMessage("PersistenceException in createVlcPlayer");

    VlcPlayer vlcPlayerCreated = new VlcPlayer();
    vlcPlayerCreated.setHostname("playerCapsuleCorp");
    vlcPlayerCreated.setPort(8080);
    vlcPlayerCreated.setUsername("goku");
    vlcPlayerCreated.setPassword("vegeta");
    vlcPlayerCreated.setId(1000L);
    
    VlcPlayer vlcPlayerCreated2 = new VlcPlayer();
    vlcPlayerCreated2.setHostname("playerCapsuleCorp");
    vlcPlayerCreated2.setPort(8080);
    vlcPlayerCreated2.setUsername("goku");
    vlcPlayerCreated2.setPassword("vegeta");
    vlcPlayerCreated2.setId(1000L);

    vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);
    vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated2);
  }
  
  /**
   * Test for getting a single VlcPlayer in the repository.
   */
  @Test
  public void getVlcPlayerTest() {

    try {
      VlcPlayer vlcPlayerCreated = new VlcPlayer();
      vlcPlayerCreated.setHostname("playerCapsuleCorp");
      vlcPlayerCreated.setPort(8080);
      vlcPlayerCreated.setUsername("goku");
      vlcPlayerCreated.setPassword("vegeta");
      vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);

      VlcPlayer vlcPlayerRetrieved = vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp");
      
      assertNotNull(vlcPlayerRetrieved);
      assertEquals("playerCapsuleCorp", vlcPlayerRetrieved.getHostname());
    } catch (KameHouseNotFoundException e) {
      e.printStackTrace();
      fail("Caught unexpected exception.");
    }
  }

  /**
   * Test for getting a single VlcPlayer in the repository Exception flows.
   */
  @Test
  public void getVlcPlayerNotFoundExceptionTest() {

    thrown.expect(KameHouseNotFoundException.class);
    thrown.expectMessage("VLC Player with hostname yukimura was not found in the repository.");
    vlcPlayerDaoJpa.getVlcPlayer("yukimura");
  }  
  
  /**
   * Test for updating an existing user in the repository.
   */
  @Test
  public void updateVlcPlayerTest() {

    try {
      VlcPlayer vlcPlayerCreated = new VlcPlayer();
      vlcPlayerCreated.setHostname("playerCapsuleCorp");
      vlcPlayerCreated.setPort(8080);
      vlcPlayerCreated.setUsername("goku");
      vlcPlayerCreated.setPassword("vegeta");
      vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);

      VlcPlayer originalVlcPlayer = vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp");
      assertEquals("playerCapsuleCorp", originalVlcPlayer.getHostname());
 
      VlcPlayer vlcPlayerModified = new VlcPlayer();
      vlcPlayerModified.setHostname("playerCapsuleCorp2");
      vlcPlayerModified.setPort(8080);
      vlcPlayerModified.setUsername("goku2");
      vlcPlayerModified.setPassword("vegeta2");
      vlcPlayerModified.setId(originalVlcPlayer.getId());
      
      vlcPlayerDaoJpa.updateVlcPlayer(vlcPlayerModified);
      VlcPlayer updatedVlcPlayer = vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp2");

      assertEquals(originalVlcPlayer.getId().toString(), updatedVlcPlayer.getId().toString());
      assertEquals("goku2", updatedVlcPlayer.getUsername());  
      assertEquals("playerCapsuleCorp2", updatedVlcPlayer.getHostname()); 
      
      vlcPlayerDaoJpa.updateVlcPlayer(originalVlcPlayer);
    } catch (KameHouseNotFoundException e) {
      e.printStackTrace();
      fail("Caught unexpected exception.");
    }
  }

  /**
   * Test for updating an existing user in the repository Exception flows.
   */
  @Test
  public void updateVlcPlayerNotFoundExceptionTest() {

    VlcPlayer vlcPlayerCreated = new VlcPlayer();
    vlcPlayerCreated.setHostname("playerCapsuleCorp");
    vlcPlayerCreated.setPort(8080);
    vlcPlayerCreated.setUsername("goku");
    vlcPlayerCreated.setPassword("vegeta");
    vlcPlayerCreated.setId(0L);
    
    thrown.expect(KameHouseNotFoundException.class);
    thrown.expectMessage("VLC Player with id 0 was not found in the repository.");
    vlcPlayerDaoJpa.updateVlcPlayer(vlcPlayerCreated);
  }

  /**
   * Test for updating an existing user in the repository Exception flows.
   */
  @Test
  public void updateVlcPlayerServerErrorExceptionTest() {

    thrown.expect(KameHouseServerErrorException.class);
    thrown.expectMessage("PersistenceException in updateVlcPlayer");

    try {
      VlcPlayer vlcPlayerCreated = new VlcPlayer();
      vlcPlayerCreated.setHostname("playerCapsuleCorp");
      vlcPlayerCreated.setPort(8080);
      vlcPlayerCreated.setUsername("goku");
      vlcPlayerCreated.setPassword("vegeta");
      vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);

      VlcPlayer originalVlcPlayer = vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp");
      assertEquals("goku", originalVlcPlayer.getUsername());
      assertEquals("playerCapsuleCorp", originalVlcPlayer.getHostname());
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 70 ; i++) {
        sb.append("goku");
      }
      String username = sb.toString();
 
      VlcPlayer vlcPlayerModified = new VlcPlayer();
      vlcPlayerModified.setHostname("playerCapsuleCorp");
      vlcPlayerModified.setPort(8080);
      vlcPlayerModified.setUsername(username);
      vlcPlayerModified.setPassword("vegeta");
      vlcPlayerModified.setId(originalVlcPlayer.getId());
      
      vlcPlayerDaoJpa.updateVlcPlayer(vlcPlayerModified);
    } catch (KameHouseNotFoundException e) {
      e.printStackTrace();
      fail("Caught unexpected exception.");
    }
  }

  /**
   * Test for deleting an existing user from the repository.
   */
  @Test
  public void deleteVlcPlayerTest() {

    try {
      VlcPlayer vlcPlayerToDelete = new VlcPlayer();
      vlcPlayerToDelete.setHostname("playerCapsuleCorp");
      vlcPlayerToDelete.setPort(8080);
      vlcPlayerToDelete.setUsername("goku");
      vlcPlayerToDelete.setPassword("vegeta");
      vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerToDelete);
      assertEquals(1, vlcPlayerDaoJpa.getAllVlcPlayers().size());
      VlcPlayer deletedVlcPlayer = vlcPlayerDaoJpa
          .deleteVlcPlayer(vlcPlayerDaoJpa.getVlcPlayer("playerCapsuleCorp").getId());
      assertEquals(0, vlcPlayerDaoJpa.getAllVlcPlayers().size());
      assertEquals("goku", deletedVlcPlayer.getUsername()); 
      assertEquals("playerCapsuleCorp", deletedVlcPlayer.getHostname()); 
      
    } catch (KameHouseNotFoundException | KameHouseBadRequestException e) {
      e.printStackTrace();
      fail("Caught unexpected exception.");
    }
  }

  /**
   * Test for deleting an existing user from the repository Exception flows.
   */
  @Test
  public void deleteVlcPlayerNotFoundExceptionTest() {

    thrown.expect(KameHouseNotFoundException.class);
    thrown.expectMessage("VLC Player with id " + 987L + " was not found in the repository.");
    vlcPlayerDaoJpa.deleteVlcPlayer(987L);
  }

  /**
   * Test for getting all the VlcPlayers in the repository.
   */
  @Test
  public void getAllVlcPlayersTest() {

    VlcPlayer vlcPlayerCreated = new VlcPlayer();
    vlcPlayerCreated.setHostname("playerCapsuleCorp");
    vlcPlayerCreated.setPort(8080);
    vlcPlayerCreated.setUsername("goku");
    vlcPlayerCreated.setPassword("vegeta");
    vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated);
    VlcPlayer vlcPlayerCreated2 = new VlcPlayer();
    vlcPlayerCreated2.setHostname("playerCapsuleCorp2");
    vlcPlayerCreated2.setPort(8080);
    vlcPlayerCreated2.setUsername("goku2");
    vlcPlayerCreated2.setPassword("vegeta2");
    vlcPlayerDaoJpa.createVlcPlayer(vlcPlayerCreated2);
    try {
      List<VlcPlayer> vlcPlayerList = vlcPlayerDaoJpa.getAllVlcPlayers();
      assertEquals(2, vlcPlayerList.size()); 
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception thrown.");
    }
  }
}