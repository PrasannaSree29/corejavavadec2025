package com.eureka.stocks.vo;

import java.util.Objects;

public class StateVO {
    String stateName;
    String stateSymbol;

    public StateVO() {
    }

    public StateVO(String stateName, String stateSymbol) {
        this.stateName = stateName;
        this.stateSymbol = stateSymbol;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateSymbol() {
        return stateSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StateVO stateVO = (StateVO) o;
        return Objects.equals(stateSymbol, stateVO.stateSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stateSymbol);
    }

    @Override
    public String toString() {
        return "StateVO{" +
                "stateName='" + stateName + '\'' +
                ", stateSymbol='" + stateSymbol + '\'' +
                '}';
    }
}

