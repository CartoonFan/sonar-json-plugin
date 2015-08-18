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
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

public class MissingNewLineAtEndOfFileCheckTest {

  private MissingNewLineAtEndOfFileCheck check = new MissingNewLineAtEndOfFileCheck();

  @Test
  public void should_have_an_empty_new_line_at_the_end_of_the_file_and_not_raise_any_issue() {
    SourceFile file = JSONAstScanner.scanSingleFile(new File("src/test/resources/checks/newLineEndOfFile.json"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages()).noMore();
  }

  @Test
  public void should_not_have_an_empty_new_line_at_the_end_of_the_file_and_raise_an_issue() {
    SourceFile file = JSONAstScanner.scanSingleFile(new File("src/test/resources/checks/noNewLineEndOfFile.json"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages()).next()
      .withMessage("Add an empty new line at the end of this file.")
      .noMore();
  }

}
