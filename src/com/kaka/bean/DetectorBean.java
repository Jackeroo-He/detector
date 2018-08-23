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

    public DetectorBean(){
    }
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
/* //检查坐标是否合格
    public boolean inspect(){
        if(this.x<0||this.x>Max_x||this.y<0||this.y>Max_y||!("E".equals(this.fx)||!"W".equals(this.fx)||!"S".equals(this.fx)||!"N".equals(this.fx))){
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

   //x坐标限制
    public void setX(int x) {
        if(x<=Max_x && x>=0){
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    //y坐标限制
    public void setY(int y) {
        if(y<=Max_y && y>=0){
            this.y = y;
        }
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        //if("E".equals(fx)||"S".equals(fx)||"W".equals(fx)||"N".equals(fx)){
            this.fx = fx.trim();
       // }
    }

    //前进一步
    public void move(){
        if("E".equals(this.fx)){
            this.x += 1;
        } else if("S".equals(this.fx)){
            this.y -= 1;
        } else if("W".equals(this.fx)){
            this.x -= 1;
        } else if("N".equals(this.fx)){
            this.y += 1;
        }
    }

    //向左转
    public void gotoLeft(){
        if("E".equals(this.fx)){
            this.fx = "N";
        } else if("S".equals(this.fx)){
            this.fx = "E";
        } else if("W".equals(this.fx)){
            this.fx = "S";
        } else if("N".equals(this.fx)){
            this.fx = "W";
        }
    }
    //向右转
    public void gotoRight(){
        if("E".equals(this.fx)){
            this.fx = "S";
        } else if("S".equals(this.fx)){
            this.fx = "W";
        } else if("W".equals(this.fx)){
            this.fx = "N";
        } else if("N".equals(this.fx)){
            this.fx = "E";
        }
    }*/
}
