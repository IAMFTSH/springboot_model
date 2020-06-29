package com.springboot.model;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @author 邝明山
 */
@SpringBootTest
public class AutoGeneratorCode {
    @Test
    void autoGeneratorCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("邝明山");  //作者名字
        gc.setOpen(false);
        gc.setFileOverride(false); //是否自动覆盖
        gc.setServiceName("%sService"); //去Service前缀   待实验
        gc.setIdType(IdType.AUTO);   //ID填充策略
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); //实体属性 Swagger2 注解

        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("ftsh");
        dsc.setPassword("123");
        dsc.setDbType(DbType.MYSQL); //mysql数据库
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("model");
        pc.setParent("com.springboot");//这语句和上面语句，自动生成在com.learn.mybatisplu.AutoGeneratorCode目录下
        pc.setEntity("entity");  //其实不用设置，这些有默认值，这只是示范设置而已

        mpg.setPackageInfo(pc);

        // 自定义配置
/*        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };*/

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
/*        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });*/
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
 //       mpg.setCfg(cfg);

        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");  // 生成哪个表
        strategy.setNaming(NamingStrategy.underline_to_camel);   //表下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列下划线转驼峰
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//是否支持Lombok
        strategy.setLogicDeleteFieldName("deleted"); //设置逻辑删除列   如果设置了全局就不用这个了
            //自动填充策略配配置
            TableFill createTime=new TableFill("create_time", FieldFill.INSERT);  //插入时填充
            TableFill updateTime=new TableFill("update_time", FieldFill.INSERT_UPDATE);  //插入和更新时填充
            ArrayList<TableFill> tableFillArraysList=new ArrayList<>();
            tableFillArraysList.add(createTime);
            tableFillArraysList.add(updateTime);
            strategy.setTableFillList(tableFillArraysList);
            //自动填充策略配配置结束
        strategy.setVersionFieldName("version");//乐观琐配置
        strategy.setRestControllerStyle(true);//开启驼峰命名格式
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2  不知所云  不知道会不会导致url问题

        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

   //     mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();  //执行
    }
}
