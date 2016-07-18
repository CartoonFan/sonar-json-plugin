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
package org.sonar.json.ast.visitors;

import java.io.File;
import javax.annotation.CheckForNull;

import org.sonar.api.BatchExtension;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.source.Highlightable;

public class SonarComponents implements BatchExtension {

  private final ResourcePerspectives resourcePerspectives;
  private final FileSystem fs;

  public SonarComponents(ResourcePerspectives resourcePerspectives, FileSystem fs) {
    this.resourcePerspectives = resourcePerspectives;
    this.fs = fs;
  }

  @CheckForNull
  public InputFile inputFileFor(File file) {
    return fs.inputFile(fs.predicates().hasAbsolutePath(file.getAbsolutePath()));
  }

  public Highlightable highlightableFor(InputFile inputFile) {
    return resourcePerspectives.as(Highlightable.class, inputFile);
  }

  public ResourcePerspectives getResourcePerspectives() {
    return resourcePerspectives;
  }

}
