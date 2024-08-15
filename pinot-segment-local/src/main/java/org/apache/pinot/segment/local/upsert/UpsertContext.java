/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.segment.local.upsert;

import com.google.common.base.Preconditions;
import java.io.File;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.pinot.segment.local.data.manager.TableDataManager;
import org.apache.pinot.spi.config.table.HashFunction;
import org.apache.pinot.spi.config.table.TableConfig;
import org.apache.pinot.spi.config.table.UpsertConfig;
import org.apache.pinot.spi.data.Schema;


public class UpsertContext {
  private final TableConfig _tableConfig;
  private final Schema _schema;
  private final List<String> _primaryKeyColumns;
  private final List<String> _comparisonColumns;
  private final String _deleteRecordColumn;
  private final HashFunction _hashFunction;
  private final PartialUpsertHandler _partialUpsertHandler;
  private final boolean _enableSnapshot;
  private final boolean _enablePreload;
  private final double _metadataTTL;
  private final double _deletedKeysTTL;
  private final UpsertConfig.ConsistencyMode _consistencyMode;
  private final long _upsertViewRefreshIntervalMs;
  private final File _tableIndexDir;
  private final boolean _dropOutOfOrderRecord;
  private final boolean _enableDeletedKeysCompactionConsistency;
  private final TableDataManager _tableDataManager;

  private UpsertContext(TableConfig tableConfig, Schema schema, List<String> primaryKeyColumns,
      List<String> comparisonColumns, @Nullable String deleteRecordColumn, HashFunction hashFunction,
      @Nullable PartialUpsertHandler partialUpsertHandler, boolean enableSnapshot, boolean enablePreload,
      double metadataTTL, double deletedKeysTTL, UpsertConfig.ConsistencyMode consistencyMode,
      long upsertViewRefreshIntervalMs, File tableIndexDir, boolean dropOutOfOrderRecord,
      boolean enableDeletedKeysCompactionConsistency, @Nullable TableDataManager tableDataManager) {
    _tableConfig = tableConfig;
    _schema = schema;
    _primaryKeyColumns = primaryKeyColumns;
    _comparisonColumns = comparisonColumns;
    _deleteRecordColumn = deleteRecordColumn;
    _hashFunction = hashFunction;
    _partialUpsertHandler = partialUpsertHandler;
    _enableSnapshot = enableSnapshot;
    _enablePreload = enablePreload;
    _metadataTTL = metadataTTL;
    _deletedKeysTTL = deletedKeysTTL;
    _consistencyMode = consistencyMode;
    _upsertViewRefreshIntervalMs = upsertViewRefreshIntervalMs;
    _tableIndexDir = tableIndexDir;
    _dropOutOfOrderRecord = dropOutOfOrderRecord;
    _enableDeletedKeysCompactionConsistency = enableDeletedKeysCompactionConsistency;
    _tableDataManager = tableDataManager;
  }

  public TableConfig getTableConfig() {
    return _tableConfig;
  }

  public Schema getSchema() {
    return _schema;
  }

  public List<String> getPrimaryKeyColumns() {
    return _primaryKeyColumns;
  }

  public List<String> getComparisonColumns() {
    return _comparisonColumns;
  }

  public String getDeleteRecordColumn() {
    return _deleteRecordColumn;
  }

  public HashFunction getHashFunction() {
    return _hashFunction;
  }

  public PartialUpsertHandler getPartialUpsertHandler() {
    return _partialUpsertHandler;
  }

  public boolean isSnapshotEnabled() {
    return _enableSnapshot;
  }

  public boolean isPreloadEnabled() {
    return _enablePreload;
  }

  public double getMetadataTTL() {
    return _metadataTTL;
  }

  public double getDeletedKeysTTL() {
    return _deletedKeysTTL;
  }

  public UpsertConfig.ConsistencyMode getConsistencyMode() {
    return _consistencyMode;
  }

  public long getUpsertViewRefreshIntervalMs() {
    return _upsertViewRefreshIntervalMs;
  }

  public File getTableIndexDir() {
    return _tableIndexDir;
  }

  public boolean isDropOutOfOrderRecord() {
    return _dropOutOfOrderRecord;
  }

  public boolean isEnableDeletedKeysCompactionConsistency() {
    return _enableDeletedKeysCompactionConsistency;
  }

  public TableDataManager getTableDataManager() {
    return _tableDataManager;
  }

  public static class Builder {
    private TableConfig _tableConfig;
    private Schema _schema;
    private List<String> _primaryKeyColumns;
    private List<String> _comparisonColumns;
    private String _deleteRecordColumn;
    private HashFunction _hashFunction = HashFunction.NONE;
    private PartialUpsertHandler _partialUpsertHandler;
    private boolean _enableSnapshot;
    private boolean _enablePreload;
    private double _metadataTTL;
    private double _deletedKeysTTL;
    private UpsertConfig.ConsistencyMode _consistencyMode;
    private long _upsertViewRefreshIntervalMs;
    private File _tableIndexDir;
    private boolean _dropOutOfOrderRecord;
    private boolean _enableDeletedKeysCompactionConsistency;
    private TableDataManager _tableDataManager;

    public Builder setTableConfig(TableConfig tableConfig) {
      _tableConfig = tableConfig;
      return this;
    }

    public Builder setSchema(Schema schema) {
      _schema = schema;
      return this;
    }

    public Builder setPrimaryKeyColumns(List<String> primaryKeyColumns) {
      _primaryKeyColumns = primaryKeyColumns;
      return this;
    }

    public Builder setComparisonColumns(List<String> comparisonColumns) {
      _comparisonColumns = comparisonColumns;
      return this;
    }

    public Builder setDeleteRecordColumn(String deleteRecordColumn) {
      _deleteRecordColumn = deleteRecordColumn;
      return this;
    }

    public Builder setHashFunction(HashFunction hashFunction) {
      _hashFunction = hashFunction;
      return this;
    }

    public Builder setPartialUpsertHandler(PartialUpsertHandler partialUpsertHandler) {
      _partialUpsertHandler = partialUpsertHandler;
      return this;
    }

    public Builder setEnableSnapshot(boolean enableSnapshot) {
      _enableSnapshot = enableSnapshot;
      return this;
    }

    public Builder setEnablePreload(boolean enablePreload) {
      _enablePreload = enablePreload;
      return this;
    }

    public Builder setMetadataTTL(double metadataTTL) {
      _metadataTTL = metadataTTL;
      return this;
    }

    public Builder setDeletedKeysTTL(double deletedKeysTTL) {
      _deletedKeysTTL = deletedKeysTTL;
      return this;
    }

    public Builder setConsistencyMode(UpsertConfig.ConsistencyMode consistencyMode) {
      _consistencyMode = consistencyMode;
      return this;
    }

    public Builder setUpsertViewRefreshIntervalMs(long upsertViewRefreshIntervalMs) {
      _upsertViewRefreshIntervalMs = upsertViewRefreshIntervalMs;
      return this;
    }

    public Builder setTableIndexDir(File tableIndexDir) {
      _tableIndexDir = tableIndexDir;
      return this;
    }

    public Builder setDropOutOfOrderRecord(boolean dropOutOfOrderRecord) {
      _dropOutOfOrderRecord = dropOutOfOrderRecord;
      return this;
    }

    public Builder setEnableDeletedKeysCompactionConsistency(boolean enableDeletedKeysCompactionConsistency) {
      _enableDeletedKeysCompactionConsistency = enableDeletedKeysCompactionConsistency;
      return this;
    }

    public Builder setTableDataManager(TableDataManager tableDataManager) {
      _tableDataManager = tableDataManager;
      return this;
    }

    public UpsertContext build() {
      Preconditions.checkState(_tableConfig != null, "Table config must be set");
      Preconditions.checkState(_schema != null, "Schema must be set");
      Preconditions.checkState(CollectionUtils.isNotEmpty(_primaryKeyColumns), "Primary key columns must be set");
      Preconditions.checkState(CollectionUtils.isNotEmpty(_comparisonColumns), "Comparison columns must be set");
      Preconditions.checkState(_hashFunction != null, "Hash function must be set");
      Preconditions.checkState(_tableIndexDir != null, "Table index directory must be set");
      return new UpsertContext(_tableConfig, _schema, _primaryKeyColumns, _comparisonColumns, _deleteRecordColumn,
          _hashFunction, _partialUpsertHandler, _enableSnapshot, _enablePreload, _metadataTTL, _deletedKeysTTL,
          _consistencyMode, _upsertViewRefreshIntervalMs, _tableIndexDir, _dropOutOfOrderRecord,
          _enableDeletedKeysCompactionConsistency, _tableDataManager);
    }
  }
}
