package anmao.mc.ned.config;

import anmao.mc.ned.lib.file._File;

import java.io.File;

public class Config {
    public static final String configDir = _File.getFilePath("config","ned");
    private static final MobSkillConfig mobSkillConfig = new MobSkillConfig();
    public static void init(){
        File folder = new File(configDir);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("文件夹创建成功");
            } else {
                System.out.println("文件夹创建失败");
            }
        }
    }

    public static void load(){
        mobSkillConfig._start();
    }

    public static MobSkillConfig getMobSkillConfig() {
        return mobSkillConfig;
    }
}
