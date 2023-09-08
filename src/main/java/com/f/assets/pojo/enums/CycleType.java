package com.f.assets.pojo.enums;

public enum CycleType {

    DAY(-1),WEEK(0), MONTH(1), YEAR(2);

    private int value;

    CycleType(int v) {
        this.value = v;
    }

    public int getValue(){
        return this.value;
    }
    public static CycleType getCycleType(int v){
        CycleType[] cycleTypes = CycleType.values();
        for (CycleType cycleType : cycleTypes) {
            if (v == cycleType.value) {
                return cycleType;
            }
        }
        return null;
    }

}
