/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.ql.parse.repl.load.log.state;

import org.apache.hadoop.hive.ql.parse.repl.ReplState;
import org.apache.hadoop.hive.ql.parse.repl.DumpType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IncrementalLoadEnd extends ReplState {
  @JsonProperty
  private String dbName;

  @JsonProperty
  private DumpType loadType;

  @JsonProperty
  private Long numEvents;

  @JsonProperty
  private Long loadEndTime;

  @JsonProperty
  private String dumpDir;

  @JsonProperty
  private String lastReplId;

  public IncrementalLoadEnd(String dbName,
                            long numEvents,
                            String dumpDir,
                            String lastReplId) {
    this.dbName = dbName;
    this.loadType = DumpType.INCREMENTAL;
    this.numEvents = numEvents;
    this.loadEndTime = System.currentTimeMillis() / 1000;
    this.dumpDir = dumpDir;
    this.lastReplId = lastReplId;
  }
}
