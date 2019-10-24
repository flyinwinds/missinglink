/*
 * Copyright (c) 2019 Spotify AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.spotify.missinglink.dependencies;

import com.spotify.missinglink.ConflictFilter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class ExpectedClasses implements ConflictFilter {
  private final HashMap<String, Set<String>> expectedClasses;

  ExpectedClasses(Collection<ArtifactContainer> usedArtifacts) {
    expectedClasses = new HashMap<>();
    usedArtifacts.forEach(artifactContainer -> {
      String artifactName = artifactContainer.getArtifactName();
      artifactContainer.getDefinedClasses().forEach(className -> {
        expectedClasses
                .computeIfAbsent(artifactName, x -> new HashSet<>())
                .add(artifactName);
      });
    });
  }

  @Override
  public boolean expectedToExist(String className) {
    return expectedClasses.containsKey(className);
  }

  @Override
  public Set<String> getFoundIn(String className) {
    return expectedClasses.getOrDefault(className, Collections.emptySet());
  }
}
