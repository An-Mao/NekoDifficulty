package anmao.mc.ned.lib.file;

public class _File {
    public static String getFilePath(String... strings){
        StringBuilder path = new StringBuilder();
        for (String s : strings){
            path.append("/").append(s);
        }
        return System.getProperty("user.dir") + path;
    }
}
