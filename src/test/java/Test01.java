import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
* @author 余胜军
* @ClassName Test01
* @qq 644064779
* @addres www.mayikt.com
* 微信:yushengjun644
*/
public class Test01 {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/phonemarket?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8",
                                 "root", "123456")
            .globalConfig(builder -> {
                builder.author("zengjing") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("D:\\data\\mybatispuls"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("org.example") // 设置父包名
                    //                            .moduleName("system") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\data\\mybatispuls")); // 设置mapperXml生成路径
            })
            //                .strategyConfig(builder -> {
            //                    builder.addInclude("t_simple") // 设置需要生成的表名
            //                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
            //                })
            //                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}