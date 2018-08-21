package org.nix.learn.auto.utils.similar;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author zhangpei341@pingan.cn.com
 * @version 1.0
 * @date 2018/7/31 上午9:13
 */
public class SimilarUtils {
    private static final Logger logger = Logger.getLogger(SimilarUtils.class);

    /**
     * 读取图片，获取高和宽
     * @param file
     * @return
     * @throws IOException
     */
    public static Pixel getPixel(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        Pixel pixel = new Pixel(image.getWidth(),image.getHeight());
        return pixel;
    }



}
