package com;

import lombok.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");
    @Getter
    private Integer retCode;
    @Getter
    private String retMsg;

    CountryEnum(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    public static CountryEnum fore_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum anEnum : myArray) {
            if(index==anEnum.getRetCode()){
                return anEnum;
            }
        }

        return null;
    }
}
