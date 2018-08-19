package org.nix.learn.auto.core.obtain;

/**
 * 搜索安卓手机信息时专有命令
 *
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public interface AndroidCommand extends Command {

    /**
     * 查看链接计算机到设备
     */
    String GET_DEVICES = "adb devices";

    /**
     * 安装APK
     */
    String INSTALL_APK = "adb install <apkfile>";

    /**
     * 安装apk到sdk卡中
     */
    String INSTALL_APK_TO_SDK = "adb install -s <apkfile>";

    /**
     * 保存数据并重新安装apk
     */
    String KEEP_DATA_RESET_APK = "adb install -r <apkfile>";

    /**
     * 卸载apk
     */
    String UNINSTALL_APK = "adb uninstall <package>";

    /**
     * 查看log
     */
    String VIEW_LOG = "adb logcat";

    /**
     * 关闭adb服务
     */
    String KILL_ADB_SERVEr = "adb kill-server";

    /**
     * 开启adb服务
     */
    String START_ADB_SERVER = " adb start-server";

    /**
     * 启动应用
     */
    String START_APP = "adb shell am start -n <package_name>/.<activity_class_name>";

    /**
     * 查看设备cpu和内存占用情况
     */
    String VIEW_CPU_USE = "adb shell top";

    /**
     * 查看占用内存前N的app
     */
    String VIEW_CPU_TOP_N = "adb shell top -m N";

    /**
     * 刷新一次内存信息，然后返回
     */
    String UPDATA_GET_CUP_USE = "adb shell top -n 1";

    /**
     * 查看进程列表
     */
    String VIEW_PID_LIST = "adb shell ps";

    /**
     * 杀死一个进程
     */
    String KILL_PID = "adb shell kill [pid]";

    /**
     * 查看后台services信息
     */
    String VIEW_SERVICE_LIST = "adb shell service list";

    /**
     * 查看指定进程状态
     */
    String VIEW_PID = "adb shell ps -x [pid]";

    /**
     * 从本地复制文件到设备
     */
    String PUSH_LOCAL_REMOTE = "adb push <local> <remote>";

    /**
     * 从本地复制文件到设备
     */
    String PUSH_REMOTE_LOCAL = "adb push <remote>  <local>";

    /**
     * 获取手机系统信息（ CPU，厂商名称等）
     */
    String GET_PHONE_INFO = "adb shell 'cat /system/build.prop | grep 'product''";

    /**
     * 获取手机系统版本
     */
    String GET_ANDROID_VERSION = "adb shell getprop ro.build.version.release";

    /**
     * 获取手机系统api版本
     */
    String GET_PHONE_API_VERSION = "adb shell getprop ro.build.version.sdk";

    /**
     * 获取手机系统版本
     */
    String GET_PHONE_MODEL = "adb -d shell getprop ro.product.model";

    /**
     * 获取手机厂商名称
     */
    String GET_PHONE_FACTORT_NAME = "adb -d shell getprop ro.product.brand";

    /**
     * 获取手机的IMEI
     */
    String GET_PHONE_IMEI = "adb shell dumpsys iphonesubinfo";

    /**
     * 获取手机分辨率
     */
    String GET_PHONE_RESOLVING_POWER = "adb shell wm size";

    /**
     * 获取手机物理密度  Physical density
     */
    String GET_PHONE_PHYSICAL_DENSITY = "adb shell wm density";

    /**
     * 查看当前app的包名和activity
     */
    String GET_APP_PACKAGE_ACTIVITY = "adb shell dumpsys window windows | grep -E 'mFocusedApp'";

}
