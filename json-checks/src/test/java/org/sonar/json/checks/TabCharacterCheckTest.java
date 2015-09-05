/*
 * SonarQube JSON Plugin
 * Copyright (C) 2015 David RACODON
 * david.racodon@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.json.checks;

import java.io.File;

import org.junit.Test;
import org.sonar.json.JSONAstScanner;
import org.sonar.json.checks.generic.TabCharacterCheck;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

public class TabCharacterCheckTest {

  private TabCharacterCheck check = new TabCharacterCheck();

  @Test
  public void should_find_tab_characters_and_raise_an_issue() {
    SourceFile testFile = JSONAstScanner.scanSingleFile(new File("src/test/resources/checks/tabCharacter.json"), check);
    CheckMessagesVerifier.verify(testFile.getCheckMessages()).next()
      .withMessage("Replace all tab characters in this file by sequences of whitespaces.")
      .noMore();
  }

  @Test
  public void should_not_find_tab_characters_and_not_raise_an_issue() {
    SourceFile testFile = JSONAstScanner.scanSingleFile(new File("src/test/resources/checks/sample.json"), check);
    CheckMessagesVerifier.verify(testFile.getCheckMessages()).noMore();
  }

}
