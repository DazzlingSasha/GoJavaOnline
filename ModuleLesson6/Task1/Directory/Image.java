package ModuleLesson6.Task1.Directory;


public class Image extends File{
    private String path = "c://img.jpeg";
    @Override
    public String getNameFile() {
        return path;
    }

    @Override
    public String toString() {
        return "Image" +
                " path = '" + path + '\'';
    }
}
