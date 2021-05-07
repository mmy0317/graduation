package com.mayang.api.model.Enum;

public enum Gender {

    MALE(1,"男"),

    FEMALE(2,"女"),

    SECRET(3,"秘密");

    private  Integer code;
    private  String value;

    Gender(Integer code , String value){
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getGender() {
        return value;
    }

    public void setGender(String gender) {
        this.value = gender;
    }

    /**
     * 根据code获取value
     */
    public static String getValueByCode(Integer code){
        for(Gender gender:Gender.values()){
            if(code.equals(gender.getCode())){
                return gender.getGender();
            }
        }
        return  null;
    }
}
