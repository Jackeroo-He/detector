package com.kaka.controller;

import com.kaka.bean.AreaBean;
import com.kaka.bean.DetectorBean;
import com.kaka.service.DetectorService;
import com.kaka.util.Constants;
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
        List<DetectorBean> brokenDetectorBeanList = new ArrayList ();
        System.out.println("======================>Please input");
        AreaBean areaBean = new AreaBean();
        areaBean.setX(sc.nextInt()); //TODO need check the input (InputMismatchException)
        areaBean.setY(sc.nextInt());

        while(sc.hasNext()){
            Integer x = sc.nextInt(); //TODO need check the input(InputMismatchException)
            Integer y = sc.nextInt();
            String direction = sc.next();
            DetectorBean detectorBean = new DetectorBean(x,y,direction);
            moveByInputPrams(areaBean, detectorBean, brokenDetectorBeanList, sc);
        }
    }

    private static void moveByInputPrams(AreaBean areaBean, DetectorBean detectorBean, List<DetectorBean> brokenDetectorBeanList, Scanner sc){
            String moveStr = sc.next();
            if(moveStr != null && moveStr.length() > 0) {
                try {
                    boolean isSuccess = true;
                    for (int i = 0; i < moveStr.length(); i++) {
                        if (Constants.MOVE.equals(String.valueOf(moveStr.charAt(i)))) {
                           detectorService.moveStraight(detectorBean);
                           if (brokenDetectorBeanList.size() == 0) {
                               detectorService.flyOnArea(detectorBean, areaBean);
                           } else {
                               detectorService.safeFly(detectorBean, brokenDetectorBeanList);
                               //detectorService.flyOnArea(detectorBean, areaBean);
                           }
                        } else if (Constants.LEFT.equals(String.valueOf(moveStr.charAt(i)))) {
                            detectorService.turnLeft(detectorBean);
                        } else if (Constants.RIGHT.equals(String.valueOf(moveStr.charAt(i)))) {
                            detectorService.turnRight(detectorBean);
                        } else {
                            System.out.println("===========>input error, please input again!");
                            isSuccess = false;
                            break;
                        }
                    }
                    if (isSuccess) {
                        if (null != detectorBean.getDestroy() && detectorBean.getDestroy().equals(Constants.DESTROY_D)){
                            brokenDetectorBeanList.add(detectorBean);
                        }
                        detectorService.logDetector(detectorBean);
                    } else {
                        moveByInputPrams(areaBean, detectorBean,brokenDetectorBeanList, sc);
                    }
                } catch (Exception e) {
                    System.out.println("================>exception:" + e.getMessage());
                }
            } else {
                System.out.println("================> please input to move");
            }
    }

}
