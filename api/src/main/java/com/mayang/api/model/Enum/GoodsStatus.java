package com.mayang.api.model.Enum;

public enum GoodsStatus {
    GOODS_IN(1,"上架状态"),
    GOODS_DOWN(2,"下架状态");

    Integer code;
    String value;

    GoodsStatus(Integer code , String value){
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
        for(GoodsStatus status:GoodsStatus.values()){
            if(code.equals(status.getCode())){
                return status.getStatus();
            }
        }
        return  null;
    }
}
