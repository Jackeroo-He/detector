package com.kaka.controller;

import com.kaka.bean.AreaBean;
import com.kaka.bean.DetectorBean;
import com.kaka.service.DetectorService;
import com.kaka.util.Constants;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  description ......
 *  create by jackeroo
 *  create on 20180822
 */
public class DetecttorController {

    private static DetectorService detectorService = new DetectorService();
    /**
     * example method
     * date 20180822
     * author jackeroo
     *
     * input--
     *          5 5
     *          1 2 N
     *          LMLMLMLMM
     *          3 3 E
     *          MMRMMRMRRM
     * output--
     *          1 3 N
     *          5 1 E
     */
    //use main method as controller and unit test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<DetectorBean> detectorBeanList = new ArrayList ();
        System.out.println("======================>Please input, when you finish please input 'exit' to exit");
        AreaBean areaBean = new AreaBean();
        areaBean.setX(sc.nextInt());
        areaBean.setY(sc.nextInt());
        System.out.println("==============>has geted area!");
        while(!sc.hasNext("exit")){
            Integer x = sc.nextInt();
            Integer y = sc.nextInt();
            String direction = sc.next();
            DetectorBean detectorBean = new DetectorBean(x,y,direction);
            moveByInputPrams(areaBean, detectorBean, detectorBeanList, sc);
        }
        detectorBeanList.forEach(detectorBean -> {
            System.out.println("The detector position------>" + detectorBean.getX() + " " + detectorBean.getY() + " " + detectorBean.getDirection());
        });
    }

    private static void moveByInputPrams(AreaBean areaBean, DetectorBean detectorBean, List<DetectorBean> detectorBeanList, Scanner sc){

        if(detectorService.checkDetector(detectorBean, areaBean)){
            String moveStr = sc.next();
            if(moveStr != null && moveStr.length() > 0) {
                try {
                    boolean isSuccess = true;
                    for (int i = 0; i < moveStr.length(); i++) {
                        if (Constants.MOVE.equals(String.valueOf(moveStr.charAt(i)))) {
                            detectorBean = detectorService.moveStraight(detectorBean, areaBean);
                        } else if (Constants.LEFT.equals(String.valueOf(moveStr.charAt(i)))) {
                            detectorBean.setDirection(detectorService.turnLeft(detectorBean.getDirection()));
                        } else if (Constants.RIGHT.equals(String.valueOf(moveStr.charAt(i)))) {
                            detectorBean.setDirection(detectorService.turnRight(detectorBean.getDirection()));
                        } else {
                            System.out.println("===========>input error, please input again!");
                            isSuccess = false;
                            break;
                        }
                    }
                    if (isSuccess) {
                        detectorBeanList.add(detectorBean);
                    } else {
                        moveByInputPrams(areaBean, detectorBean,detectorBeanList, sc);
                    }
                } catch (Exception e) {
                    System.out.println("================>exception:" + e.getMessage());
                }
            } else {
                System.out.println("================> please input to move");
            }

        } else {
            System.out.println("===========>input error, please input again!");
        }
    }
}
