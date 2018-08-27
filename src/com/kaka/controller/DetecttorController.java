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
        List<DetectorBean> detectorBeanList = new ArrayList ();
        System.out.println("======================>Please input");
        AreaBean areaBean = new AreaBean();
        areaBean.setX(sc.nextInt()); //TODO need check the input (InputMismatchException)
        areaBean.setY(sc.nextInt());

        while(sc.hasNext()){
            Integer x = sc.nextInt(); //TODO need check the input(InputMismatchException)
            Integer y = sc.nextInt();
            String direction = sc.next();
            DetectorBean detectorBean = new DetectorBean(x,y,direction);
            moveByInputPrams(areaBean, detectorBean, detectorBeanList, sc);
        }
    }

    private static void moveByInputPrams(AreaBean areaBean, DetectorBean detectorBean, List<DetectorBean> detectorBeanList, Scanner sc){
        DetectorBean newDetectorBean = detectorBean;

        //if(detectorService.checkDetector(detectorBean, areaBean)){
            String moveStr = sc.next();
            if(moveStr != null && moveStr.length() > 0) {
                try {
                    boolean isSuccess = true;
                    for (int i = 0; i < moveStr.length(); i++) {
                        if (Constants.MOVE.equals(String.valueOf(moveStr.charAt(i)))) {
                            newDetectorBean = detectorService.moveStraight(newDetectorBean, areaBean);
                        } else if (Constants.LEFT.equals(String.valueOf(moveStr.charAt(i)))) {
                            newDetectorBean.setDirection(detectorService.turnLeft(newDetectorBean.getDirection()));
                        } else if (Constants.RIGHT.equals(String.valueOf(moveStr.charAt(i)))) {
                            newDetectorBean.setDirection(detectorService.turnRight(newDetectorBean.getDirection()));
                        } else {
                            System.out.println("===========>input error, please input again!");
                            isSuccess = false;
                            break;
                        }
                    }
                    if (isSuccess) {
                        if(!detectorService.checkDetector(detectorBean, areaBean) || !detectorService.checkDetector(newDetectorBean, areaBean)) {
                            detectorBeanList.add(detectorBean);
                            detectorService.logDetector(detectorBean);
                        } else if(!checkSafe(newDetectorBean, detectorBeanList)) {
                           detectorBeanList.add(detectorBean);
                           detectorService.logDetector(detectorBean);
                       } else {
                            detectorService.logDetector(newDetectorBean);
                            detectorBeanList.add(newDetectorBean);
                        }

                    } else {
                        moveByInputPrams(areaBean, detectorBean,detectorBeanList, sc);
                    }
                } catch (Exception e) {
                    System.out.println("================>exception:" + e.getMessage());
                }
            } else {
                System.out.println("================> please input to move");
            }

       /* } else {
            sc.nextLine();// read the next line moveStr
            detectorBean.setDestroy(Constants.DESTROY_D);
            detectorService.logDetector(detectorBean);
            detectorBeanList.add(detectorBean);
        }*/
    }

    private static Boolean checkSafe(DetectorBean newDetectorBean, List<DetectorBean> detectorBeanList) {
        Boolean  flag = true;
        for(DetectorBean tempdetectorBean : detectorBeanList){
            if (null != tempdetectorBean.getDestroy() && tempdetectorBean.getDestroy().equals(Constants.DESTROY_D)) {
                if(newDetectorBean.getX() >= tempdetectorBean.getX() || newDetectorBean.getY() >= tempdetectorBean.getY()) {
                    flag =false;
                    break;
                }
            }
        }

        return flag;
    }
}
