package com.kaka.bean;
/**
 *  description ......
 *  create by jackeroo
 *  create on 20180822
 */
public class DetectorBean {

    private Integer x;
    private Integer y;
    private String direction;
    private String destroy;
    private Boolean safeX = true;
    private Boolean safeY = false;
    public DetectorBean(int x, int y, String direction){
        this.x = x;
        this.y = y;
        this.direction = direction.trim();
    }
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDestroy() {
        return destroy;
    }

    public void setDestroy(String destroy) {
        this.destroy = destroy;
    }

    public Boolean getSafeX() {
        return safeX;
    }

    public void setSafeX(Boolean safeX) {
        this.safeX = safeX;
    }

    public Boolean getSafeY() {
        return safeY;
    }

    public void setSafeY(Boolean safeY) {
        this.safeY = safeY;
    }
}
