package com.mayang.api.model.Enum;

public enum OrderStatusEnum {
    DOING(1,"订单进行中"),
    CANCEL(2,"订单取消"),
    FINISH(3,"订单完成");

    Integer code;
    String value;

    OrderStatusEnum(Integer code , String value){
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
