package ModuleLesson6.Task1.Directory;


public class Text extends File{
    private String path = "c://file.txt";
    @Override
    public String getNameFile() {
        return path;
    }

    @Override
    public String toString() {
        return "Text" +
                " path = '" + path + '\'';
    }
}
