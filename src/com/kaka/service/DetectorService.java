package com.kaka.service;

import com.kaka.bean.AreaBean;
import com.kaka.bean.DetectorBean;
import com.kaka.util.Constants;

import java.util.Arrays;

/**
 *  description ......
 *  create by jackeroo
 *  create on 20180822
 */
public class DetectorService {

    public DetectorBean moveStraight(DetectorBean detectorBean, AreaBean areaBean) throws Exception{
        Integer x = detectorBean.getX();
        Integer y = detectorBean.getY();
        String direction = detectorBean.getDirection();
        if(Constants.EAST_DIR.equals(direction)){
            x += 1;
        } else if(Constants.SOUTH_DIR.equals(direction)){
            y -= 1;
        } else if(Constants.WEST_DIR.equals(direction)){
            x -= 1;
        } else if(Constants.NORTH_DIR.equals(direction)){
            y += 1;
        } else {
            //should use logger
            throw new Exception("moveStraight: The direction is not correct!");
        }
        if (x > areaBean.getX() || y > areaBean.getY()) {
            //should use logger
            throw new Exception("moveStraight: The direction can not go,the Detector will out area !");
        }
        detectorBean.setX(x);
        detectorBean.setY(y);

        return detectorBean;
    }

    public String turnLeft(String direction) throws Exception{
        if(Constants.EAST_DIR.equals(direction)){
            direction = Constants.NORTH_DIR;
        } else if(Constants.SOUTH_DIR.equals(direction)){
            direction = Constants.EAST_DIR;
        } else if(Constants.WEST_DIR.equals(direction)){
            direction = Constants.SOUTH_DIR;
        } else if(Constants.NORTH_DIR.equals(direction)){
            direction = Constants.WEST_DIR;
        } else {
            throw new Exception("Turn left: The direction is not correct!");
        }

        return direction;
    }

    public String turnRight(String direction) throws Exception {
        if(Constants.EAST_DIR.equals(direction)){
            direction = Constants.SOUTH_DIR;
        } else if(Constants.SOUTH_DIR.equals(direction)){
            direction = Constants.WEST_DIR;
        } else if(Constants.WEST_DIR.equals(direction)){
            direction = Constants.NORTH_DIR;
        } else if(Constants.NORTH_DIR.equals(direction)){
            direction = Constants.EAST_DIR;
        } else {
            throw new Exception("Turn right: The direction is not correct!");
        }

        return direction;
    }

    public boolean checkDetector(DetectorBean detectorBean, AreaBean areaBean){
        if (detectorBean.getX() > areaBean.getX() || detectorBean.getY() > areaBean.getY() || !Constants.DIRECTIONS.contains(detectorBean.getDirection())) {
            return false;
        }
        return true;
    }
}
