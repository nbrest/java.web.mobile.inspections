package com.nicobrest.kamehouse.commons.model.systemcommand;

/**
 * System command to press type content in the server screen using VncDo.
 * 
 * @author nbrest
 *
 */
public class VncDoTypeSystemCommand extends VncDoSystemCommand {

  /**
   * Sets the command line for each operation system required for this SystemCommand.
   */
  public VncDoTypeSystemCommand(String content) {
    setVncDoSystemCommand("type", content);
    setOutputCommand();
  }
}
