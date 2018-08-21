package org.nix.learn.auto.utils.similar;

import org.apache.log4j.Logger;

/**
 * 图片信息保存实体
 * @author zhangpei341@pingan.cn.com
 * @version 1.0
 * @date 2018/7/31 上午9:16
 */
public class Pixel {
    private static final Logger logger = Logger.getLogger(Pixel.class);

    private int pixelWidth;

    private int pixelHigth;

    public Pixel(int pixelWidth, int pixelHigth) {
        this.pixelWidth = pixelWidth;
        this.pixelHigth = pixelHigth;
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    public void setPixelWidth(int pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public int getPixelHigth() {
        return pixelHigth;
    }

    public void setPixelHigth(int pixelHigth) {
        this.pixelHigth = pixelHigth;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "pixelWidth=" + pixelWidth +
                ", pixelHigth=" + pixelHigth +
                '}';
    }
}
