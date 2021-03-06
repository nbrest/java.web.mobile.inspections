package com.nicobrest.kamehouse.commons.controller;

import com.nicobrest.kamehouse.commons.model.TestEntity;
import com.nicobrest.kamehouse.commons.model.TestEntityDto;
import com.nicobrest.kamehouse.commons.service.TestEntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Test controller to test AbstractCrudController and AbstractController.
 */
@Controller
@RequestMapping(value = "/api/v1/unit-tests")
public class TestEntityCrudController extends AbstractCrudController {

  @Autowired
  private TestEntityCrudService testEntityCrudService;

  private static final String TEST_ENTITY = "/api/v1/unit-tests";
  private static final String TEST_ENTITY_ID = "/api/v1/unit-tests/";

  /**
   * Create a TestEntity.
   */
  @PostMapping(path = "/test-entity")
  @ResponseBody
  public ResponseEntity<Long> create(@RequestBody TestEntityDto dto, HttpServletRequest request) {
    logTraceRequest(request);
    return create(TEST_ENTITY, testEntityCrudService, dto);
  }

  /**
   * Read a TestEntity.
   */
  @GetMapping(path = "/test-entity/{id}")
  @ResponseBody
  public ResponseEntity<TestEntity> read(@PathVariable Long id) {
    return read(TEST_ENTITY_ID + id, testEntityCrudService, id);
  }

  /**
   * Read all TestEntities.
   */
  @GetMapping(path = "/test-entity")
  @ResponseBody
  public ResponseEntity<List<TestEntity>> readAll() {
    return readAll(TEST_ENTITY, testEntityCrudService);
  }

  /**
   * Update a TestEntity.
   */
  @PutMapping(path = "/test-entity/{id}")
  @ResponseBody
  public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TestEntityDto dto) {
    return update(TEST_ENTITY_ID + id, testEntityCrudService, id, dto);
  }

  /**
   * Delete a TestEntity.
   */
  @DeleteMapping(path = "/test-entity/{id}")
  @ResponseBody
  public ResponseEntity<TestEntity> delete(@PathVariable Long id) {
    return delete(TEST_ENTITY_ID + id, testEntityCrudService, id);
  }
}
