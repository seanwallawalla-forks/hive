/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.ql.txn.compactor;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.conf.MetastoreConf;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.hadoop.hive.metastore.metrics.MetricsConstants.COMPACTION_NUM_DELTAS;
import static org.apache.hadoop.hive.metastore.metrics.MetricsConstants.COMPACTION_NUM_OBSOLETE_DELTAS;
import static org.apache.hadoop.hive.metastore.metrics.MetricsConstants.COMPACTION_NUM_SMALL_DELTAS;

public class TestDeltaFilesMetricFlags extends CompactorTest {

  @Override
  public void setup() throws Exception {
  }

  @Override
  boolean useHive130DeltaDirName() {
    return false;
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromInitiatorWithMetricsDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METRICS_ENABLED.getVarname(), false);
    setup(conf);
    startInitiator();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_DELTAS).size());
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromWorkerWithMetricsDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METRICS_ENABLED.getVarname(), false);
    setup(conf);
    startWorker();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_SMALL_DELTAS).size());
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromCleanerWithMetricsDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METRICS_ENABLED.getVarname(), false);
    setup(conf);
    startCleaner();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_OBSOLETE_DELTAS).size());
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromInitiatorWithAcidMetricsThreadDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METASTORE_ACIDMETRICS_THREAD_ON.getVarname(), false);
    setup(conf);
    startInitiator();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_DELTAS).size());
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromWorkerWithAcidMetricsThreadDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METASTORE_ACIDMETRICS_THREAD_ON.getVarname(), false);
    setup(conf);
    startWorker();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_SMALL_DELTAS).size());
  }

  @Test(expected = javax.management.InstanceNotFoundException.class)
  public void testDeltaFilesMetricFromCleanerWithAcidMetricsThreadDisabled() throws Exception {
    conf = new HiveConf();
    conf.setBoolean(MetastoreConf.ConfVars.METASTORE_ACIDMETRICS_THREAD_ON.getVarname(), false);
    setup(conf);
    startCleaner();
    Assert.assertEquals(0, gaugeToMap(COMPACTION_NUM_OBSOLETE_DELTAS).size());
  }

}
