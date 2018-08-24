package org.nix.learn.auto.functions.schema;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 报告解决方案，每层创建一个集合来保存下一级的报告
 * 而采用地址传递方式，回调任务结果
 *
 * 该类为测试类，实际使用时将重新继承
 * @see Presentation 接口实现业务实际需要的报告通知类
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午3:02
 * @version 1.0
 */
public class TaskPresentation implements Presentation {

    private static final Logger logger = Logger.getLogger(TaskPresentation.class);

    /**
     * 该报告的子报告日志集合
     */
    private List<Presentation> presentations;

    /**
     * 该层的报告信息集合
     */
    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    /**
     * 报告名字
     */
    private String presentationName;

    /**
     * 简要描述报告内容
     */
    private String description;

    /**
     * 创建一个有名字的报告
     *
     * @param presentationName
     */
    public TaskPresentation(String presentationName) {
        this.presentationName = presentationName;
    }

    /**
     * 创建一个有名字有简介的报告
     *
     * @param presentationName
     * @param description
     */
    public TaskPresentation(String presentationName, String description) {
        this.presentationName = presentationName;
        this.description = description;
    }

    /**
     * 处理自己本层运行日志报告
     *
     * @param capabilities 配置类信息集合
     * @param key          需要获取信息的keys,在执行过程中添加的参数
     */
    @Override
    public void dataTo(Capabilities capabilities, String key) {
        desiredCapabilities.setCapability(key, capabilities.asMap());
    }

    /**
     * 添加一个子报告进入该报告中
     *
     * @param presentation 子报告
     */
    @Override
    public void addPresentation(Presentation presentation) {
        if (presentations == null) {
            presentations = new ArrayList<>();
        }
        presentations.add(presentation);
    }

    @Override
    public Presentation addSon(String name, String description) {
        return new TaskPresentation(name,description);
    }

    @Override
    public Presentation addSon(String name) {
        return new TaskPresentation(name);
    }

    /**
     * 在本级内容中添加一些自定义值来更清除的描述程序运行轨迹
     * @param key 键
     * @param value 值
     */
    @Override
    public void addKeyAndValue(String key, Object value) {
        desiredCapabilities.setCapability(key,value);
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Map<String, Object> getDesiredCapabilities() {
        return desiredCapabilities.asMap();
    }

    public void setDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public void setPresentationName(String presentationName) {
        this.presentationName = presentationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskPresentation{" +
                "presentations=" + presentations +
                ", desiredCapabilities=" + desiredCapabilities +
                ", presentationName='" + presentationName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * 模拟层次运行日志报告的生成
     *
     * @param args
     */
    public static void main(String[] args) {

        TaskPresentation prent = create("info", "第一层次信息", "father");

        TaskPresentation son_1 = create("info", "第二层次信息", "son_1");
        TaskPresentation son_1_2 = create("info", "第二层次信息", "son_2");
        prent.addPresentation(son_1);
        prent.addPresentation(son_1_2);

        TaskPresentation son_2 = create("info", "第三层次信息", "son_2");
        son_1.addPresentation(son_2);

        TaskPresentation son_3 = create("info", "第四层次信息", "son_3");
        son_2.addPresentation(son_3);

        TaskPresentation son_4 = create("info", "第五层次信息", "son_4");
        son_3.addPresentation(son_4);

        System.out.println(JSON.toJSONString(prent));

    }

    /**
     * 测试用例生成
     *
     * @param keyP
     * @param value
     * @param keepKey
     * @return
     */
    private static TaskPresentation create(String keyP, String value, String keepKey) {
        TaskPresentation colony = new TaskPresentation("测试用例");

        DesiredCapabilities colonyCa = new DesiredCapabilities();
        colonyCa.setCapability(keyP, value);
        colony.dataTo(colonyCa, keepKey);
        return colony;
    }
}
