package org.nix.learn.auto.core.obtain;

/**
 * 搜索系统信息时专有命令
 *
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public interface SystemCommand extends Command {

    /**
     * Java的运行环境版本
     */
    String JAVA_VERSION = "java.version";

    /**
     * Java的运行环境供应商
     */
    String JAVA_VENDOR = "java.vendor";

    /**
     * Java供应商的URL
     */
    String JAVA_VENDOR_URL = "java.vendor.url";

    /**
     * Java的安装路径
     */
    String JAVA_HOME = "java.home";

    /**
     * Java的虚拟机规范版本
     */
    String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";

    /**
     * Java的虚拟机规范供应商
     */
    String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

    /**
     * Java的虚拟机规范名称
     */
    String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";

    /**
     * Java的虚拟机实现版本
     */
    String JAVA_VM_VERSION = "java.vm.version";

    /**
     * Java的虚拟机实现供应商
     */
    String JAVA_VM_VENDOR = "java.vm.vendor";

    /**
     * Java的虚拟机实现名称
     */
    String JAVA_VM_NAME = "java.vm.name";

    /**
     * Java运行时环境规范版本
     */
    String JAVA_SPECIFICATION_VERSION = "java.specification.version";

    /**
     * Java运行时环境规范供应商
     */
    String JAVA_SPECIFICATION_VENDER = "java.specification.vender";

    /**
     * Java运行时环境规范名称
     */
    String JAVA_SPECIFICATION_NAME = "java.specification.name";

    /**
     * Java的类格式版本号
     */
    String JAVA_CLASS_VERSION = "java.class.version";

    /**
     * Java的类路径
     */
    String JAVA_CLASS_PATH = "java.class.path";

    /**
     * 加载库时搜索的路径列表
     */
    String JAVA_LIBRARY_PATH = "java.library.path";

    /**
     * 默认的临时文件路径
     */
    String JAVA_IO_TMPDIR = "java.io.tmpdir";

    /**
     * 一个或多个扩展目录的路径
     */
    String JAVA_EXT_DIRS = "java.ext.dirs";

    /**
     * 操作系统的名称
     */
    String OS_NAME = "os.name";

    /**
     * 操作系统的构架
     */
    String OS_ARCH = "os.arch";

    /**
     * 操作系统的版本
     */
    String OS_VERSION = "os.version";

    /**
     * 文件分隔符
     */
    String FILE_SEPARATOR = "file.separator";

    /**
     * 路径分隔符
     */
    String PATH_SEPARATOR = "path.separator";

    /**
     * 行分隔符
     */
    String LINE_SEPARATOR = "line.separator";

    /**
     * 用户的账户名称
     */
    String USER_NAME = "user.name";

    /**
     * 用户的主目录
     */
    String USER_HOME = "user.home";

    /**
     * 用户的当前工作目录
     */
    String USER_DIR = "user.dir";

    /**
     * 电脑IP地址
     */
    String COMPUTER_IP = "computer.ip";

    /**
     * 电脑Mac地址
     */
    String COMPUTER_MAC = "computer.mac";

    /**
     * 计算机名字
     */
    String COMPUTER_HOST_NAME = "computer.host.name";

    /**
     * 服务器端口号
     */
    String COMPUTER_SERVER_PORT = "server:port";
}
