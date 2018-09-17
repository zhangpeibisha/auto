package org.nix.learn.auto.functions.presentation.mange;

import com.alibaba.fastjson.JSON;
import org.nix.learn.auto.dao.mybatis.mapper.PresentationModelMapper;
import org.nix.learn.auto.dao.redis.RedisDao;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.functions.presentation.PresentationException;
import org.nix.learn.auto.functions.presentation.TaskPresentation;
import org.nix.learn.auto.model.PresentationModel;
import org.nix.learn.auto.utils.CryptoUtils;
import org.nix.learn.auto.utils.DateUtils;
import org.nix.learn.auto.utils.LogUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 从缓存中查询
 *
 * @author zhangpei341@pingan.cn.com 2018/9/4 下午2:44
 * @version 1.0
 */
@Component
public class PresentationCache implements PresentationManger {

    @Resource
    private RedisDao redisDao;

    @Resource
    private PresentationModelMapper presentationModelMapper;

    private static final Map<String, Presentation> SAVE = new ConcurrentHashMap<>();

    @Override
    public String put(Presentation presentation) {
        String id = createId(presentation);
        SAVE.put(id, presentation);
        return id;
    }

    @Override
    public Presentation get(String id) {
        Presentation presentation = SAVE.get(id);
        if (presentation == null) {
            // redis查询
            Object o = redisDao.get(id);
            if (o == null) {
                // 数据库查询
                PresentationModel model = presentationModelMapper.findPresentationByPresentationId(id);
                if (model == null) {
                    throw new PresentationException("报告ID为:" + id + "的报告不存在");
                }
                // 将数据更新到redis
                redisDao.set(id, model);
                presentation = getByModel(model);
                return presentation;
            } else if (o instanceof PresentationModel) {
                // 返回缓存中的数据
                return getByModel((PresentationModel) o);
            } else {
                throw new PresentationException("查询数据错误，结果为：" + JSON.toJSONString(o));
            }
        } else if (isComplete(presentation)) {
            PresentationModel model = save(id, presentation);
            redisDao.set(id, model);
            presentationModelMapper.insert(model);
            SAVE.remove(id);
        }
        return presentation;
    }

    /**
     * 30 分钟触发一次
     * <p>
     * 定时器触发，定时清理缓存中的信息
     * 也可以手动调用清理
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void deathSaveMapCache() {
        List<String> ids = new ArrayList<>();
        for (Map.Entry<String, Presentation> value : SAVE.entrySet()) {
            try {
                if (isComplete(value.getValue())) {
                    // 保存到数据库
                    presentationModelMapper.insert(save(value.getKey(), value.getValue()));
                    ids.add(value.getKey());
                    // 移除在map中的信息
                    SAVE.remove(value.getKey());
                    LogUtils.printLog("清理 @org.nix.learn.auto.functions.presentation.mange.PresentationCache 中map的值：",value.getKey(),value.getValue());
                }
            } catch (Exception e) {
                LogUtils.printLog("清理@org.nix.learn.auto.functions.presentation.mange.PresentationCache 中map的值："
                        + value.getKey()
                        + "失败"
                        + " "
                        + JSON.toJSONString(value.getValue()));
            }
        }
    }

    /**
     * 创建报告ID
     *
     * @param presentation 需要存储的报告信息
     * @return 该报告的查询ID
     */
    private String createId(PresentationContent presentation) {
        String time = DateUtils.getCurrentDateTime();
        String json = JSON.toJSONString(presentation);
        return CryptoUtils.encodeMD5(time + json);
    }

    /**
     * 判断是否加载完成
     *
     * @param presentation 需要检测的报告
     * @return 如果加载完成则返回true
     */
    private boolean isComplete(Presentation presentation) {
        if (presentation != null) {
            return presentation.isOk();
        }
        return false;
    }

    /**
     * 转为持久状态的形式
     *
     * @param id           查询ID
     * @param presentation 需要持久化的数据
     * @return 持久化状态
     */
    private PresentationModel save(String id, Presentation presentation) {
        PresentationModel model = new PresentationModel();
        model.setPresentationId(id);
        model.setValue(JSON.toJSONString(presentation));
        return model;
    }

    /**
     * 将持久化的数据转为报告形态
     *
     * @param model 持久台数据报告
     * @return 动态报告形式
     */
    private Presentation getByModel(PresentationModel model) {
      Map map = JSON.parseObject(model.getValue(), Map.class);
      TaskPresentation taskPresentation =  new TaskPresentation();
      taskPresentation.setInfo(map);
      return taskPresentation;
    }

}
