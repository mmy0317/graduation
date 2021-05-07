package com.mayang.api.model.Enum;

public enum StuStatus {
    ENTRANCE(1,"录取"),
    READING(2,"在读"),
    GRADUATION(3,"毕业/离校");

    Integer code;
    String value;

    StuStatus(Integer code , String value){
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return value;
    }

    public void setStatus(String status) {
        this.value = status;
    }

    /**
     * 根据code获取value
     */
    public static String getValueByCode(Integer code){
        for(StuStatus status:StuStatus.values()){
            if(code.equals(status.getCode())){
                return status.getStatus();
            }
        }
        return  null;
    }
}
