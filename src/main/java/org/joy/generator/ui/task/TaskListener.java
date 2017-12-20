/*
 * Copyright 2014 ptma@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.joy.generator.ui.task;

public interface TaskListener {
  /**
   * Called when the observed task has finished.
   */
  void taskFinished();

  /**
   * Called when the observed task reports its status.
   */
  void taskStatus(Object obj);

  /**
   * Called when the observed task reports its result.
   */
  void taskResult(Object obj);

  /**
   * Called when the observed task throws an exception.
   */
  void taskError(Exception e);
}
