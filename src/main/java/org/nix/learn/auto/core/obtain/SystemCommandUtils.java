package org.nix.learn.auto.core.obtain;

import org.nix.learn.auto.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
@Component
public class SystemCommandUtils extends AbstractCommandUtils implements SystemCommand{

    @Resource
    private SystemConfig systemConfig;

    /**
     * 系统中的配置信息
     */
    private static final Properties PROPS = System.getProperties();

    @Override
    public String runCommand(String command) {
        switch (command){
            case COMPUTER_IP:return getIp();
            case COMPUTER_MAC:return getMac();
            case COMPUTER_HOST_NAME:return getHostName();
            case COMPUTER_SERVER_PORT:return systemConfig.getPort();
            default:return PROPS.getProperty(command,"");
        }
    }

    /**
     * 得到计算机的ip地址
     * @return 服务器ip
     */
    private String getIp(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new CommandException("寻找服务器IP地址失败",e);
        }
        return address.getHostAddress();
    }

    private String getHostName(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new CommandException("获取服务器名字失败",e);
        }
        return addr.getHostName();
    }

    /**
     * 得到计算机的mac地址
     * @return  服务器的mac地址
     * @throws CommandException 命令执行失败
     */
    private String getMac(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            for (int i = 0; i < mac.length; i++) {
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : "").toString();
            }
            return sMAC;
        } catch (Exception e) {
            throw new CommandException("获取mac地址失败",e);
        }
    }
}
