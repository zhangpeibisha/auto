package org.nix.learn.auto.model;


import org.nix.learn.auto.functions.schema.SchemaException;
import org.nix.learn.auto.model.base.BaseModel;
import org.nix.learn.auto.utils.Base64;

/**
 * schema实体类
 * 装载类schema的具体信息
 *
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午3:12
 * @version 1.0
 */
public class SchemaModel  extends BaseModel {

    /**
     * scheme的中文含义
     */
    private String name;

    /**
     * schema的请求路径
     */
    private String path;

    /**
     * schema需要添加参数
     */
    private String parameter;

    /**
     * 是否还能使用
     */
    private Boolean use;

    /**
     * h5带参链接
     */
    private String eg;

    /**
     * 最低使用版本
     */
    private String useVersion;

    /**
     * 最高使用版本
     */
    private String maxUseVersion;

    /**
     * 备注信息
     */
    private String remarks;


    public SchemaModel() {
    }

    /**
     * 包含了所有参数的schema
     *
     * @param name
     * @param path
     * @param parameter
     * @param use
     * @param eg
     * @param useVersion
     * @param maxUseVersion
     */
    public SchemaModel(String name, String path, String parameter, Boolean use, String eg, String useVersion, String maxUseVersion, String remarks) {
        this.name = name;
        this.path = path;
        this.parameter = parameter;
        this.use = use;
        this.eg = eg;
        this.useVersion = useVersion;
        this.maxUseVersion = maxUseVersion;
        this.remarks = remarks;
    }

    /**
     * 没有参数的schema
     *
     * @param name
     * @param path
     * @param use
     * @param eg
     * @param useVersion
     * @param maxUseVersion
     */
    public SchemaModel(String name, String path, Boolean use, String eg, String useVersion, String maxUseVersion, String remarks) {
        this.name = name;
        this.path = path;
        this.use = use;
        this.eg = eg;
        this.useVersion = useVersion;
        this.maxUseVersion = maxUseVersion;
        this.remarks = remarks;
    }

    /**
     * 得到请求地址
     * @param apkVersion 需要使用该地址的apk
     * @return 实际需要请求的地址
     * @throws SchemaException schema使用异常
     */
    public String requestPath(String apkVersion) throws SchemaException{
        if (use){
            if (parameter!=null){
                int[] use = version(useVersion);
                int[] apk = version(apkVersion);
                int useR = versionCompare(use,apk,0);

                // 1.判断是否是低版本使用
                if (apk[0]>0&&apk[0]<4){
                    if (versionCompare(use,apk,0)<=0){
                        return path+"?_tpl="+Base64.encode(parameter);
                    }
                    throw new SchemaException("版本不支持该scheme");
                }

                // 2.判断高版本使用情况
                if (maxUseVersion!=null){
                    int[] max = version(maxUseVersion);
                    if (apk[0]>=4&&versionCompare(max,apk,0)>=0){
                        return path+"?_tpl="+Base64.encode(parameter)+"&closeWebkit=Y";
                    }
                    throw new SchemaException("版本不支持该scheme");
                }
                return path+"?_tpl="+Base64.encode(parameter)+"&closeWebkit=Y";
            }
            return path;
        }else {
            throw new SchemaException("schema不再使用");
        }

    }

    /**
     * 比较版本大小
     * @param versionOne 比较版本
     * @param versionTwo 被比较版本
     * @param index 当前比较位子
     * @return 0 相等 1大于 -1小于
     */
    private static int versionCompare(int[] versionOne,int[] versionTwo,int index){
        int one = versionOne.length;
        int two = versionTwo.length;
        int len = Math.min(one,two);
        if (index>=len){
            if (one == two){
                return 0;
            }
            if (one>two){
                return 1;
            }else {
                return -1;
            }
        }
        if (versionOne[index]==versionTwo[index]){
            ++index;
            return versionCompare(versionOne, versionTwo, index);
        }
        return versionOne[index]>versionTwo[index]?1:-1;
    }

    public static void main(String[] args) {
        int[] a = {6,0,01};
        int[] b = {6,0,00};
        System.out.println(versionCompare(b,a,0));
    }

    /**
     * 检测和获取版本号
     * @param checkVersion 需要检测的版本号
     * @return 版本号的数字形式
     * @throws SchemaException 如果版本号不能转化成数字则异常抛出
     */
    private int[] version(String checkVersion) throws SchemaException{
        String[] version = checkVersion.split("\\.");
        int len = version.length;
        int[] ver = new int[len];
        try {
            for (int i = 0; i <len ; i++) {
                int value = Integer.parseInt(version[i]);
                if (value<0){
                    throw new SchemaException("版本号格式错误,"+ checkVersion);
                }
            }
        }catch (NumberFormatException e){
            throw new SchemaException("版本号格式错误",e);
        }
       return ver;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }

    public String getUseVersion() {
        return useVersion;
    }

    public void setUseVersion(String useVersion) {
        this.useVersion = useVersion;
    }

    public String getMaxUseVersion() {
        return maxUseVersion;
    }

    public void setMaxUseVersion(String maxUseVersion) {
        this.maxUseVersion = maxUseVersion;
    }


    @Override
    public String toString() {
        return "SchemaModel{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", parameter='" + parameter + '\'' +
                ", use=" + use +
                ", eg='" + eg + '\'' +
                ", useVersion='" + useVersion + '\'' +
                ", maxUseVersion='" + maxUseVersion + '\'' +
                '}';
    }
}
