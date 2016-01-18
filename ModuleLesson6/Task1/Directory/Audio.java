package ModuleLesson6.Task1.Directory;

public class Audio extends File{
    private String path = null;
    @Override
    public String getNameFile() {
        return path;
    }

    @Override
    public String toString() {
        return "Audio " +
                "path = '" + path + '\'';
    }
}
