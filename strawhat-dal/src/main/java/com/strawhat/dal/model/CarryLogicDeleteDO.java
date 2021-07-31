package com.strawhat.dal.model;

/**
 * 携带逻辑删除标记的DO
 */
public abstract class CarryLogicDeleteDO extends CarryPrimaryKeyDO{

    public static final String DELETED = "deleted";

    private Boolean deleted;

    private Boolean isDeleted() {
        return deleted;
    }

    private void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
