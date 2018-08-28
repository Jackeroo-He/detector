package com.kaka.service;

import com.kaka.bean.AreaBean;
import com.kaka.bean.DetectorBean;
import com.kaka.util.Constants;

import java.util.List;

/**
 *  description ......
 *  create by jackeroo
 *  create on 20180822
 */
public class DetectorService {

    public DetectorBean moveStraight(DetectorBean detectorBean) throws Exception{
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
        detectorBean.setX(x);
        detectorBean.setY(y);

        return detectorBean;
    }

    public void turnLeft(DetectorBean detectorBean) throws Exception{
        String direction = detectorBean.getDirection();
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

       detectorBean.setDirection(direction);
    }

    public void turnRight(DetectorBean detectorBean) throws Exception {
        String direction = detectorBean.getDirection();
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

        detectorBean.setDirection(direction);
    }

    public void flyOnArea(DetectorBean detectorBean, AreaBean areaBean){
       if (detectorBean.getX() >= areaBean.getX()) {
           detectorBean.setX(areaBean.getX());
           detectorBean.setSafeX(false);
           detectorBean.setDestroy(Constants.DESTROY_D);
       }
        if (detectorBean.getY() >= areaBean.getY()) {
            detectorBean.setY(areaBean.getY());
            detectorBean.setSafeY(false);
            detectorBean.setDestroy(Constants.DESTROY_D);
        }
       /* if (detectorBean.getX() < 0) { // not sure the requirement
            detectorBean.setX(0);
            detectorBean.setSafeX(false);
            detectorBean.setDestroy(Constants.DESTROY_D);
        }
        if (detectorBean.getY() >= areaBean.getY()) {
            detectorBean.setY(0);
            detectorBean.setSafeY(false);
            detectorBean.setDestroy(Constants.DESTROY_D);
        }*/
    }

    public void safeFly(DetectorBean newDetectorBean, List<DetectorBean> brokenDetectorBeanList) {
        for(DetectorBean tempDetectorBean : brokenDetectorBeanList) {
            if (null != tempDetectorBean.getDestroy() && tempDetectorBean.getDestroy().equals(Constants.DESTROY_D)) {
                if (newDetectorBean.getX() > tempDetectorBean.getX() && !tempDetectorBean.getSafeX()) {
                    newDetectorBean.setX(newDetectorBean.getX()-1);
                }
                if (newDetectorBean.getY() > tempDetectorBean.getY() && !tempDetectorBean.getSafeY()) {
                    newDetectorBean.setY(newDetectorBean.getY()-1);
                }
            }
        }
    }
    public void logDetector(DetectorBean detectorBean) {
        System.out.println("The detector position------>" + detectorBean.getX() + " " + detectorBean.getY() + " " + detectorBean.getDirection() + " " + (detectorBean.getDestroy() == null?"":detectorBean.getDestroy()) + " " +detectorBean.getSafeX() + " "+ detectorBean.getSafeY());
    }

}
