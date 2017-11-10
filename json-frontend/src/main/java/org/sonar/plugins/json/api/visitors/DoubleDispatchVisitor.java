/*
 * SonarQube JSON Analyzer
 * Copyright (C) 2015-2017 David RACODON
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.json.api.visitors;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

import org.sonar.json.tree.impl.JSONTree;
import org.sonar.plugins.json.api.tree.*;

public abstract class DoubleDispatchVisitor implements TreeVisitor {

  private TreeVisitorContext context = null;

  @Override
  public TreeVisitorContext getContext() {
    Preconditions.checkState(context != null, "this#scanTree(context) should be called to initialised the context before accessing it");
    return context;
  }

  @Override
  public final void scanTree(TreeVisitorContext context) {
    this.context = context;
    scan(context.getTopTree());
  }

  protected void scan(@Nullable Tree tree) {
    if (tree != null) {
      tree.accept(this);
    }
  }

  protected void scanChildren(Tree tree) {
    Iterator<Tree> childrenIterator = ((JSONTree) tree).childrenIterator();

    Tree child;

    while (childrenIterator.hasNext()) {
      child = childrenIterator.next();
      if (child != null) {
        child.accept(this);
      }
    }
  }

  protected <T extends Tree> void scan(List<T> trees) {
    trees.forEach(this::scan);
  }

  public void visitJson(JsonTree tree) {
    scanChildren(tree);
  }

  public void visitObject(ObjectTree tree) {
    scanChildren(tree);
  }

  public void visitArray(ArrayTree tree) {
    scanChildren(tree);
  }

  public void visitPair(PairTree tree) {
    scanChildren(tree);
  }

  public void visitKey(KeyTree tree) {
    scanChildren(tree);
  }

  public void visitValue(ValueTree tree) {
    scanChildren(tree);
  }

  public void visitString(StringTree tree) {
    scanChildren(tree);
  }

  public void visitNumber(LiteralTree tree) {
    scanChildren(tree);
  }

  public void visitFalse(LiteralTree tree) {
    scanChildren(tree);
  }

  public void visitTrue(LiteralTree tree) {
    scanChildren(tree);
  }

  public void visitNull(LiteralTree tree) {
    scanChildren(tree);
  }

  public void visitToken(SyntaxToken token) {
  }

}
