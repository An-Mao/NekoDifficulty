package anmao.mc.ned.config;

import anmao.mc.amlib.system._File;

public class ConfigCDT {
    public static final String configDir = _File.getFileFullPathWithRun("config/ned/");
    static {
        _File.checkAndCreateDir(configDir);
    }
}
