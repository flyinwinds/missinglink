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
package com.spotify.missinglink;

import java.util.Set;

public interface ConflictFilter {

  /**
   *
   * @param className class to test
   * @return true if a missing class should trigger a conflict, otherwise false
   */
  boolean expectedToExist(String className);

  /**
   *
   * @param className class to test
   * @return a set of artifacts that the class was expected to be in
   */
  Set<String> getFoundIn(String className);

}
