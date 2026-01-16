package com.eureka.stocks.vo;

import java.util.Objects;

public class SubSectorVO {
    private int subSectorID;
    private String subSectorName;
    private int sectorID;

    public SubSectorVO() {
    }

    public SubSectorVO(int subSectorID) {
        this.subSectorID = subSectorID;
    }

    public String getSubSectorName() {
        return subSectorName;
    }

    public void setSubSectorName(String subSectorName) {
        this.subSectorName = subSectorName;
    }

    public int getSectorID() {
        return sectorID;
    }

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    public int getSubSectorID() {
        return subSectorID;
    }

    @Override
    public String toString() {
        return "SubSectorVO{" +
                "subSectorID=" + subSectorID +
                ", subSectorName='" + subSectorName + '\'' +
                ", sectorID=" + sectorID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubSectorVO that = (SubSectorVO) o;
        return subSectorID == that.subSectorID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subSectorID);
    }
}
