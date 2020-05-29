package com.springboot.model.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 邝明山
 * @Date
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        /*插入时填充 创建时间和更新时间*/
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        //也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        this.fillStrategy(metaObject, "createTime", new Date());
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        this.fillStrategy(metaObject, "updateTime", new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /*更新时填充 创建时间和更新时间*/
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.fillStrategy(metaObject, "updateTime", new Date());
    }
}
